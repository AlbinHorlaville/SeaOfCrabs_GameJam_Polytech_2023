package automate.ActionClass;

import automate.FunCall;
import info3.game.modele.Entity;

public class GotStuff extends FunCall{
	
	public GotStuff() {
		super("GotStuff");
	}

	@Override
	public boolean eval(Entity e) {
		return e.gotStuff();
	}

	@Override
	public void exec(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
