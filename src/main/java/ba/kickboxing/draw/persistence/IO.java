package ba.kickboxing.draw.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import ba.kickboxing.draw.EntryPoint;
import ba.kickboxing.draw.common.Player;
import ba.kickboxing.draw.common.TournamentKey;

public class IO {
	
	private static CellFormat cellFormatDefault = initDefaultCellFormat();
	private static CellFormat cellFormatPlayer = initCellFormatPlayer();
	
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
				templateInfo = new TemplateInfo(templateBasename + "b.xls", new Index(3, 1), new Index(16, 0));
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

	private static CellFormat initDefaultCellFormat() {
		WritableFont fontDefault = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD);
		WritableCellFormat cellFormatDefault = new WritableCellFormat(
				fontDefault);

		try {
			cellFormatDefault.setAlignment(Alignment.CENTRE);
			cellFormatDefault.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			// using cellFormatDefault.setBorder(Border.ALL, BorderLineStyle.THICK)
			// doesn't work
			setBorders(cellFormatDefault, BorderLineStyle.THIN);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		return cellFormatDefault;
	}

	
	private static CellFormat initCellFormatPlayer() {
		WritableFont fontDefault = new WritableFont(WritableFont.ARIAL, 12,
				WritableFont.BOLD);
		WritableCellFormat cellFormatPlayer = new WritableCellFormat(
				fontDefault);

		try {
			cellFormatPlayer.setWrap(true);
			cellFormatPlayer.setAlignment(Alignment.CENTRE);
			cellFormatPlayer.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			// using cellFormatDefault.setBorder(Border.ALL, BorderLineStyle.THICK)
			// doesn't work
			setBorders(cellFormatPlayer, BorderLineStyle.THIN);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		return cellFormatPlayer;
	}

	private static void setBorders(WritableCellFormat cellFormatDefault,
			BorderLineStyle borderLineStyle) throws WriteException {
		cellFormatDefault.setBorder(Border.BOTTOM, borderLineStyle);
		cellFormatDefault.setBorder(Border.LEFT, borderLineStyle);
		cellFormatDefault.setBorder(Border.RIGHT, borderLineStyle);
		cellFormatDefault.setBorder(Border.TOP, borderLineStyle);
	}

	public static void writeToXls(Map<TournamentKey, List<Player>> map, String xlsPath, boolean separateSheets) throws IOException,
			WriteException {
		// create workbook and fonts
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("en", "EN"));

		WritableWorkbook workbook = Workbook.createWorkbook(new File(xlsPath), ws);

		// create sheet
		WritableSheet sheet = null;
		if (!separateSheets) {
			sheet = workbook.createSheet("Svi prijavljeni ucesnici", 0);
		}
		
		int sheetNo = 0;
		for (Entry<TournamentKey, List<Player>> entry : map.entrySet()) {
			// create sheet
			if (separateSheets) {
				sheet = workbook.createSheet(entry.getKey().toString(), sheetNo++);
			}
			
			writeHeader(sheet);

			writePlayers(sheet, 1, entry.getValue());
		}
		writeSafely(workbook);

	}

	private static void writePlayers(WritableSheet sheet, int startAt,
			List<Player> players) throws RowsExceededException, WriteException {
		if (players != null && !players.isEmpty()) {
			int rowIndex = startAt;

			for (Player player : players) {
				writePlayer(sheet, rowIndex++, player);
			}
		}

	}

	private static void writePlayer(WritableSheet sheet, int rowIndex,
			Player player) throws RowsExceededException, WriteException {
		Label label = null;

		for (Entry<String, Object> entry : player.getDataMap().entrySet()) {
			label = new Label(getColumnIndex(entry.getKey()), rowIndex, entry.getValue().toString(), cellFormatDefault);
			sheet.addCell(label);
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

	private static void writeHeader(WritableSheet sheet)
			throws RowsExceededException, WriteException {
		Label label = null;

		int columnIndex = 0;
		int rowIndex = 0;

		for (String columnTitle : columnTitles) {
			label = new Label(columnIndex++, rowIndex, columnTitle,
					cellFormatDefault);
			sheet.addCell(label);
		}
	}

	private static void writeSafely(WritableWorkbook workbook) {
		try {
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			System.out.println("Error saving file: " + e.toString());
		} catch (WriteException e) {
			System.out.println("Error saving file: " + e.toString());
		}

	}

	public static void fillTemplate(String outputFilePath, Map<TournamentKey, List<Player>> categoryMap) throws BiffException, IOException, RowsExceededException, WriteException, URISyntaxException {
		for (Entry<TournamentKey, List<Player>> entry : categoryMap.entrySet()) {			
			List<Player> sameCategoryPlayers = entry.getValue();
			
			int numOfPlayers = sameCategoryPlayers != null ? sameCategoryPlayers.size() : 0;

			Workbook workbook = Workbook.getWorkbook(getTemplateXlsStream(numOfPlayers));

			String copiedFileName = outputFilePath.concat(generateFileName(entry.getKey()));
			WritableWorkbook copy = Workbook.createWorkbook(new File(copiedFileName), workbook);

			WritableSheet sheet = copy.getSheet(0);

			writeTournamentData(sheet, entry.getKey());
			writePlayersToTemplate(sheet, sameCategoryPlayers);

			try {
				copy.write();
				copy.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void writeTournamentData(WritableSheet sheet, TournamentKey key) throws RowsExceededException, WriteException {
		Label label = new Label(4, 0, key.getAgeCategory(), cellFormatDefault);
		sheet.addCell(label);
		
		label = new Label(4, 1, key.getSex(), cellFormatDefault);
		sheet.addCell(label);
		
		label = new Label(7, 0, key.getDiscipline(), cellFormatDefault);
		sheet.addCell(label);
		
		label = new Label(7, 1, key.getWeightCategory(), cellFormatDefault);
		sheet.addCell(label);
	}

	private static void writePlayersToTemplate(WritableSheet sheet, List<Player> players) throws RowsExceededException, WriteException {
		int numOfPlayers = players != null ? players.size() : 0;
		TemplateInfo templateInfo = templateMap.get(numOfPlayers);
		
		int columnIndex = templateInfo.getIndex().getColumn();
		int rowIndex = templateInfo.getIndex().getRow();
		Index playersFieldsStep = templateInfo.getStep();
		
		for (Player p : players) {
			Label label = new Label(columnIndex, rowIndex, p.getTournamentCard(), cellFormatPlayer);
			
			columnIndex += playersFieldsStep.getColumn();
			rowIndex += playersFieldsStep.getRow();
			
			sheet.addCell(label);
		}		
	}

	private static InputStream getTemplateXlsStream(int numOfPlayers) {
		TemplateInfo templateInfo = templateMap.get(numOfPlayers);
		
		return EntryPoint.class.getClassLoader().getResourceAsStream(templateInfo.getName());
	}

	private static String generateFileName(TournamentKey key) {
		return "zrijeb - " + key.toString().toLowerCase() + ".xls";
	}
}