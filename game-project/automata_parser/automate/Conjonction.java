package automate;

import info3.game.modele.Entity;

public class Conjonction extends BinaryOp{
	
	public Conjonction(String name, FunCall l, FunCall r) {
		super(name, "&",l, r);
	}
	
//	@Override
//	public boolean eval(Entity e) {
//		return left.eval(e) && right.eval(e);
//	}

}
