package info3.game.modele.MoveableEntityClass;

import automate.EnumDirection;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntity;

public abstract class CannonBall extends MoveableEntity{
	protected int endX;
	protected int endY;
	protected int damage;
	protected int range;
	protected int rateOfFire;
	public boolean fire;
	
	public CannonBall(int damage, int range, int rateOfFire) {
		super(0,0,0,0);
		this.damage = damage;
		this.range = range;
		this.rateOfFire = rateOfFire;
		fire = false;
		GameModele.entities.add(this);
	}
	
	public void setPositions(int x, int y, int endX, int endY) {
		this.x = x;
		this.y = y;
		this.endX = endX;
		this.endY = endY;
	}
	
	public void fire() {
		fire = true;
		
	}
	
	@Override
	public boolean gotPower() {
		boolean b = endX != 500 || endY != 500;
		return b;
	}
	
	@Override
	public void moveEntity(EnumDirection direction, int speed) {
		if(fire) {
			if(this.endX != 500) {
				if(endX < 500) {
					//System.out.println("position x : " + Integer.valueOf(x));
					this.x += 1;
					this.endX++;
				}
				else {
					//System.out.println("position x : " + Integer.valueOf(x));
					this.x -= 1;
					this.endX--;
				}
			}
			if(this.endY != 500) {
				if(endY < 500) {
					this.y += 1;
					this.endY++;
				}
				else {
					this.y -= 1;
					this.endY--;
				}
			}
		}
	}
	
	
}
