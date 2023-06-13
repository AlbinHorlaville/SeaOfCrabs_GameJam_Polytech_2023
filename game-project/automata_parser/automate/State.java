package automate;

import java.util.ArrayList;

import info3.game.modele.Entity;

public class State {
	private ArrayList<Transition> transitions;
	protected String name;
	protected boolean died = false;
	
	public State(String name){
		this.name = name;
	}
	
	public void SetTransitions(ArrayList<Transition> T) {
		this.transitions = T;
	}
	
	public ArrayList<Transition> GetTransitions() {
		return this.transitions;
	}
	
	public String name() {
		return name;
	}
	
	public State step(Entity e) {
		for(Transition t : transitions) {
			if(t.GetCondition().eval(e)) {
				t.GetAction().exec(e);
				return t.GetDestination();
			}
		}
		return this;
	}
	
	public void print() {
//		System.out.println("State : " + name);
//		System.out.println("\tTransition : ");
		System.out.println("\t* ("+ this.name + ")\n");
		for(Transition t : transitions) {
			t.print();
		}
	}
	
	public boolean isDead() {
		return this.died;
	}
}
