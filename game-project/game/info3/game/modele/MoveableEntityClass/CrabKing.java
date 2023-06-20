package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.vue.avatar.CrabKingAvatar;

public class CrabKing extends Crab {
	
	public static int NB_CRABS = 5;
	private int level;

	private int crabsRemaining;
	
	public CrabKing(int level,int lifePoint, int attackCoeff, int speedCoeff) {
		super(level, null);
		this.level = level;
		this.m_healthPoints = lifePoint;
		this.m_damage = DEFAULT_DAMAGE * attackCoeff;
		crabsRemaining = NB_CRABS;
		this.automate = AutomateLoader.findAutomate(GameEntity.CrabKing);
		this.current_state = automate.initial_state;
		GameModele.entities.add(this);
		this.setAvatar(new CrabKingAvatar(this));
	}
	
	public boolean gotStuff() {
		return crabsRemaining > 0;
	}
	
	public void egg() {
		switch(facing) {
			case N:
				if(GameModele.map.getTileUnderEntity(x, y - 5).isIsland()) {
					Crab b = new Crab(level, null);
				}
				break;
			case S:
				if(GameModele.map.getTileUnderEntity(x, y + 5).isIsland()) {
					Crab b = new Crab(level, null);
				}
				break;
			case E:
				if(GameModele.map.getTileUnderEntity(x + 5, y).isIsland()) {
					Crab b = new Crab(level, null);
				}
				break;
			case W:
				if(GameModele.map.getTileUnderEntity(x - 5, y).isIsland()) {
					Crab b = new Crab(level, null);
				}
				break;
			case NW:
				if(GameModele.map.getTileUnderEntity(x - 5, y - 5).isIsland()) {
					Crab b = new Crab(level, null);
				}
				break;
			case NE:
				if(GameModele.map.getTileUnderEntity(x + 5, y - 5).isIsland()) {
					Crab b = new Crab(level, null);
				}
				break;
			case SW:
				if(GameModele.map.getTileUnderEntity(x - 5, y + 5).isIsland()) {
					Crab b = new Crab(level, null);
				}
				break;
			case SE:
				if(GameModele.map.getTileUnderEntity(x + 5, y + 5).isIsland()) {
					Crab b = new Crab(level, null);
				}
				break;
		}
	}

}
