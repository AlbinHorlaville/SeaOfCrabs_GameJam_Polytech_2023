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
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.RhumAvatar;

public class Rhum extends StillEntity {
	
	private MapSection m_mapSection;

	public Rhum(int x, int y, MapSection mapSection) {
		super(x, y);
		this.automate = AutomateLoader.findAutomate(GameEntity.Rhum);
		this.current_state = automate.initial_state;
		setAvatar(new RhumAvatar(this));
		this.m_mapSection = mapSection;
	}
	
	public boolean cell(EnumDirection d, EnumCategory c) {
		if(d == EnumDirection.H && c == EnumCategory.T) {
			
			Map map = GameModele.map;
			BoatPlayer boatPlayer = GameModele.pirateBoat;
			Tiles bonusTile = map.getTileUnderEntity(this.x, this.y);
			Tiles boatTile = map.getTileUnderEntity(boatPlayer.x,boatPlayer.y );
			int X = bonusTile.getTileX();
			int Y = bonusTile.getTileY();
			Tiles[][] tiles = this.m_mapSection.getTiles();	
			
			
			
			for(int i = -1; i < 2; i++)
				for(int j = -1; j < 2; j++)
					if(X > 0 && Y > 0) {
						if(boatTile.equals(tiles[Y+j][X+i])) {
							return true;
						}
					}
			
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
