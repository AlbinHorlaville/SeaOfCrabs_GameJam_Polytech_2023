package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.vue.avatar.TentacleAvatar;

public class Tentacle extends Ennemy  {
	
	private final static int LIFEPOINT = 0;
	private final static int DAMAGE = 0;
	
	int number;
	Kraken kraken;

	public Tentacle(int x, int y, Kraken kraken, int number) {
		super(LIFEPOINT, DAMAGE,x,y);
		this.kraken = kraken;
		this.automate = AutomateLoader.findAutomate(GameEntity.Philosopher);
		this.current_state = automate.initial_state;
		this.number = number;
		this.avatar = new TentacleAvatar(this);	
		
		GameModele.entities.add(this);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeDamage() {
		// TODO Auto-generated method stub
		
	}
	
	public void move() {
		// TODO
	}
	
	public int getNumber() {
		return number;
	}

}
