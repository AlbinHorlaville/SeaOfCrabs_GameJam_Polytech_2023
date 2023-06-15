package info3.game.modele;

import info3.game.modele.map.MapSection;

public class CrabLair extends StillEntity{
	
	protected int nbCrabs;
	protected int crabsLvl;
	protected int lifePoint;
	private MapSection section;
	
	public CrabLair(int nbCrabs, int crabsLvl, int lifePoint, MapSection section) {
		this.nbCrabs = nbCrabs;
		this.crabsLvl = crabsLvl;
		this.lifePoint = lifePoint;
		this.section = section;
	}
	
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	
	

}
