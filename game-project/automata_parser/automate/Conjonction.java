package automate;

public class Conjonction extends BinaryOp{
	
	public Conjonction(String name, FunCall l, FunCall r) {
		super(name, "&",l, r);
	}
	
	@Override
	public boolean eval(Entity e) {
		return f.eval(e) && right.eval(e);
	}

}
