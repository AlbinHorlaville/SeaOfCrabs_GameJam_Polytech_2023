package automate;

import info3.game.modele.Entity;

public abstract class BinaryOp extends Condition {

	protected FunCall left;
	protected FunCall right;
	protected String operator;

	public BinaryOp(String name, String operator, FunCall left, FunCall right) {
		super();
		this.m_string = name;
		this.operator = operator;
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean eval(Entity e) {
		// TODO Auto-generated method stub
		return left.eval(e) && right.eval(e);
	}

}
