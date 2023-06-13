package automate.ActionClass;

import java.util.List;

import automate.Entity;
import automate.FunCall;
import automate.Parameter;

public class KeyFunc extends FunCall{
	
	public KeyFunc(List<Parameter> parameters) {
		super("Key", parameters, -1);
	}
	
	public boolean eval(Entity e) {
		return this.parameters.get(0).toString().equals(getPressedCharacter());
	}
	
	private String getPressedCharacter() {
		return "z";
	}

	@Override
	public void exec(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
