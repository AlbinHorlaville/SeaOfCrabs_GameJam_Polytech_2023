package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import info3.game.modele.StillEntity;
import info3.game.modele.map.MapSection;

public class RedCross extends StillEntity {
	protected Treasure treasure;
	public RedCross(MapSection section) {
		super();
		this.automate = AutomateLoader.findAutomate("Philosopher");
		this.current_state = automate.initial_state;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}
	/*
	 * Si le joueur touche la croix, elle disparait et laisse place à son trésor
	 */
	public void interact() {
		// TODO
	}

}
