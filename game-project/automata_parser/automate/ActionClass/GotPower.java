package automate.ActionClass;

import automate.FunCall;
import info3.game.modele.Entity;
import info3.game.modele.MoveableEntity;

public class GotPower extends FunCall{

	public GotPower() {
		super("GotPower");
	}
	
	@Override
	public boolean eval(Entity e) {
		return ((MoveableEntity)e).gotPower();
	}

	@Override
	public void exec(Entity e) {
	}

}
