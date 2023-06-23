package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import info3.game.GameState;
import info3.game.SeaOfCrabes;
import info3.game.modele.GameModele;
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

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();
		int widthButton = 200;
		int xButton = (windowWidth - widthButton) / 2;
		int SpaceBetweenYButton = 100;
		int yButton = 300;
		
		buttonPlay = new UIButton(xButton, yButton, widthButton,70, new UILabel(0, 0, "Play", FONT1, Color.black),UIButton.BACKGROUND_COLOR_BLUE);
		buttonSettings = new UIButton(xButton, yButton += SpaceBetweenYButton, widthButton,70,
				new UILabel(0, 0, "Settings", FONT1, Color.black),UIButton.BACKGROUND_COLOR_BLUE);
		buttonScore = new UIButton(xButton, yButton += SpaceBetweenYButton, widthButton,70,
				new UILabel(0, 0, "Score", FONT1, Color.black),UIButton.BACKGROUND_COLOR_BLUE);
		buttonCredits = new UIButton(xButton, yButton += SpaceBetweenYButton, widthButton,70,
				new UILabel(0, 0, "Credits", FONT1, Color.black),UIButton.BACKGROUND_COLOR_BLUE);

		title = new UITitle(windowWidth, windowHeight, "SEA OF CRABS", FONT2, Color.black);

		buttonPlay.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				try {
					gameView.getGame().choiceGameplay();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonPlay.setBackgroundColor(UIButton.BACKGROUND_COLOR_BLUE_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonPlay.setBackgroundColor(UIButton.BACKGROUND_COLOR_BLUE);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		buttonSettings.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
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
			public void onComponentMouseIn(int x, int y) {
				buttonSettings.setBackgroundColor(UIButton.BACKGROUND_COLOR_BLUE_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonSettings.setBackgroundColor(UIButton.BACKGROUND_COLOR_BLUE);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};

		});

		buttonScore.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
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
			public void onComponentMouseIn(int x, int y) {
				buttonScore.setBackgroundColor(UIButton.BACKGROUND_COLOR_BLUE_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonScore.setBackgroundColor(UIButton.BACKGROUND_COLOR_BLUE);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};
		});

		buttonCredits.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
				// gameView.update_view(GameState.Credits);
				// gameView.getGame().setCurrentState(GameState.Credits);
				try {
					gameView.getGame().credits();
					((CreditsView)gameView.getViewByName(GameState.Credits)).initYPositionLabel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonCredits.setBackgroundColor(UIButton.BACKGROUND_COLOR_BLUE_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonCredits.setBackgroundColor(UIButton.BACKGROUND_COLOR_BLUE);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};

		});

		// on ajoute les différents components
		addComponent(buttonPlay);
		addComponent(buttonScore);
		addComponent(buttonSettings);
		addComponent(buttonCredits);
		addComponent(title);
		/*if (GameModele.user!=null) {
			addComponent(new UILabel(10, 30, "Connected as @"+GameModele.user.getUsername(), FONT4, Color.black));
		}*/
		if (SeaOfCrabes.connectedToDatabase) {
			addComponent(new UILabel(10, 30, "Connected to database", FONT4, Color.green));
		} else {
			addComponent(new UILabel(10, 30, "Not connected to database", FONT4, Color.red));
		}
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
