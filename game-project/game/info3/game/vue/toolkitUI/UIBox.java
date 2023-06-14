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

	public UIBox(int c, Weapon w, UIImage im, Color bg, Color br) {
		super(0, 0, c, c);
		borderColor = br;
		backgroundColor = bg;
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

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(borderColor);
		g.drawRect(getPositionX() + 2, getPositionY() + 2, getWidth() - 4, getHeight() - 4);
		g.setColor(backgroundColor);
		g.drawRect(getPositionX(), getPositionY(), getWidth(), getHeight());

		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		int centerX = this.getPositionX();
		int centerY = this.getPositionY();
		image.setPositionX(centerX + imageWidth + 16);
		image.setPositionY(centerY + imageHeight + 16);
		image.paint(g);

	}

}
