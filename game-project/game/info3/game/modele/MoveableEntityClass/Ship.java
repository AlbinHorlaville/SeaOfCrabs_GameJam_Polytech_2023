package info3.game.modele.MoveableEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.Level;
import info3.game.vue.avatar.ShipAvatar;

public class Ship extends Ennemy {
	
	public final static int DEFAULT_HEALTH_POINTS = 100;
	public final static int DEFAULT_DAMAGE = 20;
	
	private float m_coeff;

	public Ship(int level) {	
		super(DEFAULT_HEALTH_POINTS, DEFAULT_DAMAGE);
		this.avatar = new ShipAvatar(this);

		this.m_coeff = (new Level(level)).getCoeffBasedOnLevel();
		this.m_healthPoints *= this.m_coeff;
		this.m_damage *= this.m_coeff;
		this.automate = AutomateLoader.findAutomate(GameEntity.Philosopher);
		this.current_state = automate.initial_state;
		GameModele.entities.add(this);
	}
}
