package info3.game.vue.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import info3.game.GameState;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class CreditsView extends View {

	UIButton buttonRetour;
	UITitle title;
	ArrayList<UILabel> listCredits;
	UILabel credits;

	public CreditsView(GameView gv) {
		super(gv);		
		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();
		
		buttonRetour = new UIButton(50, windowHeight - 100, 200,70, new UILabel(0, 0, "Retour", FONT1, Color.black),UIButton.BACKGROUND_COLOR_RED);
		title = new UITitle(windowWidth, windowHeight, "Credits", FONT2, Color.white);
		/*
		 * noms1 = new UILabel(200, 250, "Romain MIRAS," + " Albin HORLAVILLE," +
		 * " Axel COLE, " + " Brice DECURNINGE,", FONT1, Color.white); noms2 = new
		 * UILabel(250, 300, " Alexandre ARLE," + " Rémi DEL MEDICO," +
		 * " Emineh GUNDOGAN", FONT1, Color.white);
		 */
		// credits = new String(new File());
		listCredits = readCreditsFile();

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
		for (UILabel x : listCredits) {
			addComponent(x);
		}
	}

	ArrayList<UILabel> readCreditsFile() {
		try {
			File credits = new File("resources/Credits");
			Scanner myReader = new Scanner(credits);
			ArrayList<UILabel> listeCredits = new ArrayList<UILabel>();
			int i = 0;
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				UILabel lineLabel = new UILabel(250, 250 + i, line, FONT1, Color.white);
				i += 50;
				listeCredits.add(lineLabel);
			}
			myReader.close();

			return listeCredits;
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
