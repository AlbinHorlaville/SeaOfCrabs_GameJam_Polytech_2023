package info3.game.modele;

import automate.Automate;
import automate.EnumCategory;
import automate.EnumDirection;
import automate.Parameter;
import automate.State;
import info3.game.vue.avatar.Avatar;

public abstract class Entity {

	public int x;
	public int y;
	protected int r; // Radius for draw hitbox
	protected boolean valid;
	protected long timeElapsed;

	protected Avatar avatar;
	protected Automate automate;
	protected State current_state;

	public Entity(int hitbox) {
		this.r = hitbox;
	}
	
	public Entity() {
	}
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Entity(int x, int y, int hitbox) {
		this.x = x;
		this.y = y;
		this.r = hitbox;
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
	
	public int getCenterX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public int getCenterY() {
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
		current_state = this.automate.step(
				this,
				current_state);
	}
	
	public void die() {
		GameModele.entities.remove(this);
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
		//if (checkCollision(eval, speed))
		moveEntity(eval,1);
	}
	
public void moveEntity(EnumDirection direction, int speed) {
		
		if(!GameModele.solo && !GameModele.onSea) {
			int centerX = (GameModele.player1.getCenterX() + GameModele.player2.getCenterX())/2;
			int centerY = (GameModele.player1.getCenterY() + GameModele.player2.getCenterY())/2;
				switch (direction) {
				case E:
				case SE:
				case NE:
					if (x-speed > centerX - 1024/2 + GameModele.player1.getAvatar().getWidth()/4)
						x -= speed;
					break;
				case W:
				case SW:
				case NW:
					if (x+speed < centerX + 1024/2 - GameModele.player1.getAvatar().getWidth()/4)
						x += speed;
					break;
				default:
					break;
				}
			switch (direction) {
			case S:
			case SE:
			case SW:
				if (y-speed > centerY - 768/2 + GameModele.player1.getAvatar().getHeight()/2)
					y -= speed;
				break;
			case NE:
			case N:
			case NW:
				if (y+speed < centerY + 768/2 - GameModele.player1.getAvatar().getHeight()/2)
					y += speed;
				break;
			default:
				break;
			}
		}else {
			
			
			
			switch (direction) {
			case E:
			case SE:
			case NE:
					x -= speed;
				break;
			case W:
			case SW:
			case NW:
					x += speed;
				break;
			default:
				break;
			}
		switch (direction) {
		case S:
		case SE:
		case SW:
				y -= speed;
			break;
		case NE:
		case N:
		case NW:
				y += speed;
			break;
		default:
			break;
		}
			
			
		}
		
		
		
	}
	
	public long tick(long elapsed) {
		this.timeElapsed += elapsed;
		return timeElapsed;
	}
	/*
	 * D�placement de l'entit� en coordon�e relatif
	 * L'impl�mentation initial est pour le d�placement du joueur sur terre
	 */
	public void move(EnumCategory eval) {
		
	}
	
	public void move() {
		
	}
	
	public boolean closest(){
		System.out.println("Cette entité n'a pas de fonction closest()");
		return false;
	}
	
	public boolean gotStuff(){
		System.out.println("Cette entité n'a pas de fonction gotStuff()");
		return false;
	}
	
	public boolean cell(EnumDirection d, EnumCategory c) throws Exception {
		System.out.println("Cette entité n'a pas de fonction cell()");
		return false;
	}
	
	public void hit(EnumDirection d, EnumCategory c) {
		System.out.println("Cette entité n'a pas de fonction hit()");
	}
	
	public void hit() {
		System.out.println("Cette entité n'a pas de fonction hit()");
	}
	
	
	public boolean gotPower() {
		System.out.println("Cette entité n'a pas de fonction gotPower()");
		return false;
	}
	
	public void egg() {
		System.out.println("Cette entité n'a pas de fonction egg()");
	}

	public boolean closest(EnumCategory cat) {
		// TODO Auto-generated method stub
		return false;
	}

	public void power() {
		// TODO Auto-generated method stub
		
	}
	
	public void takeDamage(int damage) {
		System.out.println("Cette entité n'a pas de fonction takeDamage()");
	}
	
	public void get(EnumCategory category) {
	}
	
	
	public void get() {
	}

}
