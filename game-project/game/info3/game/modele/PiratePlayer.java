package info3.game.modele;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.map.Tiles;

public class PiratePlayer extends Player {
	
	private static final int DEFAULT_PIRATEPLAYER_LIFE_POINT = 100;
	
	public static final int DEFAULT_MAX_PLAYERS_LIFE_COEFF = 100;
	
	private static final int DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF = 1;
	
	private static final int DEFAULT_PIRATEPLAYER_SPEED_COEFF = 1;

	private static final int DEFAULT_PIRATEPLAYER_DAMAGE_COEFF = 1;
	
	private static final int DEFAULT_PIRATEPLAYER_RANGE_COEFF = 1;
	
	protected float attackSpeedCoeff;
	protected float speedCoeff;
	protected float damageCoeff;
	protected float rangeCoeff;

			
	Weapon weapon;

	public PiratePlayer(String string) {
		super(DEFAULT_PIRATEPLAYER_LIFE_POINT, DEFAULT_MAX_PLAYERS_LIFE_COEFF);
		this.automate = AutomateLoader.findAutomate(string);
		this.current_state = automate.initial_state;
		facing = EnumDirection.N;
		this.attackSpeedCoeff = DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF;
		this.speedCoeff = DEFAULT_PIRATEPLAYER_SPEED_COEFF;
		this.damageCoeff = DEFAULT_PIRATEPLAYER_DAMAGE_COEFF;
		this.rangeCoeff = DEFAULT_PIRATEPLAYER_RANGE_COEFF;
	}
	
	public PiratePlayer(String string, int x, int y) {
		super(DEFAULT_PIRATEPLAYER_LIFE_POINT, DEFAULT_MAX_PLAYERS_LIFE_COEFF, x, y);
		this.automate = AutomateLoader.findAutomate(string);
		this.current_state = automate.initial_state;
		facing = EnumDirection.N;
		this.attackSpeedCoeff = DEFAULT_PIRATEPLAYER_ATTACKSPEED_COEFF;
		this.speedCoeff = DEFAULT_PIRATEPLAYER_SPEED_COEFF;
		this.damageCoeff = DEFAULT_PIRATEPLAYER_DAMAGE_COEFF;
		this.rangeCoeff = DEFAULT_PIRATEPLAYER_RANGE_COEFF;
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		//weapon.setPlayer(this);
	}
	
	@Override
	public void move(EnumDirection eval) {
		facing = eval;
		this.moveEntity(eval, DEFAULT_PIRATEPLAYER_SPEED_COEFF);
		switch (GameModele.map.getTileUnderEntity(this.x, this.y).getType()) {
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
	
	public void takeDamage(int damage) {
		this.lifePoint -= damage;
	}

	@Override
	public boolean closeTo(EnumDirection d, EnumCategory c) {
		return false;
	}

	@Override
	public boolean closest(EnumDirection d, EnumCategory c) {
		return false;
	}

	@Override
	public boolean cell(EnumDirection d, EnumCategory c) {
		//Variable a retirer quand le bateau sera pret
		//boolean debug = true;
		int tempX;
		int tempY;
		Tiles t;
		switch(d) {
			case N:
				
				tempY = (int) ((float)this.y * this.speedCoeff);
				t = GameModele.map.getTileUnderEntity(this.x, tempY);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.x == x)
								if(e.y <= this.y && e.y >= tempY)
									return true;
						}
					}
					return false;
				}
				return true;
				
			case S:
				tempY = (int) ((float)this.y + this.speedCoeff);
				t = GameModele.map.getTileUnderEntity(this.x, tempY);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.x == x) {
								if(e.y >= this.y && e.y <= tempY) {
									System.out.println("test");
									return true;
								}
							}
						}
					}
					return false;
				}
				return true;
				
			case E:
				tempX = (int) ((float)this.x + this.speedCoeff);
				t = GameModele.map.getTileUnderEntity(tempX, y);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.y == y) {
								if(e.x >= this.x && e.x <= tempX)
									return true;
							}
						}
					}
					return false;
				}
				return true;
				
			case W:
				tempX = (int) ((float)this.x - this.speedCoeff);
				t = GameModele.map.getTileUnderEntity(tempX, y);
				if(t.isIsland()) {
					for(Entity e : GameModele.entities) {
						if(e != this) {
							if(e.y == y) {
								if(e.x <= this.x && e.x >= tempX)
									return true;
							}
						}
					}
					return false;
				}
				return true;
				
			case NE:
				tempX = (int) ((float)this.x + this.speedCoeff);
				tempY = (int) ((float)this.y - this.speedCoeff);
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
				tempX = (int) ((float)this.x - this.speedCoeff);
				tempY = (int) ((float)this.y - this.speedCoeff);
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
				tempX = (int) ((float)this.x + this.speedCoeff);
				tempY = (int) ((float)this.y + this.speedCoeff);
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
				tempX = (int) ((float)this.x - this.speedCoeff);
				tempY = (int) ((float)this.y + this.speedCoeff);
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

	@Override
	public void hit(EnumDirection d, EnumCategory c) {
		//weapon.hit();
		
	}
	
	public float getDamageCoeff() {
		return this.damageCoeff;
	}


	public void addDamageCoeff(float f) {
		this.damageCoeff += f;
	}


	public float getSpeedCoeff() {
		return speedCoeff;
	}


	public void addSpeedCoeff(float f) {
		this.speedCoeff =+ f;
	}


	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public float getAttackSpeedCoeff() {
		return attackSpeedCoeff;
	}


	public void addAttackSpeedCoeff(float attackSpeedCoeff) {
		this.attackSpeedCoeff += attackSpeedCoeff;
	}


	public float getRangeCoeff() {
		return rangeCoeff;
	}


	public void addRangeCoeff(float rangeCoeff) {
		this.rangeCoeff += rangeCoeff;
	}

}
