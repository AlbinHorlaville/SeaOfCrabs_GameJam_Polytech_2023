package info3.game.modele;

import info3.game.modele.map.MapSection;

public class RedCross extends StillEntity {
	protected Treasure treasure;
	private MapSection section;

	public RedCross(MapSection section) {
		super();
		this.section = section;
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
