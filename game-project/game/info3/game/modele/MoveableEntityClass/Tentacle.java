package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Rhum;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.TentacleAvatar;

public class Tentacle extends Ennemy {

	private final static int LIFEPOINT = 200;
	private final static int DAMAGE = 50;

	private int timerAttackMili;
	private int timerAttackSec;
	private int timerAttackMin;
	private boolean reloading;
	
	private boolean stunned;
	private int timerStunMili;
	private int timerStunSec;
	
	
	private int reloadTimeSec;
	private int reloadTimeMili;

	int number;
	Kraken kraken;

	public Tentacle(int x, int y, Kraken kraken, int number) {
		super(LIFEPOINT, DAMAGE, x, y);
		this.kraken = kraken;
		this.automate = AutomateLoader.findAutomate(GameEntity.KrakenTentacle);
		this.current_state = automate.initial_state;
		this.number = number;
		this.avatar = new TentacleAvatar(this);

		this.reloading = false;
		GameModele.entities.add(this);
		
		this.reloadTimeMili = 0;
		this.reloadTimeSec = 1;
		
		TentacleAvatar tentacle = (TentacleAvatar)this.avatar;
		tentacle.setTentacleNumber(this.number * 3);
	}

	@Override
	public void die() {
		GameModele.entities.remove(this);
		kraken.tentacleDead(this);
	}
	
	public void stunt() {
		this.automate = AutomateLoader.findAutomate(GameEntity.Stunned);
		this.current_state = automate.initial_state;
		
		this.stunned = true;
		
		this.timerStunMili = GameModele.timer.getMiliSecondes();
		this.timerStunSec = GameModele.timer.getSecondes();
	}

	@Override
	public boolean closest() {

		return GameModele.onSea && closestBoatToMe().getCurrentSection() == 8
				&& GameModele.map.getTileUnderEntity(GameModele.getCurrentPlayerX(), GameModele.getCurrentPlayerY())
						.getTileY() < GameModele.map.getSectionHeight() - 3;

	}

	private BoatPlayer closestBoatToMe() {
		return GameModele.pirateBoat;
	}

	public boolean gotPower() {
		
		
		if (this.stunned) {
			
			int timeMili = GameModele.timer.getMiliSecondes();
			int timeSec = GameModele.timer.getSecondes();
			if (this.timerStunMili <= timeMili && this.timerStunSec + 1 <= timeSec) {
				this.stunned = false;
				this.automate = AutomateLoader.findAutomate(GameEntity.KrakenTentacle);
				this.current_state = automate.initial_state;
			}
		}
		return this.m_healthPoints > 0;
	}
	
	@Override
	public void power() {
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		int timeMin = GameModele.timer.getMinutes();

		if (!this.reloading) {
			GameModele.map.setPoisoning(GameModele.getCurrentPlayerX(), GameModele.getCurrentPlayerY());
			this.reloading = true;
			this.timerAttackMili = timeMili - 20;
			this.timerAttackSec = timeSec;
			this.timerAttackMin = timeMin;
		} else if (reloadingTimePassed(timerAttackMili, timerAttackSec, timeMili, timeSec) || timerAttackMin < timeMin) {
			this.reloading = false;
		}
	}

	public void hit() {
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();
		int timeMin = GameModele.timer.getMinutes();

		if (!this.reloading) {
			GameModele.map.setDamaging(GameModele.getCurrentPlayerX(), GameModele.getCurrentPlayerY());
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

	public void move() {
		// TODO
	}

	public int getNumber() {
		return number;
	}

}
