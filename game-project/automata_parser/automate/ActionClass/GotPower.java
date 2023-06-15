package automate.ActionClass;

import automate.FunCall;
import info3.game.modele.Entity;

public class GotPower extends FunCall{

	@Override
	public boolean eval(Entity e) {
		return e.gotPower();
	}

	@Override
	public void exec(Entity e) {

	}

}
