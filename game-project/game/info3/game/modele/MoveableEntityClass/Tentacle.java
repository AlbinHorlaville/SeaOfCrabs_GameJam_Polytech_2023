package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Rhum;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.TentacleAvatar;

public class Tentacle extends Ennemy  {
	
	private final static int LIFEPOINT = 100;
	private final static int DAMAGE = 0;
	
	private int timerAttackMili;
	private int timerAttackSec;
	private boolean reloading;
	
	int number;
	Kraken kraken;

	public Tentacle(int x, int y, Kraken kraken, int number) {
		super(LIFEPOINT, DAMAGE,x,y);
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
	
	@Override
	public boolean closest() {

		return GameModele.onSea && closestBoatToMe().getCurrentSection() == 8;

	}

	private BoatPlayer closestBoatToMe() {
		return GameModele.pirateBoat;
	}
	
	public boolean gotPower() {
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
		} else if (timerAttackMili <= timeMili + 20 && timerAttackSec + 1 <= timeSec) {
			this.reloading = false;
		}

	}
	
	public void stunt() {
		this.automate = AutomateLoader.findAutomate(GameEntity.Philosopher);
		this.current_state = automate.initial_state;

	}
	
	public void move() {
		// TODO
	}
	
	public int getNumber() {
		return number;
	}

}
