package info3.game.modele;

public abstract class StillEntity extends Entity {

	public StillEntity() {
		super(50);
	}

	public StillEntity(int x, int y, int r) {
		super(x, y, r);
	}

	public StillEntity(int x, int y) {
		super(x, y);
	}

	public abstract void move();

	public void takeDamage(int damage) {
		System.out.println("Cette entité n'a pas de fonction takeDamage()");
	}

}
