package info3.game.modele;

public class PiratePlayer extends Player {
	
	Weapon weapon;

	public PiratePlayer(int lifePoint, int attackCoeff, int speedCoeff) {
		super(lifePoint, attackCoeff, speedCoeff);
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
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

}
