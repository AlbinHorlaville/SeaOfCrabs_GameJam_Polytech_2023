package info3.game.modele;

import automate.EnumDirection;

public abstract class MoveableEntity extends Entity {

	protected int m_healthPoints;
	protected float m_damage;
	public EnumDirection facing;

	public MoveableEntity(int m_healthPoints, int damage, int hitbox) {
		super(hitbox);
		this.m_healthPoints = m_healthPoints;
		this.m_damage = damage;
	}

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

	public MoveableEntity(int m_healthPoints, int damage, int x, int y, int hitbox) {
		super(x, y, hitbox);
		this.m_healthPoints = m_healthPoints;
		this.m_damage = damage;
	}

	public void takeDamage(int damage) {
		System.out.println("h"+ this.m_healthPoints);
		this.m_healthPoints -= damage;
		if (this.m_healthPoints <= 0) {
			this.die();
		}
	}

	public boolean gotPower() {
		return this.m_healthPoints <= 0;
	}

	public int getHealthPoints() {
		return m_healthPoints;
	}

	public void addHealthPoints(int m_healthPoints) {
		this.m_healthPoints += m_healthPoints;
	}

	public EnumDirection getFacing() {
		return facing;
	}

	public void setFacing(EnumDirection facing) {
		this.facing = facing;
	}

	public void setHealthPoints(int m_healthPoints) {
		this.m_healthPoints = m_healthPoints;
	}

	public float getDamage() {
		return m_damage;
	}

	public void setDamage(float m_damage) {
		this.m_damage = m_damage;
	}

}
