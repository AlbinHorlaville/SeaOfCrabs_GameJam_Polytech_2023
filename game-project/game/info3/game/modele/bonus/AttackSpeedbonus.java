package info3.game.modele.bonus;

import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;

public class AttackSpeedbonus extends Bonus{
	
	public AttackSpeedbonus(int level, int x, int y) {
		super(level, x, y);
		
	}

	@Override
	public void power() {
		GameModele.player1.addAttackpeedCoeff(BONUS_APPLIED, getLevel());
	}

	@Override
	public void move() {
		setX(getX()+1);
	}

}
