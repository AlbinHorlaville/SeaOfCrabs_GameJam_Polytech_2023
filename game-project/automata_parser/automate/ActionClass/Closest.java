package automate.ActionClass;

import java.util.List;

import automate.EnumCategory;
import automate.FunCall;
import info3.game.modele.Entity;

public class Closest extends FunCall{
	
	public Closest(List<automate.Parameter> parameters) {
		super("Closest", parameters, -1);
	}

	@Override
	public boolean eval(Entity e) {
		if(this.parameters.size()> 0)
			return e.closest((EnumCategory)parameters.get(0).eval());
		else
			return e.closest();
	}

	@Override
	public void exec(Entity e) {
		
	}
	

}
