package info3.game.modele;

import info3.game.vue.avatar.Avatar;

public abstract class Entity {

	protected int x, y;

	Avatar avatar;

	// Automaton Variable TODO 
	// State Variable TODO 


	public Entity() {
		// TODO Auto-generated constructor stub
	}

	public void initAvatar(Avatar avatar) {
		this.avatar = avatar;
		avatar.setEntity(this);
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
