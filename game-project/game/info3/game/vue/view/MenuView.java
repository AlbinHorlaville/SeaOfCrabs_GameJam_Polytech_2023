package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import info3.game.GameState;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIChecker;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UICursor;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class MenuView extends View {

	UIButton buttonPlay;
	UIButton buttonSettings;
	UIButton buttonScore;
	UIButton buttonCredits;
	UICursor cursor;
	UITitle title;
	UIChecker checker;

	public MenuView(GameView gv) {
		super(gv);

		buttonPlay = new UIButton(412, 300, 200, new UILabel(0, 0, "Play", FONT1, c1), c2);
		buttonSettings = new UIButton(412, 400, 200, new UILabel(0, 0, "Settings", FONT1, c1), c2);
		buttonScore = new UIButton(412, 500, 200, new UILabel(0, 0, "Score", FONT1, c1), c2);
		buttonCredits = new UIButton(412, 600, 200, new UILabel(0, 0, "Credits", FONT1, c1), c2);

		// cursor = new UICursor(300, 300, 100, 400, Color.black, Color.red);
		title = new UITitle(1024, 768, "SEA OF CRABS", FONT2, Color.white);
		// checker = new UIChecker(400, 400, new UILabel(0, 0, "Checker", FONT1,
		// Color.white), Color.black);

		buttonPlay.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				try {
					gameView.getGame().start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn() {
				buttonPlay.setBackgroundColor(c1);
				buttonPlay.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				buttonPlay.setBackgroundColor(c2);
				buttonPlay.setForegroundColor(c1);
			}

			public void onComponentPressed(int x, int y) {
			};

		});

		buttonSettings.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				// gameView.update_view(GameState.Parametre);
				// gameView.getGame().setCurrentState(GameState.Parametre);
				try {
					gameView.getGame().param();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn() {
				buttonSettings.setBackgroundColor(c1);
				buttonSettings.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				buttonSettings.setBackgroundColor(c2);
				buttonSettings.setForegroundColor(c1);
			}

			public void onComponentPressed(int x, int y) {
			};

		});

		buttonScore.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				// gameView.update_view(GameState.Score);
				// gameView.getGame().setCurrentState(GameState.Score);
				try {
					gameView.getGame().score();
					;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn() {
				buttonScore.setBackgroundColor(c1);
				buttonScore.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				buttonScore.setBackgroundColor(c2);
				buttonScore.setForegroundColor(c1);
			}

			public void onComponentPressed(int x, int y) {
			};
		});

		buttonCredits.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				// gameView.update_view(GameState.Credits);
				// gameView.getGame().setCurrentState(GameState.Credits);
				try {
					gameView.getGame().credits();
					;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn() {
				buttonCredits.setBackgroundColor(c1);
				buttonCredits.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				buttonCredits.setBackgroundColor(c2);
				buttonCredits.setForegroundColor(c1);
			}

			public void onComponentPressed(int x, int y) {
			};

		});

		// on ajoute les différents components
		addComponent(buttonPlay);
		addComponent(buttonScore);
		addComponent(buttonSettings);
		addComponent(buttonCredits);
		addComponent(title);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
