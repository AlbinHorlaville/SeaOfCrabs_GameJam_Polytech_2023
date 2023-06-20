package info3.game.modele.bonus;

import automate.AutomateLoader;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;
import info3.game.vue.avatar.BonusAvatar;

public class HealthBonus extends Bonus {
	
	public HealthBonus(int level, int x, int y) {
		super(level, x, y);
		setAvatar(new BonusAvatar(this));
	}

	@Override
	public void power() {
		GameModele.player1.addMaxLifePointsCoeff(this.BONUS_APPLIED, getLevel());
	}

	@Override
	public void move() {
		setX(getX()+1);
	}

}
