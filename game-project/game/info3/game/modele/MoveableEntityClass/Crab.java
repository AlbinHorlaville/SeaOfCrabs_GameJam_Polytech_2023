package info3.game.modele.MoveableEntityClass;

import java.lang.Math;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.Entity;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.Level;
import info3.game.modele.StillEntityClass.CrabLair;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.CrabAvatar;

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
		this.automate = AutomateLoader.findAutomate("Crab");
		this.current_state = automate.initial_state;
		GameModele.entities.add(this);
		
//		System.out.println("Coord Player : (" +  GameModele.player1.x + ", " + GameModele.player1.y + ")");
//		System.out.println("Coord Crab : (" +  this.x + ", " + this.y + ")");
	}

	public void hit() {
		//GameModele.player1.takeDamage(DEFAULT_DAMAGE);
	}
	
	public void move() {
		//System.out.println("MOVE SANS PARAMETRES");
		//Nearer pirate to me 
		PiratePlayer closestPlayer = this.closestPirateToMe();
		
		//Get next coordinate
		int nextX = this.x;
		int nextY = this.y;
		
		if(this.x > closestPlayer.x)
			nextX --;
		else if(this.x < closestPlayer.x)
			nextX ++;
		
		if(this.y > closestPlayer.y)
			nextY --;
		else if (this.y < closestPlayer.y)
			nextY ++;
		
//		int nextX = this.x - ((this.x - closestPlayer.x) * 1);
//		int nextY = this.y - ((this.y - closestPlayer.y) * 1);
		
		//Can the the tile be moved on buy a crab
		Tiles tile = GameModele.map.getTileUnderEntity(nextX,nextY);
		if(tile.isIsland() &&(int)tick(this.timeElapsed) % 3 == 0) {
			this.x = nextX;
			this.y = nextY;
		}
	}
	
	public void move(EnumDirection dir) {
		//System.out.println("MOVE AVEC PARAMETRES");

		// Une seule direction possible pour l'instant, aller à droite => test
		
		PiratePlayer closestPlayer = this.closestPirateToMe();
		
		//Get next coordinate
		int nextX = this.x;
		int nextY = this.y;
		
		if(this.x > closestPlayer.x)
			nextX --;
		else if(this.x < closestPlayer.x)
			nextX ++;
		
		if(this.y > closestPlayer.y)
			nextY --;
		else if (this.y < closestPlayer.y)
			nextY ++;
		
		Tiles tileX = GameModele.map.getTileUnderEntity(nextX,this.y);
		Tiles tileY = GameModele.map.getTileUnderEntity(this.x, nextY);
		
		if(!tileX.isIsland()) {
			this.y ++;
		}else {
			this.x ++;
		}
		
	}
	
	
	public boolean cell(EnumDirection dir, EnumCategory cat) {
		
		//System.out.println("CELL");

			
		PiratePlayer closestPlayer = this.closestPirateToMe();
		
		//Get next coordinate
		int nextX = this.x;
		int nextY = this.y;
		
		if(this.x > closestPlayer.x)
			nextX --;
		else if(this.x < closestPlayer.x)
			nextX ++;
		
		if(this.y > closestPlayer.y)
			nextY --;
		else if (this.y < closestPlayer.y)
			nextY ++;
		
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
		}else { //cat == EnumCategory.T
			for(Entity e : GameModele.entities) {
				if(e instanceof Crab) {
					if(GameModele.map.getTileUnderEntity(e.x, e.y).equals(tile)) {
						return true;
					}
				}
			}
			return false;
		}
	}

	
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

	public void setM_crabLair(CrabLair m_crabLair) {
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

}
