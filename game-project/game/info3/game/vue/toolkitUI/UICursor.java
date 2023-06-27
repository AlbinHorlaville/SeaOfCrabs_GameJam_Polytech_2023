package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

public class UICursor extends UIComponent {
	private int value;

	public void setValue(int value) {
		this.value = value;
	}

	private int sizeCursor;
	private Color colorBar;
	private Color colorCursor;

	public UICursor(int x, int y, int h, int w, Color cBar, Color cCursor, int v) {
		super(x, y, h, w);
		value = v;
		this.colorBar = cBar;
		this.colorCursor = cCursor;

		// Le Curseur est un carré 20% plus grand que la barre sur laquelle il se
		// balade
		int min = java.lang.Math.min(h, w);
		this.sizeCursor = (int) (min + 0.4 * min); // (int)(min + 0.2*min);
	}

	public Color getColorCursor() {
		return colorCursor;
	}

	public void setColorCursor(Color colorCursor) {
		this.colorCursor = colorCursor;
	}

	public int getValue() {
		if (getWidth() > getHeight())
			return (value - getPositionX()) * 100 / (getWidth() - sizeCursor);
		else {
			return (value - getPositionY()) * 100 / (getHeight() - sizeCursor);
		}
	}

	/**
	 * move met à jour la position du curseur le long de la barre selon si le
	 * curseur est horizontal ou vertical.
	 * 
	 * @param x La position de la souris en abscisse
	 * @param y La position de la souris en ordonnée
	 */
	public void move(int x, int y) {

		// Curseur Vertical

		if (getWidth() > getHeight()) {
			if (x >= getPositionX() && x <= getPositionX() + getWidth() - sizeCursor) {
				this.value = (x - getPositionX()) * 100 / getWidth();
				// Correctif pour permettre au curseur de prendre les valeurs extrêmes.
			} else if (x < getPositionX()) {
				this.value = 0;
			} else if (x > getPositionX() + getWidth() - sizeCursor) {
				this.value = 100;
			}
		}

		// Curseur horizontal

		else if (getWidth() < getHeight()) {
			if (y >= getPositionY() && y <= getPositionY() + getHeight() - sizeCursor) {
				this.value = (y - getPositionY()) * 100 / getHeight();
				// Correctif pour permettre au curseur de prendre les valeurs extrêmes.
			} else if (y < getPositionY()) {
				this.value = 0;
			} else if (y > getPositionY() + getHeight() - sizeCursor) {
				this.value = 100;
			}
		}

	}

	@Override
	public void paint(Graphics g) {
		int X = getPositionX(), Y = getPositionY();
		System.out.println(value);

		g.setColor(this.colorBar);
		g.fillRect(X, Y, getWidth(), getHeight());
		g.setColor(new Color(230, 230, 230));

		g.fillRect(X + 2, Y + 2, getWidth() - 4, getHeight() - 4);
		g.setColor(this.colorCursor);

		// Gère l'affichage du curseur peu importe si le Component est horizontal ou
		// vertical.
		int min = java.lang.Math.min(getWidth(), getHeight());
		if (getWidth() < getHeight()) {
			Y = (getHeight() - sizeCursor) * this.value + getPositionY();
			X = (int) (X - min * 0.2); // Décale le curseur pour le centré sur la barre
		} else {
			X = ((getWidth() - sizeCursor) * this.value) / 100 + getPositionX();
			Y = (int) (Y - min * 0.2); // Décale le curseur pour le centré sur la barre
		}

		g.fillRect(X, Y, this.sizeCursor, this.sizeCursor);
	}
}
