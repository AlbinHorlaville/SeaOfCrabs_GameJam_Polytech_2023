package info3.game.modele;

public abstract class StillEntity extends Entity {
	
	public StillEntity() {
		super();
	}
	
	public StillEntity(int x, int y) {
		super(x,y);
	}

	public abstract void move();

}
