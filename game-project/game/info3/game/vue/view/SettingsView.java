package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import info3.game.GameState;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIChecker;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UICursor;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITitle;

public class SettingsView extends View{
	UIButton buttonRetour;
	UICursor cursorVolume;
	UITitle title;
	UILabel Volume;
	UIChecker checkerMute;
	
	public SettingsView(GameView gv) {
		super(gv);
		title = new UITitle(1024, 768, "Paramètres", FONT2, Color.white);
		buttonRetour = new UIButton(100, 600, 200,  new UILabel(0, 0, "Retour", FONT1, c1), c2);
		cursorVolume = new UICursor(300, 300, 200, 20, c2, c3);
		checkerMute = new UIChecker(600, 400, new UILabel(0, 0, "Mute", FONT1, c1), c2, true);
		Volume = new UILabel(260, 250, "Volume : "+cursorVolume.getValue(), FONT1, c3);
		
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
			};

		});
		
		cursorVolume.setUIComponentListener(new UIComponentListener() {

			public void onComponentPressed(int x, int y) {
				cursorVolume.move(x, y);
				Volume.setText("Volume :"+cursorVolume.getValue());
			}

			public void onComponentClicked() {
			}

			@Override
			public void onComponentMouseIn() {
				// TODO Auto-generated method stub
				cursorVolume.setColorCursor(c1);
			}

			@Override
			public void onComponentMouseOut() {
				// TODO Auto-generated method stub
				cursorVolume.setColorCursor(c2);

			};
		});
		
		checkerMute.setUIComponentListener(new UIComponentListener() {

		public void onComponentPressed(int x, int y) {
		}

		public void onComponentClicked() {
			checkerMute.check();
		}

		@Override
		public void onComponentMouseIn() {
			// TODO Auto-generated method stub
		}

		@Override
		public void onComponentMouseOut() {
			// TODO Auto-generated method stub
		};
	});
		
		
		addComponent(buttonRetour);
		addComponent(cursorVolume);
		addComponent(checkerMute);
		addComponent(title);
		addComponent(Volume);
		
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		
	}

}
