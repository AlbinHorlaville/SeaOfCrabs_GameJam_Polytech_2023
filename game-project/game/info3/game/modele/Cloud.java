package info3.game.modele;

import automate.AutomateLoader;

public class Cloud extends Entity {

	private int size;
	private CloudCluster cluster;

	protected Cloud(int s, int x, int y) {
		this.size = s;
		this.x = x;
		this.y = y;
		this.automate = AutomateLoader.findAutomate("Philosopher");
		this.current_state = automate.initial_state;
	}

	protected void die() {
		this.cluster.getClouds().remove(this);
	}

	public int getSize() {
		return this.size;
	}
}
