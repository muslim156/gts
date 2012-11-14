package ba.kickboxing.draw.persistence;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import ba.kickboxing.draw.EntryPoint;
import ba.kickboxing.draw.common.Player;
import ba.kickboxing.draw.common.TournamentKey;

public class IO {
	
//	private static CellFormat cellFormatDefault = initDefaultCellFormat();
//	private static CellFormat cellFormatPlayer = initCellFormatPlayer();
	
	private static List<String> columnTitles = Arrays.asList("Ime i prezime",
			"Disciplina", "Tezina", "Spol", "Uzrasna kategorija", "Klub");
	
	
	private static String templateBasename = "turnir-";
	private static Map<Integer, TemplateInfo> templateMap = initTemplateMap();

	private static Map<Integer, TemplateInfo> initTemplateMap() {
		Map<Integer, TemplateInfo> map = new HashMap<Integer, TemplateInfo>();
		TemplateInfo templateInfo = null;
		
		for (int i = 1; i <= 8; i++) {
			if (i == 1) {
				templateInfo = new TemplateInfo(templateBasename + "a.xls", new Index(11, 2), new Index(16, 0));
				map.put(i, templateInfo);		
			} else if (i == 2) {
				templateInfo = new TemplateInfo(templateBasename + "a.xls", new Index(9, 1), new Index(16, 0));
				map.put(i, templateInfo);		
			} else if (i == 3 || i == 4) {
				templateInfo = new TemplateInfo(templateBasename + "b.xls", new Index(3, 1), new Index(4, 0));
				map.put(i, templateInfo);		
			} else if (i == 5 || i == 6) {
				templateInfo = new TemplateInfo(templateBasename + "c.xls", new Index(3, 1), new Index(16, 0));
				map.put(i, templateInfo);		
			} else if (i == 7 || i == 8) {
				templateInfo = new TemplateInfo(templateBasename + "d.xls", new Index(3, 1), new Index(16, 0));
				map.put(i, templateInfo);		
			} 
		}
		
		return map;
	}
	
	public static List<Player> readFromTxt(String filePath) throws IOException {
		List<Player> players = new ArrayList<Player>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(filePath), "UTF8"));
		

		Player player = null;

		for (String line = reader.readLine(); line != null; line = reader.readLine()) {
			String[] splitted = line.split("\\|");

			player = new Player(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4], splitted[5]);
			players.add(player);
		}

		reader.close();

		return players;

	}

	public static void writeToXls(Map<TournamentKey, List<Player>> map, String xlsPath, boolean separateSheets) 
			throws IOException, WriteException {
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = null;;
		
		// create sheet
		if (!separateSheets) {
			sheet = wb.createSheet("Svi prijavljeni ucesnici");
		}

		for (Entry<TournamentKey, List<Player>> entry : map.entrySet()) {
			// create sheet
			if (separateSheets) {
				sheet = wb.createSheet(entry.getKey().toString());
			}

			writeHeader(sheet);

			writePlayers(sheet, 1, entry.getValue());
		}
		
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(xlsPath);
		wb.write(fileOut);
		fileOut.close();

	}

	private static void writePlayers(Sheet sheet, int startAt, List<Player> players) {

		if (players != null && !players.isEmpty()) {
			int rowIndex = startAt;

			for (Player player : players) {
				writePlayer(sheet, rowIndex++, player);
			}
		}

	}

	private static void writePlayer(Sheet sheet, int rowIndex, Player player) {
		for (Entry<String, Object> entry : player.getDataMap().entrySet()) {
			write(sheet, new Index(rowIndex, getColumnIndex(entry.getKey())), entry.getValue().toString());
		}
	}

	private static void writeHeader(Sheet sheet) {
		int columnIndex = 0;
		int rowIndex = 0;

		for (String columnTitle : columnTitles) {
			write(sheet, new Index(columnIndex++, rowIndex), columnTitle);
		}
		
	}

	private static int getColumnIndex(String key) {
		int index = -1;

		if ("nameSurname".equals(key)) {
			index = 0;
		} else if ("discipline".equals(key)) {
			index = 1;
		} else if ("weight".equals(key)) {
			index = 2;
		} else if ("sex".equals(key)) {
			index = 3;
		} else if ("ageCategory".equals(key)) {
			index = 4;
		} else if ("clubName".equals(key)) {
			index = 5;
		}

		return index;
	}

	public static void fillTemplate(String outputFilePath, Map<TournamentKey, List<Player>> categoryMap) throws BiffException, IOException, RowsExceededException, WriteException, URISyntaxException {
		for (Entry<TournamentKey, List<Player>> entry : categoryMap.entrySet()) {			
			List<Player> sameCategoryPlayers = entry.getValue();
			
			int numOfPlayers = sameCategoryPlayers != null ? sameCategoryPlayers.size() : 0;

			InputStream is = getTemplateXlsStream(numOfPlayers);
			
			Workbook wb = new HSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);
			
			writeTournamentData(sheet, entry.getKey());
			writePlayersToTemplate(sheet, sameCategoryPlayers);
			
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(outputFilePath.concat(generateFileName(entry.getKey())));
			wb.write(fileOut);
			fileOut.close();

		}
	}
		
	private static void writePlayersToTemplate(Sheet sheet, List<Player> players) {

		int numOfPlayers = players != null ? players.size() : 0;
		TemplateInfo templateInfo = templateMap.get(numOfPlayers);
		
		int columnIndex = templateInfo.getIndex().getColumn();
		int rowIndex = templateInfo.getIndex().getRow();
		Index playersFieldsStep = templateInfo.getStep();
		
		for (Player p : players) {
			write(sheet, new Index(rowIndex, columnIndex), p.getTournamentCard());
			
			columnIndex += playersFieldsStep.getColumn();
			rowIndex += playersFieldsStep.getRow();
		}		
		
	}

	private static void writeTournamentData(Sheet sheet, TournamentKey key) {
		write(sheet, new Index(0, 4), key.getAgeCategory());
		write(sheet, new Index(1, 4), key.getSex());
		
		write(sheet, new Index(0, 7), key.getDiscipline());
		write(sheet, new Index(1, 7), key.getWeightCategory());
	}

	private static void write(Sheet sheet, Index index, String content) {
		Row row = getCreateRow(sheet, index.getRow()); 
		
		Cell cell = getCreateCell(row, index.getColumn());
		
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(content);		
	}

	private static Cell getCreateCell(Row row, int column) {
		Cell cell = row.getCell(column);
		if (cell == null) {
			cell = row.createCell(column);
		}
		
		return cell;
	}

	private static Row getCreateRow(Sheet sheet, int rowNumber) {
		Row row = sheet.getRow(rowNumber);		
		if (row == null) {
			row = sheet.createRow(rowNumber);
		}
		
		return row;
	}

	private static InputStream getTemplateXlsStream(int numOfPlayers) {
		TemplateInfo templateInfo = templateMap.get(numOfPlayers);
		
		return EntryPoint.class.getClassLoader().getResourceAsStream(templateInfo.getName());
	}

	private static String generateFileName(TournamentKey key) {
		return "zrijeb - " + key.toString().toLowerCase() + ".xls";
	}
}