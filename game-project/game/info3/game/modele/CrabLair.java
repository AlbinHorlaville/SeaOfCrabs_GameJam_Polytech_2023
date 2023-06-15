package info3.game.modele;

import java.util.ArrayList;
import info3.game.modele.map.Tiles;
import info3.game.modele.map.MapSection;

public class CrabLair extends StillEntity{
	
	public final int SCRAB_SPANWING_RANGE = GameModele.map.getSectionHeight() * GameModele.map.getTileHeigth();
	
	protected int nbCrabs;
	protected int crabsLvl;
	protected int lifePoint;
	protected boolean isDead = false;
	private ArrayList<Crab> crabs; 
	private MapSection section;
	
	public CrabLair(int nbCrabs, int crabsLvl, int lifePoint, MapSection section) {
		this.nbCrabs = nbCrabs;
		this.crabsLvl = crabsLvl;
		this.lifePoint = lifePoint;
		this.crabs = new ArrayList<Crab>();
	}
	
	public ArrayList<Crab> getCrabs(){
		return this.crabs;
		this.section = section;
	}
	
	@Override
	public void move() {
		//entity doesn't move
	}

	@Override
	public void die() {
		this.nbCrabs = 0;
		isDead = true;
	}
	
	public  boolean closest() {
		double distanceP1 = Math.sqrt(Math.pow(this.x - GameModele.player1.x,2) + Math.pow(this.y - GameModele.player1.y,2));
		double distanceP2 = Math.sqrt(Math.pow(this.x - GameModele.player2.x,2) + Math.pow(this.y - GameModele.player2.y,2));
		
		if(distanceP1 < SCRAB_SPANWING_RANGE && distanceP2 < SCRAB_SPANWING_RANGE) {
			return true;
		}
		return false;
		
	}
	
	public void egg() {
//		Crab crab = new Crab(100, 1, 1, 1, this);
//		this.crabs.add(crab);
//		Tiles crabLairTile = GameModele.map.getTileUnderEntity(this.x, this.y);
//		int initX = crabLairTile.getX();
//		int initY = crabLairTile.getY();
//		ArrayList<Tiles> tilesWhreCrabsCouldSpawn = new ArrayList<Tiles>();
//		tilesWhreCrabsCouldSpawn.add(GameModele.map.);
		
		//à terminer avec l'accès çà la maps section pour trouver toutes les tiles sur lequel le brabe peut spawn e
	
	}
	
	

}
