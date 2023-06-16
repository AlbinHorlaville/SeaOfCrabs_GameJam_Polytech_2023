package info3.game.modele;

import java.lang.Math;

import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.map.EnumTiles;
import info3.game.modele.map.Tiles;

public class Crab extends Ennemy {
	
	public final static int DEFAULT_HEALTH_POINTS = 100;
	public final static int DEFAULT_DAMAGE = 20;

	private CrabLair m_crabLair;
	private float m_coeff;

	public Crab(int level, CrabLair crabLair) {
		super(DEFAULT_HEALTH_POINTS, DEFAULT_DAMAGE, level);
		this.m_coeff = (new Level(level)).getCoeffBasedOnLevel();
		this.m_healthPoints *= this.m_coeff;
		this.m_damage *= this.m_coeff;
		this.m_crabLair = crabLair;
	}

	public void hit() {
		GameModele.player1.takeDamage(DEFAULT_DAMAGE);
	}

	
	public void move() {
		//Nearer pirate to me 
		PiratePlayer closestPlayer = this.closestPirateToMe();
		
		//Get next coordinate
		int nextX = this.x + (closestPlayer.x - this.x) * 1;
		int nextY = this.y + (this.y - closestPlayer.y) * 1;
		
		//Can the the tile be moved on buy a crab
		Tiles tile = GameModele.map.getTileUnderEntity(nextX,nextY);
		if(tile.isIsland()) {
			this.x = nextX;
			this.y = nextY;
		}		
	}

	
	private PiratePlayer closestPirateToMe() {
		double distanceP1 = Math.sqrt(Math.pow(this.x - GameModele.player1.x,2) + Math.pow(this.y - GameModele.player1.y,2));
		double distanceP2 = Math.sqrt(Math.pow(this.x - GameModele.player2.x,2) + Math.pow(this.y - GameModele.player2.y,2));
		
		if(distanceP1 < distanceP2) {
			return GameModele.player1;
		}
		return GameModele.player2;
	}

	public void die() {
		this.m_crabLair.getCrabs().remove(this);
		super.die();
	}


	@Override
	public boolean closest() {
		return (GameModele.map.getTileUnderEntity(GameModele.player1.x, GameModele.player1.y).isIsland() 
				&& GameModele.map.getTileUnderEntity(GameModele.player1.x, GameModele.player1.y).isIsland());
	}

	public CrabLair getM_crabLair() {
		return m_crabLair;
	}

	public void setM_crabLair(CrabLair m_crabLair) {
		this.m_crabLair = m_crabLair;
	}

	public float getM_coeff() {
		return m_coeff;
	}

	public void setM_coeff(float m_coeff) {
		this.m_coeff = m_coeff;
	}	

}
