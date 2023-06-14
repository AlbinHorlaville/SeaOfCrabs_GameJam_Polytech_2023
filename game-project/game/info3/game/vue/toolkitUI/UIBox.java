package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import info3.game.modele.Weapon;

public class UIBox extends UIComponent {

	private Color borderColor;
	private Color backgroundColor;
	private UIImage image;
	private Weapon weapon;

	private static final Font FONT = new Font("TimesRoman", Font.PLAIN, 12);

	public UIBox(int c, Weapon w, UIImage im) {
		super(0, 0, c, c);
		image = im;
		weapon = w;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public String getStringWeapon() {
		switch (weapon) {
		case Sword:
			return "Epée";
		case Scythe:
			return "Faux";
		default:
			return "";
		}
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(getPositionX()+4, getPositionY()+4, getWidth()-4, getHeight()-4);

		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		int centerX = this.getPositionX();
		int centerY = this.getPositionY();
		image.setPositionX(centerX + imageWidth + 16);
		image.setPositionY(centerY + imageHeight + 16);
		image.paint(g);
	}

}
