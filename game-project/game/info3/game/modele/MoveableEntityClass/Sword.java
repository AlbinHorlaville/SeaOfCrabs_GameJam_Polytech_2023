package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.Weapon;
import info3.game.vue.avatar.SwordAvatar;

public class Sword extends Weapon{
	
	
	private static Sword sword;
	public static int DAMAGE = 2;
	public static double RANGE  = 10.0;
	public static double ALPHA  = 45.0;

	private Sword() {
		super("Sword", DAMAGE, RANGE, ALPHA);
		this.setAvatar(new SwordAvatar(this));
		this.automate = AutomateLoader.findAutomate(GameEntity.Philosopher);
		this.current_state = automate.initial_state;
	}
	
	public static Sword getInstance() {
		if(sword == null)
			sword = new Sword();
		return sword;
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

}
