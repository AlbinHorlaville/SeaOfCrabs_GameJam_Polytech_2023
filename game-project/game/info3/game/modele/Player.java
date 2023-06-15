package info3.game.modele;

public abstract class Player extends MoveableEntity {
	
	public Player(int lifePoint, int maxLifePointsCoeff, int attackSpeedCoeff, int speedCoeff, int damageCoeff, int rangeCoeff) {
		super(lifePoint, maxLifePointsCoeff, attackSpeedCoeff, speedCoeff, damageCoeff, rangeCoeff);
	}
	
	@Override
	public abstract void attack();

	@Override
	public abstract void takeDamage();
	
	public void heal(int lifePoints) {
		this.lifePoint += lifePoints;
	}
	
}
