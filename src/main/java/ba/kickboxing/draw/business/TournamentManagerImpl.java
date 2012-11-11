package ba.kickboxing.draw.business;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.write.WriteException;

import ba.kickboxing.draw.common.Player;
import ba.kickboxing.draw.common.TournamentCategoryInfo;
import ba.kickboxing.draw.common.TournamentKey;
import ba.kickboxing.draw.persistence.DAO;
import ba.kickboxing.draw.persistence.IO;

public class TournamentManagerImpl implements TournamentManager {

    private DAO dao;
    private String outputDirectoryPath = "";
    
    private Map<String, TournamentCategoryInfo> tournamentDisciplinesData = new HashMap<String, TournamentCategoryInfo>();

    {    
    	
    	// Add data for Light contact
    	TournamentCategoryInfo lightContact = new TournamentCategoryInfo();
    	
    	lightContact.put("Kadeti", "M", Arrays.asList("-32","-37","-42","-47","-52","-57","-63","-69","+69"));
    	lightContact.put("Kadeti", "F", Arrays.asList("-32","-37","-42","-46","-50","-55","-60","-65","+65"));
    	
		lightContact.put("Juniori", "M", Arrays.asList("57", "-63", "-69", "-74", "-79", "-84", "-89", "+89"));
		lightContact.put("Juniori", "F", Arrays.asList("-50", "-55", "-60", "-65", "-70", "+70"));
    	
		lightContact.put("Seniori", "M", Arrays.asList("-57", "-63", "-69", "-74", "-79", "-84", "-89", "-94", "+94"));
    	lightContact.put("Seniori", "F", Arrays.asList("-50", "-55", "-60", "-65", "-70", "+70"));
    	
    	tournamentDisciplinesData.put("Light contact", lightContact);
    	
    	// Add data for kick light
    	TournamentCategoryInfo kickLight = new TournamentCategoryInfo();
    	
    	kickLight.put("Juniori", "M", Arrays.asList("-51","-57","-63","-69","-74","-79","-84","-89","+89"));
		kickLight.put("Juniori", "F", Arrays.asList("-50","-55","-60","-65","-70","+70"));
    	
		kickLight.put("Seniori", "M", Arrays.asList("-57","-63","-69","-74","-79","-84","-89","-94","+94"));
		kickLight.put("Seniori", "F", Arrays.asList("-50","-55","-60","-65","-70","+70"));
    	
    	tournamentDisciplinesData.put("Kick light", kickLight);
    	
    	// Add data for low kick
    	TournamentCategoryInfo lowKick = new TournamentCategoryInfo();
    	
    	lowKick.put("Mladji juniori", "M", Arrays.asList("-39","-42","-45","-48","-51","-54","-57","-60","-63","-66","-69","-74","-79","-84","+84"));
    	lowKick.put("Mladji juniori", "F", Arrays.asList("-36","-40","-44","-48","-52","-56","-60","+60"));
    	
		lowKick.put("Stariji juniori", "M", Arrays.asList("-51","-54","-57","-60","-63,5","-67","-71","-75","-81","-86","-91","+91"));
		lowKick.put("Stariji juniori", "F", Arrays.asList("-48","-52","-56","-60","-65","-70","+70"));
    	
		lowKick.put("Seniori", "M", Arrays.asList("-51","-54","-57","-60","-63,5","-67","-71","-75","-81","-86","-91","+91"));
    	lowKick.put("Seniori", "F", Arrays.asList("-48","-52","-56","-60","-65","-70","+70"));
    	
    	tournamentDisciplinesData.put("Low kick", lowKick);
    }
    
    public TournamentManagerImpl(DAO dao) {
        super();
        this.dao = dao;
    }

    public String getOutputDirectoryPath() {
		return outputDirectoryPath;
	}

	public void setOutputDirectoryPath(String outputDirectoryPath) {
		this.outputDirectoryPath = outputDirectoryPath;
	}

	@Override
    public void savePlayer(Player p) {
        dao.savePlayer(p);
    }

    @Override
    public List<Player> listAllPlayers() {
        List<Player> allPlayers = Collections.emptyList();
        try {
            allPlayers = dao.listAllPlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allPlayers;
    }

    @Override
    public void drawAndSave(String outputFilePath) throws Exception {
        List<Player> allPlayers = listAllPlayers();

        Drawing drawing = new Drawing(allPlayers);
        drawing.draw();

        IO.fillTemplate(outputFilePath, drawing.getCategoryMap());
    }

    @Override
    public Set<String> getDisciplines() {
        return tournamentDisciplinesData.keySet();
    }

    @Override
    public Set<String> getAgeCategories(String discipline) {
        TournamentCategoryInfo tournamentDiscipline = tournamentDisciplinesData.get(discipline);

        return tournamentDiscipline != null ? tournamentDiscipline.getAgeCategories() : Collections.EMPTY_SET;
    }

    @Override
    public List<String> getWeightCategories(String discipline, String age, String sex) {
        TournamentCategoryInfo tournamentDiscipline = tournamentDisciplinesData.get(discipline);

        return tournamentDiscipline != null ? tournamentDiscipline.getWeightCategories(age, sex) : Collections.EMPTY_LIST;
    }

	@Override
	public void savePlayers(List<Player> players) throws WriteException, IOException {
		Map<TournamentKey, List<Player>> map = new HashMap<TournamentKey, List<Player>>();
		map.put(new TournamentKey("Svi", "Svi", "Svi", "Svi"), players);
		
		IO.writeToXls(map, outputDirectoryPath + "svi-prijavljeni.xls", false);
	}

}
