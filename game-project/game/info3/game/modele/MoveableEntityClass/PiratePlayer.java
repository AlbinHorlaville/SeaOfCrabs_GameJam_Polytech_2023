package info3.game.modele.MoveableEntityClass;

import java.util.ArrayList;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.Controller;
import info3.game.modele.Entity;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.Weapon;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.Avatar;

public class PiratePlayer extends Player {

	// Default stat (at Spawn)
	private static final int DEFAULT_PIRATEPLAYER_LIFE_POINT = 100;

	private static final int DEFAULT_PIRATEPLAYER_DAMAGE = 25;

	private static final int DEFAULT_PIRATEPLAYER_MAX_LIFE_POINT = 100;

	private static final int DEFAULT_PIRATEPLAYER_RANGE = 1;

	private static final int DEFAULT_PIRATEPLAYER_SPEED = 1;

	private static final int DEFAULT_PIRATEPLAYER_ATTACKSPEED = 1;

	// Default coeff (at spawn)
	public static final int DEFAULT_MAX_PLAYERS_LIFE_COEFF = 1;

	private static final int DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF = 1;

	private static final int DEFAULT_PIRATEPLAYER_SPEED_COEFF = 1;

	private static final int DEFAULT_PIRATEPLAYER_DAMAGE_COEFF = 1;

	private static final int DEFAULT_PIRATEPLAYER_RANGE_COEFF = 1;

//	// ACTUAL PIRATE STAT
//	private static int ACTUAL_PIRATEPLAYER_LIFE_POINT = 100;
//	
//	private static int ACTUAL_MAX_PLAYERS_LIFE = 100;
//	
//	private static int ACTUAL_PIRATEPLAYER_ATTACKSPEED = 1;
//	
//	private static int ACTUAL_PIRATEPLAYER_SPEED = 1;
//
//	private static int ACTUAL_PIRATEPLAYER_DAMAGE = 1;
//	
//	private static int ACTUAL_PIRATEPLAYER_RANGE = 1;

	// player caracteristics
	protected static int m_attackSpeed;
	protected static int m_speed;
	protected static int m_range;
	public static int m_maxHealthPoints;
	protected static int m_healthPoints;

	// Coeff variables
	protected static float m_attackspeedCoeff;
	protected static float m_speedCoeff;
	protected static float m_damageCoeff;
	protected static float m_rangeCoeff;
	protected static float m_maxHealthCoeff;

	public Weapon weapon;
	public boolean invincible;
	public int timerInvicibleMili;
	public int timerInvicibleSec;

	public boolean reloading;
	public int timerReloadingMili;
	public int timerReloadingSec;

