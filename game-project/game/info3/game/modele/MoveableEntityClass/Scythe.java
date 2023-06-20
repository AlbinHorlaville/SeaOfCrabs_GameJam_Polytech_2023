package info3.game.modele.MoveableEntityClass;

import info3.game.modele.Weapon;

public class Scythe extends Weapon{
	
	private static Scythe scythe;
	public static int DAMAGE = 2;
	public static int RANGE  = 5;
	public static double ALPHA  = 80.0;

	private Scythe() {
		super("Scythe", DAMAGE, RANGE, ALPHA);
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
