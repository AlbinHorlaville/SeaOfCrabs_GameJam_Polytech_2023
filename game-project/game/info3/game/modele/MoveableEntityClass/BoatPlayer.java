package info3.game.modele.MoveableEntityClass;

import java.util.ArrayList;

import automate.AutomateLoader;
import automate.EnumCategory;
import automate.EnumDirection;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.map.Tiles;
import info3.game.vue.avatar.Avatar;

public class BoatPlayer extends Player {

	ArrayList<CannonBall> bouletDeCannon;
	CannonBall current_ball;

	private static final int DEFAULT_BOATPLAYER_LIFE_POINT = 100;

	private static final int DEFAULT_MAX_BOATPLAYER_LIFE_POINT = 100;

	public static final int DEFAULT_BOATPLAYER_SPEED = 1;

	public BoatPlayer() {
		super(DEFAULT_BOATPLAYER_LIFE_POINT, 0, DEFAULT_MAX_BOATPLAYER_LIFE_POINT);

		bouletDeCannon = new ArrayList<>();
		this.current_ball = new BasicCannonBall();

		this.automate = AutomateLoader.findAutomate(GameEntity.PlayerBoat);
		this.current_state = automate.initial_state;

		this.facing = EnumDirection.N;
	}

	public BoatPlayer(int x, int y) {
		super(DEFAULT_BOATPLAYER_LIFE_POINT, 0, DEFAULT_MAX_BOATPLAYER_LIFE_POINT, x, y);

		bouletDeCannon = new ArrayList<>();
		// this.current_ball = new BasicCannonBall();

		this.automate = AutomateLoader.findAutomate(GameEntity.PlayerBoat);
		this.current_state = automate.initial_state;

		this.facing = EnumDirection.N;
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
	public void addBoulet(CannonBall boulet) {
		this.bouletDeCannon.add(boulet);
	}
	
	/*public boolean cell() {
		int tempX = this.x;
		int tempY = this.y;
		this.moveEntity(facing, DEFAULT_BOATPLAYER_SPEED);
		Tiles under = GameModele.map.getTileUnderEntity(x, y - (this.avatar.getHeight()/Avatar.SCALE_IMG));
		System.out.println(under.getType().toString());
		if(under.isIsland()) {
			x = tempX;
			y = tempY;
			return true;
					
		}
		return false;
	}*/

	@Override
	public void move(EnumDirection eval) {
		facing = eval;
		//if(!cell()) {
			this.moveEntity(eval, DEFAULT_BOATPLAYER_SPEED);
		//}
		int tempY = y - (this.avatar.getHeight()/Avatar.SCALE_IMG);
		Tiles under = GameModele.map.getTileUnderEntity(this.x, tempY);
		int tileY = under.getTileY();
		int section = GameModele.map.getSectionOfEntity(this.x, tempY);
		
		if (under.getTileX() < 9) {
			this.x = GameModele.map.getMap()[section].getTiles()[tileY][GameModele.map.getSectionWidth() - 10].getX();
			this.y = GameModele.map.getMap()[section].getTiles()[tileY][GameModele.map.getSectionWidth() - 10].getY();
		} else if (under.getTileX() > GameModele.map.getSectionWidth() - 10) {
			this.x = GameModele.map.getMap()[section].getTiles()[tileY][10].getX();
			this.y = GameModele.map.getMap()[section].getTiles()[tileY][10].getY();
		}

		if (under.isIsland()) {
			
			GameModele.entities.remove(this);
			GameModele.player1.setLocation(under.getX(), under.getY());
			GameModele.player1.facing = this.facing;
			GameModele.entities.add(GameModele.player1);
			GameModele.onSea = !GameModele.onSea;
			
		}
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub

	}

	public void startFire(int mouseX, int mouseY) {
		// A modifier pour choisir le boulet de cannon à tirer
		CannonBall b;
		if (bouletDeCannon.size() > 0)
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
