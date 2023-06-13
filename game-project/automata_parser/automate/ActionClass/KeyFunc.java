package automate.ActionClass;

import java.util.List;

import automate.Entity;
import automate.FunCall;
import automate.Key;
import automate.Parameter;
import info3.game.CanvasListener;

public class KeyFunc extends FunCall{
	
	public KeyFunc(List<Parameter> parameters) {
		super("Key", parameters, -1);
	}
	
	public boolean eval(Entity e) {
		Key k = (Key)parameters.get(0);
		String s = String.valueOf(CanvasListener.getPressed());
		boolean b = k.eval().equals(s);
		System.out.println("b : " + b);
		return b;
	}

	@Override
	public void exec(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
