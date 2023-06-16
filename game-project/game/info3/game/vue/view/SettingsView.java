package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.IOException;

import info3.game.GameState;
import info3.game.sound.BackgroundMusic;
import info3.game.sound.SoundTool;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIChecker;
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

		title = new UITitle(windowWidth, windowHeight, "Settings", FONT2, Color.white);
		buttonRetour = new UIButton(50, windowHeight - 100, 200,70, new UILabel(0, 0, "Back", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_RED);
		
		buttonAutomatonView = new UIButton(774, windowHeight - 100, 200,70, new UILabel(0, 0, "Automata", FONT1, Color.black),
				UIButton.BACKGROUND_COLOR_CYAN);

		backgroundSoundChecker = new UIChecker(320, 230, new UILabel(0, 0, "Mute", FONT1, c1), c2, true);
		effectSoundChecker = new UIChecker(320, 280, new UILabel(0, 0, "Mute", FONT1, c1), c2, true);

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
					current = SoundTool.stopBackgroundMusic();
				} else {
					SoundTool.playBackgroundMusic();
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

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
