package automate.ActionClass;

import java.util.List;

import automate.FunCall;
import automate.Key;
import automate.Parameter;
import info3.game.Controller;
import info3.game.modele.Entity;

public class KeyFunc extends FunCall{
	
	public KeyFunc(List<Parameter> parameters) {
		super("Key", parameters, -1);
	}
	
	public boolean eval(Entity e) {
		Key k = (Key)parameters.get(0);
		String s = String.valueOf(Controller.getBuffer());
		System.out.println(s);
		boolean b = k.eval().equals(s);
		return b;
	}

	@Override
	public void exec(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
