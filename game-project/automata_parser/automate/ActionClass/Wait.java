package automate.ActionClass;

import java.util.List;

import automate.Entity;
import automate.FunCall;
import automate.Parameter;

public class Wait extends FunCall{
	
	protected int probability;
	
	public Wait(List<Parameter> parameters, int proba) {
		super("Wait", parameters, proba);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(Entity e) {
		System.out.println(this.toString());
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean eval(Entity e) {
		return false;
	}
	
	public String toString() {
		String s = "";
		s += "Wait(";
		for(Parameter p : parameters) {
			s+= p.toString();
			s+=", ";
		}
		s+=");";
		return s;
	}

}

