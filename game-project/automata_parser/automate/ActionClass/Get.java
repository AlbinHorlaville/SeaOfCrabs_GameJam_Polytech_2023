package automate.ActionClass;

import java.util.List;

import automate.Entity;
import automate.FunCall;
import automate.Parameter;

public class Get extends FunCall{
	
	protected int probability;
	
	public Get(List<Parameter> parameters, int proba) {
		super("Get", parameters, proba);
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
		s += "Get(";
		for(Parameter p : parameters) {
			s+= p.toString();
			s+=", ";
		}
		s+=");";
		return s;
	}

}

