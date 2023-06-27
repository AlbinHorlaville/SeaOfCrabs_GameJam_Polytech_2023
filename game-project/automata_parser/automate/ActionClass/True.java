package automate.ActionClass;

import automate.FunCall;
import info3.game.modele.Entity;

public class True extends FunCall {

	public True() {
		super("True", null, -1);
	}

	@Override
	public boolean eval(Entity e) {
		return true;
	}

	@Override
	public void exec(Entity e) {
		// TODO Auto-generated method stub

	}

}
