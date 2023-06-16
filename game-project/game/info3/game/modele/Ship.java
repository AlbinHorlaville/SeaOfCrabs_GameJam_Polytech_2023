package info3.game.modele;

public class Ship extends Ennemy {
	
	private int level;

	public Ship(int lifePoint, int attackCoeff, int speedCoeff, int level) {		// To delete
		super(lifePoint, attackCoeff);
		// TODO Auto-generated constructor stub
	}
	
	public Ship(int healthPoints, int damage, int level) {		// To delete
		super(healthPoints, damage);
		this.level = level;
		// TODO Auto-generated constructor stub
	}

}
