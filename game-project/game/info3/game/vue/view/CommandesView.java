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
	boolean state;
	

	public CommandesView(GameView gv) throws IOException {
		super(gv);

		state = false;
		
		File fileCJ1 = new File("resources/CommandesJoueur1.png");
		File fileCJ2 = new File("resources/CommandesJoueur2.png");
		File fileCG = new File("resources/CommandesGénérales.png");
		try {
			imageCJ1 = ImageIO.read(fileCJ1);
			imageCJ2 = ImageIO.read(fileCJ2);
			imageCG = ImageIO.read(fileCG);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int windowWidth = (int) gameView.getWidthCanvas();
		
		buttonRetour = new UIButton(100, 600, 200, new UILabel(0, 0, "Retour", FONT1, c1), c2);
		switchCommand = new UIButton(windowWidth/2 - 100, 50, 200, new UILabel(0, 0, "Commandes J1", FONT1, c1), c2);
		
		imageCJ = new UIImage((windowWidth - imageCJ1.getWidth())/2, 125, "resources/CommandesJoueur1.png", 1);
		uiImageCG = new UIImage(400, 500, "resources/CommandesGénérales.png", 1);
		
		
		buttonRetour.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				gameView.update_view(GameState.Menu);
				gameView.getGame().setCurrentState(GameState.Menu);
			}

			@Override
			public void onComponentMouseIn() {
				buttonRetour.setBackgroundColor(c1);
				buttonRetour.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				buttonRetour.setBackgroundColor(c2);
				buttonRetour.setForegroundColor(c1);
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
			public void onComponentClicked() {
				state = !state;
				if (state) {
					switchCommandText = "Commandes J1";
					imageCJ.setImage(imageCJ1);
				}
				else {
					switchCommandText = "Commandes J2";
					imageCJ.setImage(imageCJ2);
				}
				switchCommand.setLabel(new UILabel(0, 0, switchCommandText, FONT1, c1));
			}

			@Override
			public void onComponentMouseIn() {
				switchCommand.setBackgroundColor(c1);
				switchCommand.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				switchCommand.setBackgroundColor(c2);
				switchCommand.setForegroundColor(c1);
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
			public void onComponentClicked() {
			}

			@Override
			public void onComponentMouseIn() {
			}

			@Override
			public void onComponentMouseOut() {
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
			};

		});
		
		uiImageCG.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
			}

			@Override
			public void onComponentMouseIn() {
			}

			@Override
			public void onComponentMouseOut() {
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
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		
	}

}
