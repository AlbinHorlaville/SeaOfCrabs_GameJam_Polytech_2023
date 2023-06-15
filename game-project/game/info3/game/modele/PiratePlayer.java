package info3.game.modele;

import automate.AutomateLoader;
import automate.EnumDirection;

public class PiratePlayer extends Player {
	
	private static final int DEFAULT_PIRATEPLAYER_LIFE_POINT = 100;
	
	public static final int DEFAULT_MAX_PLAYERS_LIFE_COEFF = 100;
	
	private static final int DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF = 1;
	
	private static final int DEFAULT_PIRATEPLAYER_SPEED_COEFF = 1;

	private static final int DEFAULT_PIRATEPLAYER_DAMAGE_COEFF = 1;
	
	private static final int DEFAULT_PIRATEPLAYER_RANGE_COEFF = 1;

			
	Weapon weapon;

	public PiratePlayer() {
		super(DEFAULT_PIRATEPLAYER_LIFE_POINT, DEFAULT_MAX_PLAYERS_LIFE_COEFF, DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF, DEFAULT_PIRATEPLAYER_SPEED_COEFF, DEFAULT_PIRATEPLAYER_DAMAGE_COEFF, DEFAULT_PIRATEPLAYER_RANGE_COEFF);
		this.automate = AutomateLoader.findAutomate("Player");
		this.current_state = automate.initial_state;
		facing = EnumDirection.N;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub

	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	@Override
	public void move(EnumDirection eval) {
		facing = eval;
		this.moveEntity(eval, DEFAULT_PIRATEPLAYER_SPEED_COEFF);
	}

}
