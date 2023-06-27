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

public class Crab extends Ennemy {

	public final static int DEFAULT_HEALTH_POINTS = 100;
	public final static int RANGE = 100;
	public final static int DEFAULT_DAMAGE = 10;
	public final static int HIT_BOX = 50;

	private CrabLair m_crabLair;
	private int lastX = 0;
	private int lastY = 0;

	public Crab(int level, CrabLair crabLair) {
		super(DEFAULT_HEALTH_POINTS, DEFAULT_DAMAGE, HIT_BOX);
		this.m_healthPoints += Level.getAugmentLifeCrab(level);
		this.m_damage += Level.getAugmentDamageCrab(level);
		this.m_crabLair = crabLair;
		this.automate = AutomateLoader.findAutomate(GameEntity.Crab);
		this.current_state = automate.initial_state;
		this.setAvatar(new CrabAvatar(this, level));

	}

	public Crab(int level, CrabLair crabLair, int x, int y) {
		super(DEFAULT_HEALTH_POINTS, DEFAULT_DAMAGE, HIT_BOX, x, y);
		this.avatar = new CrabAvatar(this, level);
		this.m_healthPoints += Level.getAugmentLifeCrab(level);
		this.m_damage += Level.getAugmentDamageCrab(level);
		this.m_crabLair = crabLair;
		this.automate = AutomateLoader.findAutomate(GameEntity.Crab);
		this.current_state = automate.initial_state;

	}

	public void hit() {
		GameModele.player1.takeDamage(this.m_damage);
	}

