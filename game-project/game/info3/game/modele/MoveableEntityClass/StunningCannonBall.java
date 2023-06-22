package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.GameEntity;
import info3.game.vue.avatar.BasicCannonBallAvatar;

public class StunningCannonBall extends CannonBall {
	int amount;
	
	public StunningCannonBall() {
		super(0, BASIC_RANGE, BASIC_RATE_OF_FIRE);
		this.setAvatar(new BasicCannonBallAvatar(this));
		this.r = this.avatar.getWidth();
		this.automate = AutomateLoader.findAutomate(GameEntity.CannonBall);
		this.current_state = automate.initial_state;
		this.amount = 1;
	}
	
	public void addBullet() {
		this.amount++;
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit(EnumDirection d, EnumCategory c) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void hit() {
		if (ennemyAimed instanceof Ship) {
			((Ship) ennemyAimed).stunt();
		}
		else if (ennemyAimed instanceof Tentacle){
			
		}

	}
}