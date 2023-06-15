package info3.game.modele;

public class Scythe extends Weapon{
	
	private static Scythe scythe;
	public static int DAMAGE = 2;
	public static double RANGE  = 5.0;
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
