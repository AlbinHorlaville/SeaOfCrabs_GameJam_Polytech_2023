package info3.game.modele.MoveableEntityClass;

import info3.game.modele.StillEntityClass.SeaTreasure;
import info3.game.vue.avatar.DamagedCannonBallAvatar;

public class DamagedCannonBall extends CannonBall {
	
	static final int DAMAGED_DAMAGE = 50; // A modifier
	
	public DamagedCannonBall() {
		super(DAMAGED_DAMAGE, BASIC_RANGE, BASIC_RATE_OF_FIRE);
		this.setAvatar(new DamagedCannonBallAvatar(this));
		this.r = this.avatar.getWidth();
	}

	@Override
	public void hit() {
		if (ennemyAimed instanceof Ship) {
			((Ship) ennemyAimed).takeDamage(damage);
		}
		
		if (ennemyAimed instanceof Tentacle) {
			((Tentacle) ennemyAimed).takeDamage(damage);
		}
		
		if (ennemyAimed instanceof SeaTreasure ) {
			((SeaTreasure) ennemyAimed).takeDamage(damage);
		}
	}
}