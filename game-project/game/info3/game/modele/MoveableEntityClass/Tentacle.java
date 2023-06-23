package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Rhum;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.TentacleAvatar;

public class Tentacle extends Ennemy {

	private final static int LIFEPOINT = 100;
	private final static int DAMAGE = 0;

	private int timerAttackMili;
	private int timerAttackSec;
	private boolean reloading;
	
	private boolean stunned;
	private int timerStunMili;
	private int timerStunSec;

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

	public void hit() {
		int timeMili = GameModele.timer.getMiliSecondes();
		int timeSec = GameModele.timer.getSecondes();

		if (!this.reloading) {
			GameModele.map.setDamaging(GameModele.getCurrentPlayerX(), GameModele.getCurrentPlayerY());
			this.reloading = true;
			this.timerAttackMili = timeMili;
			this.timerAttackSec = timeSec;
		} else if (timerAttackMili <= timeMili && timerAttackSec + 1 <= timeSec) {
			this.reloading = false;
		}

	}

	public void move() {
		// TODO
	}

	public int getNumber() {
		return number;
	}

}
