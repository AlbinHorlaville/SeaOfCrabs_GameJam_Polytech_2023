package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import automate.EnumCategory;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.Level;
import info3.game.modele.StillEntityClass.Rhum;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.ShipAvatar;

public class Ship extends Ennemy {

	public final static int DEFAULT_HEALTH_POINTS = 100;
	public final static int DEFAULT_DAMAGE = 25;

	private int timerAttackMili;
	private int timerAttackSec;	
	private boolean reloading;

	private boolean stunned;
	private int timerStunMili;
	private int timerStunSec;

	public Ship(MapSection mapSection) {
		super(DEFAULT_HEALTH_POINTS, DEFAULT_DAMAGE);
		this.avatar = new ShipAvatar(this);
		this.automate = AutomateLoader.findAutomate(GameEntity.Ship);
		this.current_state = automate.initial_state;
		GameModele.entities.add(this);
		this.reloading = false;
	}

	public void move() {
		if ((int) tick(this.timeElapsed) % 3 == 0) {
			// Nearer pirate to me
			BoatPlayer closestPlayer = this.closestBoatToMe();

			// Get next coordinate
			int nextX = this.x;
			int nextY = this.y;

			if (this.x > closestPlayer.x)
				nextX--;
			else if (this.x < closestPlayer.x)
				nextX++;

			if (this.y > closestPlayer.y)
				nextY--;
			else if (this.y < closestPlayer.y)
				nextY++;

//			int nextX = this.x - ((this.x - closestPlayer.x) * 1);
//			int nextY = this.y - ((this.y - closestPlayer.y) * 1);

			// Can the the tile be moved on buy a crab
			Tiles tile = GameModele.map.getTileUnderEntity(nextX, nextY);
			if (tile.isWater()) {
				this.x = nextX;
				this.y = nextY;
			}
		}
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
			GameModele.map.setDamaging(GameModele.pirateBoat.getCenterX(), GameModele.pirateBoat.getCenterY());
			this.reloading = true;
			this.timerAttackMili = timeMili;
			this.timerAttackSec = timeSec;
		} else if (timerAttackMili <= timeMili && timerAttackSec + 1 <= timeSec) {
			this.reloading = false;
		}
	}

	@Override
	public void die() {
		Rhum rhum = new Rhum(this.x, this.y,
				GameModele.map.getMap()[GameModele.map.getSectionOfEntity(this.x, this.y)]);
		GameModele.entities.add(rhum);
		GameModele.entities.remove(this);

	}

	@Override
	public boolean closest(EnumCategory cat) {
		
		int height = GameModele.map.getSectionHeight();

		Tiles tileUnderPlayer = GameModele.map.getTileUnderEntity(GameModele.getCurrentPlayerX(),
				GameModele.getCurrentPlayerY());
		Tiles tileUnderThis = GameModele.map.getTileUnderEntity(this.x, this.y);

		int playerTileY = GameModele.currentSection * height + height
				- tileUnderPlayer.getTileY();

		int EntityTileY = GameModele.map.getSectionOfEntity(this.x, this.y) * height
				+ height - tileUnderThis.getTileY();

		switch (cat) {
		case A:
			return (GameModele.onSea && Math.sqrt(Math.pow(tileUnderPlayer.getTileX() - tileUnderThis.getTileX(), 2)
					+ Math.pow(playerTileY - EntityTileY, 2)) < 5);
		case V:
			return (GameModele.onSea && Math.sqrt(Math.pow(tileUnderPlayer.getTileX() - tileUnderThis.getTileX(), 2)
					+ Math.pow(playerTileY - EntityTileY, 2)) < 40);
		default:
			return false;
		}

	}

	public void stunt() {
		this.automate = AutomateLoader.findAutomate(GameEntity.Stunned);
		this.current_state = automate.initial_state;

		this.stunned = true;

		this.timerStunMili = GameModele.timer.getMiliSecondes();
		this.timerStunSec = GameModele.timer.getSecondes();
	}

	private BoatPlayer closestBoatToMe() {

		// A jouter pour le mode 2 joueurs
//		double distanceP1 = Math.sqrt(Math.pow(this.x - GameModele.player1.x,2) + Math.pow(this.y - GameModele.player1.y,2));
//		double distanceP2 = Math.sqrt(Math.pow(this.x - GameModele.player2.x,2) + Math.pow(this.y - GameModele.player2.y,2));
//		
//		if(distanceP1 < distanceP2) {
//			return GameModele.player1;
//		}
//		return GameModele.player2;

		return GameModele.pirateBoat;
	}
}
