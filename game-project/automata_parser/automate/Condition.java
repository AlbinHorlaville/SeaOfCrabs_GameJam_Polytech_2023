package automate;


public class Condition extends FunCall{
	protected FunCall f;
	
	public Condition() {
		super();
	}
	
	public Condition(FunCall f) {
		super(f.m_string, f.parameters, -1);
		this.f = f;
	}

	public boolean eval(Entity e) {
		return f.eval(e);
	}

	@Override
	public void exec(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		if(this.f != null)
			return this.f.toString();
		else return "";
	}
	
}
