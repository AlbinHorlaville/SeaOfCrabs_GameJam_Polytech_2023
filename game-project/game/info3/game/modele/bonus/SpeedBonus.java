package info3.game.modele.bonus;

import info3.game.modele.Bonus;
import info3.game.modele.GameModele;

public class SpeedBonus extends Bonus {

	public SpeedBonus(int level) {
		super(level);
	}

	@Override
	public void power() {
		GameModele.player1.addSpeedCoeff(this.BONUS_APPLIED);
	}

}
