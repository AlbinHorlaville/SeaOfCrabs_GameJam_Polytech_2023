package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// Cette classe implémente une zone de texte appelée label. Attention,
// la calsse n'affiche que sur une seule ligne, donc penser à faire une
// ArrayList<UILabel> si vous voulez afficher un texte sur plusieurs lignes.
// Les Font doivent être définies dans View pour être accessible par tout le monde.
public class UILabel extends UIComponent {

	private Color fontColor;
	private Font font;
	private String text;

	/**
	 * The UILabel is a simple label based from string text
	 * @param x
	 * @param y
	 * @param text
	 * @param font
	 * @param fg
	 */
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
