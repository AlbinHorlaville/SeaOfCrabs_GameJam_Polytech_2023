package info3.game.modele;

import java.util.ArrayList;

public class Kraken extends Ennemy {

	ArrayList<Tentacle> tentacles;
	int nb_tentacles;

	public Kraken(int lifePoint, int attackCoeff, int speedCoeff, int level, int nb_tentacles) {
		super(lifePoint, attackCoeff, speedCoeff, level);
		this.nb_tentacles = nb_tentacles;
		this.tentacles = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void takeDamage() {
		// TODO Auto-generated method stub

	}

	public void move() {
		// TODO
	}

}
