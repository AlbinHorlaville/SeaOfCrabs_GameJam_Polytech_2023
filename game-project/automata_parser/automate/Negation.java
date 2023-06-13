package automate;

public class Negation extends UnaryOp{
	
	public Negation(FunCall c) {
		super("!", c);
	}
	
	@Override
	public boolean eval(Entity e) {
		return !this.f.eval(e);
	}

}
