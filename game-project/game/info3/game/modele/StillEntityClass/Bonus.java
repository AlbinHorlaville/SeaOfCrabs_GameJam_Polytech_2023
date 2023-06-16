package info3.game.modele.StillEntityClass;

public abstract class Bonus {
	
	protected int _level;
	public final float BONUS_APPLIED = 0.10f;
	
	public Bonus(int level) {
		
	}
	
	abstract public void power();
}
