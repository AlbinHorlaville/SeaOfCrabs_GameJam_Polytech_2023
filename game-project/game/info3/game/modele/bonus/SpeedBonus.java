package info3.game.modele.bonus;

import info3.game.GameState;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;
import info3.game.modele.map.MapSection;
import info3.game.vue.GameView;
import info3.game.vue.avatar.BonusAvatar;
import info3.game.vue.view.PlayingView;

public class SpeedBonus extends Bonus {

	public final float BONUS_APPLIED = 0.5f;

	public SpeedBonus(MapSection mapSection) {
		super(mapSection);
		setAvatar(new BonusAvatar(this));
	}

	@Override
	public void power() {
		GameModele.player1.addSpeedCoeff(this.BONUS_APPLIED);
		((PlayingView) GameView.all_views.get(GameState.Jeu)).speedBonusLabel
				.setText(Float.toString(GameModele.player1.getSpeedCoeff()));
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

}
