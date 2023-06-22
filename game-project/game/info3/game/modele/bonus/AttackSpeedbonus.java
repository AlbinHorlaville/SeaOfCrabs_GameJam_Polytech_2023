package info3.game.modele.bonus;

import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;
import info3.game.modele.map.MapSection;
import info3.game.vue.avatar.BonusAvatar;

public class AttackSpeedbonus extends Bonus{
	
	public AttackSpeedbonus(MapSection mapSection) {
		super(mapSection);
		setAvatar(new BonusAvatar(this));
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
