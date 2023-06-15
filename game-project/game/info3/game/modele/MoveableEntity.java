package info3.game.modele;

import automate.EnumCategory;
import automate.EnumDirection;

public abstract class MoveableEntity extends Entity{
	protected int lifePoint;
	protected float maxLifePointsCoeff;

	EnumDirection facing;

	public MoveableEntity(int lifePoint, int maxLifePointsCoeff) {
		super();
		this.lifePoint = lifePoint;
		this.maxLifePointsCoeff = maxLifePointsCoeff;

	}

	public abstract void takeDamage(int damage);
	
	public abstract boolean closeTo(EnumDirection d, EnumCategory c);
	
	public abstract boolean closest(EnumDirection d, EnumCategory c);
	
	public abstract boolean gotStuff();
	
	public abstract boolean cell(EnumDirection d, EnumCategory c);
	
	public abstract void hit(EnumDirection d, EnumCategory c);
	
	
	public boolean gotPower() {
		return lifePoint <= 0;
	}

	public void die() {
		GameModele.entities.remove(this);
	}

	public int getLifePoint() {
		return lifePoint;
	}


	public void addLifePoint(int lifePoint) {
		this.lifePoint += lifePoint;
	}


	

	public EnumDirection getFacing() {
		return facing;
	}


	public void setFacing(EnumDirection facing) {
		this.facing = facing;
	}


	public float getMaxLifePointsCoeff() {
		return maxLifePointsCoeff;
	}


	public void addMaxLifePointsCoeff(float maxLifePointsCoeff) {
		this.maxLifePointsCoeff += maxLifePointsCoeff;
	}


	
	
	//public abstract void move(EnumDirection eval);
	
}
