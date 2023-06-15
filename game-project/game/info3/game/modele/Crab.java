package info3.game.modele;

import java.lang.Math;

import info3.game.modele.map.EnumTiles;
import info3.game.modele.map.Tiles;

public class Crab extends Ennemy {
	
	private static final int DAMAGE = 10;
	private CrabLair crabLair;

	public Crab(int lifePoint, int attackCoeff, int speedCoeff, int level, CrabLair crabLair) {
		super(lifePoint, attackCoeff, speedCoeff, level);
		// TODO Auto-generated constructor stub
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
		EnumTiles type;// = //not yet implemented
		if(type == Grass) {
			this.x = nextX;
			this.y = nextY;
		}		
	}
	
	private void addCoordinate(int x, int y) {
		this.x += x;
		this.y += y;
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
	
	

}
