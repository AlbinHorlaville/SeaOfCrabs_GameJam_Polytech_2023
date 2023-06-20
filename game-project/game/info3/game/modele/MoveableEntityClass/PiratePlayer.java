package info3.game.modele.MoveableEntityClass;

import java.util.ArrayList;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.Entity;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.Weapon;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.Avatar;

public class PiratePlayer extends Player {
	
	private static final int DEFAULT_PIRATEPLAYER_LIFE_POINT = 100;
	
	private static final int DEFAULT_PIRATEPLAYER_DAMAGE = 25;
	
	public static final int DEFAULT_MAX_PLAYERS_LIFE_COEFF = 1;
	
	private static final int DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF = 1;
	
	private static final int DEFAULT_PIRATEPLAYER_SPEED_COEFF = 1;

	private static final int DEFAULT_PIRATEPLAYER_DAMAGE_COEFF = 1;
	
	private static final int DEFAULT_PIRATEPLAYER_RANGE_COEFF = 1;
	
	private static int ACTUAL_PIRATEPLAYER_LIFE_POINT = 100;
	
	public static int ACTUAL_MAX_PLAYERS_LIFE = 1;
	
	private static int ACTUAL_PIRATEPLAYER_ATTACKSPEED = 1;
	
	private static int ACTUAL_PIRATEPLAYER_SPEED = 1;

	private static int ACTUAL_PIRATEPLAYER_DAMAGE = 1;
	
	private static int ACTUAL_PIRATEPLAYER_RANGE = 1;
	
	protected float m_attackspeedCoeff;
	protected float m_speedCoeff;
	protected float m_damageCoeff;
	protected float m_rangeCoeff;
	protected float m_maxHealthCoeff;

			
	public Weapon weapon;
	public boolean invincible;
	public int timerInvicible;
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		//weapon.setPlayer(this);
	}
	
	public PiratePlayer(GameEntity entity) {
		super(DEFAULT_PIRATEPLAYER_LIFE_POINT, DEFAULT_PIRATEPLAYER_DAMAGE, DEFAULT_MAX_PLAYERS_LIFE );		//The good one
		this.automate = AutomateLoader.findAutomate(entity);
		this.current_state = automate.initial_state;
		facing = EnumDirection.N;
		this.m_attackspeedCoeff = DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF;
		this.m_speedCoeff = DEFAULT_PIRATEPLAYER_SPEED_COEFF;
		this.m_damageCoeff = DEFAULT_PIRATEPLAYER_DAMAGE_COEFF;
		this.m_rangeCoeff = DEFAULT_PIRATEPLAYER_RANGE_COEFF;
		this.m_maxHealthCoeff = DEFAULT_MAX_PLAYERS_LIFE_COEFF;
		this.invincible = false;
	}
	
	
	@Override
	public void move(EnumDirection eval) {
		if(eval == EnumDirection.F) { // CHANGEMENT PIRATE A BATEAU
			this.moveEntity(facing, DEFAULT_PIRATEPLAYER_SPEED_COEFF);
			
			if(GameModele.solo) {
				GameModele.entities.remove(this);
			} else {
				GameModele.entities.remove(GameModele.player1);
				GameModele.entities.remove(GameModele.player2);
			}
			
			GameModele.pirateBoat.setLocation(x, y);
			GameModele.pirateBoat.facing = this.facing;
			GameModele.entities.add(GameModele.pirateBoat);
			GameModele.onSea = !GameModele.onSea;
			GameModele.pirateBoat.moveEntity(facing,150);
		}
		else {
			facing = eval;
			this.moveEntity(eval, DEFAULT_PIRATEPLAYER_SPEED_COEFF);
		}
	}
	
	public Tiles getTilesForCell(EnumDirection d, int tempX, int tempY) {
		switch(d) {
			case N:
				
				tempY += this.m_speedCoeff;
				return GameModele.map.getTileUnderEntity(tempX, tempY);
				
			case S:
				tempY -=this.m_speedCoeff;
				return GameModele.map.getTileUnderEntity(tempX, tempY);
				
			case E:
				tempX -= this.m_speedCoeff;
				return GameModele.map.getTileUnderEntity(tempX, tempY);
				
			case W:
				tempX += this.m_speedCoeff;
				return GameModele.map.getTileUnderEntity(tempX, tempY);
				
			case NE:
				tempX -= this.m_speedCoeff;
				tempY += this.m_speedCoeff;
				return GameModele.map.getTileUnderEntity(tempX, tempY);
				
			case NW:
				tempX += this.m_speedCoeff;
				tempY += this.m_speedCoeff;
				return GameModele.map.getTileUnderEntity(tempX, tempY);
				
			case SE:
				tempX -= this.m_speedCoeff;
				tempY -= this.m_speedCoeff;
				return GameModele.map.getTileUnderEntity(tempX, tempY);
				
			case SW:
				tempX += this.m_speedCoeff;
				tempY -= this.m_speedCoeff;
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
			switch(c) {
			case O:
				t = getTilesForCell(d,tempX, tempY);
				if(t.isIsland()) {
					for(Entity e :tempEntities) {
						if(!(e instanceof PiratePlayer)) {
							if(e.x == x) {
								if(e.y <= this.y && e.y >= tempY) {
									return true;
								}
							}
						}
					}
					return false;
				}
				return true;
			case J:
				return getTilesForCell(facing,tempX, tempY).isWater();
			default:
				return false;
			}
	}
	
	@Override
	public void hit() {
		weapon.hit();
	}
	
	public int getCenterX() {
		return this.x + (2* this.avatar.getWidth() /Avatar.SCALE_IMG);
	}
	
	public int getCenterY() {
		return this.y + ( this.avatar.getHeight() / Avatar.SCALE_IMG);
	}
	
	public float getDamageCoeff() {
		return this.m_damageCoeff;
	}


	public void addDamageCoeff(float f, int lvl) {
		this.m_damageCoeff += f;
	}


	public float getSpeedCoeff() {
		return m_speedCoeff;
	}


	public void addSpeedCoeff(float f, int lvl) {
		this.m_speedCoeff = f;
		ACTUAL_PIRATEPLAYER_SPEED += lvl;
	}
	
	public float getAttackspeedCoeff() {
		return m_attackspeedCoeff;
	}


	public void addAttackpeedCoeff(float f, int lvl) {
		this.m_attackspeedCoeff += f;
		ACTUAL_PIRATEPLAYER_ATTACKSPEED += lvl;
	}


	public float getRangeCoeff() {
		return m_rangeCoeff;
	}


	public void addRangeCoeff(float f, int lvl) {
		this.m_rangeCoeff += f;
		ACTUAL_PIRATEPLAYER_RANGE += lvl;
	}

	public void addMaxLifePointsCoeff(float f, int lvl) {
		this.m_maxHealthCoeff += f;
		ACTUAL_MAX_PLAYERS_LIFE += lvl;
	}
	
	public void takeDamage(int damage) {
		int time = GameModele.timer.getSecondes();
		if(!invincible) {
			super.takeDamage(damage);
			invincible = true;
			timerInvicible = time;
		}
		else if(time >= timerInvicible + 1){
			invincible = false;
		}
	}
}
