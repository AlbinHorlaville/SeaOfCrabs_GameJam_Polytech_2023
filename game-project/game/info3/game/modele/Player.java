package info3.game.modele;

public abstract class Player extends MoveableEntity {
	
	private int m_maxHealthPoints;
	
	public Player(int healthPoints, int damage) {				//To delete
		super(healthPoints, damage);
	}
	
	public Player(int healthPoints, int damage, int x, int y) { //To delete
		super(healthPoints, damage, x, y);
	}
	
	public Player(int healthPoints, int damage, int maxHealthPoints) { 							// the good one
		super(healthPoints, damage);
		this.m_maxHealthPoints = maxHealthPoints;
	}
	
	//Heal player and boats
	public void heal(int healthPoints) {
		this.m_healthPoints += healthPoints;
		if(this.m_healthPoints > this.m_maxHealthPoints)
			this.m_healthPoints = this.m_maxHealthPoints;
	}
	
	public void takeDamage(int damage) {
		this.m_healthPoints -= damage;
		if(this.m_healthPoints <= 0) {
			this.die();
		}
	}
	
}
