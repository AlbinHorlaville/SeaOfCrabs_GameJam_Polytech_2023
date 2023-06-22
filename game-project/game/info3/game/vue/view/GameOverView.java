package info3.game.vue.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import info3.game.GameState;
import info3.game.SeaOfCrabes;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class GameOverView extends View {
	
	UITitle title;
	UIButton buttonMenu, buttonReplay;

	public GameOverView(GameView gv) {
		super(gv);
		
		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		title = new UITitle(windowWidth, windowHeight, "GAME OVER", FONT2, Color.black);
		buttonMenu = new UIButton(50, windowHeight - 100, 200, 70, new UILabel(0, 0, "Menu", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_RED);
		
		buttonReplay = new UIButton(774, windowHeight - 100, 200, 70, new UILabel(0, 0, "Play again", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_GREEN);

		buttonMenu.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				gv.update_view(GameState.Menu);
				gv.getGame().setCurrentState(GameState.Menu);
				GameModele.reset();
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonMenu.setBackgroundColor(UIButton.BACKGROUND_COLOR_RED_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonMenu.setBackgroundColor(UIButton.BACKGROUND_COLOR_RED);
			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		
		buttonReplay.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				gv.update_view(GameState.AvantJeu);
				gv.getGame().setCurrentState(GameState.AvantJeu);
				GameModele.reset();
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonReplay.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonReplay.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN);
			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		
		addComponent(title);
		addComponent(buttonMenu);
		addComponent(buttonReplay);
		if (SeaOfCrabes.connectedToDatabase) {
			addComponent(new UILabel(10, 30, "Connected to database", FONT4, Color.red));
		} else {
			addComponent(new UILabel(10, 30, "Not connected to database", FONT4, Color.red));
		}
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
