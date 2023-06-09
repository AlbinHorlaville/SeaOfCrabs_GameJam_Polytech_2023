package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

import java.lang.Math;

public class UICursor extends UIComponent{
	private int value;
	private int sizeCursor;
	private Color colorBar;
	private Color colorCursor;

	public UICursor(int x, int y, int h, int w, Color cBar, Color cCursor) {
		super(x, y, h, w);
		this.value = 0;
		this.colorBar = cBar;
		this.colorCursor = cCursor;
		
		// Le Curseur est un carré 20% plus grand que la barre sur laquelle il se balade
		//int min = java.lang.Math.min(h, w);
		this.sizeCursor = java.lang.Math.min(h, w); //(int)(min + 0.2*min);
	}
	
	public void move(int x, int y) {
		if (x>=getPositionX() && x<=getPositionX()+getWidth()) {
			setPositionX(x);
		}
		if (y>=getPositionY() && y<=getPositionY()+getHeight()) {
			setPositionY(y);
		}
	}

	@Override
	public void paint(Graphics g) {
		int X = getPositionX(), Y = getPositionY();
		
		g.setColor(this.colorBar);
		g.fillRect(X, Y, getWidth(), getHeight());
		
		g.setColor(this.colorCursor);
		
		// Gère l'affichage du curseur peu importe si le Component est horizontal ou vertical.
		if (getPositionX() < getPositionY()) {
			X += this.value;
		}
		else {
			Y += this.value;
		}
		g.fillRect(X, Y, this.sizeCursor, this.sizeCursor);
	}
}
