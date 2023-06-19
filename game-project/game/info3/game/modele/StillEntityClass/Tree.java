package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.Level;
import info3.game.modele.StillEntity;
import info3.game.vue.avatar.TreeAvatar;

public class Tree extends StillEntity {
	
	public static final int DEFAULT_HEALTH_POINTS_T0_HEAL_PIRATEBOAT = 50;
	public static final int DEFAULT_HEALTH_POINTS = 50;
	
	private int healthPoints;
	private int healthPointsToHeal;
	private float coeff;
	
	public Tree() {
		super();
		this.healthPoints = DEFAULT_HEALTH_POINTS;
		this.healthPointsToHeal = DEFAULT_HEALTH_POINTS_T0_HEAL_PIRATEBOAT;
		this.automate = AutomateLoader.findAutomate(GameEntity.Philosopher);
		this.current_state = automate.initial_state;
		this.setAvatar(new TreeAvatar(this));
	}
	
	public Tree(int level) { // mettre le numéro du tronçon
		super();
		this.coeff = (new Level(level)).getCoeffBasedOnLevel();
		this.healthPoints = DEFAULT_HEALTH_POINTS;
		this.healthPointsToHeal = (int) ((float)DEFAULT_HEALTH_POINTS_T0_HEAL_PIRATEBOAT * this.coeff);
	}

	public void move() {
		//Mettre la fonction move de Crabe once done
	}

	
	public void die() {
		GameModele.pirateBoat.heal(this.healthPointsToHeal);
		super.die();
	}

	//Damage taken by the tree
	public void takeDamage(int damage) {
		this.healthPoints -= damage;
		
	}

}
