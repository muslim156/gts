package ba.kickboxing.draw.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ba.kickboxing.draw.common.Player;
import ba.kickboxing.draw.common.TournamentKey;

public class Drawing {
	private List<Player> players;	
	private Map<TournamentKey, List<Player>> categoryMap;
	
	public Drawing(List<Player> players) {
		this.players = players;
		this.categoryMap = createCategoryMap(players);
	}
	
	private Map<TournamentKey, List<Player>> createCategoryMap(List<Player> players) {
		
		Map<TournamentKey, List<Player>> categoryMap = new  HashMap<TournamentKey, List<Player>>();
		
		for (Iterator<Player> iterator = players.iterator(); iterator.hasNext();) {
			Player player = (Player) iterator.next();
			
			TournamentKey key = new TournamentKey(player.getDiscipline(), player.getAgeCategory(), player.getWeight(), player.getSex());

			if (categoryMap.containsKey(key)) {
				categoryMap.get(key).add(player);
			} else {
				List<Player> sameCategoryPlayers = new ArrayList<Player>();
				sameCategoryPlayers.add(player);
				
				categoryMap.put(key, sameCategoryPlayers);
			}
		}
		
		return categoryMap;
	}

	public void draw() {		
		for (List<Player> sameCategoryPlayers : this.categoryMap.values()) {
			Collections.shuffle(sameCategoryPlayers);
		}
	}
	
	public Map<TournamentKey, List<Player>> getCategoryMap() {
		return categoryMap;
	}
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

}
