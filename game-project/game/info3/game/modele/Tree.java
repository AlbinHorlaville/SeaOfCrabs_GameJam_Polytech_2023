package info3.game.modele;

public class Tree extends MoveableEntity {
	
	public static final int BOARDNUMBER = 3; 
	public static final int HEALTHPOINTSPERBOARD = 100;
	
	public Tree() {
		super(1,0,0);
	}

	public void move() {
		//Doesn't do a thing
	}

	public void die() {
		//add hp to the unique boat
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
