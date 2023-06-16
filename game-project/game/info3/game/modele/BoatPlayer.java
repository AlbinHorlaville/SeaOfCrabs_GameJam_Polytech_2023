package info3.game.modele;

import java.util.ArrayList;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;

public class BoatPlayer extends Player {

	ArrayList<CannonBall> bouletDeCannon;
	CannonBall current_ball;
	
	private static final int DEFAULT_BOATPLAYER_LIFE_POINT = 100;
	
	private static final int DEFAULT_MAX_BOATPLAYER_LIFE_POINT = 100;

	private static final int DEFAULT_BOATPLAYER_SPEED = 1;
	
	public BoatPlayer() {
		super(DEFAULT_BOATPLAYER_LIFE_POINT,0, DEFAULT_MAX_BOATPLAYER_LIFE_POINT);
		
		bouletDeCannon = new ArrayList<>();
		this.current_ball = new BasicCannonBall();
		
		this.automate = AutomateLoader.findAutomate("PlayerBoat");
		this.current_state = automate.initial_state;
		
		this.facing = EnumDirection.N;
	}
	
	public BoatPlayer(int x, int y) {
		super(DEFAULT_BOATPLAYER_LIFE_POINT,0, DEFAULT_MAX_BOATPLAYER_LIFE_POINT, x, y);
		
		bouletDeCannon = new ArrayList<>();
		//this.current_ball = new BasicCannonBall();
		
		this.automate = AutomateLoader.findAutomate("PlayerBoat");
		this.current_state = automate.initial_state;
		
		this.facing = EnumDirection.N;
	}
	
	public void addHealthPoints(int healthPoints) {
		this.lifePoint += healthPoints;
	}
	
	/**
	 * Cette m�thode doit �tre changer et permettra d'ajouter un boulet quand ramasser
	 * @param boulet
	 */
	public void addBoulet(CannonBall boulet) {
		this.bouletDeCannon.add(boulet);
	}
	
	@Override
	public void move(EnumDirection eval) {
		facing = eval;
		this.moveEntity(eval, DEFAULT_BOATPLAYER_SPEED);
		switch (GameModele.map.getTileUnderEntity(this.x, this.y).getType()) {
		case SAND:
		case SAND_WATER:
		case GRASS:
			GameModele.entities.remove(this);
			GameModele.player1.setLocation(x, y);
			GameModele.player1.facing = this.facing;
			GameModele.entities.add(GameModele.player1);
			GameModele.onSea = !GameModele.onSea;
			break;
		default:
			break;
		}
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		
	}
	
	public void startFire(int mouseX, int mouseY) {
		//A modifier pour choisir le boulet de cannon à tirer
		CannonBall b;
		if(bouletDeCannon.size() > 0)
			b = bouletDeCannon.remove(0);
		else
			b = new BasicCannonBall();
		b.setPositions(this.x, this.y, mouseX, mouseY);
		b.fire();
	}

	@Override
	public void hit(EnumDirection d, EnumCategory c) {
		
		
	}

}
