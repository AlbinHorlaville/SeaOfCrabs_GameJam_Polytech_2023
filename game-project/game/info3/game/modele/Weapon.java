package info3.game.modele;

import java.util.ArrayList;

import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.StillEntityClass.Tree;
import info3.game.vue.GameView;
import info3.game.vue.avatar.Avatar;

public abstract class Weapon extends MoveableEntity {

	public static String Sword = "Sword";
	public static String Scythe = "Scythe";
	public static String Dagger = "Dagger";

	public PiratePlayer player;
	protected String name;
	protected int damage;
	public int range;
	protected double alpha;
	public int tempX;
	public int tempY;
	public int width;
	public int height;

	private boolean attacking; // Sert à l'animation de l'arme

	public Weapon(String name, int damage, int range, double alpha) {
		super(0, 0, 0);
		this.name = name;
		this.damage = damage;
		this.range = range;
		this.alpha = alpha;
	}

	/*
	 * public void hit() { attacking = true; tempX = player.getCenterX(); tempY =
	 * player.getCenterY(); //width = player.avatar.getWidth()/ Avatar.SCALE_IMG;
	 * //height = 2 * player.avatar.getHeight() / Avatar.SCALE_IMG; width =
	 * player.avatar.getWidth(); height = player.avatar.getHeight();
	 * ArrayList<Entity> tempEntities = (ArrayList) GameModele.entities.clone();
	 * for(Entity e : tempEntities) { if(e != player) { switch(player.facing) { case
	 * N: //System.out.println(GameModele.map.getTileUnderEntity(tempX, tempY +
	 * range + height).getType().toString()); if(e.getCenterX() <= tempX + width &&
	 * e.getCenterX() >= tempX - width) { if(e.getCenterY() >= tempY &&
	 * e.getCenterY() <= tempY + range) { //System.out.println("touché N " +
	 * e.getClass()); if(e instanceof MoveableEntity) {
	 * ((MoveableEntity)e).takeDamage(damage); } } } break; case S:
	 * //System.out.println(GameModele.map.getTileUnderEntity(tempX, tempY -
	 * range).getType().toString()); if(e.getCenterX() <= tempX + width &&
	 * e.getCenterX() >= tempX - width) { if(e.getCenterY() <= tempY &&
	 * e.getCenterY() >= tempY - range) { //System.out.println("touché S " +
	 * e.getClass()); if(e instanceof MoveableEntity) {
	 * ((MoveableEntity)e).takeDamage(damage); } } } break; case E:
	 * //System.out.println(GameModele.map.getTileUnderEntity(tempX, tempY +
	 * height).getType().toString()); if(e.getCenterY() >= tempY && e.getCenterY()
	 * <= tempY + height) { if(e.getCenterX() <= tempX && e.getCenterX() >= tempX -
	 * range) { //System.out.println("touché E " + e.getClass()); if(e instanceof
	 * MoveableEntity) { ((MoveableEntity)e).takeDamage(damage); } } } break; case
	 * W: //System.out.println(GameModele.map.getTileUnderEntity(tempX + range +
	 * width, tempY).getType().toString()); if(e.getCenterY() >= tempY &&
	 * e.getCenterY() <= tempY + height) if(e.getCenterX() >= tempX &&
	 * e.getCenterX() <= tempX + range) { //System.out.println("touché W " +
	 * e.getClass()); if(e instanceof MoveableEntity) {
	 * ((MoveableEntity)e).takeDamage(damage); } } break; }
	 * 
	 * }
	 * 
	 * } }
	 */

	public void hit(float rangeCoeff, float damageCoeff) {
		attacking = true;
		tempX = player.getX()+player.getAvatar().getWidth()/2;
		tempY = player.getY()+player.getAvatar().getHeight()/2;	
		width = player.avatar.getWidth() / 2;//player.avatar.getWidth();
		height = player.avatar.getHeight() / 2	;//player.avatar.getHeight();
		ArrayList<Entity> tempEntities = (ArrayList) GameModele.entities.clone();
		for (Entity e : tempEntities) {
			if (e != player) {
				int eCenterX = e.getCenterX();
				int eCenterY = e.getCenterY();
				if (e instanceof Tree) {
					eCenterY += 50;
				}
				switch (player.facing) {
				case N:
					//System.out.println("North "
					//		+ GameModele.map.getTileUnderEntity(tempX, tempY + range).getType().toString());
					if (eCenterX - e.r <= tempX + width / 2 && eCenterX + e.r >= tempX - width / 2) {
						if (eCenterY + e.r >= tempY && eCenterY - e.r <= tempY + (int)(range*rangeCoeff)) {
							// System.out.println("touché N " + e.getClass());
							e.takeDamage((int)(damage * damageCoeff));
						}
					}
					break;
				case S:
					//System.out.println(
					//		"South " + GameModele.map.getTileUnderEntity(tempX, tempY - range).getType().toString());
					if (eCenterX - e.r <= tempX + width / 2 && eCenterX + e.r >= tempX - width / 2) {
						if (eCenterY - e.r <= tempY && eCenterY + e.r >= tempY - (int)(range*rangeCoeff)) {
							// System.out.println("touché S " + e.getClass());
							e.takeDamage((int)(damage * damageCoeff));
						}
					}
					break;
				case E:
					//System.out.println("East "
					//		+ GameModele.map.getTileUnderEntity(tempX - range, tempY).getType().toString());
					if (eCenterY + e.r >= tempY + player.getAvatar().getWidth()/4 - height / 2 && eCenterY - e.r <= tempY - player.getAvatar().getWidth()/4 + height / 2) {
						if (eCenterX - e.r <= tempX && eCenterX + e.r >= tempX - (int)(range*rangeCoeff)) {
							// System.out.println("touché E " + e.getClass());
							e.takeDamage((int)(damage * damageCoeff));
						}
					}
					break;
				case W:
					//System.out.println("West "
					//		+ GameModele.map.getTileUnderEntity(tempX + range, tempY).getType().toString());
					if (eCenterY + e.r >= tempY + player.getAvatar().getWidth()/4 - height / 2 && eCenterY - e.r <= tempY - player.getAvatar().getWidth()/4 + height / 2)
						if (eCenterX + e.r >= tempX && eCenterX - e.r <= tempX + (int)(range*rangeCoeff)) {
							// System.out.println("touché W " + e.getClass());
							e.takeDamage((int)(damage * damageCoeff));
						}
					break;
				}

			}

		}
	}

	public void setPlayer(PiratePlayer player) {
		this.player = player;
	}

	public String getName() {
		return name;
	}

	public void setAttacking(boolean cond) {
		this.attacking = cond;
	}

	public boolean getAttacking() {
		return this.attacking;
	}
}
