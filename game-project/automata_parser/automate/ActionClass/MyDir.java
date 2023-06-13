package automate.ActionClass;

import java.util.List;

import automate.Direction;
import automate.Entity;
import automate.FunCall;
import automate.Parameter;

public class MyDir extends FunCall{
	
	private Direction direction;
	
	public MyDir(List<Parameter> parameters) {
		super("Mydir");
		this.direction = (Direction) parameters.get(0);
	}

	@Override
	public boolean eval(Entity e) {
		//return EnumMyDirection.valueOf(m_string);
		
		return false;

	}

	@Override
	public void exec(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		String s = "Mydir(" + this.direction.m_string + ");";
		return s;
	}


}
