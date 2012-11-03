package ba.kickboxing.draw.persistence;

import java.io.IOException;
import java.util.List;

import ba.kickboxing.draw.common.Player;

public interface DAO {
	void savePlayer(Player player);

	List<Player> listAllPlayers() throws IOException;
}
