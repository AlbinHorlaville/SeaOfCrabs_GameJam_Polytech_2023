package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UIButton extends UIComponent {

	private UILabel label;
	private Color backgroundColor;
	private Color foregroundColor;

	public UIButton(int x, int y, int w, UILabel l, Color bg) {
		super(x, y, 0, w);
		label = l;
		backgroundColor = bg;
		foregroundColor = label.getFontColor();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(backgroundColor);
		g.setFont(label.getFont());
		int labelWidth = g.getFontMetrics().stringWidth(label.getText());
		int labelHeight = g.getFontMetrics().getHeight();
		int rectHeight = labelHeight + 2 * label.getFont().getSize();
		//int rectWidth = 200;//labelWidth + 3 * label.getFont().getSize();
		this.setHeight(rectHeight);
		//this.setWidth(rectWidth);
		g.fillRect(super.getPositionX(), super.getPositionY(), getWidth(), rectHeight);
		g.setColor(foregroundColor);
		int centerX = this.getPositionX() + (getWidth() - labelWidth) / 2;
		int centerY = this.getPositionY() + rectHeight - labelHeight;
		g.drawString(label.getText(), centerX, centerY);
	}

	public UILabel getLabel() {
		return label;
	}

	public void setLabel(UILabel label) {
		this.label = label;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.label.setFontColor(foregroundColor);
		this.foregroundColor = foregroundColor;
	}

}
