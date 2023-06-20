package automate;

import info3.game.modele.Entity;

public class StateDeath extends State{

	public StateDeath() {
		super("()");
		this.died = false;
	}
	
	@Override
	public State step(Entity e) {
		e.die();
		return this;
	}
	
	@Override
	public void print() {
		System.out.println("State : " + name);
	}
	
	public State step(Entity e) {
		e.die();
		this.died = true;
		return this;
	}

}
