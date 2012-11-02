package ba.kickboxing.draw;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import jxl.write.WriteException;

import ba.kickboxing.draw.persistence.DAO;

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
		List<Player> allPlayers = Collections.EMPTY_LIST;
		try {
			allPlayers = dao.listAllPlayers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return allPlayers;
	}

	@Override
	public void drawAndSave(String outputFilePath) throws WriteException, IOException {
		List<Player> allPlayers = listAllPlayers();

		Drawing drawing = new Drawing(allPlayers);
		drawing.draw();

		IO.writeToXls(outputFilePath, drawing.getCategoryMap());
	}

}
