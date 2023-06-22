package info3.game.modele.bonus;

import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;
import info3.game.modele.map.MapSection;
import info3.game.vue.avatar.BonusAvatar;

public class DamageBonus extends Bonus {
//
//	public DamageBonus(MapSection mapSection, int x, int y) {
//		super(mapSection, x, y);
//		setAvatar(new BonusAvatar(this));
//	}
	
	public DamageBonus(MapSection mapSection) {
		super(mapSection);
		setAvatar(new BonusAvatar(this));
	}


	@Override
	public void power() {
		GameModele.player1.addDamageCoeff(BONUS_APPLIED);
	}

	@Override
	public void move() {
		setX(getX()+1);
	}
}
