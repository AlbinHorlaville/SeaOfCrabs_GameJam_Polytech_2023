package info3.game.modele.bonus;

import info3.game.modele.Bonus;
import info3.game.modele.GameModele;

public class HealthBonus extends Bonus {
	
	public HealthBonus(int level) {
		super(level);
	}

	@Override
	public void power() {
		GameModele.player1.addMaxLifePointsCoeff(this.BONUS_APPLIED);
	}

}
