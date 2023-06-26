package info3.game.modele.MoveableEntityClass;


import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.Weapon;
import info3.game.vue.avatar.SwordAvatar;

public class Sword extends Weapon{
	
	public static int RANGE  = 200;

	public Sword() {
		super("Sword", RANGE);
		setAvatar(new SwordAvatar(this));
		this.automate = AutomateLoader.findAutomate(GameEntity.Philosopher);
		this.current_state = automate.initial_state;
	}
}
