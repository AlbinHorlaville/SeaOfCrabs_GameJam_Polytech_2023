package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.vue.avatar.BasicCannonBallAvatar;

public class BasicCannonBall extends CannonBall {
	
	static final int BASIC_DAMAGE = 1;
	static final int BASIC_RANGE = 2000;
	static final int BASIC_RATE_OF_FIRE = 1;

	public BasicCannonBall() {
		super(BASIC_DAMAGE,BASIC_RANGE,BASIC_RATE_OF_FIRE);
		this.setAvatar(new BasicCannonBallAvatar(this));
		this.r = this.avatar.getWidth();
		this.automate = AutomateLoader.findAutomate("CannonBall");
		this.current_state = automate.initial_state;
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit(EnumDirection d, EnumCategory c) {
		// TODO Auto-generated method stub
		
	}
}
