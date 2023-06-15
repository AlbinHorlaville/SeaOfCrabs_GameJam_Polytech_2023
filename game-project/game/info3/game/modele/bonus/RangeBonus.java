package info3.game.modele.bonus;

import info3.game.modele.Bonus;
import info3.game.modele.GameModele;

public class RangeBonus extends Bonus {

	public RangeBonus(int level) {
		super(level);
	}

	@Override
	public void power() {
		GameModele.player1.addRangeCoeff(BONUS_APPLIED);
	}
}
