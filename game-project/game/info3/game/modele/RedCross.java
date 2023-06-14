package info3.game.modele;

public class RedCross extends StillEntity {
	protected Treasure treasure;

	public RedCross(Treasure treasure) {
		super();
		this.treasure = treasure;
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
