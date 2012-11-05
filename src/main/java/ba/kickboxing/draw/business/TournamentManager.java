package ba.kickboxing.draw.business;

import java.util.List;

import ba.kickboxing.draw.common.Player;

public interface TournamentManager {
	void savePlayer(Player p);

	List<Player> listAllPlayers();

	void drawAndSave(String string) throws Exception;
}
