package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.GameEntity;
import info3.game.modele.StillEntityClass.SeaTreasure;
import info3.game.vue.avatar.BasicCannonBallAvatar;

public class BasicCannonBall extends CannonBall {


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
		
		if (ennemyAimed instanceof SeaTreasure ) {
			((SeaTreasure) ennemyAimed).takeDamage(damage);
		}
	}

	
}
