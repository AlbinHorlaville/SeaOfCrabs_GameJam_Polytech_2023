package info3.game.modele.bonus;

import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;
import info3.game.modele.map.MapSection;
import info3.game.vue.avatar.BonusAvatar;

public class DamageBonus extends Bonus {
	
	public final float BONUS_APPLIED = 1;
	
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
