package automate;

public abstract class UnaryOp extends Condition {

	protected String operator;

	public UnaryOp(String operator, FunCall f) {
		super(f);
		this.operator = operator;
		// TODO Auto-generated constructor stub
	}

}
