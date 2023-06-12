package info3.game.vue.view;

import java.awt.Graphics;
import java.util.ArrayList;

import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIComponent;

public abstract class View {

	ArrayList<UIComponent> components;
	GameView gameView;

	public View(GameView gv) {
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
