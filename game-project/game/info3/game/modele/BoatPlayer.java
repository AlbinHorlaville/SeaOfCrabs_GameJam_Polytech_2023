package info3.game.modele;

import java.util.ArrayList;

public class BoatPlayer extends Player {

	ArrayList<CannonBall> bouletDeCannon;
	CannonBall current_ball;
	
	public BoatPlayer(int lifePoint, int attackCoeff, int speedCoeff) {
		super(lifePoint, attackCoeff, speedCoeff);
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
	
	/**
	 * Cette méthode doit être changer et permettra d'ajouter un boulet quand ramasser
	 * @param boulet
	 */
	public void addBoulet(CannonBall boulet) {
		this.bouletDeCannon.add(boulet);
	}

}
