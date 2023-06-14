package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIComponent;

public abstract class View {

	ArrayList<UIComponent> components;
	GameView gameView;

	protected int window_width;
	protected int window_height;
	protected Color c1;
	protected Color c2;
	protected Color c3;

	protected static final Font FONT1 = new Font("TimesRoman", Font.BOLD, 20);
	protected static final Font FONT2 = new Font("IMPACT", Font.BOLD, 100);
	protected static final Font FONT3 = new Font("TimesRoman", Font.BOLD, 12);
	protected static final Font FONT4 = new Font("IMPACT", Font.BOLD, 40);

	public View(GameView gv) {
		window_width = 1024;
		window_height = 768;
		c1 = new Color(255, 100, 100);
		c2 = new Color(255, 255, 102);
		c3 = new Color(255, 218, 185);

		gameView = gv;
		components = new ArrayList<>();
	}

	public abstract void tick(long elapsed);

	public void paint(Graphics g, int width, int height) {
		for (UIComponent c : components) {
			c.paint(g);
		}
	}

	public void addComponent(UIComponent c) {
		components.add(c);
	}

	public UIComponent getHoveredComponent(int x, int y) {
		for (UIComponent c : components) {
			if (c.mouseOnComponent(x, y)) {
				return c;
			}
		}
		return null;
	}

}
