package info3.game.vue.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import info3.game.GameState;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class ScoreView extends View {

	UIButton buttonRetour;
	UITitle title;
	ArrayList<UILabel> listScore;

	public ScoreView(GameView gv) {
		super(gv);
		
		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		buttonRetour = new UIButton(50, windowHeight - 100, 200,70, new UILabel(0, 0, "Back", FONT1, Color.black),UIButton.BACKGROUND_COLOR_RED);
		title = new UITitle(windowWidth, windowHeight, "Score", FONT2, Color.black);
		listScore = readScoreFile();

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

		addComponent(buttonRetour);
		addComponent(title);
		if (GameModele.user!=null) {
			addComponent(new UILabel(10, 30, "Connected as @"+GameModele.user.getUsername(), FONT4, Color.black));
		}
		for (UILabel x : listScore) {
			addComponent(x);
		}

	}

	ArrayList<UILabel> readScoreFile() {
		try {
			File score = new File("resources/scorefile.txt");
			Scanner myReader = new Scanner(score);
			ArrayList<UILabel> listeScore = new ArrayList<UILabel>();
			int i = 0;
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				UILabel lineLabel = new UILabel(250, 250 + i, line, FONT1, Color.white);
				i += 50;
				listeScore.add(lineLabel);
			}
			myReader.close();

			return listeScore;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
