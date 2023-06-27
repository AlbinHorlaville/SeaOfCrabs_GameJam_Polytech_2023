package info3.game.vue.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.GameState;
import info3.game.SeaOfCrabes;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponent;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UIImage;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class ControlsView extends View{

	UIButton buttonRetour;
	UIImage imageCJ;
	UIImage uiImageCG;
	BufferedImage imageCJ1;
	BufferedImage imageCJ2;
	BufferedImage imageCG;
	UIButton switchCommand;
	String switchCommandText;
	UITitle title;
	UILabel labelCmdGenerales;
	boolean state;

	public ControlsView(GameView gv) throws IOException {
		super(gv);

		state = true;

		File fileCJ1 = new File("resources/img/CommandesJoueur1.png");
		File fileCJ2 = new File("resources/img/CommandesJoueur2.png");
		File fileCG = new File("resources/img/CommandesGenerales.png");

		try {
			imageCJ1 = ImageIO.read(fileCJ1);
			imageCJ2 = ImageIO.read(fileCJ2);
			imageCG = ImageIO.read(fileCG);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		title = new UITitle(windowWidth, windowHeight, "Player 1's controls", FONT2, Color.black);
		
		labelCmdGenerales = new UILabel(460, 490, "General controls", FONT1, Color.black);

		buttonRetour = new UIButton(50, windowHeight - 100, 200,70, new UILabel(0, 0, "Back", FONT1, Color.black),UIButton.BACKGROUND_COLOR_RED);
		switchCommand = new UIButton(50, windowHeight - 200, 200,70, new UILabel(0, 0, "See player 2", FONT1, Color.black),UIButton.BACKGROUND_COLOR_YELLOW);

		imageCJ = new UIImage((windowWidth - imageCJ1.getWidth()) / 2, 125, "resources/img/CommandesJoueur1.png", 1);
		uiImageCG = new UIImage(400, 500, "resources/img/CommandesGenerales.png", 1);

		buttonRetour.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
				gameView.update_view(GameState.AvantJeu);
				gameView.getGame().setCurrentState(GameState.AvantJeu);
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

		switchCommand.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
				state = !state;
				if (state) {
					switchCommandText = "See player 2";
					title.setText("Player 1's controls");
					imageCJ.setImage(imageCJ1);
				} else {
					switchCommandText = "See player 1";
					title.setText("Player 2's controls");
					imageCJ.setImage(imageCJ2);
				}
				switchCommand.setLabel(new UILabel(0, 0, switchCommandText, FONT1, c1));
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				switchCommand.setBackgroundColor(UIButton.BACKGROUND_COLOR_YELLOW_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				switchCommand.setBackgroundColor(UIButton.BACKGROUND_COLOR_YELLOW);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};

		});

		imageCJ.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
			};

		});

		uiImageCG.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
			};

		});

		addComponent(buttonRetour);
		addComponent(imageCJ);
		addComponent(uiImageCG);
		addComponent(switchCommand);
		addComponent(title);
		addComponent(labelCmdGenerales);
		
	}
	
	@Override
	public void paint(Graphics g, int width, int height) {
		if (GameModele.currentScore!=null) {
			if (SeaOfCrabes.connectedToDatabase) {
				addComponent(new UILabel(10, 30, "Connected to database", FONT4, Color.green));
				addComponent(new UILabel(10, 50, "@"+GameModele.currentUser.getUsername(), FONT4, Color.black));
			} else {
				addComponent(new UILabel(10, 30, "Not connected to database", FONT4, Color.red));
			}
		}
		if (GameModele.solo) {
			title.setText("Controls");
		} else {
			if (state) {
				title.setText("Player 1's controls");
			} else {
				title.setText("Player 2's controls");
			}
		}
		for (UIComponent c : components) {
			if (c == switchCommand  && GameModele.solo) continue;
			c.paint(g);
		}
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
	}

}
