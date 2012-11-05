package ba.kickboxing.draw.persistence;

import ba.kickboxing.draw.common.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

public class IO {

    private static CellFormat cellFormatDefault = initDefaultCellFormat();
    private static CellFormat borderCellFormatDefault = initBorderCellFormat();
    private static List<String> columnTitles = Arrays.asList("Ime i prezime", "Disciplina", "Tezina", "Spol", "Uzrasna kategorija", "Klub");

    public static List<Player> readFromTxt(String filePath) throws IOException {
        List<Player> players = new ArrayList<Player>();

        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));

        String name = null;
        Discipline disc = null;
        WeightCategory weightCategory = null;
        AgeCategory ageCategory = null;
        Sex sex = null;
        String clubName = null;

        Player player = null;

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            String[] splitted = line.split("\\|");

            name = splitted[0];
            disc = Discipline.valueOf(splitted[1].toUpperCase());
            weightCategory = WeightCategory.valueOf(splitted[2].toUpperCase());
            ageCategory = AgeCategory.valueOf(splitted[3].toUpperCase());
            sex = Sex.valueOf(splitted[4].toUpperCase());
            clubName = splitted[5];

            player = new Player(name, disc, weightCategory, ageCategory, sex, clubName);
            players.add(player);
        }

        reader.close();

        return players;

    }

    private static CellFormat initBorderCellFormat() {
        WritableFont fontDefault = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        WritableCellFormat cellFormatDefault = new WritableCellFormat(fontDefault);

        try {
        	setBorders(cellFormatDefault, BorderLineStyle.THIN);            
        } catch (WriteException e) {
            e.printStackTrace();
        }

        return cellFormatDefault;
    }

    // using cellFormatDefault.setBorder(Border.ALL, BorderLineStyle.THICK) doesn't work
    private static void setBorders(WritableCellFormat cellFormatDefault, BorderLineStyle borderLineStyle) throws WriteException {
    	cellFormatDefault.setBorder(Border.BOTTOM, borderLineStyle);
        cellFormatDefault.setBorder(Border.LEFT, borderLineStyle);
        cellFormatDefault.setBorder(Border.RIGHT, borderLineStyle);
        cellFormatDefault.setBorder(Border.TOP, borderLineStyle);		
	}

	private static CellFormat initDefaultCellFormat() {
        WritableFont fontDefault = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        WritableCellFormat cellFormatDefault = new WritableCellFormat(fontDefault);

        try {
            cellFormatDefault.setBorder(Border.ALL, BorderLineStyle.THIN);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        return cellFormatDefault;
    }

    public static void writeToXls(String xlsPath, Map<TournamentKey, List<Player>> map) throws IOException, WriteException {
        // create workbook and fonts
        WorkbookSettings ws = new WorkbookSettings();
        ws.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(new File(xlsPath), ws);

        int sheetNo = 0;
        for (Entry<TournamentKey, List<Player>> entry : map.entrySet()) {
            // create sheet
            WritableSheet sheet = workbook.createSheet(entry.getKey().toString(), sheetNo++);

            writeHeader(sheet);

            writePlayers(sheet, 1, entry.getValue());
        }
        writeSafely(workbook);

    }

    private static void writePlayers(WritableSheet sheet, int startAt, List<Player> players) throws RowsExceededException, WriteException {
        if (players != null && !players.isEmpty()) {
            int rowIndex = startAt;

            for (Player player : players) {
                writePlayer(sheet, rowIndex++, player);
            }
        }

    }

    private static void writePlayer(WritableSheet sheet, int rowIndex, Player player) throws RowsExceededException, WriteException {
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

    private static void writeHeader(WritableSheet sheet) throws RowsExceededException, WriteException {
        Label label = null;

        int columnIndex = 0;
        int rowIndex = 0;

        for (String columnTitle : columnTitles) {
            label = new Label(columnIndex++, rowIndex, columnTitle, cellFormatDefault);
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

    public static void fillTemplate(String outputFilePath, Map<TournamentKey, List<Player>> categoryMap) throws BiffException, IOException, RowsExceededException, WriteException {
        for (Entry<TournamentKey, List<Player>> entry : categoryMap.entrySet()) {
            List<Player> sameCategoryPlayers = entry.getValue();

            Workbook workbook = Workbook.getWorkbook(new File("turnir.xls"));

            String copiedFileName = outputFilePath.concat(generateFileName(entry.getKey()));
            WritableWorkbook copy = Workbook.createWorkbook(new File(copiedFileName), workbook);

            WritableSheet sheet = copy.getSheet(0);

            int columnIndex = 1;
            int rowIndex = 4;

            for (Player p : sameCategoryPlayers) {
                Label label = new Label(columnIndex, rowIndex, p.getNameSurname(), borderCellFormatDefault);
                rowIndex += 2;
                sheet.addCell(label);
            }

            try {
                copy.write();
                copy.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String generateFileName(TournamentKey key) {
        return "zrijeb - " + key.toString().toLowerCase() + ".xls";
    }
}