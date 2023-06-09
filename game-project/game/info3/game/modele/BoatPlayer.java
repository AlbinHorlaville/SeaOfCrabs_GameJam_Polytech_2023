package info3.game.modele;

import java.util.ArrayList;

public class BoatPlayer extends Player {

	ArrayList<CannonBall> bouletDeCannon;
	
	
	public BoatPlayer(int lifePoint, int attackCoeff, int speedCoeff) {
		super(lifePoint, attackCoeff, speedCoeff);
		
		bouletDeCannon = new ArrayList<>();
	}
	
	@Override
	public void attack() {
		// TODO Auto-generated method stub
	}

	@Override
	public void takeDamage() {
		// TODO Auto-generated method stub

	}
	
	public void addBoulet(CannonBall boulet) {
		this.bouletDeCannon.add(boulet);
	}

}
