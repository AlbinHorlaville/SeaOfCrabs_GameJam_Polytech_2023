package info3.game.modele.MoveableEntityClass;

import info3.game.modele.MoveableEntity;

public abstract class Player extends MoveableEntity {

	public static final int DEFAULT_MAX_PLAYERS_LIFE = 100;

	protected int m_maxHealthPoints;

	public Player(int healthPoints, int damage, int maxHealthPoints) {
		super(healthPoints, damage, 0);
		this.m_maxHealthPoints = DEFAULT_MAX_PLAYERS_LIFE;
	}

	public Player(int healthPoints, int damage, int maxHealthPoints, int x, int y) {
		super(healthPoints, damage, x, y);
		this.m_maxHealthPoints = DEFAULT_MAX_PLAYERS_LIFE;
	}

	// Heal player and boats
	public void heal(int healthPoints) {
		this.m_healthPoints += healthPoints;
		if (this.m_healthPoints > this.m_maxHealthPoints)
			this.m_healthPoints = this.m_maxHealthPoints;
	}

	public void takeDamage(int damage) {
		this.m_healthPoints -= damage;
		if (this.m_healthPoints <= 0) {
			this.die();
		}
	}

	public int getMaxHealthPoints() {
		return m_maxHealthPoints;
	}

	public void setMaxHealthPoints(int m_maxHealthPoints) {
		this.m_maxHealthPoints = m_maxHealthPoints;
	}

}
