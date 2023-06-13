package automate;

import java.util.LinkedList;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import visitor.Visitor;

public abstract class Entity {
	private int x;
	public State current;
	public Automate automate;
	
	protected Entity(String name) throws Exception{
		System.out.println("path : " + System.getProperty("user.dir"));
		this.automate = this.findAutomate(name);
		automate.print();
		this.current = automate.initial_state;
		this.x = 0;
		
	}
	
	public automate.Automate findAutomate(String name) throws Exception {
		AST ast = AutomataParser.from_file(System.getProperty("user.dir") + "/../gal/exemples/exemples.gal");
		Visitor v = new Visitor();
		LinkedList<Object> l = (LinkedList<Object>) ast.accept(v);
		for(Object a : l) {
			if(((automate.Automate)a).name.equals(name) ) {
				return (automate.Automate)a;
			}
		}
		return null;

	}

	public abstract void move(EnumDirection eval);
	
//	public boolean move() {
//		this.x++;
//		return true;
//	}
//	
//	public int GetX() {
//		return this.x;
//	}
}
