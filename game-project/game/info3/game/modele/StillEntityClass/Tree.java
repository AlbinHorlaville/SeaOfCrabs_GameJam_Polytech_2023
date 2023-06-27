package info3.game.modele.StillEntityClass;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntity;
import info3.game.vue.avatar.TreeAvatar;

public class Tree extends StillEntity {

	public static final int DEFAULT_HEALTH_POINTS_T0_HEAL_PIRATEBOAT = 5;
	public static final int DEFAULT_HEALTH_POINTS = 50;

	private int healthPoints;
	private int healthPointsToHeal;

	public Tree(int level) { // mettre le numéro du tronçon
		super();
		this.healthPoints = DEFAULT_HEALTH_POINTS;
		this.healthPointsToHeal = DEFAULT_HEALTH_POINTS_T0_HEAL_PIRATEBOAT;// (int)
																			// ((float)DEFAULT_HEALTH_POINTS_T0_HEAL_PIRATEBOAT
																			// * this.coeff); this.coeff = (new
																			// Level(level)).getCoeffBasedOnLevel();
		this.automate = AutomateLoader.findAutomate(GameEntity.Tree);
		this.current_state = automate.initial_state;
		this.setAvatar(new TreeAvatar(this));
	}

	public void move() {
	}

	public boolean gotPower() {
		return this.healthPoints > 0;
	}

	public void power() {
		GameModele.pirateBoat.heal(this.healthPointsToHeal);
	}

	public void die() {
		super.die();
	}

	public void takeDamage(int damage) {
		this.healthPoints -= damage;

	}

	public int getHealth() {
		return this.healthPoints;
	}

}
