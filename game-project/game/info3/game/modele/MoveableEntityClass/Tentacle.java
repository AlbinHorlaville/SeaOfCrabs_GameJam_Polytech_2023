package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.vue.avatar.TentacleAvatar;

public class Tentacle extends Ennemy  {

	public Tentacle(int lifePoint, int attackCoeff, int speedCoeff, int level) {
		super(lifePoint, attackCoeff, speedCoeff, level);
		this.automate = AutomateLoader.findAutomate(GameEntity.Philosopher);
		this.current_state = automate.initial_state;
		
		this.avatar = new TentacleAvatar(this);
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
