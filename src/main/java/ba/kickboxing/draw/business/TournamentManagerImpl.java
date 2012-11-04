package ba.kickboxing.draw.business;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import ba.kickboxing.draw.common.Player;
import ba.kickboxing.draw.common.TournamentKey;
import ba.kickboxing.draw.persistence.DAO;
import ba.kickboxing.draw.persistence.IO;

public class TournamentManagerImpl implements TournamentManager {

	private DAO dao;

	public TournamentManagerImpl(DAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public void savePlayer(Player p) {
		dao.savePlayer(p);
	}

	@Override
	public List<Player> listAllPlayers()  {
		List<Player> allPlayers = Collections.emptyList();
		try {
			allPlayers = dao.listAllPlayers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return allPlayers;
	}

	@Override
	public void drawAndSave(String outputFilePath) throws WriteException, IOException, BiffException {
		List<Player> allPlayers = listAllPlayers();

		Drawing drawing = new Drawing(allPlayers);
		drawing.draw();

		for (Entry<TournamentKey, List<Player>> entry : drawing.getCategoryMap().entrySet()) {
			List<Player> sameCategoryPlayers = entry.getValue();
			
			WorkbookSettings ws = new WorkbookSettings();
			ws.setLocale(new Locale("en", "EN"));

			Workbook workbook = Workbook.getWorkbook(new File("turnir.xls"));

			String copiedFileName = "ZRIJEB-" + entry.getKey().toString() + ".xls"; 
			WritableWorkbook copy = Workbook.createWorkbook(new File(copiedFileName), workbook);

			WritableSheet sheet = copy.getSheet(0);

			int columnIndex = 1;
			int rowIndex = 4;

			for (Player p : sameCategoryPlayers) {
				Label label = new Label(columnIndex, rowIndex, p.getNameSurname(),
						IO.cellFormatDefault);
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

}
