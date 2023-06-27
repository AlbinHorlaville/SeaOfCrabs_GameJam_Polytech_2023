package automate.ActionClass;

import java.util.List;

import automate.Direction;
import automate.EnumCategory;
import automate.EnumDirection;
import automate.FunCall;
import automate.Parameter;
import info3.game.modele.Entity;

public class Move extends FunCall {

	protected int probability;

	public Move(List<Parameter> parameters, int proba) {
		super("Move", parameters, proba);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(Entity e) {
		if (parameters.size() != 0) {
			Parameter param = parameters.get(0);
			if (param instanceof Direction)
				e.move((EnumDirection) parameters.get(0).eval());
			else
				e.move((EnumCategory) parameters.get(0).eval());
		}

		e.move();
		// TODO Auto-generated method stub
	}

	@Override
	public boolean eval(Entity e) {
		return false;
	}

	public String toString() {
		String s = "";
		s += "Move(";
		for (Parameter p : parameters) {
			s += p.toString();
			s += ", ";
		}
		s += ");";
		return s;
	}

}
