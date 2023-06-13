package automate;

import java.util.LinkedList;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import visitor.Visitor;

public class Entity {
	private int x;
	public State current;
	public Automate automate;
	
	protected Entity(String name) throws Exception{
		this.automate = this.findAutomate(name);
		this.current = automate.initial_state;
		this.x = 0;
		
	}
	
	public automate.Automate findAutomate(String name) throws Exception {
		AST ast = AutomataParser.from_file("../../../gal/automaton/exemples/exemples.gal");
		Visitor v = new Visitor();
		LinkedList<Object> l = (LinkedList<Object>) ast.accept(v);
		for(Object a : l) {
			if(((automate.Automate)a).name.equals(name) ) {
				return (automate.Automate)a;
			}
		}
		return null;

	}
	
	public boolean move() {
		this.x++;
		return true;
	}
	
	public int GetX() {
		return this.x;
	}
}
