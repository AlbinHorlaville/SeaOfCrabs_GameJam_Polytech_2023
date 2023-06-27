package info3.game.modele.MoveableEntityClass;

import info3.game.modele.StillEntityClass.SeaTreasure;
import info3.game.vue.avatar.KrakenSlayerCannonBallAvatar;

public class KrakenSlayerCannonBall extends CannonBall {

	static final int KRAKEN_DAMAGE = 100; // A modifier

	public KrakenSlayerCannonBall() {
		super(KRAKEN_DAMAGE, BASIC_RANGE, BASIC_RATE_OF_FIRE);
		this.setAvatar(new KrakenSlayerCannonBallAvatar(this));
		this.r = this.avatar.getWidth();
	}

	@Override
	public void hit() {
		if (ennemyAimed instanceof Ship) {
			((Ship) ennemyAimed).takeDamage(0);
		}

		if (ennemyAimed instanceof Tentacle) {
			((Tentacle) ennemyAimed).takeDamage(damage);
		}

		if (ennemyAimed instanceof SeaTreasure) {
			((SeaTreasure) ennemyAimed).takeDamage(0);
		}
	}

	@Override
	protected void tripleShot(int mouseX, int mouseY, BoatPlayer boatPlayer) {
		// TODO Auto-generated method stub

	}
}