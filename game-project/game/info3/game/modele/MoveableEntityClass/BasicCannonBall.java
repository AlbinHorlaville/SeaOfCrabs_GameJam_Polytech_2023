package info3.game.modele.MoveableEntityClass;

import info3.game.modele.StillEntityClass.SeaTreasure;
import info3.game.vue.avatar.BasicCannonBallAvatar;

public class BasicCannonBall extends CannonBall {

	static final int BASIC_DAMAGE = 20;

	public BasicCannonBall() {
		super(BASIC_DAMAGE, BASIC_RANGE, BASIC_RATE_OF_FIRE);
		this.setAvatar(new BasicCannonBallAvatar(this));
		this.r = this.avatar.getWidth();
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hit() {
		if (ennemyAimed instanceof Ship) {
			((Ship) ennemyAimed).takeDamage(damage);
		}

		if (ennemyAimed instanceof Tentacle) {
			((Tentacle) ennemyAimed).takeDamage(damage);
		}

		if (ennemyAimed instanceof SeaTreasure) {
			((SeaTreasure) ennemyAimed).takeDamage(damage);
		}
	}

	@Override
	protected void tripleShot(int mouseX, int mouseY, BoatPlayer boatPlayer) {
		// TODO Auto-generated method stub

	}

}
