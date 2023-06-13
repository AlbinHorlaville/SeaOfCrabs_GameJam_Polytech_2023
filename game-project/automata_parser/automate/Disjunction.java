package automate;

import info3.game.modele.Entity;

public class Disjunction extends BinaryOp{
	
	public Disjunction(String name,FunCall l, FunCall r) {
		super(name,"/", l, r);

	}
	
	@Override
	public boolean eval(Entity e) {
		return f.eval(e) || right.eval(e);
	}
}
