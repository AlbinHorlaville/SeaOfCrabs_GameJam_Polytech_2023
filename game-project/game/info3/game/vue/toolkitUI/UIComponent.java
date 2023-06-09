package info3.game.vue.toolkitUI;

import java.awt.Graphics;

public abstract class UIComponent {
	private int positionX;
	private int positionY;
	private int height;
	private int width;
	
	public UIComponent(int x, int y, int h, int w) {
		this.positionX = x;
		this.positionY = y;
		this.height = h;
		this.width =  w;
	}
	
	public abstract void paint(Graphics g);
}
