package info3.game.modele;

import automate.EnumCategory;
import automate.EnumDirection;

public abstract class MoveableEntity extends Entity{
	
	protected int m_healthPoints;
	protected float m_damage; 
	EnumDirection facing;

	public MoveableEntity(int m_healthPoints, int damage) {
		super();
		this.m_healthPoints = m_healthPoints;
		this.m_damage = damage;
	}
	
	public MoveableEntity(int m_healthPoints, int damage, int x, int y) {
		super(x, y);
		this.m_healthPoints = m_healthPoints;
		this.m_damage = damage;

	}

	public void takeDamage(int damage) {
		this.m_healthPoints -= damage;
		if(this.m_healthPoints <= 0) {
			this.die();
		}
	}
	
	
	public boolean gotPower() {
		return this.m_healthPoints <= 0;
	}


	public int getm_healthPoints() {
		return m_healthPoints;
	}


	public void addm_healthPoints(int m_healthPoints) {
		this.m_healthPoints += m_healthPoints;
	}

	public EnumDirection getFacing() {
		return facing;
	}


	public void setFacing(EnumDirection facing) {
		this.facing = facing;
	}

	public int getM_healthPoints() {
		return m_healthPoints;
	}

	public void setM_healthPoints(int m_healthPoints) {
		this.m_healthPoints = m_healthPoints;
	}

	public float getM_damage() {
		return m_damage;
	}

	public void setM_damage(float m_damage) {
		this.m_damage = m_damage;
	}
	
}
