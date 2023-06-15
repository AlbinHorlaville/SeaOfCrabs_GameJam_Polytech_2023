package info3.game.modele;

import automate.Automate;
import automate.EnumCategory;
import automate.EnumDirection;
import automate.State;
import info3.game.vue.avatar.Avatar;

public abstract class Entity {

	protected int x, y;
	protected int r; // Radius for draw hitbox
	protected boolean valid;

	Avatar avatar;

	Automate automate;
	State current_state;

	public Entity() {
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
	
	public boolean checkCollision(EnumDirection direction, int speed) {
		int X = x;
		int Y = y;
		switch (direction) {
		case W:
			X += speed;
			break;
		case E:
			X -= speed;
			break;
		case N:
			Y += speed;
			break;
		case S:
			Y -= speed;
			break;
		case SE:
			Y -= speed;
			X -= speed;
			break;
		case NE:
			Y += speed;
			X -= speed;
			break;
		case SW:
			Y -= speed;
			X += speed;
			break;
		case NW:
			Y += speed;
			X += speed;
			break;
		default:
			break;
		}
		for (Entity e :GameModele.entities) {
			// Calcul de la distance entre les hitboxs de cette entity et des autres
			int distanceBetweenIandE = (int) java.lang.Math.sqrt(java.lang.Math.pow(X-e.x, 2)+java.lang.Math.pow(Y-e.y, 2)) - r - e.r;
			if (distanceBetweenIandE<0)
				System.out.println("COLLISION");
				return false;
		}
		return true;
	}
	/*
	 * D�placement de l'entit� en coordon�e absolue
	 * L'impl�mentation initial est pour le d�placement du joueur sur terre
	 */
	public void move(EnumDirection eval) {
		int speed = 1;
		if (checkCollision(eval, speed))
			moveEntity(eval,speed);
	}
	
	protected void moveEntity(EnumDirection direction, int speed) {
		switch (direction) {
		case W:
			x += speed;
			break;
		case E:
			x -= speed;
			break;
		case N:
			y += speed;
			break;
		case S:
			y -= speed;
			break;
		case SE:
			y -= speed;
			x -= speed;
			break;
		case NE:
			y += speed;
			x -= speed;
			break;
		case SW:
			y -= speed;
			x += speed;
			break;
		case NW:
			y += speed;
			x += speed;
			break;
		default:
			break;
		}
	}
	
	/*
	 * D�placement de l'entit� en coordon�e relatif
	 * L'impl�mentation initial est pour le d�placement du joueur sur terre
	 */
	public void move(EnumCategory eval) {
		
	}

}
