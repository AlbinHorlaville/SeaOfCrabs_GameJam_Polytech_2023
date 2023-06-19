package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.StillEntity;
import info3.game.vue.avatar.SeaTreasureAvatar;

public class SeaTreasure extends StillEntity{
	
	
	public SeaTreasure(int x, int y) {
		super(x, y);
		this.automate = AutomateLoader.findAutomate(GameEntity.Philosopher);
		this.current_state = automate.initial_state;
		this.avatar = new SeaTreasureAvatar(this);
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		x += 1;
	}
	

}
