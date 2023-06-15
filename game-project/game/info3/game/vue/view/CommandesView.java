package info3.game.vue.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.GameState;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UIImage;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class CommandesView extends View{

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

	public CommandesView(GameView gv) throws IOException {
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

		title = new UITitle(windowWidth, windowHeight, "Commandes du joueur n°1", FONT4, Color.white);
		
		labelCmdGenerales = new UILabel(460, 490, "Commandes générales", FONT1, Color.white);

		buttonRetour = new UIButton(50, windowHeight - 100, 200, new UILabel(0, 0, "Retour", FONT1, Color.black),UIButton.BACKGROUND_COLOR_RED);
		switchCommand = new UIButton(50, windowHeight - 200, 200, new UILabel(0, 0, "Voir joueur n°2", FONT1, Color.black),UIButton.BACKGROUND_COLOR_YELLOW);

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
					switchCommandText = "Voir joueur n°2";
					title.setText("Commandes du joueur n°1");
					imageCJ.setImage(imageCJ1);
				} else {
					switchCommandText = "Voir joueur n°1";
					title.setText("Commandes du joueur n°2");
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
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
	}

}
