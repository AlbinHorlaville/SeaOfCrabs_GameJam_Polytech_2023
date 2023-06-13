package info3.game.modele;

import automate.AutomateLoader;
import automate.EnumDirection;

public class PiratePlayer extends Player {
	
	Weapon weapon;

	public PiratePlayer(int lifePoint, int attackCoeff, int speedCoeff) {
		super(lifePoint, attackCoeff, speedCoeff);
		this.automate = AutomateLoader.getPiratePlayerAutomate();
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
	}

	@Override
	public void takeDamage() {
		// TODO Auto-generated method stub

	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	@Override
	public void move(EnumDirection eval) {
		// TODO Auto-generated method stub
		
	}

}
