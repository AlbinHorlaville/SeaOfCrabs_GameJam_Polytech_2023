package info3.game.modele.bonus;

import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;

public class AttackSpeedbonus extends Bonus{
	
	public AttackSpeedbonus(int level) {
		super(level);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void power() {
		GameModele.player1.addAttackSpeedCoeff(BONUS_APPLIED);
	}

}
