package info3.game.modele.bonus;

import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;
import info3.game.modele.map.MapSection;
import info3.game.vue.avatar.BonusAvatar;

public class SpeedBonus extends Bonus {
	
	public SpeedBonus(MapSection mapSection) {
		super(mapSection);
		setAvatar(new BonusAvatar(this));
	}

	@Override
	public void power() {
		GameModele.player1.addSpeedCoeff(this.BONUS_APPLIED);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
