package info3.game.vue.toolkitUI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public abstract class UIComponent {

	private int positionX;
	private int positionY;
	private int height;
	private int width;
	private UIComponentListener componentListener;

	public UIComponent(int x, int y, int h, int w) {
		this.positionX = x;
		this.positionY = y;
		this.height = h;
		this.width = w;
	}

	public boolean mouseOnComponent(int x, int y) {
		return x >= getPositionX() && x <= getPositionX() + getWidth() && y >= getPositionY()
				&& y <= getPositionY() + getHeight();
	}

	public abstract void paint(Graphics g);

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setUIComponentListener(UIComponentListener listener) {
		this.componentListener = listener;
	}

	public void clicked() {
		componentListener.onComponentClicked();
	}

	public void mouseIn() {
		componentListener.onComponentMouseIn();
	}

	public void mouseOut() {
		componentListener.onComponentMouseOut();
	}

	public void pressed(int x, int y) {
		componentListener.onComponentPressed(x, y);
	}
	
	public void keyPressed(KeyEvent e) {
		componentListener.onKeyPressed(e);
	}

}
