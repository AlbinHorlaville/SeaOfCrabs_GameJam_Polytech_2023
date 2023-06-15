package info3.game.modele;

import java.util.ArrayList;

import automate.AutomateLoader;
import automate.EnumDirection;

public class BoatPlayer extends Player {

	ArrayList<CannonBall> bouletDeCannon;
	CannonBall current_ball;
	
	private static final int DEFAULT_BOATPLAYER_LIFE_POINT = 100;

	private static final int DEFAULT_BOATPLAYER_ATTACK = 2;

	private static final int DEFAULT_BOATPLAYER_SPEED = 1;
	
	public BoatPlayer() {
		super(DEFAULT_BOATPLAYER_LIFE_POINT, DEFAULT_BOATPLAYER_ATTACK, DEFAULT_BOATPLAYER_SPEED);
		
		bouletDeCannon = new ArrayList<>();
		this.current_ball = new BasicCannonBall();
		
		this.automate = AutomateLoader.findAutomate("Player");
		this.current_state = automate.initial_state;
		
		this.facing = EnumDirection.N;
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
	}

	@Override
	public void takeDamage() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Cette méthode doit être changer et permettra d'ajouter un boulet quand ramasser
	 * @param boulet
	 */
	public void addBoulet(CannonBall boulet) {
		this.bouletDeCannon.add(boulet);
	}
	
	@Override
	public void move(EnumDirection eval) {
		facing = eval;
		this.moveEntity(eval, DEFAULT_BOATPLAYER_SPEED);
		switch (GameModele.map.getTileUnderEntity(this.x, this.y).getType()) {
		case SAND:
		case SAND_WATER:
		case GRASS:
			GameModele.entities.remove(this);
			GameModele.player1.setLocation(x, y);
			GameModele.player1.facing = this.facing;
			GameModele.entities.add(GameModele.player1);
			GameModele.onSea = !GameModele.onSea;
			break;
		default:
			break;
		}
	}

}
