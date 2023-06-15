package info3.game.modele;

public abstract class Player extends MoveableEntity {
	
	public Player(int lifePoint, int maxLifePointsCoeff) {
		super(lifePoint, maxLifePointsCoeff);
	}
	
	public void heal(int lifePoints) {
		this.lifePoint += lifePoints;
	}
	
}
