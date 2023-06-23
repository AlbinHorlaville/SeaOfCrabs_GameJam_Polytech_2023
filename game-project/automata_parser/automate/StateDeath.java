package automate;

import info3.game.modele.Entity;

public class StateDeath extends State{

	public StateDeath() {
		super("()");
	}
	
	@Override
	public void print() {
		System.out.println("State : " + name);
	}
	
	public State step(Entity e) {
		//e.die();
		//this.died = true;
		return this;
	}
	
	public boolean isDead() {
		return true;
	}

}
