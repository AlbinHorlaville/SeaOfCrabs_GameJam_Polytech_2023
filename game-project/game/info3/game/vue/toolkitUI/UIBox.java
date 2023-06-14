package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class UIBox extends UIComponent {

	Color borderColor;
	Color backgroundColor;
	UILabel subLabel;
	UIImage image;

	private static final Font FONT = new Font("TimesRoman", Font.PLAIN, 12);

	public UIBox(int c, String sl, UIImage im, Color bg, Color br) {
		super(0, 0, c, c);
		borderColor = br;
		backgroundColor = bg;
		image = im;
		subLabel = new UILabel(0, 0, sl, FONT, borderColor);
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
		int labelWidth = g.getFontMetrics().stringWidth(subLabel.getText());
		image.setPositionX(getPositionX());
		image.setPositionY(getPositionY());
		image.paint(g);

	}

}
