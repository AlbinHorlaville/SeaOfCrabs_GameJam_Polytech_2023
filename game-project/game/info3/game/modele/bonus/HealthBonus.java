package info3.game.modele.bonus;

import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;

public class HealthBonus extends Bonus {
	
	public HealthBonus(int level) {
		super(level);
	}

	@Override
	public void power() {
		GameModele.player1.addMaxLifePointsCoeff(this.BONUS_APPLIED);
	}

}
