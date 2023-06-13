package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import info3.game.GameState;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UICursor;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITextInput;

public class MenuView extends View {

	UIButton button;
	UICursor cursor;
	UILabel label;
	UITextInput input;

	private static final Font FONT1 = new Font("TimesRoman", Font.BOLD, 20);
	private static final Font FONT2 = new Font("TimesRoman", Font.BOLD, 30);

	public MenuView(GameView gv) {
		super(gv);
		button = new UIButton(100, 100, new UILabel(0, 0, "Button", FONT1, Color.white), Color.black);
		cursor = new UICursor(300, 300, 20, 400, Color.black, Color.red);
		label = new UILabel(200, 400, "SeaOfCrabs", FONT2, Color.red);
		input = new UITextInput(500, 200, 200,Color.black, Color.red, Color.white);
				
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

			public void onComponentPressed(int x, int y) {
			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}


		});

		cursor.setUIComponentListener(new UIComponentListener() {

			public void onComponentPressed(int x, int y) {
				cursor.move(x, y);
			}

			public void onComponentClicked() {
			}

			@Override
			public void onComponentMouseIn() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentMouseOut() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});

		// on ajoute les différents components
		addComponent(button);
		addComponent(cursor);
		addComponent(label);
		addComponent(input);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
