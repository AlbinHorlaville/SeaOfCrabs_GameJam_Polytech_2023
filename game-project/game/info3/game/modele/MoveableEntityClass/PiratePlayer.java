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
import info3.game.sound.SoundEffect;
import info3.game.sound.SoundTool;
import info3.game.vue.avatar.Avatar;

public class PiratePlayer extends Player {

	// Default stat (at Spawn)
	private static final int DEFAULT_PIRATEPLAYER_LIFE_POINT = 100;

	private static final int DEFAULT_PIRATEPLAYER_DAMAGE = 50;

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
	protected static int m_damage;
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
	public int timerInvicibleMin;

	public boolean reloading;
	public int timerReloadingMili;
	public int timerReloadingSec;
	public int timerReloadingMin;

	public int reloadTimeSec;
	public int reloadTimeMili;

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public PiratePlayer(GameEntity entity) {
		super(DEFAULT_PIRATEPLAYER_LIFE_POINT, DEFAULT_PIRATEPLAYER_DAMAGE, DEFAULT_MAX_PLAYERS_LIFE); // The good one
		automate = AutomateLoader.findAutomate(entity);
		current_state = automate.initial_state;
		facing = EnumDirection.N;
		this.invincible = false;

		m_attackspeedCoeff = DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF;
		m_speedCoeff = DEFAULT_PIRATEPLAYER_SPEED_COEFF;
		m_damageCoeff = DEFAULT_PIRATEPLAYER_DAMAGE_COEFF;
		m_rangeCoeff = DEFAULT_PIRATEPLAYER_RANGE_COEFF;
		m_maxHealthCoeff = DEFAULT_MAX_PLAYERS_LIFE_COEFF;

		m_damage = DEFAULT_PIRATEPLAYER_DAMAGE;
		m_maxHealthPoints = DEFAULT_MAX_PLAYERS_LIFE;
		m_healthPoints = DEFAULT_PIRATEPLAYER_LIFE_POINT;
		m_attackSpeed = DEFAULT_PIRATEPLAYER_ATTACKSPEED;
		m_range = DEFAULT_PIRATEPLAYER_RANGE;
		m_speed = DEFAULT_PIRATEPLAYER_SPEED;
		
		invincible = false;
		this.reloading = false;
		resetPiratePlayer();
		reloadTimeSec = 1;
		reloadTimeMili = 0;
	}

	@Override
	public void move(EnumDirection eval) {
		if (eval == EnumDirection.F) { // CHANGEMENT PIRATE A BATEAU
			moveEntity(facing, (int) (m_speed * m_speedCoeff));

			if (GameModele.solo) {
				GameModele.entities.remove(this);
			} else {
				GameModele.entities.remove(GameModele.player1);
				GameModele.entities.remove(GameModele.player2);
			}

			GameModele.pirateBoat.setLocation(x, y);
			GameModele.pirateBoat.facing = facing;
			GameModele.entities.add(GameModele.pirateBoat);
			GameModele.onSea = !GameModele.onSea;
			GameModele.pirateBoat.moveEntity(facing, 150);
		} else {
			facing = eval;
			moveEntity(eval, (int) (m_speed * m_speedCoeff));
		}
	}

