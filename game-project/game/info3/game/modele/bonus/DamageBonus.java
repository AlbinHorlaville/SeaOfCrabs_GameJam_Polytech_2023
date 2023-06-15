package info3.game.modele.bonus;

import info3.game.modele.Bonus;
import info3.game.modele.GameModele;

public class DamageBonus extends Bonus {

	public DamageBonus(int level) {
		super(level);
	}

	@Override
	public void power() {
		GameModele.player1.addDamageCoeff(BONUS_APPLIED);
	}
}
