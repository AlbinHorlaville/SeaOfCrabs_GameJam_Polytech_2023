package info3.game.modele.bonus;

import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;

public class SpeedBonus extends Bonus {

	public SpeedBonus(int level) {
		super(level);
	}

	@Override
	public void power() {
		GameModele.player1.addSpeedCoeff(this.BONUS_APPLIED);
	}

}
