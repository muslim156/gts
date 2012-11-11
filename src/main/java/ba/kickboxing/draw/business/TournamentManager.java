package ba.kickboxing.draw.business;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import jxl.write.WriteException;

import ba.kickboxing.draw.common.Player;

public interface TournamentManager {
	void setOutputDirectoryPath(String path);
	
	String getOutputDirectoryPath();
	
	void savePlayers(List<Player> players) throws WriteException, IOException;
	
	void savePlayer(Player p);

	List<Player> listAllPlayers();

	void drawAndSave(String string) throws Exception;

	Set<String> getDisciplines();

	Set<String> getAgeCategories(String discipline);

	List<String> getWeightCategories(String discipline, String age, String sex);
}
