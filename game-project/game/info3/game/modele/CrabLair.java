package info3.game.modele;

import java.util.ArrayList;
import java.util.Random;
import automate.AutomateLoader;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.CrabslairAvatar;
import info3.game.modele.map.MapSection;

public class CrabLair extends StillEntity{
	
	public static final int SCRAB_SPANWING_RANGE = 400;
	
	protected int nbCrabs;
	protected int crabsLvl;
	protected int lifePoint;
	protected boolean isDead = false;
	private ArrayList<Crab> crabs; 
	private ArrayList<Tiles> tilesForCrabSpanwing;
	private MapSection section;
	
	public CrabLair(int nbCrabs, int crabsLvl, int lifePoint, MapSection section) {
		this.nbCrabs = nbCrabs;
		this.crabsLvl = crabsLvl;
		this.lifePoint = lifePoint;
		this.crabs = new ArrayList<Crab>();
		this.section = section;
		this.automate = AutomateLoader.findAutomate("Philosopher");
		this.current_state = automate.initial_state;
		this.avatar = new CrabslairAvatar(this);
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
		
		int rand = new Random().nextInt(this.crabs.size());
		Tiles tile = this.tilesForCrabSpanwing.get(rand);
		
		//Create crab and addint it to the List
		Crab crab = new Crab(this.crabsLvl, this);
		crab.setLocation(tile.getX(), tile.getY());
		this.crabs.add(crab);
		
		
		
		
		
		//à terminer avec l'accès çà la maps section pour trouver toutes les tiles sur lequel le brabe peut spawn e
	
	}
	
	private void getTilesWhreCrabsCouldSpawn() {
		
		//get coordinates of the crabsLair (Top left hand corner)
		Tiles crabLairTile = GameModele.map.getTileUnderEntity(this.x, this.y);
		int initX = crabLairTile.getX();
		int initY = crabLairTile.getY();
		
		//adding all the tiles around the CrabsLair to an arrayList
		ArrayList<Tiles> tilesWhreCrabsCouldSpawn = new ArrayList<Tiles>();
		
			//All the tiles above the crabs lair
			for(int i = 0; i < 5; i++) {
				Tiles tile = section.getTiles()[initY-1][initX-1+i];
				if(tile.isIsland()) {
					tilesWhreCrabsCouldSpawn.add(tile);
				}
			}
			
			//All the tiles below the crabs lair
			for(int i = 0; i < 5; i++) {
				Tiles tile = section.getTiles()[initY+2][initX+i];
				if(tile.isIsland()) {
					tilesWhreCrabsCouldSpawn.add(tile);
				}
			}
			
			//All the tiles on the left  the crabs lair
			for(int i = 0; i < 2; i++) {
				Tiles tile = section.getTiles()[initY+i][initX-1];
				if(tile.isIsland()) {
					tilesWhreCrabsCouldSpawn.add(tile);
				}
			}
			
			//All the tiles on the left  the crabs lair
			for(int i = 0; i < 2; i++) {
				Tiles tile = section.getTiles()[initY+i][initX+3];
				if(tile.isIsland()) {
					tilesWhreCrabsCouldSpawn.add(tile);
				}
			}
		
	}

	public ArrayList<Crab> getCrabs() {
		return crabs;
	}

	public void setCrabs(ArrayList<Crab> crabs) {
		this.crabs = crabs;
	}
	
	

}
