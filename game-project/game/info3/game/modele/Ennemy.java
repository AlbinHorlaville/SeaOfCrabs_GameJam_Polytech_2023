package info3.game.modele;

public abstract class Ennemy extends MoveableEntity {
	
	protected int level;
	
	public Ennemy(int lifePoint, int attackCoeff, int speedCoeff, int level) {
		super(lifePoint, attackCoeff, speedCoeff);
		this.level = level;
		
	}

	

	
	
	
	
}
