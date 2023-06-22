package automate.ActionClass;

import java.util.List;

import automate.EnumCategory;
import automate.FunCall;
import automate.Parameter;
import info3.game.modele.Entity;

public class Get extends FunCall{
	
	protected int probability;
	
	public Get(List<Parameter> parameters, int proba) {
		super("Get", parameters, proba);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(Entity e) {
		if (parameters.size() > 0) {
			e.get((EnumCategory) parameters.get(0).eval());
		}
		e.get();
	}

	@Override
	public boolean eval(Entity e) {
		return false;
	}
	
	public String toString() {
		String s = "";
		s += "Get(";
		for(Parameter p : parameters) {
			s+= p.toString();
			s+=", ";
		}
		s+=");";
		return s;
	}

}

