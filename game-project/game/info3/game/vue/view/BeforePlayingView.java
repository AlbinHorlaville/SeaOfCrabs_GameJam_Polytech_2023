package info3.game.vue.view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIBox;
import info3.game.vue.toolkitUI.UIBoxes;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UIImage;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITextInput;

public class BeforePlayingView extends View {

	UIButton buttonPlay, buttonCmd, buttonRandomSeed;
	UILabel seedLabel, weaponLabelPlayer1, weaponLabelPlayer2;
	UITextInput seedInput;
	UIBox box;
	UIBoxes weaponsBoxesPlayer1, weaponsBoxesPlayer2;

	public BeforePlayingView(GameView gv) {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		buttonPlay = new UIButton(700, windowHeight - 100, 200, new UILabel(0, 0, "Play", FONT1, c1), c2);
		buttonCmd = new UIButton(100, windowHeight - 100, 200, new UILabel(0, 0, "Commands", FONT1, c1), c2);
		buttonRandomSeed = new UIButton(450, 75, 200, new UILabel(0, 0, "Set random seed", FONT3, c1), c2);
		seedLabel = new UILabel(100, 100, "Seed :", FONT1, c2);
		seedInput = new UITextInput(200, 75, 200, "Enter a number", c2, c1, c1);

		weaponLabelPlayer1 = new UILabel(100, 200, "Weapon of player n°1 : ", FONT1, c2);
		weaponLabelPlayer2 = new UILabel(100, 300, "Weapon of player n°2 : ", FONT1, c2);

		weaponsBoxesPlayer1 = new UIBoxes(390, 170);
		// box = new UIBox(390, 170, 50, "Epee", Color.gray, Color.black);
		weaponsBoxesPlayer1.addBox(new UIBox(90, "Epee", new UIImage(0, 0, "resources/img/Sword.png", 2F), Color.gray, Color.black));
		weaponsBoxesPlayer1.addBox(new UIBox(90, "Epee", new UIImage(0, 0, "resources/img/Scythe.png", 2F), Color.gray, Color.black));

		weaponsBoxesPlayer2 = new UIBoxes(390, 270);
		weaponsBoxesPlayer2.addBox(new UIBox(90, "Epee", new UIImage(0, 0, "resources/img/Sword.png", 2F), Color.gray, Color.black));
		weaponsBoxesPlayer2.addBox(new UIBox(90, "Epee", new UIImage(0, 0, "resources/img/Scythe.png", 2F), Color.gray, Color.black));

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
						gameView.getGame().start(Integer.valueOf(seedInput.getInputText()));
					} else {
						seedInput.setInputText(Integer.toString(i));
						gameView.getGame().start(i);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonPlay.setBackgroundColor(c1);
				buttonPlay.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonPlay.setBackgroundColor(c2);
				buttonPlay.setForegroundColor(c1);
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

			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonCmd.setBackgroundColor(c1);
				buttonCmd.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonCmd.setBackgroundColor(c2);
				buttonCmd.setForegroundColor(c1);
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
				buttonRandomSeed.setBackgroundColor(c1);
				buttonRandomSeed.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonRandomSeed.setBackgroundColor(c2);
				buttonRandomSeed.setForegroundColor(c1);
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
		addComponent(buttonCmd);
		addComponent(seedLabel);
		addComponent(seedInput);
		addComponent(buttonRandomSeed);
		addComponent(weaponLabelPlayer1);
		addComponent(weaponLabelPlayer2);
		addComponent(weaponsBoxesPlayer1);
		addComponent(weaponsBoxesPlayer2);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
