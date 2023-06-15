package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.IOException;

import info3.game.GameState;
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
	UICursor cursorVolume;
	UITitle title;
	UILabel backgroundSoundVolumeLabel;
	UILabel effectSoundVolumeLabel;
	UIChecker checkerMute;

	public SettingsView(GameView gv) {
		super(gv);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		title = new UITitle(windowWidth, windowHeight, "Paramètres", FONT2, Color.white);
		buttonRetour = new UIButton(50, windowHeight - 100, 200, new UILabel(0, 0, "Retour", FONT1, Color.black),UIButton.BACKGROUND_COLOR_RED);
		cursorVolume = new UICursor(300, 300, 20, 200, c2, c3);
		checkerMute = new UIChecker(600, 400, new UILabel(0, 0, "Mute", FONT1, c1), c2, true);
		backgroundSoundVolumeLabel = new UILabel(50, 250, "Volume de la musique de fond : " + this.formatFloatVolumeToString(SoundTool.getBackgroundSoundVolume())+ " %", FONT1, Color.black);
		effectSoundVolumeLabel = new UILabel(50, 300, "Volume des effets sonores : " + this.formatFloatVolumeToString(SoundTool.getEffectSoundVolume())+ " %", FONT1, Color.black);

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

		cursorVolume.setUIComponentListener(new UIComponentListener() {

			public void onComponentPressed(int x, int y) {
				cursorVolume.move(x, y);
				Volume.setText("Volume : " + cursorVolume.getValue());
			}

			public void onComponentClicked(int x, int y) {
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				// TODO Auto-generated method stub
				cursorVolume.setColorCursor(c1);
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				// TODO Auto-generated method stub
				cursorVolume.setColorCursor(c2);

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			};
		});

		checkerMute.setUIComponentListener(new UIComponentListener() {

			public void onComponentPressed(int x, int y) {
			}

			public void onComponentClicked(int x, int y) {
				checkerMute.check();
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
		addComponent(cursorVolume);
		addComponent(checkerMute);
		addComponent(title);
		addComponent(backgroundSoundVolumeLabel);
		addComponent(effectSoundVolumeLabel);
	}
	
	public String formatFloatVolumeToString(float v) {
		v = v*100f;
		Integer i = (int) v;
		return i.toString();
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
