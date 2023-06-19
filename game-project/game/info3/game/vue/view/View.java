package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
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
	
	protected static final Font FONT1 = new Font(GameView.customFont.getFontName(), Font.PLAIN, 40);
	protected static final Font FONT2 = new Font(GameView.customFont.getFontName(), Font.PLAIN, 100);
	protected static final Font FONT3 = new Font(GameView.customFont.getFontName(), Font.PLAIN, 50);
	protected static final Font FONT4 = new Font(GameView.customFont.getFontName(), Font.PLAIN, 30);
	protected static final Font FONT5 = new Font(GameView.customFont.getFontName(), Font.PLAIN, 20);
	
	
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
