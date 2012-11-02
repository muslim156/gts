package ba.kickboxing.draw;

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

}
