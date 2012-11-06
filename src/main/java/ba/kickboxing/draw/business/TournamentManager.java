package ba.kickboxing.draw.business;

import java.util.List;
import java.util.Set;

import ba.kickboxing.draw.common.Player;

public interface TournamentManager {
	void savePlayer(Player p);

	List<Player> listAllPlayers();

	void drawAndSave(String string) throws Exception;
	
	Set<String> getDisciplines();
	
	Set<String> getAgeCategories(String discipline);
	
	List<String> getWeightCategories(String discipline, String age, String sex);
}
