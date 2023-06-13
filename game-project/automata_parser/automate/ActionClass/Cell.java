package automate.ActionClass;

import java.util.List;

import automate.FunCall;
import automate.Parameter;
import info3.game.modele.Entity;

public class Cell extends FunCall {
	private Parameter direction;
	private Parameter categorie;
	
	public Cell(List<automate.Parameter> parameters) {
		super("Cell", parameters, -1);
		this.direction = parameters.get(0);
		this.categorie = parameters.get(1);
	}

	@Override
	public boolean eval(Entity e) {
		return true;
	}

	@Override
	public void exec(Entity e) {
		
		
	}
	public String toString() {
		String s = "Cell("+this.direction.m_string+", "+this.categorie.m_string+");";
		return s;
	}

}
