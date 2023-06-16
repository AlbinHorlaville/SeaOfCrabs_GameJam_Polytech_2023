package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import info3.game.modele.StillEntity;

public class Cloud extends StillEntity {

	private int size;
	private CloudCluster cluster;

	protected Cloud(int s, int x, int y) {
		this.size = s;
		this.x = x;
		this.y = y;
		this.automate = AutomateLoader.findAutomate("Philosopher");
		this.current_state = automate.initial_state;
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
		
	}
}
