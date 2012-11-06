package ba.kickboxing.draw.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
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
	private static CellFormat borderCellFormatDefault = initBorderCellFormat();
	private static List<String> columnTitles = Arrays.asList("Ime i prezime",
			"Disciplina", "Tezina", "Spol", "Uzrasna kategorija", "Klub");

	public static List<Player> readFromTxt(String filePath) throws IOException {
		List<Player> players = new ArrayList<Player>();

		BufferedReader reader = new BufferedReader(new FileReader(new File(
				filePath)));

		Player player = null;

		for (String line = reader.readLine(); line != null; line = reader
				.readLine()) {
			String[] splitted = line.split("\\|");

			player = new Player(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4], splitted[5]);
			players.add(player);
		}

		reader.close();

		return players;

	}

	private static CellFormat initBorderCellFormat() {
		WritableFont fontDefault = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD);
		WritableCellFormat cellFormatDefault = new WritableCellFormat(
				fontDefault);

		try {
			setBorders(cellFormatDefault, BorderLineStyle.THIN);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		return cellFormatDefault;
	}

	// using cellFormatDefault.setBorder(Border.ALL, BorderLineStyle.THICK)
	// doesn't work
	private static void setBorders(WritableCellFormat cellFormatDefault,
			BorderLineStyle borderLineStyle) throws WriteException {
		cellFormatDefault.setBorder(Border.BOTTOM, borderLineStyle);
		cellFormatDefault.setBorder(Border.LEFT, borderLineStyle);
		cellFormatDefault.setBorder(Border.RIGHT, borderLineStyle);
		cellFormatDefault.setBorder(Border.TOP, borderLineStyle);
	}

	private static CellFormat initDefaultCellFormat() {
		WritableFont fontDefault = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD);
		WritableCellFormat cellFormatDefault = new WritableCellFormat(
				fontDefault);

		try {
			cellFormatDefault.setBorder(Border.ALL, BorderLineStyle.THIN);
		} catch (WriteException e) {
			e.printStackTrace();
		}

		return cellFormatDefault;
	}

	public static void writeToXls(String xlsPath,
			Map<TournamentKey, List<Player>> map) throws IOException,
			WriteException {
		// create workbook and fonts
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("en", "EN"));

		WritableWorkbook workbook = Workbook.createWorkbook(new File(xlsPath),
				ws);

		int sheetNo = 0;
		for (Entry<TournamentKey, List<Player>> entry : map.entrySet()) {
			// create sheet
			WritableSheet sheet = workbook.createSheet(entry.getKey()
					.toString(), sheetNo++);

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
			label = new Label(getColumnIndex(entry.getKey()), rowIndex, entry
					.getValue().toString(), cellFormatDefault);
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

			Workbook workbook = Workbook.getWorkbook(getTemplateXlsStream());

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
		Label label = new Label(6, 0, key.getAgeCategory(), cellFormatDefault);
		sheet.addCell(label);
		
		label = new Label(6, 1, key.getSex(), cellFormatDefault);
		sheet.addCell(label);
		
		label = new Label(9, 0, key.getDiscipline(), cellFormatDefault);
		sheet.addCell(label);
		
		label = new Label(9, 1, key.getWeightCategory(), cellFormatDefault);
		sheet.addCell(label);
	}

	private static void writePlayersToTemplate(WritableSheet sheet, List<Player> players) throws RowsExceededException, WriteException {
		int columnIndex = 1;
		int rowIndex = 4;

		for (Player p : players) {
			Label label = new Label(columnIndex, rowIndex, p.getNameSurname(), borderCellFormatDefault);
			rowIndex += 2;
			sheet.addCell(label);
		}		
	}

	private static InputStream getTemplateXlsStream() {
		return EntryPoint.class.getClassLoader().getResourceAsStream("turnir.xls");
	}

	private static String generateFileName(TournamentKey key) {
		return "zrijeb - " + key.toString().toLowerCase() + ".xls";
	}
}