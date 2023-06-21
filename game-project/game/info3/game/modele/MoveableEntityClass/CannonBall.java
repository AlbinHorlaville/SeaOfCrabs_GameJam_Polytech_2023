package info3.game.modele.MoveableEntityClass;

import automate.EnumCategory;
import automate.EnumDirection;
import automate.StateDeath;
import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntity;
import info3.game.modele.map.Tiles;
import info3.game.vue.GameView;
import info3.game.vue.avatar.Avatar;

public abstract class CannonBall extends MoveableEntity {
	protected int endX;
	protected int endY;
	protected int startX;
	protected int startY;
	protected int damage;
	protected int range;
	protected int rateOfFire;
	protected int speedX;
	protected int speedY;
	protected int radiusX;
	protected int radiusY;
	public boolean fire;

	public CannonBall(int damage, int range, int rateOfFire) {
		super(0, 0, 0, 0);
		this.damage = damage;
		this.range = range;
		this.rateOfFire = rateOfFire;
		fire = false;
		startX = 500;
		startY = 500;
		GameModele.entities.add(this);
	}

	public void setPositions(int x, int y, int endX, int endY) {
		startX = x;
		startY = y;
		this.x = x;
		this.y = y;
		this.endX = endX;
		this.endY = endY;
		speedX = (endX - (GameView.screenWidth / 2)) / 50;
		speedY = (endY - (GameView.screenHeight / 2)) / 50;
	}

	public void fire() {
		fire = true;

	}

	@Override
	public boolean gotPower() {
		if (Math.abs(speedX) < 1 && Math.abs(speedY) < 1) {
			return false;
		} else {
			Tiles tile = GameModele.map.getTileUnderEntity(x, y);
			radiusX = Math.abs(startX - x);
			radiusY = Math.abs(startY - y);
			boolean b = fire && (radiusX < range) && (radiusY < range) && !tile.isIsland();
			b = tile.getTileX() > 8 && tile.getTileX() < GameModele.map.getSectionWidth() - 9;
			return b;
		}

	}

	@Override
	public boolean cell(EnumDirection d, EnumCategory c) {
		switch (c) {
		case A:
			for (Entity s : GameModele.entities) {
				if (s instanceof Ship && collide(this, x - speedX, y - speedY, s)) {
					System.out.println("hit");
					s.takeDamage(damage);
					return false;
				}
			}

		default:
			return true;

		}
	}

	public boolean collide(MoveableEntity m, int x, int y, Entity e) { // Code propre lvl 1
		int centerx = x - m.getAvatar().getWidth() / (2 * Avatar.SCALE_IMG);
		int centery = y - m.getAvatar().getHeight() / (2 * Avatar.SCALE_IMG);

		int w = e.getAvatar().getWidth();
		int h = e.getAvatar().getHeight();

		double rayon = Math.sqrt(Math.pow(w, 2) + Math.pow(h, 2)) / 2;
		double distance = Math.sqrt(Math.pow(e.getX() - e.getAvatar().getWidth() / (2 * Avatar.SCALE_IMG) - centerx, 2)
				+ Math.pow(e.getY() - e.getAvatar().getHeight() / (2 * Avatar.SCALE_IMG) - centery, 2));
		return distance <= rayon;
	}

	@Override
	public void moveEntity(EnumDirection direction, int speed) {
		if (fire) {
			x -= speedX;
			y -= speedY;
			startX += speedX;
			startY += speedY;
		}

	}

}
