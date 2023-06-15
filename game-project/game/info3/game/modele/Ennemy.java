package info3.game.modele;

public abstract class Ennemy extends MoveableEntity {
	
	protected int level;
	
	public Ennemy(int lifePoint, int attackCoeff, int level) {
		super(lifePoint, attackCoeff);
		this.level = level;
		
	}

	

	
	
	
	
}
