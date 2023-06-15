package info3.game.modele;

import java.lang.Math;

import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.map.EnumTiles;
import info3.game.modele.map.Tiles;

public class Crab extends Ennemy {
	
	public final static int HEALTH_POINTS = 100;
	public final static int DAMAGE_COEFF = 1;
	public final static int DAMAGE = 20;

	private CrabLair crabLair;

	public Crab(int level, CrabLair crabLair) {
		super(HEALTH_POINTS, DAMAGE_COEFF, level);
		this.crabLair = crabLair;
	}

	public void hit() {
		GameModele.player1.takeDamage(DAMAGE);
	}

	@Override
	public void takeDamage(int damage) {
		this.lifePoint -= damage;
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
	
	public CrabLair getCrabLair() {
		return this.crabLair;
	}
	
	public void die() {
		this.crabLair.getCrabs().remove(this);
	}

	@Override
	public boolean closeTo(EnumDirection d, EnumCategory c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest(EnumDirection d, EnumCategory c) {
		return (GameModele.map.getTileUnderEntity(GameModele.player1.x, GameModele.player1.y).isIsland() 
				&& GameModele.map.getTileUnderEntity(GameModele.player1.x, GameModele.player1.y).isIsland());
	}

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cell(EnumDirection d, EnumCategory c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void hit(EnumDirection d, EnumCategory c) {
		// TODO Auto-generated method stub
		
	}
	
	

}
