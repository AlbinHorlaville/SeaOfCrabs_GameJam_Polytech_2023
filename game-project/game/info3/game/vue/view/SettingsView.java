package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import automate.AutomateLoader;
import info3.game.GameState;
import info3.game.SeaOfCrabes;
import info3.game.modele.GameModele;
import info3.game.sound.BackgroundMusic;
import info3.game.sound.SoundTool;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIChecker;
import info3.game.vue.toolkitUI.UIComponent;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UICursor;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class SettingsView extends View {
	UIButton buttonRetour;
	UIButton buttonAutomatonView;
	UITitle title;
	UILabel backgroundSoundVolumeLabel;
	UILabel effectSoundVolumeLabel;
	UIChecker backgroundSoundChecker, effectSoundChecker;
	BackgroundMusic current;
	AutomataView automataView;
	boolean automataViewOpen;

	public SettingsView(GameView gv) {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();
		
		automataView = new AutomataView();
		automataView.setVisible(false);
		automataViewOpen = false;

		title = new UITitle(windowWidth, windowHeight, "Settings", FONT2, Color.black);
		buttonRetour = new UIButton(50, windowHeight - 100, 200,70, new UILabel(0, 0, "Back", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_RED);
		
		buttonAutomatonView = new UIButton(774, windowHeight - 100, 200,70, new UILabel(0, 0, "Automata", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_CYAN);

		backgroundSoundChecker = new UIChecker(320, 230, new UILabel(0, 0, "", FONT1, c1), c2, true);
		effectSoundChecker = new UIChecker(320, 280, new UILabel(0, 0, "", FONT1, c1), c2, true);

		backgroundSoundVolumeLabel = new UILabel(50, 250, "Background sound : ", FONT1, Color.black);
		effectSoundVolumeLabel = new UILabel(50, 300, "Sound effects : ", FONT1, Color.black);

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
		
		buttonAutomatonView.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked(int x, int y) {
				if (!automataViewOpen) {
					automataView.setVisible(true);
				} else {
					automataView.setVisible(false);
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				buttonAutomatonView.setBackgroundColor(UIButton.BACKGROUND_COLOR_CYAN_HOVER);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				buttonAutomatonView.setBackgroundColor(UIButton.BACKGROUND_COLOR_CYAN);
			}

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};

		});
		

		backgroundSoundChecker.setUIComponentListener(new UIComponentListener() {

			public void onComponentPressed(int x, int y) {
			}

			public void onComponentClicked(int x, int y) {
				backgroundSoundChecker.check();
				if (!backgroundSoundChecker.isState()) {
					SoundTool.setCancelBackgroundMusic(true);
				} else {
					SoundTool.setCancelBackgroundMusic(false);
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
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			};
		});

		effectSoundChecker.setUIComponentListener(new UIComponentListener() {

			public void onComponentPressed(int x, int y) {
			}

			public void onComponentClicked(int x, int y) {
				effectSoundChecker.check();
				if (!effectSoundChecker.isState()) {
					SoundTool.setCancelSoundEffect(true);
				} else {
					SoundTool.setCancelSoundEffect(false);
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
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			};
		});

		addComponent(buttonRetour);
		addComponent(backgroundSoundChecker);
		addComponent(title);
		addComponent(backgroundSoundVolumeLabel);
		addComponent(effectSoundVolumeLabel);
		addComponent(effectSoundChecker);
		addComponent(buttonAutomatonView);
	
	}
	
	public void paint(Graphics g, int width, int height) {
		for (UIComponent c : components) {
			c.paint(g);
		}
		if (GameModele.currentScore!=null) {
			if (SeaOfCrabes.connectedToDatabase) {
				addComponent(new UILabel(10, 30, "Connected to database", FONT4, Color.green));
				addComponent(new UILabel(10, 50, "@"+GameModele.currentUser.getUsername(), FONT4, Color.black));
			} else {
				addComponent(new UILabel(10, 30, "Not connected to database", FONT4, Color.red));
			}
		}
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
