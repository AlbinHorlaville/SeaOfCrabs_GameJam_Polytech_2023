package info3.game.vue.toolkitUI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public abstract class UIComponent {

	private int positionX;
	private int positionY;
	private int height;
	private int width;
	private boolean visible;
	private UIComponentListener componentListener;

	public UIComponent(int x, int y, int h, int w) {
		this.positionX = x;
		this.positionY = y;
		this.height = h;
		this.width = w;
	}

	/**
	 * This function returns if the mouse is currently hovered by the mouse 
	 * @param x : the mouse's x-position
	 * @param y : the mouse's y-position
	 * @return boolean
	 */
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
	
	/**
	 * This function calls the listener when the needed UIComponent's behavior is Clicked event
	 */
	public void clicked(int x, int y) {
		componentListener.onComponentClicked(x, y);
	}

	/**
	 * This function calls the listener when the needed UIComponent's behavior is mouseIn event
	 */
	public void mouseIn(int x, int y) {
		componentListener.onComponentMouseIn(x, y);
	}

	/**
	 * This function calls the listener when the needed UIComponent's behavior is mouseOut event
	 */
	public void mouseOut(int x, int y) {
		componentListener.onComponentMouseOut(x, y);
	}

	/**
	 * This function calls the listener when the needed UIComponent's behavior is Pressed event
	 */
	public void pressed(int x, int y) {
		componentListener.onComponentPressed(x, y);
	}

	/**
	 * This function calls the listener when the needed UIComponent's behavior is KeyPressed event
	 */
	public void keyPressed(KeyEvent e) {
		componentListener.onKeyPressed(e);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
