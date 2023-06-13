package info3.game.modele;

import automate.Automate;
import automate.EnumCategory;
import automate.EnumDirection;
import automate.State;
import info3.game.vue.avatar.Avatar;

public abstract class Entity {

	protected int x, y;
	protected boolean valid;

	Avatar avatar;

	Automate automate;
	State current_state;

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

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setAutomate(Automate automate) {
		this.automate = automate;
	}
	
	public void step() {
		this.automate.step(this, current_state);
	}
	
	public abstract void move(EnumDirection eval);
	
	public abstract void move(EnumCategory eval);

}
