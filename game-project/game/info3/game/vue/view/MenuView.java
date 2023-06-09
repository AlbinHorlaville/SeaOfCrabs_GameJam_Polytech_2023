package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UICursor;
import info3.game.vue.toolkitUI.UILabel;

public class MenuView extends View {
	
	UIButton button;
	UICursor cursor;
	UILabel label;

	public MenuView() {
		button = new UIButton(100, 100, 100, 50, new UILabel(0,0,"Button",new Font("TimesRoman", Font.BOLD, 40),Color.white), Color.black);
		cursor = new UICursor(300,300,20,100,Color.black,Color.red);
		label = new UILabel(200,400,"SeaOfCrabs",new Font("TimesRoman", Font.BOLD, 20),Color.white);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g, int width, int height) {
		// TODO Auto-generated method stub
		button.paint(g);
		cursor.paint(g);
		label.paint(g);
	}

}
