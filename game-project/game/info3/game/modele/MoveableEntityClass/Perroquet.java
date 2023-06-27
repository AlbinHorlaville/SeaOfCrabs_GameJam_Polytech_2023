package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumDirection;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntity;
import info3.game.vue.avatar.PerroquetAvatar;

// Le perroquet suit le bateau et le joueur

public class Perroquet extends MoveableEntity {

	public Player closestPlayer;
	private int speed;

	public Perroquet(int m_healthPoints, int damage, int hitbox) {
		super(m_healthPoints, damage, hitbox);
		this.automate = AutomateLoader.findAutomate(GameEntity.Perroquet);
		this.current_state = automate.initial_state;
		this.setAvatar(new PerroquetAvatar(this));
		speed = 1;
	}

	public void takeDamage(int damage) {
		m_healthPoints -= damage;
	}

	private Player closestPirateToMe() {
		if (GameModele.onSea) {
			return GameModele.pirateBoat;
		} else {
			if (GameModele.solo) {
				return GameModele.player1;
			}
			double distanceP1 = Math
					.sqrt(Math.pow(this.x - GameModele.player1.x, 2) + Math.pow(this.y - GameModele.player1.y, 2));
			double distanceP2 = Math
					.sqrt(Math.pow(this.x - GameModele.player2.x, 2) + Math.pow(this.y - GameModele.player2.y, 2));

			if (distanceP1 < distanceP2) {
				return GameModele.player1;
			}
			return GameModele.player2;
		}
	}

	public boolean closest() {
		return GameModele.map.getSectionOfEntity(GameModele.player1.x, GameModele.player1.y) == GameModele.map
				.getSectionOfEntity(x, y);
	}

	public void move(EnumDirection dir) {
		int valueX = 0;
		int valueY = 0;
		closestPlayer = this.closestPirateToMe();
		int dist = GameModele.onSea ? 200 : 100;
		if (Math.sqrt(Math.pow(this.x - closestPlayer.x, 2) + Math.pow(this.y - closestPlayer.y, 2)) > dist
				&& this.x != closestPlayer.x && this.y != closestPlayer.y) {
			valueX = ((this.x >= closestPlayer.x) ? -speed : speed);
			valueY = ((this.y >= closestPlayer.y) ? -speed : speed);
		} else {
			if (this.x == closestPlayer.x && this.y < closestPlayer.y) {
				valueX -= speed;
			} else if ((this.x == closestPlayer.x && this.y > closestPlayer.y)) {
				valueX += speed;
			} else if (this.y == closestPlayer.y && this.x < closestPlayer.x) {
				valueY += speed;
			} else if ((this.y == closestPlayer.y && this.x > closestPlayer.x)) {
				valueY -= speed;
			} else {
				if (this.x < closestPlayer.x && this.y > closestPlayer.y) {
					valueY += speed;
				}
				if (this.x >= closestPlayer.x && this.y > closestPlayer.y) {
					valueX += speed;
				}
				if (this.x >= closestPlayer.x && this.y <= closestPlayer.y) {
					valueY -= speed;
				}
				if (this.x < closestPlayer.x && this.y <= closestPlayer.y) {
					valueX -= speed;
				}
			}
		}
		this.x += valueX;
		this.y += valueY;
	}

	public boolean gotPower() {
		return this.m_healthPoints > 0;
	}

}
