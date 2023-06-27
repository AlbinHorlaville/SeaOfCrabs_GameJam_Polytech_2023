package info3.game.modele.MoveableEntityClass;

import info3.game.modele.StillEntityClass.SeaTreasure;
import info3.game.vue.avatar.StunningCannonBallAvatar;

public class StunningCannonBall extends CannonBall {

	static final int STUNT_DAMAGE = 10; // A modifier

	public StunningCannonBall() {
		super(STUNT_DAMAGE, BASIC_RANGE, BASIC_RATE_OF_FIRE);
		this.setAvatar(new StunningCannonBallAvatar(this));
		this.r = this.avatar.getWidth();
	}

	@Override
	public void hit() {
		if (ennemyAimed instanceof Ship) {
			((Ship) ennemyAimed).stunt();
			((Ship) ennemyAimed).takeDamage(damage);
		} else if (ennemyAimed instanceof Tentacle) {
			((Tentacle) ennemyAimed).stunt();
			((Tentacle) ennemyAimed).takeDamage(damage);
		} else if (ennemyAimed instanceof SeaTreasure) {
			((SeaTreasure) ennemyAimed).takeDamage(damage);
		}
	}

	@Override
	protected void tripleShot(int mouseX, int mouseY, BoatPlayer boatPlayer) {
		// TODO Auto-generated method stub

	}
}