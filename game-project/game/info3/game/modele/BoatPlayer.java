package info3.game.modele;

import java.util.ArrayList;

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
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
	}

	@Override
	public void takeDamage() {
		// TODO Auto-generated method stub

	}
	
	public void addHealthPoints(int healthPoints) {
		this.lifePoint += healthPoints;
	}
	
	/**
	 * Cette m�thode doit �tre changer et permettra d'ajouter un boulet quand ramasser
	 * @param boulet
	 */
	public void addBoulet(CannonBall boulet) {
		this.bouletDeCannon.add(boulet);
	}

}
