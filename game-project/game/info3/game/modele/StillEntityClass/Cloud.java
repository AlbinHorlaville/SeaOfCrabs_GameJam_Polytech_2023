package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntity;
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
		setX(getX()+1);
	}
	
	@Override
	public boolean cell(EnumDirection dir, EnumCategory cat) {
		Tiles tile_cloud = GameModele.map.getTileUnderEntity(getX(),getY());
		Tiles tile_player = GameModele.map.getTileUnderEntity(GameModele.getCurrentPlayerX(),GameModele.getCurrentPlayerY());
		return tile_cloud == tile_player;
	}
}
