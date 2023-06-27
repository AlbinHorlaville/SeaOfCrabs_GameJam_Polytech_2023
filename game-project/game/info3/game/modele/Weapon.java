package info3.game.modele;

import java.util.ArrayList;

import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.StillEntityClass.Tree;
import info3.game.vue.avatar.SwordAvatar;

public abstract class Weapon extends MoveableEntity {

	public static String Sword = "Sword";
	public static String Scythe = "Scythe";
	public static String Dagger = "Dagger";

	public PiratePlayer player;
	public PiratePlayer playerRescu;
	protected String name;
	protected int damage;
	public int range;
	public int tempX;
	public int tempY;
	public int width;
	public int height;

	public Weapon(String name, int range) {
		super(0, 0, 0);
		this.name = name;
		this.range = range;
	}

	public void hit(float rangeCoeff, float damageCoeff) {
		SwordAvatar sword = (SwordAvatar) this.avatar;
		sword.setAttacking();
		if (player == null)
			player = playerRescu;
		tempX = player.getX() + player.getAvatar().getWidth() / 2;
		tempY = player.getY() + player.getAvatar().getHeight() / 2;
		width = player.avatar.getWidth() / 2;// player.avatar.getWidth();
		height = player.avatar.getHeight() / 2;// player.avatar.getHeight();
		ArrayList<Entity> tempEntities = (ArrayList) GameModele.entities.clone();
		for (Entity e : tempEntities) {
			if (e != player && e != GameModele.perroquet) {
				int eCenterX = e.getCenterX();
				int eCenterY = e.getCenterY();
				if (e instanceof Tree) {
					eCenterY += 50;
				}
				switch (player.facing) {
				case N:
					if (eCenterX - e.r <= tempX + width / 2 && eCenterX + e.r >= tempX - width / 2) {
						if (eCenterY + e.r >= tempY && eCenterY - e.r <= tempY + (int) (range * rangeCoeff)) {
							e.takeDamage((int) (damage * damageCoeff));
							// return;
						}
					}
					break;
				case S:
					if (eCenterX - e.r <= tempX + width / 2 && eCenterX + e.r >= tempX - width / 2) {
						if (eCenterY - e.r <= tempY && eCenterY + e.r >= tempY - (int) (range * rangeCoeff)) {
							e.takeDamage((int) (damage * damageCoeff));
							// return;
						}
					}
					break;
				case E:
					if (eCenterY + e.r >= tempY + player.getAvatar().getWidth() / 4 - height / 2
							&& eCenterY - e.r <= tempY - player.getAvatar().getWidth() / 4 + height / 2) {
						if (eCenterX - e.r <= tempX && eCenterX + e.r >= tempX - (int) (range * rangeCoeff)) {
							e.takeDamage((int) (damage * damageCoeff));
							// return;
						}
					}
					break;
				case W:
					if (eCenterY + e.r >= tempY + player.getAvatar().getWidth() / 4 - height / 2
							&& eCenterY - e.r <= tempY - player.getAvatar().getWidth() / 4 + height / 2)
						if (eCenterX + e.r >= tempX && eCenterX - e.r <= tempX + (int) (range * rangeCoeff)) {
							e.takeDamage((int) (damage * damageCoeff));
							// return;
						}
					break;
				}

			}

		}
	}

	public void setPlayer(PiratePlayer player) {
		this.player = player;
		this.playerRescu = player;
		this.damage = player.getDamage();
	}

	public String getName() {
		return name;
	}
}
