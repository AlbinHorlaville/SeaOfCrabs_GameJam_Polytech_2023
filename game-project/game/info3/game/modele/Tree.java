package info3.game.modele;

import automate.Category;

public class Tree extends MoveableEntity {
	
	public static final int BOARDNUMBER = 3; 
	public static final int HEALTHPOINTSPERBOARD = 100;
	
	public Tree() {
		super(1,0,0);
	}

	public void move() {
		//Mettre la fonction move de Crabe once done
	}

	public void die() {
		GameModele.pirateBoat.addHealthPoints(this.BOARDNUMBER * this.HEALTHPOINTSPERBOARD);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeDamage(int damage) {
		this.lifePoint -= damage;
		
	}

	@Override
	public boolean closeTo(Direction d, Category c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest(Direction d, Category c) {
		// TODO Auto-generated method stub
		return false;
	}

	//Number of boars once the tree is cut
	@Override
	public boolean gotStuff() {
		return this.BOARDNUMBER > 1;
	}

	@Override
	public boolean cell(Direction d, Category c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void hit(Direction d, Category c) {
		// TODO Auto-generated method stub
		
	}
}
