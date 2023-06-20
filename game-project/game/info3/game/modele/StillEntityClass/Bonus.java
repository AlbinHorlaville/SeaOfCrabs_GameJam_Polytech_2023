package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.StillEntity;

public abstract class Bonus extends StillEntity{
	
	protected int _level;
	public final float BONUS_APPLIED = 0.10f;
	// Quand un bonus est appliqué, le joueur ajoute à la statistique correspondante le level du tronçon plus 10% de sa stat.
	
	public Bonus(int level, int x, int y) {
		_level = level;
		this.x = x;
		this.y = y;
		this.automate = AutomateLoader.findAutomate(GameEntity.Bonus);
		this.current_state = automate.initial_state;
	}
	
	abstract public void power();
	
	public int getLevel() {
		return _level;
	}
}
