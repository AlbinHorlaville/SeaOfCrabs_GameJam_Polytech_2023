package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UILabel extends UIComponent {

	private Color fontColor;
	private Font font;
	private String text;

	public UILabel(int x, int y, String text, Font font, Color fg) {
		super(x, y, 0, 0);
		fontColor = fg;
		this.font = font;
		this.text = text;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(fontColor);
		g.setFont(font);
		g.drawString(text, super.getPositionX(), super.getPositionY());
	}
	
	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
