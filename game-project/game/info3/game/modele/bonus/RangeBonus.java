package info3.game.modele.bonus;

import automate.AutomateLoader;
import info3.game.GameState;
import info3.game.modele.GameEntity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.Bonus;
import info3.game.modele.map.MapSection;
import info3.game.vue.GameView;
import info3.game.vue.avatar.BonusAvatar;
import info3.game.vue.view.PlayingView;

public class RangeBonus extends Bonus {

	public final float BONUS_APPLIED = 1;

	public RangeBonus(MapSection mapSection) {
		super(mapSection);
		setAvatar(new BonusAvatar(this));
	}

	@Override
	public void power() {
		GameModele.player1.addRangeCoeff(BONUS_APPLIED);
		((PlayingView) GameView.all_views.get(GameState.Jeu)).rangeBonusLabel
				.setText(Float.toString(GameModele.player1.getRangeCoeff()));
	}

	@Override
	public void move() {
		setX(getX() + 1);
	}
}
