package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import info3.game.GameState;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UICursor;
import info3.game.vue.toolkitUI.UILabel;

public class MenuView extends View {

	UIButton button;
	UICursor cursor;
	UILabel label;

	private static final Font FONT1 = new Font("TimesRoman", Font.BOLD, 20);
	private static final Font FONT2 = new Font("TimesRoman", Font.BOLD, 30);

	public MenuView(GameView gv) {
		super(gv);
		button = new UIButton(100, 100, new UILabel(0, 0, "Button", FONT1, Color.white), Color.black);
		cursor = new UICursor(300, 300, 20, 100, Color.black, Color.red);
		label = new UILabel(200, 400, "SeaOfCrabs", FONT2, Color.white);

		button.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				try {
					gameView.getGame().start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn() {
				button.setBackgroundColor(Color.white);
				button.setForegroundColor(Color.black);
			}

			@Override
			public void onComponentMouseOut() {
				button.setBackgroundColor(Color.black);
				button.setForegroundColor(Color.white);
			}

		});

		// on ajoute les différents components
		addComponent(button);
		addComponent(cursor);
		addComponent(label);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
