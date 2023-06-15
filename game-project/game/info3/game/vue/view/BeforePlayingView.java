package info3.game.vue.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import info3.game.GameState;
import info3.game.modele.Scythe;
import info3.game.modele.Sword;
import info3.game.modele.Weapon;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIBox;
import info3.game.vue.toolkitUI.UIBoxes;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UIImage;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITextInput;

public class BeforePlayingView extends View {

	UIButton buttonPlay, buttonCmd, buttonRandomSeed, buttonRetour;
	UILabel seedLabel, weaponLabelPlayer1, weaponLabelPlayer2;
	UITextInput seedInput;
	UIBoxes weaponsBoxesPlayer1, weaponsBoxesPlayer2;
	UILabel weaponSelectedLabel1, weaponSelectedLabel2;

	UIBox boxSwordPlayer1, boxScythePlayer1, boxSwordPlayer2, boxScythePlayer2;

	public BeforePlayingView(GameView gv) {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		buttonRetour = new UIButton(50, windowHeight - 100, 200, new UILabel(0, 0, "Retour", FONT1, Color.black), UIButton.BACKGROUND_COLOR_RED);

		buttonPlay = new UIButton(774, windowHeight - 100, 200, new UILabel(0, 0, "Jouer", FONT1, Color.black), UIButton.BACKGROUND_COLOR_GREEN);
		buttonCmd = new UIButton(400, windowHeight - 100, 200, new UILabel(0, 0, "Commandes", FONT1, Color.black), UIButton.BACKGROUND_COLOR_YELLOW);
		buttonRandomSeed = new UIButton(774, 65, 200, new UILabel(0, 0, "Graine aléatoire", FONT1, Color.black),UIButton.BACKGROUND_COLOR_PURPLE);
		seedLabel = new UILabel(50, 107, "Graine pour la génération du monde :", FONT1, c2);
		seedInput = new UITextInput(500, 65, 200, "Entrer un nombre", UIButton.BACKGROUND_COLOR_YELLOW, Color.black);

		weaponLabelPlayer1 = new UILabel(50, 221, "Arme du joueur n°1 : ", FONT1, c2);
		weaponLabelPlayer2 = new UILabel(50, 321, "Arme du joueur n°2 : ", FONT1, c2);

		boxSwordPlayer1 = new UIBox(96, Sword.getInstance(), new UIImage(0, 0, "resources/img/Sword.png", 2F));
		boxScythePlayer1 = new UIBox(96, Scythe.getInstance(), new UIImage(0, 0, "resources/img/Scythe.png", 2F));
		boxSwordPlayer2 = new UIBox(96, Sword.getInstance(), new UIImage(0, 0, "resources/img/Sword.png", 2F));
		boxScythePlayer2 = new UIBox(96, Scythe.getInstance(), new UIImage(0, 0, "resources/img/Scythe.png", 2F));

		weaponSelectedLabel1 = new UILabel(550, 221, "", FONT1, Color.black);
		weaponSelectedLabel2 = new UILabel(550, 321, "", FONT1, Color.black);

		weaponsBoxesPlayer1 = new UIBoxes(320, 170, weaponSelectedLabel1);

		weaponsBoxesPlayer1.addBox(boxSwordPlayer1);
		weaponsBoxesPlayer1.addBox(boxScythePlayer1);
		weaponsBoxesPlayer1.setSelectedBox(boxSwordPlayer1);

		weaponsBoxesPlayer2 = new UIBoxes(320, 270, weaponSelectedLabel2);
		weaponsBoxesPlayer2.addBox(boxSwordPlayer2);
		weaponsBoxesPlayer2.addBox(boxScythePlayer2);
		weaponsBoxesPlayer2.setSelectedBox(boxSwordPlayer2);

		buttonPlay.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				Random r = new Random();
				int i = r.nextInt();
				try {
					if (!seedInput.isTextEmpty()) {
						try {
							Integer.valueOf(seedInput.getInputText());
						} catch (NumberFormatException e) {
							seedInput.setInputText(Integer.toString(i));
							gameView.getGame().start(i);
						}
						// TODO : relier la vue au modele avant d'appeler start
						gameView.getGame().start(Integer.valueOf(seedInput.getInputText()));
					} else {
						seedInput.setInputText(Integer.toString(i));
						// TODO : relier la vue au modele avant d'appeler start
						gameView.getGame().start(i);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonPlay.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonPlay.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN);
			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		buttonRetour.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				gameView.update_view(GameState.AvantJeu);
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

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		buttonCmd.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				try {
					gameView.getGame().commandes();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonCmd.setBackgroundColor(UIButton.BACKGROUND_COLOR_YELLOW_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonCmd.setBackgroundColor(UIButton.BACKGROUND_COLOR_YELLOW);
			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		buttonRandomSeed.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				Random r = new Random();
				int i = r.nextInt();
				seedInput.setInputText(Integer.toString(i));
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonRandomSeed.setBackgroundColor(UIButton.BACKGROUND_COLOR_PURPLE_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonRandomSeed.setBackgroundColor(UIButton.BACKGROUND_COLOR_PURPLE);
			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		weaponSelectedLabel1.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		weaponSelectedLabel2.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		addComponent(buttonPlay);
		addComponent(buttonRetour);
		addComponent(buttonCmd);
		addComponent(seedLabel);
		addComponent(seedInput);
		addComponent(buttonRandomSeed);
		addComponent(weaponLabelPlayer1);
		addComponent(weaponLabelPlayer2);
		addComponent(weaponsBoxesPlayer1);
		addComponent(weaponsBoxesPlayer2);
		addComponent(weaponSelectedLabel1);
		addComponent(weaponSelectedLabel2);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
