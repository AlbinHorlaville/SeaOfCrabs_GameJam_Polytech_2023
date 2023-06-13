package info3.game.modele;

import automate.EnumDirection;

public abstract class MoveableEntity extends Entity{
	protected int lifePoint;
	protected int attackCoeff;
	protected int speedCoeff;
	EnumDirection facing;

	public MoveableEntity(int lifePoint, int attackCoeff,int speedCoeff) {
		super();
		this.attackCoeff = attackCoeff;
		this.lifePoint = lifePoint;
		this.speedCoeff = speedCoeff;
	}


	public abstract void attack();

	public abstract void takeDamage();

	public void die() {
		GameModele.entities.remove(this);
	}

	public int getLifePoint() {
		return lifePoint;
	}


	public void setLifePoint(int lifePoint) {
		this.lifePoint = lifePoint;
	}


	public int getAttackCoeff() {
		return attackCoeff;
	}


	public void setAttackCoeff(int attackCoeff) {
		this.attackCoeff = attackCoeff;
	}


	public int getSpeedCoeff() {
		return speedCoeff;
	}


	public void setSpeedCoeff(int speedCoeff) {
		this.speedCoeff = speedCoeff;
	}


	public EnumDirection getFacing() {
		return facing;
	}


	public void setFacing(EnumDirection facing) {
		this.facing = facing;
	}
	
	//public abstract void move(EnumDirection eval);
	
}