	public int reloadTimeSec;
	public int reloadTimeMili;

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		// weapon.setPlayer(this);
	}

	public PiratePlayer(GameEntity entity) {
		super(DEFAULT_PIRATEPLAYER_LIFE_POINT, DEFAULT_PIRATEPLAYER_DAMAGE, DEFAULT_MAX_PLAYERS_LIFE); // The good one
		this.automate = AutomateLoader.findAutomate(entity);
		this.current_state = automate.initial_state;
		facing = EnumDirection.N;
		this.invincible = false;

		this.m_attackspeedCoeff = DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF;
		this.m_speedCoeff = DEFAULT_PIRATEPLAYER_SPEED_COEFF;
		this.m_damageCoeff = DEFAULT_PIRATEPLAYER_DAMAGE_COEFF;
		this.m_rangeCoeff = DEFAULT_PIRATEPLAYER_RANGE_COEFF;
		this.m_maxHealthCoeff = DEFAULT_MAX_PLAYERS_LIFE_COEFF;

		this.m_maxHealthPoints = DEFAULT_MAX_PLAYERS_LIFE;
		this.m_healthPoints = DEFAULT_PIRATEPLAYER_LIFE_POINT;
		this.m_attackSpeed = DEFAULT_PIRATEPLAYER_ATTACKSPEED;
		this.m_range = DEFAULT_PIRATEPLAYER_RANGE;
		this.m_speed = DEFAULT_PIRATEPLAYER_SPEED;

		this.reloadTimeSec = 1;
		this.reloadTimeMili = 0;

	}

	@Override
	public void move(EnumDirection eval) {
		if (eval == EnumDirection.F) { // CHANGEMENT PIRATE A BATEAU
			this.moveEntity(facing, (int) (this.m_speed * this.m_speedCoeff));

			if (GameModele.solo) {
				GameModele.entities.remove(this);
			} else {
				GameModele.entities.remove(GameModele.player1);
				GameModele.entities.remove(GameModele.player2);
			}

			GameModele.pirateBoat.setLocation(x, y);
			GameModele.pirateBoat.facing = this.facing;
			GameModele.entities.add(GameModele.pirateBoat);
			GameModele.onSea = !GameModele.onSea;
			GameModele.pirateBoat.moveEntity(facing, 150);
		} else {
			facing = eval;
			this.moveEntity(eval, (int) (this.m_speed * this.m_speedCoeff));
		}
	}

	public Tiles getTilesForCell(EnumDirection d, int tempX, int tempY) {
		switch (d) {
		case N:

			tempY += (int) (this.m_speed * this.m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case S:
			tempY -= (int) (this.m_speed * this.m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case E:
			tempX -= (int) (this.m_speed * this.m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case W:
			tempX += (int) (this.m_speed * this.m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case NE:
			tempX -= (int) (this.m_speed * this.m_speedCoeff);
			tempY += (int) (this.m_speed * this.m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case NW:
			tempX += (int) (this.m_speed * this.m_speedCoeff);
			tempY += (int) (this.m_speed * this.m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case SE:
			tempX -= (int) (this.m_speed * this.m_speedCoeff);
			tempY -= (int) (this.m_speed * this.m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case SW:
			tempX += (int) (this.m_speed * this.m_speedCoeff);
			tempY -= (int) (this.m_speed * this.m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		default:
			return null;
		}
	}

	@Override
	public boolean cell(EnumDirection d, EnumCategory c) {
		int tempX = this.getCenterX();
		int tempY = this.getCenterY();
		Tiles t;
		ArrayList<Entity> tempEntities = GameModele.entities;
		switch (c) {
		case O:
			t = getTilesForCell(d, tempX, tempY);
			if (t.isIsland()) {
				for (Entity e : tempEntities) {
					if (!(e instanceof PiratePlayer)) {
						if (e.x == x) {
							if (e.y <= this.y && e.y >= tempY) {
								return true;
							}
						}
					}
				}
				return false;
			}
			return true;
		case J:
			return getTilesForCell(facing, tempX, tempY).isWater();
		default:
			return false;
		}
	}

	@Override
	public void hit() {

		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		if (!reloading) {
			weapon.hit(this.m_rangeCoeff, this.m_damageCoeff);

			reloading = true;
			this.timerReloadingMili = timeMili;
			this.timerReloadingSec = timeSec;
		} else if (reloadingTimePassed(timerReloadingMili, timerReloadingSec, timeMili, timeSec)) {
			reloading = false;
		}
	}

	public boolean reloadingTimePassed(int debutReloadMili, int debutReloadSec, int actualMili, int actualSec) {
		if (debutReloadSec * 1000 + this.reloadTimeSec * 1000 + debutReloadMili + this.reloadTimeMili < actualSec * 1000
				+ actualMili) {
			return true;
		} else {
			return false;
		}
	}

	public int getCenterX() {
		return this.x + (2 * this.avatar.getWidth() / (Avatar.SCALE_IMG));
	}

	public int getCenterY() {
		return this.y + (this.avatar.getHeight() / Avatar.SCALE_IMG);
	}

	public float getDamageCoeff() {
		return this.m_damageCoeff;
	}

	public void addDamageCoeff(float f) {
		this.m_damageCoeff += f;
	}

	public float getSpeedCoeff() {
		return m_speedCoeff;
	}

	public void addSpeedCoeff(float f) {
		this.m_speedCoeff += f;
	}

	public float getAttackspeedCoeff() {
		return m_attackspeedCoeff;
	}

	public void addAttackpeedCoeff(float f) {
		this.m_attackspeedCoeff += f;
		
		if (this.m_attackSpeed * this.m_attackspeedCoeff > 1) {
			this.reloadTimeSec = 0;
			this.reloadTimeMili = 1000 - (int) (this.m_attackSpeed * this.m_attackspeedCoeff * 20);
		}
	}

	public float getRangeCoeff() {
		return m_rangeCoeff;
	}

	public void addRangeCoeff(float f) {
		this.m_rangeCoeff += f;
	}

	public float getMaxLifePointsCoeff() {
		return m_maxHealthCoeff;
	}

	public void addMaxLifePointsCoeff(float f) {
		this.m_maxHealthCoeff += f;
		this.setNewMaxHealthPoints();
	}

	public void setHealthPoints(int m_healthPoints) {
		this.m_healthPoints = m_healthPoints;
	}

	public int getMaxHealthPoints() {
		return PiratePlayer.m_maxHealthPoints;
	}

	public int getHealthPoints() {
		return PiratePlayer.m_healthPoints;
	}

	public void setNewMaxHealthPoints() {
		this.m_maxHealthPoints = (int) (this.m_maxHealthPoints * this.m_maxHealthCoeff);
	}

	public void takeDamage(int damage) {
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		if (!invincible) {
			this.m_healthPoints -= damage;
			if (this.m_healthPoints <= 0) {
				this.die();
			}
			invincible = true;
			this.timerInvicibleMili = timeMili;
			this.timerInvicibleSec = timeSec;
		} else if (timerInvicibleMili <= timeMili && timerInvicibleSec + 1 <= timeSec) {
			invincible = false;
		}
	}

	@Override
	public void die() {
		Controller.getGameModele().gameover();
	}

	public static void resetPiratePlayer() {
		// RESET LIFE
		PiratePlayer.m_maxHealthCoeff = PiratePlayer.DEFAULT_MAX_PLAYERS_LIFE;
		PiratePlayer.m_healthPoints = PiratePlayer.DEFAULT_PIRATEPLAYER_LIFE_POINT;

		// RESET STAT
		PiratePlayer.m_attackSpeed = DEFAULT_PIRATEPLAYER_ATTACKSPEED;
		PiratePlayer.m_damageCoeff = DEFAULT_PIRATEPLAYER_DAMAGE;
		PiratePlayer.m_range = DEFAULT_PIRATEPLAYER_RANGE;
		PiratePlayer.m_speed = DEFAULT_PIRATEPLAYER_SPEED;

		// TODO CAN ADD COEFF ?
	}
}
