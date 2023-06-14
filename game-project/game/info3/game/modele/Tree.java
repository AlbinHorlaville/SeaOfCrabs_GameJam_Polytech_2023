package info3.game.modele;

public class Tree extends MoveableEntity {
	public Tree() {
		super(1,0,0);
	}

	public void move() {
		// TODO
	}

	public void die() {
		// TODO
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeDamage(int damage) {
		lifePoint -= damage;
		
	}
}
