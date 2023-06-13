package automate;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import info3.game.modele.Entity;

public abstract class FunCall extends Expression {
	
	protected String string;
	protected List<automate.Parameter> parameters;
	protected int probability;
	
	protected FunCall() {
		super();
		parameters = new ArrayList<>();
	}
	
	protected FunCall(String string) {
		super(string);
		this.parameters = new ArrayList<>();
	}
	
	protected FunCall(String string, List<automate.Parameter> parameters, int probability) {
		super(string);
		if(parameters != null) {
			this.parameters = parameters;
		}
		else {
			this.parameters = new ArrayList<>();
		}
		this.probability = (probability == -1)? 100 : probability;
	}
		
	public String toString() {
		String s = this.m_string;
		if (parameters.size() > 0) {
			s += "(";
			ListIterator<automate.Parameter> iterator = parameters.listIterator();
			while (iterator.hasNext()) {
				s += iterator.next().toString();
				if (iterator.hasNext())
					s += ",";
			}
			s += ")";
		}
		return s;

	}
	
	public abstract boolean eval(Entity e);
	
	public abstract void exec(Entity e);
}
