package info3.game.modele;

public class BasicCannonBall extends CannonBall {
	
	static final int BASIC_DAMAGE = 1;
	static final int BASIC_RANGE = 1;
	static final int BASIC_RATE_OF_FIRE = 1;


	public BasicCannonBall() {
		this.damage = BASIC_DAMAGE;
		this.range = BASIC_RANGE;
		this.rateOfFire = BASIC_RATE_OF_FIRE;
	}
}
