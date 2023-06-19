package info3.game.vue.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.GameState;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UIImage;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class GameModeView extends View {

	UIButton buttonRetour, buttonSolo, buttonCoop;
	UITitle title;

	public GameModeView(GameView gv) throws IOException {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		title = new UITitle(windowWidth, windowHeight, "Select a game mode", FONT2, Color.black);

		buttonRetour = new UIButton(50, windowHeight - 100, 200,70, new UILabel(0, 0, "Back", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_RED);

		buttonSolo = new UIButton(74, 300, 400,120, new UILabel(0, 0, "Solo\n( 1 player)", FONT3, Color.black), UIButton.BACKGROUND_COLOR_GREEN);
		buttonCoop = new UIButton(548, 300, 400,120, new UILabel(0, 0, "Cooperation\n( 2 players)", FONT3, Color.black), UIButton.BACKGROUND_COLOR_CYAN);

		buttonRetour.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
				gameView.update_view(GameState.Menu);
				gameView.getGame().setCurrentState(GameState.Menu);
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonRetour.setBackgroundColor(UIButton.BACKGROUND_COLOR_RED_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonRetour.setBackgroundColor(UIButton.BACKGROUND_COLOR_RED);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};

		});

		buttonSolo.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
				try {
					GameModele.solo = true;
					gameView.getGame().beforePlaying();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonSolo.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonSolo.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};

		});

		buttonCoop.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
				try {
					GameModele.solo = false;
					gameView.getGame().beforePlaying();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonCoop.setBackgroundColor(UIButton.BACKGROUND_COLOR_CYAN_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonCoop.setBackgroundColor(UIButton.BACKGROUND_COLOR_CYAN);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};

		});

		addComponent(buttonRetour);
		addComponent(title);
		addComponent(buttonSolo);
		addComponent(buttonCoop);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
