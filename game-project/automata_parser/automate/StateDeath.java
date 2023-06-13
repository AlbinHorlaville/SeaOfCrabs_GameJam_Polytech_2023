package automate;

public class StateDeath extends State{

	public StateDeath() {
		super("()");
		super.died = true;
	}
	
	@Override
	public void print() {
		System.out.println("State : " + name);
	}

}
