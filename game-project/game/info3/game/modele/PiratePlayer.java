package info3.game.modele;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.map.Tiles;

public class PiratePlayer extends Player {
	
	private static final int DEFAULT_PIRATEPLAYER_LIFE_POINT = 100;

	private static final int DEFAULT_PIRATEPLAYER_ATTACK = 2;

	private static final int DEFAULT_PIRATEPLAYER_SPEED = 1;
	
	public static final int MAX_PLAYERS_LIFE = 100;
	
	public static final int PLAYERS_LIFE = 100;
			
	Weapon weapon;

	public PiratePlayer() {
		super(DEFAULT_PIRATEPLAYER_LIFE_POINT, DEFAULT_PIRATEPLAYER_ATTACK, DEFAULT_PIRATEPLAYER_SPEED);
		this.automate = AutomateLoader.findAutomate("Player");
		this.current_state = automate.initial_state;
		facing = EnumDirection.N;
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		//weapon.setPlayer(this);
	}
	
	@Override
	public void move(EnumDirection eval) {
		facing = eval;
		this.moveEntity(eval, DEFAULT_PIRATEPLAYER_SPEED);
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
		boolean debug = true;
		int tempX;
		int tempY;
		Tiles t;
		switch(d) {
			case N:
				
				tempY = this.y - this.speedCoeff;
				t = GameModele.map.getTileUnderEntity(this.x, tempY);
				if(t.isIsland() || debug) {
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
				tempY = this.y + this.speedCoeff;
				t = GameModele.map.getTileUnderEntity(this.x, tempY);
				if(t.isIsland() || debug) {
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
				tempX = this.x + this.speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, y);
				if(t.isIsland() || debug) {
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
				tempX = this.x - this.speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, y);
				if(t.isIsland() || debug) {
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
				tempX = this.x + this.speedCoeff;
				tempY = this.y - this.speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, tempY);
				if(t.isIsland() || debug) {
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
				tempX = this.x - this.speedCoeff;
				tempY = this.y - this.speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, tempY);
				if(t.isIsland() || debug) {
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
				tempX = this.x + this.speedCoeff;
				tempY = this.y + this.speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, y);
				if(t.isIsland() || debug) {
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
				tempX = this.x - this.speedCoeff;
				tempY = this.y + this.speedCoeff;
				t = GameModele.map.getTileUnderEntity(tempX, y);
				if(t.isIsland() || debug) {
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

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}

}
