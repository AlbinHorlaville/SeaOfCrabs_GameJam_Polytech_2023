package info3.game.vue.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import info3.game.GameState;
import info3.game.SeaOfCrabes;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponent;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class ScoreView extends View {

	UIButton buttonRetour, buttonScore;
	UITitle title;

	public ScoreView(GameView gv) {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		buttonRetour = new UIButton(50, windowHeight - 100, 200, 70, new UILabel(0, 0, "Back", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_RED);

		buttonScore = new UIButton(windowWidth / 2 - 150, windowHeight / 2, 300, 70,
				new UILabel(0, 0, "See World Ranking", FONT1, Color.black), UIButton.BACKGROUND_COLOR_YELLOW);

		title = new UITitle(windowWidth, windowHeight, "Score", FONT2, Color.black);

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

		buttonScore.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
				Desktop desktop = java.awt.Desktop.getDesktop();
				try {
					URI oURL = new URI("http://seaofcrabs.000webhostapp.com/pages/ranking.php");
					desktop.browse(oURL);
				} catch (URISyntaxException | IOException e) {
					return;
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonScore.setBackgroundColor(UIButton.BACKGROUND_COLOR_YELLOW_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonScore.setBackgroundColor(UIButton.BACKGROUND_COLOR_YELLOW);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};

		});

		addComponent(buttonRetour);
		addComponent(buttonScore);

		if (GameModele.bestUserScore != null) {
			addComponent(new UILabel(windowWidth / 2 - 200, windowHeight / 2 - 100,
					"My best score : " + GameModele.bestUserScore.toSQLStringFormat(), FONT3, Color.black));
		} else {
			addComponent(new UILabel(windowWidth / 2 - 230, windowHeight / 2 - 50,
					"Please connect to database to see your best score", FONT4, Color.black));
		}

		addComponent(new UILabel(windowWidth / 2 - 280, windowHeight / 2 - 20,
				"You can consult the Sea Of Crabs world ranking by clicking here", FONT4, Color.black));
		addComponent(title);

	}

	public void paint(Graphics g, int width, int height) {
		for (UIComponent c : components) {
			c.paint(g);
		}
		if (SeaOfCrabes.connectedToDatabase) {
			addComponent(new UILabel(10, 30, "Connected to database", FONT4, Color.green));
			if (GameModele.currentUser != null) {
				addComponent(new UILabel(10, 50, "@" + GameModele.currentUser.getUsername(), FONT4, Color.black));
			}
		} else {
			addComponent(new UILabel(10, 30, "Not connected to database", FONT4, Color.red));
		}
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
