package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Rhum;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.ShipAvatar;

public class Ship extends Ennemy {

	public final static int DEFAULT_HEALTH_POINTS = 100;
	public final static int DEFAULT_DAMAGE = 25;

	private int timerAttackMili;
	private int timerAttackSec;
	private int timerAttackMin;
	private boolean reloading;

	private boolean stunned;
	private int timerStunMili;
	private int timerStunSec;
	private int timerStunMin;

	private int sectionHeight;

	public Ship(MapSection mapSection) {
		super(DEFAULT_HEALTH_POINTS, DEFAULT_DAMAGE);
		this.avatar = new ShipAvatar(this);
		this.automate = AutomateLoader.findAutomate(GameEntity.Ship);
		this.current_state = automate.initial_state;
		GameModele.entities.add(this);
		this.reloading = false;

		this.sectionHeight = GameModele.map.getSectionHeight();
	}

	public void move() {
		if ((int) tick(this.timeElapsed) % 3 == 0) {
			// Nearer pirate to me
			BoatPlayer closestPlayer = this.closestBoatToMe();

			// Get next coordinate
			int nextX = this.x;
			int nextY = this.y;

			if (this.x > closestPlayer.x) {// Aller à droite
				if (GameModele.map.getTileUnderEntity(nextX, nextY).getTileX() < GameModele.map.getSectionWidth()
						- 20) {
					nextX--;
				}

			} else if (this.x < closestPlayer.x) {
				if (GameModele.map.getTileUnderEntity(nextX, nextY).getTileX() > 20) {
					nextX++;
				}
			}
			if (this.y > closestPlayer.y) {
				if (GameModele.map.getTileUnderEntity(nextX, nextY).getTileX() < GameModele.map.getSectionWidth()
						- 20) {
					nextY--;
				}
			} else if (this.y < closestPlayer.y) {
				if (GameModele.map.getTileUnderEntity(nextX, nextY).getTileX() > 20) {
					nextY++;
				}
			}

//			int nextX = this.x - ((this.x - closestPlayer.x) * 1);
//			int nextY = this.y - ((this.y - closestPlayer.y) * 1);

			// Can the the tile be moved on buy a crab
			Tiles tile = GameModele.map.getTileUnderEntity(nextX, nextY);
			if (tile.isWater()) {
				this.x = nextX;
				this.y = nextY;
			}
		}
	}

	private boolean stunTimePassed(int debutStunMili, int debutStunSec, int actualMili, int actualSec) {
		if (debutStunSec * 1000 + 5000 + debutStunMili < actualSec * 1000 + actualMili) {
			return true;
		} else {
			return false;
		}
	}

	public boolean gotPower() {
		if (this.stunned) {

			int timeMili = GameModele.timer.getMiliSecondes();
			int timeSec = GameModele.timer.getSecondes();
			int timeMin = GameModele.timer.getMinutes();
			if (stunTimePassed(this.timerStunMili, this.timerStunSec, timeMili, timeSec)
					|| this.timerStunMin < timeMin) {
				this.stunned = false;
				this.automate = AutomateLoader.findAutomate(GameEntity.Ship);
				this.current_state = automate.initial_state;
			}
		}

		return this.m_healthPoints > 0;
	}

	private boolean reloadingTimePassed(int debutReloadingMili, int debutReloadingSec, int actualMili, int actualSec) {
		if (debutReloadingSec * 1000 + 1000 + debutReloadingMili < actualSec * 1000 + actualMili) {
			return true;
		} else {
			return false;
		}
	}

	public void hit() {
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		int timeMin = GameModele.timer.getMinutes();

		if (!this.reloading) {
			GameModele.map.setDamaging(GameModele.pirateBoat.getCenterX(), GameModele.pirateBoat.getCenterY());
			this.reloading = true;
			this.timerAttackMili = timeMili;
			this.timerAttackSec = timeSec;
			this.timerAttackMin = timeMin;
		} else if (reloadingTimePassed(this.timerAttackMili, this.timerAttackSec, timeMili, timeSec)
				|| this.timerAttackMin < timeMin) {
			this.reloading = false;
		}
	}

	@Override
	public void die() {
		Rhum rhum = new Rhum(this.x, this.y,
				GameModele.map.getMap()[GameModele.map.getSectionOfEntity(this.x, this.y)]);
		GameModele.entities.add(rhum);
		GameModele.entities.remove(this);
		GameModele.map.getMiniMap().removeEnnemi(this);
		GameModele.seaEnnemie.remove(this);

	}

	@Override
	public boolean closest(EnumCategory cat) {

		if (GameModele.onSea) {

			Tiles tileUnderPlayer = GameModele.map.getTileUnderEntity(GameModele.getCurrentPlayerX(),
					GameModele.getCurrentPlayerY());
			Tiles tileUnderThis = GameModele.map.getTileUnderEntity(this.x, this.y);

			int playerTileY = GameModele.currentSection * this.sectionHeight + this.sectionHeight
					- tileUnderPlayer.getTileY();

			int EntityTileY = GameModele.map.getSectionOfEntity(this.x, this.y) * this.sectionHeight
					+ this.sectionHeight - tileUnderThis.getTileY();

			// Only for optimisation
			int distanceY = Math.abs(playerTileY - EntityTileY);
			int distanceX = Math.abs(tileUnderPlayer.getTileX() - tileUnderThis.getTileX());

			switch (cat) {
			case A:
				if (distanceY > 5 || distanceX > 5) {
					return false;
				} else {
					return (Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) < 5);
				}

			case V:
				if (distanceY > 40 || distanceX > 40) {
					return false;
				} else {
					return (Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) < 40);
				}
			default:
				return false;
			}
		}

		return false;

	}

	public void stunt() {
		this.automate = AutomateLoader.findAutomate(GameEntity.Stunned);
		this.current_state = automate.initial_state;

		this.stunned = true;

		this.timerStunMili = GameModele.timer.getMiliSecondes();
		this.timerStunSec = GameModele.timer.getSecondes();
		this.timerStunMin = GameModele.timer.getMinutes();
	}

	private BoatPlayer closestBoatToMe() {

		// A jouter pour le mode 2 joueurs
//		double distanceP1 = Math.sqrt(Math.pow(this.x - GameModele.player1.x,2) + Math.pow(this.y - GameModele.player1.y,2));
//		double distanceP2 = Math.sqrt(Math.pow(this.x - GameModele.player2.x,2) + Math.pow(this.y - GameModele.player2.y,2));
//		
//		if(distanceP1 < distanceP2) {
//			return GameModele.player1;
//		}
//		return GameModele.player2;

		return GameModele.pirateBoat;
	}
}
