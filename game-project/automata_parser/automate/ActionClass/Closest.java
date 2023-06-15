package automate.ActionClass;

import automate.FunCall;
import info3.game.modele.Entity;

public class Closest extends FunCall{
	
	public Closest() {
		super("Closest");
	}

	@Override
	public boolean eval(Entity e) {
		return e.closest();
	}

	@Override
	public void exec(Entity e) {
		
	}
	

}