	public void move(EnumCategory cat) {

//		//Nearer pirate to me 
//		PiratePlayer closestPlayer = this.closestPirateToMe();
//		int valueX = ((this.x > closestPlayer.x)? -1: 1);
//		int nextX = this.getCenterX() + valueX;
//		int valueY = ((this.y > closestPlayer.y)? -1: 1);
//		Tiles tile = GameModele.map.getTileUnderEntity(nextX,this.getCenterY());
//		if(tile.isIsland())
//			this.x += valueX;
//		else {
//			int nextY = this.getCenterY() + valueY;
//			tile = GameModele.map.getTileUnderEntity(this.getCenterX(),nextY);
//			if(tile.isIsland())
//				this.y += valueY;
//			else {
//				tile = GameModele.map.getTileUnderEntity(nextX,nextY);
//				if(tile.isIsland()) {
//					this.x += valueX;
//					this.y += valueY;
//				}
//			}
//		}
//		
//		if((int)tick(this.timeElapsed) % 2 == 0) {
//
//			PiratePlayer closestPlayer = this.closestPirateToMe();
//			
//			int valueX = ((this.x > closestPlayer.x)? -1: 1);
//			int nextX = this.getCenterX() + valueX;
//			int valueY = ((this.y > closestPlayer.y)? -1: 1);
//			int nextY = this.getCenterY() + valueY;
//			
//			
//			
//			if(GameModele.map.getTileUnderEntity(nextX,nextY).isIsland()) {
//				this.x += valueX;
//				this.y += valueY;
//				System.out.println("Going Forward");
//			}else {
//				if(GameModele.map.getTileUnderEntity(nextX,this.getCenterY()).isIsland()){
//					this.y = valueY;
//					System.out.println("Going Y");
//	
//				}
//				if(GameModele.map.getTileUnderEntity(this.getCenterX(),nextY).isIsland()) {
//					this.x = valueX;
//					System.out.println("Going X");
//	
//				}
//			}
//		}

		if ((int) tick(this.timeElapsed) % 2 == 0) {

			PiratePlayer closestPlayer = this.closestPirateToMe();

			int valueX = ((this.x > closestPlayer.x) ? -1 : 1);
			int nextX = this.getCenterX() + valueX;
			int valueY = ((this.y > closestPlayer.y) ? -1 : 1);
			int nextY = this.getCenterY() + valueY;

			Tiles nextTile = GameModele.map.getTileUnderEntity(nextX, nextY);

			if (nextTile.isIsland() || nextTile.isIslandObstacle()) {
				if (!GameModele.map.getTileUnderEntity(nextX, getCenterY()).isIsland()) {
					this.y += valueY;
					lastY = valueY;
					lastX = 0;
				} else if (!GameModele.map.getTileUnderEntity(getCenterX(), nextY).isIsland()) {
					this.x += valueX;
					lastX = valueX;
					lastY = 0;
				} else {
					this.x += lastX;
					this.y += lastY;

					lastX = 0;
				}
			}
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
		if ((int) tick(this.timeElapsed) % 2 == 0) {
			// Nearer pirate to me
			PiratePlayer closestPlayer = this.closestPirateToMe();
			int valueX = (this.x > closestPlayer.x) ? -1 : 1;
			int valueY = (this.y > closestPlayer.y) ? -1 : 1;
			// Moving directly to the player
			if (dir == EnumDirection.F) {

				this.x += valueX;
				this.y += valueY;
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

			// moving toward the player while avoiding other crabs
		}

	}

	private boolean nextTileHasCrab(Tiles tile) {
		ArrayList<Entity> tab = GameModele.entities;
		for (Entity e : tab) {
			if (GameModele.map.getTileUnderEntity(e.x, e.y).equals(tile)) {

				return true;

			}
		}
		return false;
	}

	public boolean cell(EnumDirection dir, EnumCategory cat) {

		PiratePlayer closestPlayer = this.closestPirateToMe();

		if (cat == EnumCategory.A) {
			return Math.abs(closestPlayer.getCenterX() - getCenterX()) < RANGE
					&& Math.abs(closestPlayer.getCenterY() - getCenterY()) < RANGE;
		} else if (cat == EnumCategory.V) {
			// Get next coordinate
			int nextX = this.getCenterX();
			int nextY = this.getCenterY();

			nextX += (this.x > closestPlayer.getCenterX()) ? -1 : 1;
			nextY += (this.y > closestPlayer.getCenterY()) ? -1 : 1;

			Tiles tile = GameModele.map.getTileUnderEntity(nextX, nextY);
			if (tile.isIsland())
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
		} else {
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

		if (GameModele.solo) {
			return GameModele.player1;
		}

		double distanceP1 = Math
				.sqrt(Math.pow(this.x - GameModele.player1.x, 2) + Math.pow(this.y - GameModele.player1.y, 2));
		double distanceP2 = Math
				.sqrt(Math.pow(this.x - GameModele.player2.x, 2) + Math.pow(this.y - GameModele.player2.y, 2));

		if (distanceP1 < distanceP2) {
			return GameModele.player1;
		}
		return GameModele.player2;

	}

	public void die() {
		if (m_crabLair != null)
			this.m_crabLair.aCrabDied();
		super.die();
	}

	@Override
	public boolean closest() {
		return !GameModele.onSea && GameModele.currentSection == GameModele.map.getSectionOfEntity(x, y);
		// return (GameModele.map.getTileUnderEntity(GameModele.player1.x,
		// GameModele.player1.y).isIsland()
		// && GameModele.map.getTileUnderEntity(GameModele.player1.x,
		// GameModele.player1.y).isIsland());
	}

	public CrabLair getCrabLair() {
		return m_crabLair;
	}

	public void setCrabLair(CrabLair m_crabLair) {
		this.m_crabLair = m_crabLair;
	}

	public boolean gotPower() {
		return this.m_healthPoints > 0;
	}

	public int getCenterX() {
		return this.x; // + (Avatar.SCALE_IMG* (this.avatar.getWidth() /Avatar.SCALE_IMG))/2;
	}

	public int getCenterY() {
		return this.y + (Avatar.SCALE_IMG * (this.avatar.getHeight()) / Avatar.SCALE_IMG) / 2;
	}
}
