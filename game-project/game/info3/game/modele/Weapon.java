package info3.game.modele;

import java.util.ArrayList;

import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.vue.avatar.Avatar;

public abstract class Weapon{
	
	public static String Sword = "Sword";
	public static String Scythe = "Scythe";
	public static String Dagger = "Dagger";
	
	private PiratePlayer player;
	protected String name;
	protected int damage;
	protected int range;
	protected double alpha;
	
	public Weapon(String name, int damage, int range, double alpha) {
		this.name = name;
		this.damage = damage;
		this.range = range;
		this.alpha = alpha;
	}
	
	public void hit() {
		int tempX = player.getCenterX();
		int tempY = player.getCenterY();
		int width = player.avatar.getWidth()/ Avatar.SCALE_IMG;
		int height = 2 * player.avatar.getHeight() / Avatar.SCALE_IMG;
		ArrayList<Entity> tempEntities = (ArrayList) GameModele.entities.clone();
		for(Entity e : tempEntities) {
			if(e != player) {
				switch(player.facing) {
				case N:
					//System.out.println(GameModele.map.getTileUnderEntity(tempX, tempY + range + height).getType().toString());
					if(e.getCenterX() <= tempX + width && e.getCenterX() >= tempX - width) {
						if(e.getCenterY() >= tempY && e.getCenterY() <= tempY + range) {
							//System.out.println("touché N " + e.getClass());
							if(e instanceof MoveableEntity) {
								((MoveableEntity)e).takeDamage(damage);
							}
						}
					}
					break;
				case S:
					//System.out.println(GameModele.map.getTileUnderEntity(tempX, tempY - range).getType().toString());
					if(e.getCenterX() <= tempX + width && e.getCenterX() >= tempX - width) {
						if(e.getCenterY() <= tempY && e.getCenterY() >= tempY - range) {
							//System.out.println("touché S " + e.getClass());
							if(e instanceof MoveableEntity) {
								((MoveableEntity)e).takeDamage(damage);
							}
						}
					}
					break;
				case E:
					//System.out.println(GameModele.map.getTileUnderEntity(tempX, tempY + height).getType().toString());
					if(e.getCenterY() >= tempY && e.getCenterY() <= tempY + height) {
						if(e.getCenterX() <= tempX && e.getCenterX() >= tempX - range) {
							//System.out.println("touché E " + e.getClass());
							if(e instanceof MoveableEntity) {
								((MoveableEntity)e).takeDamage(damage);
							}
						}
					}
					break;
				case W:
					//System.out.println(GameModele.map.getTileUnderEntity(tempX + range + width, tempY).getType().toString());
					if(e.getCenterY() >= tempY && e.getCenterY() <= tempY + height)
						if(e.getCenterX() >= tempX && e.getCenterX() <= tempX + range) {
							//System.out.println("touché W " + e.getClass());
							if(e instanceof MoveableEntity) {
								((MoveableEntity)e).takeDamage(damage);
							}
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
	
}
