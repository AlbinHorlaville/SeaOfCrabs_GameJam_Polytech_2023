package info3.game.modele;

public abstract class Player extends MoveableEntity {
	
	public Player(int lifePoint, int maxLifePointsCoeff) {
		super(lifePoint, maxLifePointsCoeff);
	}
	
	public Player(int lifePoint, int maxLifePointsCoeff, int x, int y) {
		super(lifePoint, maxLifePointsCoeff, x, y);
	}
	
	public void heal(int lifePoints) {
		this.lifePoint += lifePoints;
	}
	
}
