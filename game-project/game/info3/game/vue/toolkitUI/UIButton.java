package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

public class UIButton extends UIComponent {

	private UILabel label;
	private Color backgroundColor;

	public UIButton(int x, int y, int h, int w, UILabel l, Color bg) {
		super(x, y, h, w);
		label = l;
		backgroundColor = bg;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(super.getPositionX(), super.getPositionY(), super.getHeight(), super.getWidth());
		g.setColor(label.getFontColor());
		g.setFont(label.getFont());

		int labelWidth = g.getFontMetrics().stringWidth(label.getText());
		int labelHeight = g.getFontMetrics().getHeight();

		int centerX = this.getPositionX() + (this.getPositionX() - labelWidth) / 2;
		int centerY = this.getPositionY() + this.getPositionY()/2 -  labelHeight;
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

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