	public Tiles getTilesForCell(EnumDirection d, int tempX, int tempY) {
		switch (d) {
		case N:

			tempY += (int) (m_speed * m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case S:
			tempY -= (int) (m_speed * m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case E:
			tempX -= (int) (m_speed * m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case W:
			tempX += (int) (m_speed * m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case NE:
			tempX -= (int) (m_speed * m_speedCoeff);
			tempY += (int) (m_speed * m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case NW:
			tempX += (int) (m_speed * m_speedCoeff);
			tempY += (int) (m_speed * m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case SE:
			tempX -= (int) (m_speed * m_speedCoeff);
			tempY -= (int) (m_speed * m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		case SW:
			tempX += (int) (m_speed * m_speedCoeff);
			tempY -= (int) (m_speed * m_speedCoeff);
			return GameModele.map.getTileUnderEntity(tempX, tempY);

		default:
			return null;
		}
	}

	@Override
	public boolean cell(EnumDirection d, EnumCategory c) {
		int tempX = getCenterX();
		int tempY = getCenterY();
		Tiles t;
		ArrayList<Entity> tempEntities = GameModele.entities;
		switch (c) {
		case O:
			t = getTilesForCell(d, tempX, tempY);
			if (t.isIsland()) {
				for (Entity e : tempEntities) {
					if (!(e instanceof PiratePlayer)) {
						if (e.x == x) {
							if (e.y <= y && e.y >= tempY) {
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

	public boolean reloadingTimePassed(int debutReloadMili, int debutReloadSec, int actualMili, int actualSec) {
		if (debutReloadSec * 1000 + reloadTimeSec * 1000 + debutReloadMili + reloadTimeMili < actualSec * 1000
				+ actualMili) {
			return true;
		} else {
			return false;
		}
	}

	public boolean invincibilityTimePassed(int debutInvincibilityMili, int debutInvincibilitySec, int actualMili,
			int actualSec) {
		if (debutInvincibilitySec * 1000 + 1000 + debutInvincibilityMili < actualSec * 1000 + actualMili) {
			return true;
		} else {
			return false;
		}
	}

	public int getCenterX() {
		return x + (2 * avatar.getWidth() / (Avatar.SCALE_IMG));
	}

	public int getCenterY() {
		return y + (avatar.getHeight() / Avatar.SCALE_IMG);
	}

	public float getDamageCoeff() {
		return m_damageCoeff;
	}

	public int getDamage() {
		return m_damage;
	}

	public void addDamageCoeff(float f) {
		m_damageCoeff += f;
	}

	public float getSpeedCoeff() {
		return m_speedCoeff;
	}

	public void addSpeedCoeff(float f) {
		m_speedCoeff += f;
	}

	public float getAttackspeedCoeff() {
		return m_attackspeedCoeff;
	}

	public void addAttackpeedCoeff(float f) {
		m_attackspeedCoeff += f;

		if (m_attackSpeed * m_attackspeedCoeff > 1) {
			reloadTimeSec = 0;
			reloadTimeMili = 1000 - (int) (m_attackSpeed * m_attackspeedCoeff * 20);
		}
	}

	public float getRangeCoeff() {
		return m_rangeCoeff;
	}

	public void addRangeCoeff(float f) {
		m_rangeCoeff += f;
	}

	public float getMaxLifePointsCoeff() {
		return m_maxHealthCoeff;
	}

	public void addMaxLifePointsCoeff(float f) {
		m_maxHealthCoeff += f;
		setNewMaxHealthPoints();
	}

	public void setHealthPoints(int m_healthPoints) {
		PiratePlayer.m_healthPoints = m_healthPoints;
	}

	public int getMaxHealthPoints() {
		return PiratePlayer.m_maxHealthPoints;
	}

	public int getHealthPoints() {
		return PiratePlayer.m_healthPoints;
	}

	public void setNewMaxHealthPoints() {
		m_maxHealthPoints = (int) (this.DEFAULT_MAX_PLAYERS_LIFE * m_maxHealthCoeff);
		m_healthPoints = m_maxHealthPoints;
	}

	@Override
	public void hit() {

		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		int timeMin = GameModele.timer.getMinutes();

		if (!this.reloading) {
			weapon.hit(m_rangeCoeff, m_damageCoeff);

			this.reloading = true;
			this.timerReloadingMili = timeMili - 20;
			this.timerReloadingSec = timeSec;
			this.timerReloadingMin = timeMin;
		} else if (reloadingTimePassed(this.timerReloadingMili, this.timerReloadingSec, timeMili, timeSec)
				|| this.timerReloadingMin < timeMin) {
			this.reloading = false;
		}
	}

	public void updateInvincible() {
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		int timeMin = GameModele.timer.getMinutes();
		if (invincibilityTimePassed(this.timerInvicibleMili, this.timerInvicibleSec, timeMili, timeSec)
				|| this.timerInvicibleMin < timeMin) {
			this.invincible = false;
		}
	}

	public void takeDamage(int damage) {
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		int timeMin = GameModele.timer.getMinutes();
		if (!this.invincible) {
			SoundTool.playSoundEffect(SoundEffect.PirateHitted, 0);
			m_healthPoints -= damage;
			if (m_healthPoints <= 0) {
				die();
			} else {
				this.invincible = true;
				this.timerInvicibleMili = timeMili;
				this.timerInvicibleSec = timeSec;
				this.timerInvicibleMin = timeMin;
			}
		} else if (invincibilityTimePassed(this.timerInvicibleMili, this.timerInvicibleSec, timeMili, timeSec)
				|| this.timerInvicibleMin < timeMin) {
			this.invincible = false;
		}
	}

	@Override
	public void die() {
		Controller.getGameModele().gameover();
	}

	public static void resetPiratePlayer() {
		m_attackspeedCoeff = DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF;
		m_speedCoeff = DEFAULT_PIRATEPLAYER_SPEED_COEFF;
		m_damageCoeff = DEFAULT_PIRATEPLAYER_DAMAGE_COEFF;
		m_rangeCoeff = DEFAULT_PIRATEPLAYER_RANGE_COEFF;
		m_maxHealthCoeff = DEFAULT_MAX_PLAYERS_LIFE_COEFF;

		m_maxHealthPoints = DEFAULT_PIRATEPLAYER_LIFE_POINT;
		m_healthPoints = DEFAULT_PIRATEPLAYER_LIFE_POINT;
		m_attackSpeed = DEFAULT_PIRATEPLAYER_ATTACKSPEED;
		m_range = DEFAULT_PIRATEPLAYER_RANGE;
		m_speed = DEFAULT_PIRATEPLAYER_SPEED;
	}

}
