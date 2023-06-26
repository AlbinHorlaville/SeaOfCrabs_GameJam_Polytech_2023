package info3.game.modele.MoveableEntityClass;

import java.util.HashMap;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.Controller;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.DamagedCannonBallAvatar;

public class BoatPlayer extends Player {

	HashMap<EnumCannonBall,Integer> bouletDeCannon;
	EnumCannonBall currentBall = EnumCannonBall.Basic;

	private static final int DEFAULT_BOATPLAYER_LIFE_POINT = 100;

	private static final int DEFAULT_MAX_BOATPLAYER_LIFE_POINT = 100;

	public static final int DEFAULT_BOATPLAYER_SPEED = 2;

	private int currentSection;

	public boolean invincible;
	public int timerInvicibleMili;
	public int timerInvicibleSec;
	public int timerInvicibleMin;
	
	private int timerAttackMili;
	private int timerAttackSec;
	private int timerAttackMin;
	private boolean reloading;
	private int reloadTimeSec;
	private int reloadTimeMili;

	public BoatPlayer() {
		super(DEFAULT_BOATPLAYER_LIFE_POINT, 0, DEFAULT_MAX_BOATPLAYER_LIFE_POINT);

		initHashmap();

		this.automate = AutomateLoader.findAutomate(GameEntity.PlayerBoat);
		this.current_state = automate.initial_state;

		this.facing = EnumDirection.N;
		this.currentSection = 0;
		
		this.reloadTimeMili = 0;
		this.reloadTimeSec = 1;
	}

	private void initHashmap() {
		bouletDeCannon = new HashMap<>();
		for(EnumCannonBall ball : EnumCannonBall.values()) {
			if (ball != EnumCannonBall.Basic) {
				bouletDeCannon.put(ball, 0);
			}
		}
		
	}

	public BoatPlayer(int x, int y) {
		super(DEFAULT_BOATPLAYER_LIFE_POINT, 0, DEFAULT_MAX_BOATPLAYER_LIFE_POINT, x, y);

		initHashmap();

		this.automate = AutomateLoader.findAutomate(GameEntity.PlayerBoat);
		this.current_state = automate.initial_state;

		this.facing = EnumDirection.N;
		this.currentSection = 0;
	}

	public void addHealthPoints(int healthPoints) {
		this.m_healthPoints += healthPoints;
	}

	/**
	 * Cette m�thode doit �tre changer et permettra d'ajouter un boulet quand
	 * ramasser
	 * 
	 * @param boulet
	 */
	public void addBoulet(EnumCannonBall boulet,int amount) {
		this.bouletDeCannon.replace(boulet, getAmount(boulet)+amount);
	}

	public EnumCannonBall getBall() {
		return this.currentBall;
	}

	/*
	 * public boolean cell() { int tempX = this.x; int tempY = this.y;
	 * this.moveEntity(facing, DEFAULT_BOATPLAYER_SPEED); Tiles under =
	 * GameModele.map.getTileUnderEntity(x, y -
	 * (this.avatar.getHeight()/Avatar.SCALE_IMG));
	 * System.out.println(under.getType().toString()); if(under.isIsland()) { x =
	 * tempX; y = tempY; return true; SpecificCannnonBall } return false; }
	 */

	@Override
	public void die() {
		Controller.getGameModele().gameover();
	}

	@Override
	public void move(EnumDirection eval) {
		facing = eval;

		// if(!cell()) {
		this.moveEntity(eval, DEFAULT_BOATPLAYER_SPEED);
		// }

		Tiles under = GameModele.map.getTileUnderEntity(this.x, y);
		int tileY = under.getTileY();
		int tileX = under.getTileX();
		int section = GameModele.map.getSectionOfEntity(this.x, y);
		if (under.isIsland()) {
			GameModele.entities.remove(this);
			GameModele.player1.setLocation(under.getX(), under.getY());
			GameModele.player1.facing = this.facing;

			if (!GameModele.solo) {
				GameModele.player2.setLocation(under.getX(), under.getY());
				GameModele.player2.facing = this.facing;
				GameModele.entities.add(GameModele.player2);
			}

			GameModele.entities.add(GameModele.player1);
			GameModele.onSea = !GameModele.onSea;
		}

		if (tileX < 9) {
			this.x = GameModele.map.getMap()[section].getTiles()[tileY][GameModele.map.getSectionWidth() - 10].getX();
			this.y = GameModele.map.getMap()[section].getTiles()[tileY][GameModele.map.getSectionWidth() - 10].getY();
		} else if (tileX > GameModele.map.getSectionWidth() - 10) {
			this.x = GameModele.map.getMap()[section].getTiles()[tileY][10].getX();
			this.y = GameModele.map.getMap()[section].getTiles()[tileY][10].getY();
		}

		this.currentSection = section;
	}

	public int getCurrentSection() {
		return this.currentSection;
	}
	
	public void updateInvincible () {
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		int timeMin = GameModele.timer.getMinutes();
		if ((timerInvicibleMili <= timeMili && timerInvicibleSec + 1 <= timeSec) || this.timerInvicibleMin < timeMin) {
			invincible = false;
		}
	}

	@Override
	public void takeDamage(int damage) {
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		int timeMin = GameModele.timer.getMinutes();
		if (!invincible) {
			this.m_healthPoints -= damage;
			if (this.m_healthPoints <= 0) {
				this.die();
			}
			invincible = true;
			this.timerInvicibleMili = timeMili;
			this.timerInvicibleSec = timeSec;
			this.timerInvicibleMin = timeMin;
		} else if ((timerInvicibleMili <= timeMili && timerInvicibleSec + 1 <= timeSec) || this.timerInvicibleMin < timeMin) {
			invincible = false;
		}
	}

	public void startFire(int mouseX, int mouseY) { // A changer
		// A modifier pour choisir le boulet de cannon à tirer
		
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		int timeMin = GameModele.timer.getMinutes();

		if (!this.reloading) {
			
			CannonBall b = null;
			if (EnumCannonBall.Basic == currentBall) {
				b = new BasicCannonBall();
				b.setPositions(this.x, this.y, mouseX, mouseY);
				b.fire();
			} else if (getAmount(currentBall) != 0) {
				this.bouletDeCannon.replace(currentBall, getAmount(currentBall)-1);
				switch (currentBall) {
				case Stunt:
					b = new StunningCannonBall();
					break;
				case KrakenSlayer:
					b = new KrakenSlayerCannonBall();
					break;
				case Damaged:
					b = new DamagedCannonBall();
					break;
				default:
					return;
				}
				b.setPositions(this.x, this.y, mouseX, mouseY);
				b.fire();
			}
			
			this.reloading = true;
			this.timerAttackMili = timeMili - 20;
			this.timerAttackSec = timeSec;
			this.timerAttackMin = timeMin;
		} else if (reloadingTimePassed(timerAttackMili, timerAttackSec, timeMili, timeSec) || timerAttackMin < timeMin) {
			this.reloading = false;
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
		
	public int getAmount(EnumCannonBall ball) {
		return this.bouletDeCannon.get(ball);
	}

	@Override
	public void get(EnumCategory d) {
		switch(d) {
		case O:
			currentBall = EnumCannonBall.Basic;
			break;
		case A:
			currentBall = EnumCannonBall.Stunt;
			break;
		case D:
			currentBall = EnumCannonBall.KrakenSlayer;
			break;
		case G:
			currentBall = EnumCannonBall.Damaged;
			break;
		default:
			break;
		}
	}
	
	public EnumCannonBall getCurrentBall() {
		return currentBall;
	}
	
	



}
