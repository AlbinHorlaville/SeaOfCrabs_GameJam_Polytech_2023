package info3.game.vue.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import info3.game.GameState;
import info3.game.SeaOfCrabes;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponent;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UICursor;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UIMoveableText;
import info3.game.vue.toolkitUI.UIParagraph;
import info3.game.vue.toolkitUI.UITitle;

public class CreditsView extends View {

	UIButton buttonRetour, buttonWebsite;
	UITitle title;
	UIParagraph credits;
	UIMoveableText creditsPanel;
	UICursor cursor;

	private File creditsFile;

	public CreditsView(GameView gv) {
		super(gv);
		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		creditsFile = new File("resources/Credits");

		buttonRetour = new UIButton(50, windowHeight - 100, 200, 70, new UILabel(0, 0, "Back", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_RED);
		title = new UITitle(windowWidth, windowHeight, "Credits", FONT2, Color.black);
		
		
		buttonWebsite = new UIButton(774, windowHeight - 100, 200, 70, new UILabel(0, 0, "Website", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_YELLOW);

		credits = new UIParagraph(readCreditsFile());

		creditsPanel = new UIMoveableText(150, 150, 400, 724, credits);

		credits.setPanel(creditsPanel);

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
		
		buttonWebsite.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
				Desktop desktop = java.awt.Desktop.getDesktop();
				try {
					URI oURL = new URI("http://seaofcrabs.000webhostapp.com/pages/home.php");
					desktop.browse(oURL);
				} catch (URISyntaxException | IOException e) {
					return;
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonWebsite.setBackgroundColor(UIButton.BACKGROUND_COLOR_YELLOW_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonWebsite.setBackgroundColor(UIButton.BACKGROUND_COLOR_YELLOW);
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
		addComponent(creditsPanel);
		addComponent(buttonWebsite);
		if (SeaOfCrabes.connectedToDatabase) {
			addComponent(new UILabel(10, 30, "Connected to database: @"+GameModele.currentUser.getUsername(), FONT4, Color.green));
		} else {
			addComponent(new UILabel(10, 30, "Not connected to database", FONT4, Color.red));
		}
	}

	public void paint(Graphics g, int width, int height) {
		g.setColor(Color.black);
		for (UIComponent c : components) {
			c.paint(g);
		}
	}

	public String readCreditsFile() {
		try {
			Scanner myReader = new Scanner(this.creditsFile);
			String stringCredits = new String();
			int i = 0;
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				stringCredits += "\n" + line;
			}
			myReader.close();

			return stringCredits;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public void initYPositionLabel() {
		this.credits.setLabelsPositionsY(this.creditsPanel.getPositionY() + this.creditsPanel.getHeight());
	}

	@Override
	public void tick(long elapsed) {
	}

}
