package info3.game.modele.MoveableEntityClass;

import automate.EnumDirection;
import automate.StateDeath;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntity;
import info3.game.vue.GameView;

public abstract class CannonBall extends MoveableEntity{
	protected int endX;
	protected int endY;
	protected int startX;
	protected int startY;
	protected int damage;
	protected int range;
	protected int rateOfFire;
	protected int speedX;
	protected int speedY;
	protected int radiusX;
	protected int radiusY;
	public boolean fire;
	
	public CannonBall(int damage, int range, int rateOfFire) {
		super(0,0,0,0);
		this.damage = damage;
		this.range = range;
		this.rateOfFire = rateOfFire;
		fire = false;
		startX = 500;
		startY = 500;
		GameModele.entities.add(this);
	}
	
	public void setPositions(int x, int y, int endX, int endY) {
		startX = x;
		startY = y;
		this.x = x;
		this.y = y;
		this.endX = endX;
		this.endY = endY;
		speedX = (endX-(GameView.screenWidth/2)) / 50;
		speedY = (endY-(GameView.screenHeight/2)) / 50;
	}
	
	public void fire() {
		fire = true;
		
	}
	
	@Override
	public boolean gotPower() {
		if(Math.abs(speedX) < 1 || Math.abs(speedY) < 1) {
			return false;
		}
		else {
			radiusX = Math.abs(startX - x) ;
			radiusY = Math.abs(startY - y) ;
			boolean b = fire && (radiusX < range) && (radiusY< range) && !GameModele.map.getTileUnderEntity(x, y).isIsland();
			return b;
		}
	}
	
	@Override
	public void moveEntity(EnumDirection direction, int speed) {
		if(fire) {
			x -= speedX;
			y -= speedY;
			startX += speedX;
			startY += speedY;
		}
			
	}
	
	
}
