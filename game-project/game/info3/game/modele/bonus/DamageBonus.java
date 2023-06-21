package info3.game.modele.bonus;

import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;
import info3.game.vue.avatar.BonusAvatar;

public class DamageBonus extends Bonus {

	public DamageBonus(int level, int x, int y) {
		super(level, x, y);
		setAvatar(new BonusAvatar(this));
	}
	
	public DamageBonus(int level) {
		super(level);
		setAvatar(new BonusAvatar(this));
	}


	@Override
	public void power() {
		GameModele.player1.addDamageCoeff(BONUS_APPLIED, getLevel());
	}

	@Override
	public void move() {
		setX(getX()+1);
	}
}
