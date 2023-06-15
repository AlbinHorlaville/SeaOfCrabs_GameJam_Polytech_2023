package info3.game.modele;

import automate.Category;
import automate.EnumDirection;

public abstract class MoveableEntity extends Entity{
	protected int lifePoint;
	protected float maxLifePointsCoeff;
	protected float attackSpeedCoeff;
	protected float speedCoeff;
	protected float damageCoeff;
	protected float rangeCoeff;
	EnumDirection facing;

	public MoveableEntity(int lifePoint, int maxLifePointsCoeff, int attackSpeedCoeff, int speedCoeff, int damageCoeff, int rangeCoeff) {
		super();
		this.lifePoint = lifePoint;
		this.maxLifePointsCoeff = maxLifePointsCoeff;
		this.attackSpeedCoeff = attackSpeedCoeff;
		this.speedCoeff = speedCoeff;
		this.damageCoeff = damageCoeff;
		this.rangeCoeff = rangeCoeff;
	}
	
	public abstract void attack();

	public abstract void takeDamage(int damage);
	
	public abstract boolean closeTo(Direction d, Category c);
	
	public abstract boolean closest(Direction d, Category c);
	
	public abstract boolean gotStuff();
	
	public abstract boolean cell(Direction d, Category c);
	
	public abstract void hit(Direction d, Category c);
	
	
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


	public float getDamageCoeff() {
		return this.damageCoeff;
	}


	public void addDamageCoeff(float f) {
		this.damageCoeff += f;
	}


	public float getSpeedCoeff() {
		return speedCoeff;
	}


	public void addSpeedCoeff(float f) {
		this.speedCoeff =+ f;
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


	public float getAttackSpeedCoeff() {
		return attackSpeedCoeff;
	}


	public void addAttackSpeedCoeff(float attackSpeedCoeff) {
		this.attackSpeedCoeff += attackSpeedCoeff;
	}


	public float getRangeCoeff() {
		return rangeCoeff;
	}


	public void addRangeCoeff(float rangeCoeff) {
		this.rangeCoeff += rangeCoeff;
	}
	
	//public abstract void move(EnumDirection eval);
	
}
