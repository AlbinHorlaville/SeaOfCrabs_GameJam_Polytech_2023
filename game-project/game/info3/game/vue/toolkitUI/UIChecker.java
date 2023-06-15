package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

// Cette classe implémente un checker binaire avec comme valeurs possibles vrai/on et faux/off

public class UIChecker extends UIButton {
	private boolean state;

	public UIChecker(int x, int y, UILabel l, Color c, boolean baseState) {
		super(x, y, 0, 0, l,0);
		this.state = baseState;
		setHeight(30);
		setWidth(30);
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public void check() {
		this.state = !state;
	}

	@Override
	// Le checker est un carré qui change de couleur quand on clique dessus, suivant
	// son état.
	// Un lizeré de 2 pixel entoure le rectangle.
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
		if (state) {
			g.setColor(Color.green);
		} else {
			g.setColor(Color.red);
		}
		g.fillRect(getPositionX() + 2, getPositionY() + 2, getWidth() - 4, getHeight() - 4);
		g.setColor(Color.black);
		if (state) {
			g.drawString("Activé", getPositionX() + 2 * getWidth(), getPositionY() + getWidth() * 4 / 5);
		} else {
			g.drawString("Désactivé", getPositionX() + 2 * getWidth(), getPositionY() + getWidth() * 4 / 5);
		}
	}

}
