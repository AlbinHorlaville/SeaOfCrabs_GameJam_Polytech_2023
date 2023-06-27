package automate;

public class Transition {
	private Condition cond;
	private Action action;
	private State destination;

	public Transition(Condition cond, Action action, State dest) {
		this.cond = cond;
		this.action = action;
		this.destination = dest;
	}

	public State GetDestination() {
		return this.destination;
	}

	public Condition GetCondition() {
		return this.cond;
	}

	public Action GetAction() {
		return this.action;
	}

	public void setCond(Condition cond) {
		this.cond = cond;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setDestination(State destination) {
		this.destination = destination;
	}

	public void print() {
//		System.out.println("\tCond " + cond.m_string 
//				+ " action : " + action.toString() 
//				+ " target : " + destination.name());

		System.out.println("\t| " + this.cond.toString() + " ? " + this.action.toString() + " : ("
				+ this.destination.name + ")\n");
	}

}
