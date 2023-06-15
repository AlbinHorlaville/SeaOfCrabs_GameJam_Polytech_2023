package info3.game.modele;

import automate.AutomateLoader;
import automate.EnumDirection;

public class PiratePlayer extends Player {
	
	private static final int DEFAULT_PIRATEPLAYER_LIFE_POINT = 100;

	private static final int DEFAULT_PIRATEPLAYER_ATTACK = 2;

	private static final int DEFAULT_PIRATEPLAYER_SPEED = 1;
	
	public static final int MAX_PLAYERS_LIFE = 100;
	
	public static final int PLAYERS_LIFE = 100;
			
	Weapon weapon;

	public PiratePlayer(String aut) {
		super(DEFAULT_PIRATEPLAYER_LIFE_POINT, DEFAULT_PIRATEPLAYER_ATTACK, DEFAULT_PIRATEPLAYER_SPEED);
		this.automate = AutomateLoader.findAutomate(aut);
		this.current_state = automate.initial_state;
		facing = EnumDirection.N;
		r = 100;
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
		facing = eval;
		this.moveEntity(eval, DEFAULT_PIRATEPLAYER_SPEED);
	}

}
