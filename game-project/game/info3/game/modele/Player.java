package info3.game.modele;

public abstract class Player extends MoveableEntity {
	
	public Player(int lifePoint, int attackCoeff, int speedCoeff) {
		super(lifePoint, attackCoeff, speedCoeff);
	}

	@Override
	public abstract void attack();

	@Override
	public abstract void takeDamage();
	
	public void heal(int lifePoints) {
		this.lifePoint += lifePoints;
	}
	
}
