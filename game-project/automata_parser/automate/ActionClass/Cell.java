package automate.ActionClass;

import java.util.List;

import automate.EnumCategory;
import automate.EnumDirection;
import automate.FunCall;
import automate.Parameter;
import info3.game.modele.Entity;
import info3.game.modele.MoveableEntity;

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
		try {
			return e.cell((EnumDirection)direction.eval(), (EnumCategory)categorie.eval());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public void exec(Entity e) {
		
		
	}
	public String toString() {
		String s = "Cell("+this.direction.m_string+", "+this.categorie.m_string+");";
		return s;
	}

}
