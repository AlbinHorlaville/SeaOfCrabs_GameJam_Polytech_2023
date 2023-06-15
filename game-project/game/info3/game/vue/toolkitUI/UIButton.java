package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class UIButton extends UIComponent {

	private UILabel label;
	private int backgroundColor;
	private Color foregroundColor;
	private UIImage backgroundImage;
	
	public final static int BACKGROUND_COLOR_GREEN = 0;
	public final static int BACKGROUND_COLOR_BLUE = 1;
	public final static int BACKGROUND_COLOR_RED = 2;
	public final static int BACKGROUND_COLOR_YELLOW = 3;
	public final static int BACKGROUND_COLOR_CYAN = 4;
	public final static int BACKGROUND_COLOR_PURPLE = 5;
	
	public final static int BACKGROUND_COLOR_GREEN_HOVER = 6;
	public final static int BACKGROUND_COLOR_BLUE_HOVER = 7;
	public final static int BACKGROUND_COLOR_RED_HOVER = 8;
	public final static int BACKGROUND_COLOR_YELLOW_HOVER = 9;
	public final static int BACKGROUND_COLOR_CYAN_HOVER = 10;
	public final static int BACKGROUND_COLOR_PURPLE_HOVER = 11;
	
	
	// Implémente un bouton de taille, label et couleur adaptable
	public UIButton(int x, int y, int w, int h, UILabel l, int bg) {
		super(x, y, h, w);
		label = l;
		foregroundColor = label.getFontColor();
		backgroundColor = bg;
	}

	// Le texte est centré sur le bouton
	@Override
	public void paint(Graphics g) {
		BufferedImage[] i = SpriteLoader.get(SpriteType.Buttons);
		g.drawImage(i[backgroundColor], this.getPositionX(), this.getPositionY(), this.getWidth(), this.getHeight(), null);
		g.setFont(label.getFont());
	
		g.setColor(foregroundColor);
		
		if (label.getText().contains("\n")) {
			int marginY = 0;
			for (String line : label.getText().split("\n")) {
				int labelHeight = g.getFontMetrics().getHeight();
				int labelWidth = g.getFontMetrics().stringWidth(line);
				int rectHeight = labelHeight + 2 * label.getFont().getSize();
				int centerX = this.getPositionX() + (getWidth() - labelWidth) / 2;
				int centerY = this.getPositionY() + rectHeight - labelHeight + marginY *30;
				g.drawString(line, centerX, centerY);
				marginY++;
			}
		} else {
			int labelHeight = g.getFontMetrics().getHeight();
			int labelWidth = g.getFontMetrics().stringWidth(label.getText());
			int rectHeight = labelHeight + 2 * label.getFont().getSize();
			int centerX = this.getPositionX() + (getWidth() - labelWidth) / 2;
			int centerY = this.getPositionY() + rectHeight - labelHeight;
			g.drawString(label.getText(), centerX, centerY);
		}
		
		//g.drawString(label.getText(), centerX, centerY);
	}
	
	void drawString(Graphics g, String text, int x, int y) {
	    for (String line : text.split("\n"))
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	public UILabel getLabel() {
		return label;
	}

	public void setLabel(UILabel label) {
		this.label = label;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}
	
	// Change la couleur de fond du bouton
	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	// Change la couleur du texte
	public void setForegroundColor(Color foregroundColor) {
		this.label.setFontColor(foregroundColor);
		this.foregroundColor = foregroundColor;
	}

}
