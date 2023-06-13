package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

// Cette classe implémente un checker binaire avec comme valeurs possibles vrai/on et faux/off

public class UIChecker extends UIButton {
	private boolean state;

	public UIChecker(int x, int y, UILabel l, Color c, boolean baseState) {
		super(x, y, 0, l, c);
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
	public void paint(Graphics g) {
		g.setColor(new Color(90, 90, 255));
		g.fillRect(getPositionX(), getPositionY(), getWidth(), getHeight());
		if (state) {
			g.setColor(new Color(90, 255, 90));
		} else {
			g.setColor(new Color(255, 90, 90));
		}
		g.fillRect(getPositionX() + 2, getPositionY() + 2, getWidth() - 4, getHeight() - 4);
		g.setColor(Color.WHITE);
		if (state) {
			g.drawString("ON", getPositionX() + 2 * getWidth(), getPositionY() + getWidth() * 4 / 5);
		} else {
			g.drawString("OFF", getPositionX() + 2 * getWidth(), getPositionY() + getWidth() * 4 / 5);
		}
	}

}
