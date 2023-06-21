package automate;

import info3.game.modele.Entity;

public class StateDeath extends State{

	public StateDeath() {
		super("()");
		this.died = false;
	}
	
	@Override
	public void print() {
		System.out.println("State : " + name);
	}

}
