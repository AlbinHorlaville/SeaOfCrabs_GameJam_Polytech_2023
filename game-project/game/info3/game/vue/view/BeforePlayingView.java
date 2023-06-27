package info3.game.vue.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import info3.game.GameState;
import info3.game.SeaOfCrabes;
import info3.game.modele.GameModele;
import info3.game.modele.Weapon;
import info3.game.modele.MoveableEntityClass.Perroquet;
import info3.game.modele.MoveableEntityClass.Scythe;
import info3.game.modele.MoveableEntityClass.Sword;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;
import info3.game.vue.toolkitUI.UIBox;
import info3.game.vue.toolkitUI.UIBoxes;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIChecker;
import info3.game.vue.toolkitUI.UIComponent;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UIImage;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITextInput;

public class BeforePlayingView extends View {

	UIButton buttonPlay, buttonCmd, buttonRandomSeed, buttonRetour;
	UILabel seedLabel, weaponLabelPlayer1, weaponLabelPlayer2, sectionLabel;
	UITextInput seedInput;
	UIBoxes weaponsBoxesPlayer1, weaponsBoxesPlayer2, sectionBoxes;
	UIChecker perroquetBox;
	UILabel weaponSelectedLabel1, weaponSelectedLabel2, labelParrot;

	UIBox boxSwordPlayer1, boxScythePlayer1, boxSwordPlayer2, boxScythePlayer2, section10, section16, section22;
	

	public static Weapon weapon1;
	public static Weapon weapon2;

	public static Perroquet perroquet;

	public BeforePlayingView(GameView gv) {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		buttonRetour = new UIButton(50, windowHeight - 100, 200, 70, new UILabel(0, 0, "Back", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_RED);

		buttonPlay = new UIButton(774, windowHeight - 100, 200, 70, new UILabel(0, 0, "Play", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_GREEN);
		buttonCmd = new UIButton(400, windowHeight - 100, 200, 70, new UILabel(0, 0, "Controls", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_YELLOW);
		buttonRandomSeed = new UIButton(774, 65, 200, 70, new UILabel(0, 0, "Random seed", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_PURPLE);
		seedLabel = new UILabel(50, 107, "Seed for map generation :", FONT1, Color.black);
		seedInput = new UITextInput(400, 65, 200, "Enter a number", UIButton.BACKGROUND_COLOR_YELLOW, Color.black);

		weaponLabelPlayer1 = new UILabel(50, 221, "Player 1's weapon : ", FONT1, Color.black);
		weaponLabelPlayer2 = new UILabel(50, 321, "Player 2's weapon : ", FONT1, Color.black);

		boxSwordPlayer1 = new UIBox(96, new Sword(), new UIImage(0, 0, "resources/img/Epee.png", 2F));
		boxSwordPlayer2 = new UIBox(96, new Sword(), new UIImage(0, 0, "resources/img/Epee.png", 2F));

		weaponSelectedLabel1 = new UILabel(450, 221, "", FONT1, Color.black);
		weaponSelectedLabel2 = new UILabel(450, 321, "", FONT1, Color.black);

		weaponsBoxesPlayer1 = new UIBoxes(300, 170, weaponSelectedLabel1);
		weaponsBoxesPlayer1.addBox(boxSwordPlayer1);
		weaponsBoxesPlayer1.setSelectedBox(boxSwordPlayer1);

		weaponsBoxesPlayer2 = new UIBoxes(300, 270, weaponSelectedLabel2);
		weaponsBoxesPlayer2.addBox(boxSwordPlayer2);
		weaponsBoxesPlayer2.setSelectedBox(boxSwordPlayer2);
				
		sectionLabel = new UILabel(50, 351, "Section number :", FONT1, Color.black);
		
		sectionBoxes=new UIBoxes(300,314);
		section10 = new UIBox(64, 10);
		section16 = new UIBox(64, 16);
		section22 = new UIBox(64, 22);
		sectionBoxes.addBox(section10);
		sectionBoxes.addBox(section16);
		sectionBoxes.addBox(section22);
		sectionBoxes.setSelectedBox(section10);
		
		labelParrot = new UILabel(50, 451, "Pirate's parrot?", FONT1, Color.black);
		
		perroquetBox = new UIChecker(300, 428,new UILabel(0, 0, "", FONT1, Color.black), c1, false);

		perroquetBox.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				perroquetBox.check();
				if (perroquetBox.isState()) {
					perroquet = new Perroquet(100, 0, 10);
				}
				else {
					perroquet = null;
				}
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

		buttonPlay.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				Random r = new Random(System.currentTimeMillis());
				int i = r.nextInt(200);
				try {
					if (!seedInput.isTextEmpty() && i >= 0 && i <= 200) {
						try {
							i = Integer.valueOf(seedInput.getInputText());
						} catch (NumberFormatException e) {
							i = r.nextInt(200);
							seedInput.setInputText(Integer.toString(i));
						}
						weapon1 = weaponsBoxesPlayer1.getSelectedBox().getWeapon();
						if (!GameModele.solo) {
							weapon2 = weaponsBoxesPlayer2.getSelectedBox().getWeapon();
						}
						// TODO : relier la vue au modele avant d'appeler start
						GameModele.seed = i;
						GameModele.section = sectionBoxes.getSelectedBox().getValue();
						gameView.getGame().start();
					} else {
						seedInput.setInputText(Integer.toString(i));
						// TODO : relier la vue au modele avant d'appeler start
						weapon1 = weaponsBoxesPlayer1.getSelectedBox().getWeapon();
						if (!GameModele.solo) {
							weapon2 = weaponsBoxesPlayer2.getSelectedBox().getWeapon();
						}
						GameModele.seed = i;
						GameModele.section = sectionBoxes.getSelectedBox().getValue();
						gameView.getGame().start();
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
				gameView.update_view(GameState.ChoixGameplay);
				gameView.getGame().setCurrentState(GameState.ChoixGameplay);
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
				int i = r.nextInt(200);
				GameModele.seed = i;
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

		addComponent(perroquetBox);

		addComponent(weaponLabelPlayer1);
		addComponent(weaponsBoxesPlayer1);
		addComponent(weaponSelectedLabel1);

		addComponent(weaponLabelPlayer2);
		addComponent(weaponsBoxesPlayer2);
		addComponent(weaponSelectedLabel2);
		addComponent(sectionBoxes);
		addComponent(sectionLabel);
		addComponent(labelParrot);
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
			weaponLabelPlayer1.setText("Player's weapon : ");
			sectionLabel.setPositionY(351);
			sectionBoxes.setPositionY(314);
			perroquetBox.setPositionY(428);
			labelParrot.setPositionY(451);
		} else {
			weaponLabelPlayer1.setText("Player 1's weapon : ");
			sectionLabel.setPositionY(451);
			sectionBoxes.setPositionY(414);
			perroquetBox.setPositionY(528);
			labelParrot.setPositionY(551);
		}
		for (UIComponent c : components) {
			if ((c == weaponLabelPlayer2 || c == weaponsBoxesPlayer2 || c == weaponSelectedLabel2) && GameModele.solo)
				continue;
			c.paint(g);
		}
	}
	
	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
