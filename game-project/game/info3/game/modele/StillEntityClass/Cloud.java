package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntity;
import info3.game.modele.MoveableEntityClass.BoatPlayer;
import info3.game.modele.map.Map;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.CloudAvatar;

public class Cloud extends StillEntity {

	private int size;
	private CloudCluster cluster;

	protected Cloud(int s, int x, int y, CloudCluster cluster) {
		this.size = s;
		this.x = x;
		this.y = y;
		this.cluster = cluster;
		this.avatar = new CloudAvatar(this, y);
		this.automate = AutomateLoader.findAutomate(GameEntity.Cloud);
		this.current_state = automate.initial_state;
	}

	public void step() {
		current_state = this.automate.step(this, current_state);
		if (current_state.isDead()) {
			die();
		}
	}

	public void die() {
		this.cluster.getClouds().remove(this);
	}

	public int getSize() {
		return this.size;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		setX(getX() + 1);
	}

	@Override
	public boolean cell(EnumDirection d, EnumCategory c) {
		if (d == EnumDirection.H && c == EnumCategory.T) {

			Map map = GameModele.map;
			BoatPlayer boatPlayer = GameModele.pirateBoat;
			Tiles bonusTile = map.getTileUnderEntity(this.x, this.y);
			Tiles boatTile = map.getTileUnderEntity(boatPlayer.x, boatPlayer.y);
			int X = bonusTile.getTileX();
			int Y = bonusTile.getTileY();
			Tiles[][] tiles = this.cluster.getMapSection().getTiles();

			for (int i = -1; i < 2; i++)
				for (int j = -1; j < 2; j++)
					if (X > 0 && Y > 0)
						if (boatTile.equals(tiles[Y + j][X + i])) {
							return true;
						}

		}
		return false;
	}
}
