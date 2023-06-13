package automate;

public class StateDeath extends State{

	public StateDeath() {
		super("()");
	}
	
	@Override
	public void print() {
		System.out.println("State : " + name);
	}

}
