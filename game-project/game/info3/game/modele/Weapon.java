package info3.game.modele;

import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.vue.avatar.Avatar;

public abstract class Weapon extends Entity{
	
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
	
	@Override
	public void move() {
		this.x = player.x;
		this.y = player.y;
	}
	
	public int getXtest() {
		return player.x + (2* player.avatar.getWidth() /Avatar.SCALE_IMG);
	}
	
	public int getYtest() {
		return player.y + ( player.avatar.getHeight() / Avatar.SCALE_IMG);
	}
	
	public int getWidth() {
		return player.avatar.getWidth()/ Avatar.SCALE_IMG;
	}
	
	public int getHeight() {
		return player.avatar.getHeight() / Avatar.SCALE_IMG;
	}
	
	public void hit() {
		int tempX = player.x + (2* player.avatar.getWidth() /Avatar.SCALE_IMG);
		int tempY = player.y + ( player.avatar.getHeight() / Avatar.SCALE_IMG);
		int width = player.avatar.getWidth()/ Avatar.SCALE_IMG;
		int height = player.avatar.getHeight() / Avatar.SCALE_IMG;
		for(Entity e : GameModele.entities) {
			if(e != this && e != player) {
				switch(player.facing) {
				case N:
					//System.out.println(GameModele.map.getTileUnderEntity(tempX, tempY + range + height).getType().toString());
					if(e.x <= tempX + width && e.x >= tempX - width) {
						if(e.y >= tempY && e.y <= tempY + range + height) {
							//System.out.println("touché N " + e.getClass());
						}
					}
					break;
				case S:
					//System.out.println(GameModele.map.getTileUnderEntity(tempX, tempY - range).getType().toString());
					if(e.x <= tempX + width && e.x >= tempX - width) {
						if(e.y <= tempY && e.y >= y - range) {
							//System.out.println("touché S " + e.getClass());
						}
					}
					break;
				case E:
					//System.out.println(GameModele.map.getTileUnderEntity(tempX - range - width, tempY).getType().toString());
					if(e.y >= tempY && e.y <= tempY + height)
						if(e.x <= tempX && e.x >= tempX - range - width) {
							//System.out.println("touché E " + e.getClass());
						}
					break;
				case W:
					//System.out.println(GameModele.map.getTileUnderEntity(tempX + range + width, tempY).getType().toString());
					if(e.y >= tempY && e.y <= tempY + height)
						if(e.x >= tempX && e.x <= tempX + range + width) {
							//System.out.println("touché W " + e.getClass());
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
