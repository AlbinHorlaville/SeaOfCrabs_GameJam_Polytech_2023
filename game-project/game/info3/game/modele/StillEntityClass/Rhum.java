package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntity;
import info3.game.modele.MoveableEntityClass.BoatPlayer;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.map.Map;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.RhumAvatar;

public class Rhum extends StillEntity {

	public Rhum(int x, int y) {
		super(x, y);
		this.automate = AutomateLoader.findAutomate(GameEntity.Rhum);
		this.current_state = automate.initial_state;
		setAvatar(new RhumAvatar(this));
	}
	
	public boolean cell(EnumDirection d, EnumCategory c) {
		if(d == EnumDirection.H && c == EnumCategory.T) {
			
			Map map = GameModele.map;
			BoatPlayer boatPlayer = GameModele.pirateBoat;
			Tiles myTile = map.getTileUnderEntity(this.x, this.y);
			
			if(myTile.equals(map.getTileUnderEntity(boatPlayer.x,boatPlayer.y )))
				return true;
			
		}
		return false;
	}

	public void power() { // HEAL FULL LIFE
		GameModele.player1.setHealthPoints(GameModele.player1.getMaxHealthPoints());
	}

	@Override
	public void move() {
		setX(getX()+1);
	}

}
