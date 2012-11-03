package ba.kickboxing.draw.business;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

import jxl.write.WriteException;
import ba.kickboxing.draw.common.Player;
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
	public void drawAndSave(String outputFilePath) throws WriteException, IOException {
		List<Player> allPlayers = listAllPlayers();

		Drawing drawing = new Drawing(allPlayers);
		drawing.draw();

		//TODO use template XLS to fill in data we have
		Path source = Paths.get("src/main/resources/turnir.xls");
		Path target = Paths.get("turnir.xls");
		Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		
		// IO.writeToXls(outputFilePath, drawing.getCategoryMap());
	}

}
