package automate;

import java.util.ArrayList;

public class State {
	private ArrayList<Transition> transitions;
	protected String name;
	
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
	
	public void step(Entity e) {
		
	}
	
	public void print() {
//		System.out.println("State : " + name);
//		System.out.println("\tTransition : ");
		System.out.println("\t* ("+ this.name + ")\n");
		for(Transition t : transitions) {
			t.print();
		}
	}
}
