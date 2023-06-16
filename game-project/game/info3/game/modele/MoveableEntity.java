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
	
	public MoveableEntity(int lifePoint, int maxLifePointsCoeff, int x, int y) {
		super(x, y);
		this.lifePoint = lifePoint;
		this.maxLifePointsCoeff = maxLifePointsCoeff;

	}

	public abstract void takeDamage(int damage);
		
	public boolean closest() throws Exception {
		throw new Exception("Closest Method is not implemented in the entity");
	}
	
	public boolean gotStuff() throws Exception{
		throw new Exception("GotStuff Method is not implemented in the entity");
	}
	
	public boolean cell(EnumDirection d, EnumCategory c) throws Exception {
		throw new Exception("Cell Method is not implemented in the entity");
	}
	
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
