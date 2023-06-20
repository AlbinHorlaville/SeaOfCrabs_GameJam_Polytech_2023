package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.StillEntity;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.vue.avatar.RhumAvatar;

public class Rhum extends StillEntity {

	public Rhum(int x, int y) {
		super(x, y);
		this.automate = AutomateLoader.findAutomate(GameEntity.Rhum);
		this.current_state = automate.initial_state;
		setAvatar(new RhumAvatar(this));
	}

	public void power() { // HEAL FULL LIFE
		PiratePlayer.setACTUAL_PIRATEPLAYER_LIFE_POINT(PiratePlayer.getACTUAL_MAX_PLAYERS_LIFE());
	}

	@Override
	public void move() {
		setX(getX()+1);
	}

}
