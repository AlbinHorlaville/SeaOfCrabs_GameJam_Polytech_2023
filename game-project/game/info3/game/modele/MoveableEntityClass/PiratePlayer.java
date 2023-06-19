package info3.game.modele.MoveableEntityClass;

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
	
	protected float m_attackspeedCoeff;
	protected int m_speedCoeff;
	protected float m_damageCoeff;
	protected float m_rangeCoeff;
	protected float m_maxHealthCoeff;

			
	Weapon weapon;
	
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
	}
	
	
	@Override
	public void move(EnumDirection eval) {
		facing = eval;
		this.moveEntity(eval, DEFAULT_PIRATEPLAYER_SPEED_COEFF);
		switch (GameModele.map.getTileUnderEntity(x + (2 * this.avatar.getWidth()/Avatar.SCALE_IMG), y + (this.avatar.getHeight()/Avatar.SCALE_IMG)).getType()) {
		case CALM_WATER:
		case STORMY_WATER:
		case RAGING_WATER:
			GameModele.entities.remove(this);
			GameModele.pirateBoat.setLocation(x, y);
			GameModele.pirateBoat.facing = this.facing;
			GameModele.entities.add(GameModele.pirateBoat);
			GameModele.onSea = !GameModele.onSea;
			break;
		default:
			break;
		}
	}

	@Override
	public boolean cell(EnumDirection d, EnumCategory c) {
		//Variable a retirer quand le bateau sera pret
		//boolean debug = true;
		int tempX = this.x + (2* this.avatar.getWidth() /Avatar.SCALE_IMG);
		int tempY = this.y + ( this.avatar.getHeight() / Avatar.SCALE_IMG);
		Tiles t;
		switch(d) {
			case N:
				
				tempY += this.m_speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, tempY);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.x == x) {
								if(e.y <= this.y && e.y >= tempY) {
									System.out.println(e.x + " " + e.y);
									return true;
								}
							}
						}
					}
					return false;
				}
				return true;
				
			case S:
				tempY -=this.m_speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, tempY);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.x == x) {
								if(e.y >= this.y && e.y <= tempY) {
									System.out.println(e.x + " " + e.y);
									return true;
								}
							}
						}
					}
					return false;
				}
				return true;
				
			case E:
				tempX -= this.m_speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, tempY);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.y == y) {
								if(e.x >= this.x && e.x <= tempX) {
									System.out.println(e.x + " " + e.y);
									return true;
								}
							}
						}
					}
					return false;
				}
				return true;
				
			case W:
				tempX += this.m_speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, tempY);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.y == y) {
								if(e.x <= this.x && e.x >= tempX) {
									System.out.println(e.x + " " + e.y);
									return true;
								}
							}
						}
					}
					return false;
				}
				return true;
				
			case NE:
				tempX -= this.m_speedCoeff;
				tempY += this.m_speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, tempY);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.x >= this.x && e.x <= tempX) {
								if(e.y <= this.y && e.y >= tempY)
									return true;
							}
						}
					}
					return false;
				}
				return true;
				
			case NW:
				tempX += this.m_speedCoeff;
				tempY += this.m_speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, tempY);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.x <= this.x && e.x >= tempX) {
								if(e.y <= this.y && e.y >= tempY)
									return true;
							}
						}
					}
					return false;
				}
				return true;
				
			case SE:
				tempX -= this.m_speedCoeff;
				tempY -= this.m_speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, y);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.x >= this.x && e.x <= tempX) {
								if(e.y >= this.y && e.y <= tempY)
									return true;
							}
						}
					}
					return false;
				}
				return true;
				
			case SW:
				tempX += this.m_speedCoeff;
				tempY -= this.m_speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, y);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.x <= this.x && e.x >= tempX) {
								if(e.y >= this.y && e.y <= tempY)
									return true;
							}
						}
					}
					return false;
				}
				return true;
			
			default:
				return false;
		}
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


	public void addSpeedCoeff(int f) {
		this.m_speedCoeff =+ f;
	}
	
	public float getAttackspeedCoeff() {
		return m_attackspeedCoeff;
	}


	public void addAttackpeedCoeff(float m_attackm_speedCoeff) {
		this.m_attackspeedCoeff += m_attackspeedCoeff;
	}


	public float getRangeCoeff() {
		return m_rangeCoeff;
	}


	public void addRangeCoeff(float rangeCoeff) {
		this.m_rangeCoeff += rangeCoeff;
	}
}
