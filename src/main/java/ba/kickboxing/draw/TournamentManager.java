package ba.kickboxing.draw;

import java.io.IOException;
import java.util.List;

import jxl.write.WriteException;

public interface TournamentManager {
	void savePlayer(Player p);

	List<Player> listAllPlayers();

	void drawAndSave(String string) throws WriteException, IOException;
}
