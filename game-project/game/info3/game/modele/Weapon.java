package info3.game.modele;

import info3.game.modele.MoveableEntityClass.PiratePlayer;

public abstract class Weapon {
	
	public static String Sword = "Sword";
	public static String Scythe = "Scythe";
	public static String Dagger = "Dagger";
	
	private PiratePlayer player;
	protected String name;
	protected int damage;
	protected double range;
	protected double alpha;
	
	public Weapon(String name, int damage, double range, double alpha) {
		this.name = name;
		this.damage = damage;
		this.range = range;
		this.alpha = alpha;
	}
	
	public abstract void hit();
	
	public void setPlayer(PiratePlayer player) {
		this.player = player;
	}
	
	public String getName() {
		return name;
	}
	
}
