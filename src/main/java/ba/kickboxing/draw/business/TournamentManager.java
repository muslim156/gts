package ba.kickboxing.draw.business;

import java.io.IOException;
import java.util.List;

import ba.kickboxing.draw.common.Player;

import jxl.write.WriteException;

public interface TournamentManager {
	void savePlayer(Player p);

	List<Player> listAllPlayers();

	void drawAndSave(String string) throws WriteException, IOException;
}
