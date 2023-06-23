package info3.game.modele.MoveableEntityClass;

import info3.game.modele.Weapon;

public class Scythe extends Weapon{
	
	private static Scythe scythe;
	public static int RANGE  = 5;

	public Scythe() {
		super("Scythe", RANGE);
	}
	
	public static Scythe getInstance() {
		if(scythe == null)
			scythe = new Scythe();
		return scythe;
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

}
