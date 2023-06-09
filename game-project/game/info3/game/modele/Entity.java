package info3.game.modele;

import info3.game.vue.avatar.Avatar;

public abstract class Entity {

	  int x, y;
	  
	  Avatar avatar;
	  
	  public Entity() {
		// TODO Auto-generated constructor stub
	  }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	  
}
