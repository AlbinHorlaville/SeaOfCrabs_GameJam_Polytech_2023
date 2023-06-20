package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.Entity;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.Level;
import info3.game.modele.StillEntityClass.CrabLair;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.Avatar;
import info3.game.vue.avatar.CrabAvatar;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class Crab extends Ennemy {
	
	public final static int DEFAULT_HEALTH_POINTS = 100;
	public final static int DEFAULT_DAMAGE = 20;

	private CrabLair m_crabLair;
	private float m_coeff;

	public Crab(int level, CrabLair crabLair) {
		super(DEFAULT_HEALTH_POINTS, DEFAULT_DAMAGE);
		this.m_coeff = (new Level(level)).getCoeffBasedOnLevel();
		this.m_healthPoints *= this.m_coeff;
		this.m_damage *= this.m_coeff;
		this.m_crabLair = crabLair;
		this.automate = AutomateLoader.findAutomate(GameEntity.Crab);
		this.current_state = automate.initial_state;
		GameModele.entities.add(this);

	}
	
	public Crab(int level, CrabLair crabLair, int x, int y) {
		super(DEFAULT_HEALTH_POINTS, DEFAULT_DAMAGE, x, y);
		this.m_coeff = (new Level(level)).getCoeffBasedOnLevel();
		this.avatar = new CrabAvatar(this);
		this.m_healthPoints *= this.m_coeff;
		this.m_damage *= this.m_coeff;
		this.m_crabLair = crabLair;
		this.automate = AutomateLoader.findAutomate(GameEntity.Crab);
		this.current_state = automate.initial_state;
		GameModele.entities.add(this);
		
//		System.out.println("Coord Player : (" +  GameModele.player1.x + ", " + GameModele.player1.y + ")");
//		System.out.println("Coord Crab : (" +  this.x + ", " + this.y + ")");
	}

	public void hit() {
		GameModele.player1.takeDamage(DEFAULT_DAMAGE);
	}
	
	private boolean isNextXPossible() {
		PiratePlayer closestPlayer = this.closestPirateToMe();

		int nextX = this.x + ((this.x > closestPlayer.x)? -1: 1);
		
		Tiles tile = GameModele.map.getTileUnderEntity(nextX,this.y);
		
		return tile.isIsland();
	}
	
	public void move(EnumCategory cat) {
		//Nearer pirate to me 
		PiratePlayer closestPlayer = this.closestPirateToMe();
	
		if(isNextXPossible()) {
			this.y += ((this.y > closestPlayer.y)? -1: 1);
		}else {
			this.x += ((this.x > closestPlayer.x)? -1: 1);
		}
		
	}
	
//	public void move(EnumDirection dir) {
//		System.out.println("MOVE AVEC direction");
//		//Nearer pirate to me 
//		PiratePlayer closestPlayer = this.closestPirateToMe();
//		
//		//Get next coordinate
//		int nextX = this.x;
//		int nextY = this.y;
//		
//		//Moving directly to the player
//		if(dir == EnumDirection.F) {
//			
//			nextX += (this.x > closestPlayer.x)? -1: 1;
//			nextY += (this.y > closestPlayer.y)? -1: 1;
//			
//			//Can the the tile be moved on buy a crab
//			Tiles tile = GameModele.map.getTileUnderEntity(nextX,nextY);
//			if((int)tick(this.timeElapsed) % 3 == 0) {
//				this.x = nextX;
//				this.y = nextY;
//			}
//			//moving toward the player while avoiding other crabs
//		}else if(Math.abs(this.x - closestPlayer.x)  > Math.abs(this.y - closestPlayer.y) && (int)tick(this.timeElapsed) % 3 == 0) { //has more distance to the player in x
//			if(this.x - closestPlayer.x > 0) { // The crab is above the player
//				if(dir == EnumDirection.R) { //Going right
//					nextY --;
//					this.y = nextY;
//				}else { //Going left
//					nextY ++;
//					this.y = nextY;
//				}
//			}else {	//The crab is below the player
//				if(dir == EnumDirection.R) { //Going right
//					nextY ++;
//					this.y = nextY;
//				}else { //Going left
//					nextY --;
//					this.y = nextY;
//				}
//			}
//		}else {	//has more distance to the player in y
//			if(this.y - closestPlayer.y > 0) { // The crab is above the player
//				if(dir == EnumDirection.R) { //Going right
//					nextX ++;
//					this.x = nextX;
//				}else { //Going left
//					nextX --;
//					this.x = nextX;
//
//				}
//			}else {	//The crab is below the player
//				if(dir == EnumDirection.R) { //Going right
//					nextX --;
//					this.x = nextX;
//				}else { //Going left
//					nextX ++;
//					this.x = nextX;
//				}
//			}
//			
//		}
//		
//	    
//		
//	}
	
	public void move(EnumDirection dir) {
		//Nearer pirate to me 
		PiratePlayer closestPlayer = this.closestPirateToMe();
		
		//Get next coordinate
		int nextX = this.x;
		int nextY = this.y;
		
		//Moving directly to the player
		if(dir == EnumDirection.F) {
			
			nextX += (this.x > closestPlayer.x)? -1: 1;
			nextY += (this.y > closestPlayer.y)? -1: 1;
			
			//Can the the tile be moved on buy a crab
			Tiles tile = GameModele.map.getTileUnderEntity(nextX,nextY);
			
			if((int)tick(this.timeElapsed) % 2 == 0) {
			this.x = nextX;
			this.y = nextY;
		}
			
//			if(this.nextTileHasCrab(tile)) {
//				System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
//				
//				int rand = new Random().nextInt(100);
//				if(Math.abs(this.x - closestPlayer.x)  > Math.abs(this.y - closestPlayer.y) && (int)tick(this.timeElapsed) % 3 == 0) { //has more distance to the player in x
//					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAn");
//
//					if(this.x - closestPlayer.x > 0) { // The crab is above the player
//
//						if(this.y < closestPlayer.y) { //Going right
//							nextY -= 1;
//							this.y = nextY;
//						}else { //Going left
//							nextY += 1;
//							this.y = nextY;
//						}
//					}else {	//The crab is below the player
//						if(this.y > closestPlayer.y) { //Going right
//							nextY += 1;
//							this.y = nextY;
//						}else { //Going left
//							nextY -= 1;
//							this.y = nextY;
//						}
//					}
//				}else {	//has more distance to the player in y
//					System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
//
//					if(this.y - closestPlayer.y > 0) { // The crab is above the player
//						if(rand < 50) { //Going right
//							nextX +=1;
//							this.x = nextX;
//						}else { //Going left
//							nextX -= 1;
//							this.x = nextX;
//
//						}
//					}else {	//The crab is below the player
//						if(rand < 50) { //Going right
//							nextX -= 1;
//							this.x = nextX;
//						}else { //Going left
//							nextX += 1;
//							this.x = nextX;
//						}
//					}
//					
//				}
//			}else {
//				if((int)tick(this.timeElapsed) % 3 == 0) {
//					this.x = nextX;
//					this.y = nextY;
//				}
//			}
			
			//moving toward the player while avoiding other crabs
		}
		
	    
		
	}
	
	private boolean nextTileHasCrab(Tiles tile) {
		ArrayList<Entity> tab = GameModele.entities;
		for(Entity e : tab) {
			if(GameModele.map.getTileUnderEntity(e.x, e.y).equals(tile)){

				return true;

			}
		}
		return false;
	}
	
	
	public boolean cell(EnumDirection dir, EnumCategory cat) {
		
		//System.out.println("CELL");

			
		PiratePlayer closestPlayer = this.closestPirateToMe();
		
		//Get next coordinate
		int nextX = this.x;
		int nextY = this.y;
		
		nextX += (this.x > closestPlayer.x)? -1: 1;
		nextY += (this.y > closestPlayer.y)? -1: 1;
		
		Tiles tile = GameModele.map.getTileUnderEntity(nextX,nextY);
		
		if(cat == EnumCategory.A) {
			if ((GameModele.map.getTileUnderEntity(closestPlayer.x, closestPlayer.y)).equals(tile)) {
				return true;
			}
			return false;
		}else if (cat == EnumCategory.V){

			if(tile.isIsland())
				return true;
			return false;
//		}else if(cat == EnumCategory.T) {
//			for(Entity e : GameModele.entities) {
//				if(e instanceof Crab) {
//					if(GameModele.map.getTileUnderEntity(e.x, e.y).equals(GameModele.map.getTileUnderEntity(this.x, this.y))) {
//						return true;
//					}
//				}
//			}
//			return false;
		}else {
			return false;
		}
	}
	
//	private boolean isCrabLairTiles(Tiles tile) {
//		
//		ArrayList<Tiles> crabLairTiles = this.m_crabLair.getCrabLairTiles();
//		
//		for(Tiles t: crabLairTiles) {
//			if(tile.equals(t)) {
//				return true;
//			}
//		}
//		return false;
//	}

	
	private PiratePlayer closestPirateToMe() {
		
		// A jouter pour le mode 2 joueurs
//		double distanceP1 = Math.sqrt(Math.pow(this.x - GameModele.player1.x,2) + Math.pow(this.y - GameModele.player1.y,2));
//		double distanceP2 = Math.sqrt(Math.pow(this.x - GameModele.player2.x,2) + Math.pow(this.y - GameModele.player2.y,2));
//		
//		if(distanceP1 < distanceP2) {
//			return GameModele.player1;
//		}
//		return GameModele.player2;
		
		return GameModele.player1;
	}

	public void die() {
		this.m_crabLair.aCrabDied();
		super.die();
	}
	
	


	@Override
	public boolean closest() {
		return (GameModele.map.getTileUnderEntity(GameModele.player1.x, GameModele.player1.y).isIsland() 
				&& GameModele.map.getTileUnderEntity(GameModele.player1.x, GameModele.player1.y).isIsland());
	}

	public CrabLair getCrabLair() {
		return m_crabLair;
	}

	public void setCrabLair(CrabLair m_crabLair) {
		this.m_crabLair = m_crabLair;
	}

	public float getCoeff() {
		return m_coeff;
	}

	public void setCoeff(float m_coeff) {
		this.m_coeff = m_coeff;
	}	
	
	public boolean gotPower() {
		return this.m_healthPoints > 0;
	}

	public int getCenterX() {
		return this.x + (Avatar.SCALE_IMG* (this.avatar.getWidth() /Avatar.SCALE_IMG))/2;
	}
	
	public int getCenterY() {
		return this.y + ( Avatar.SCALE_IMG * ( this.avatar.getHeight())/Avatar.SCALE_IMG)/2;
	}
}
