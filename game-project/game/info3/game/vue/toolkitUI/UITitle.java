package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// Cette classe implémente un titre à partir de la class Label.
// Le titre est toujours centré et avec la FONT2.
public class UITitle extends UILabel {

	private int drawX;
	private int drawY;
	private int drawWidth;
	private int drawHeight;

	private int offsetY;

	private boolean sectionTitle;

	public UITitle(int widthWindow, int heightWindow, String text, Font font, Color fg) {
		super(widthWindow, heightWindow, text, font, fg);
		offsetY = 0;
		this.sectionTitle = false;
	}

	public void setOffsetY(int offset) {
		this.offsetY = offset;
	}

	public void setValue(Graphics g) {
		g.setFont(this.getFont());
		this.drawWidth = g.getFontMetrics().stringWidth(this.getText());
		this.drawHeight = g.getFontMetrics().getHeight();

		this.drawX = (this.getPositionX() - this.drawWidth) / 2; // Ici X et Y servent à connaître la taille de la
																	// fenêtre
		this.drawY = (int) (this.drawHeight * 1.5) + offsetY;
		this.sectionTitle = true;
	}

	public void paint(Graphics g) {
		if (!this.sectionTitle) {
			g.setFont(this.getFont());
			int labelWidth = g.getFontMetrics().stringWidth(this.getText());
			int labelHeight = g.getFontMetrics().getHeight();

			int x = (this.getPositionX() - labelWidth) / 2; // Ici X et Y servent à connaître la taille de la fenêtre
			int y = (int) (labelHeight * 1.5) + offsetY;

			this.drawX = x;
			this.drawY = y;

			this.drawWidth = labelWidth;
			this.drawHeight = labelHeight;
		}

		g.setColor(getFontColor());
		g.setFont(getFont());
		g.drawString(getText(), this.drawX, this.drawY);
	}

	public int getX() {
		return this.drawX;
	}

	public int getY() {
		return this.drawY;
	}

	public int getDrawWidth() {
		return this.drawWidth;
	}

	public int getDrawHeight() {
		return this.drawHeight;
	}

}
