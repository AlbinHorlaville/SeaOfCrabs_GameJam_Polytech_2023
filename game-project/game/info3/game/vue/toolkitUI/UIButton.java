package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

public abstract class UIButton extends UIComponent {
	
	private UILabel label;
	private Color backgroundColor;
	
	public UIButton(int x, int y, int w, int h, UILabel l, Color bg) {
		super(x,y,w,h);
		label = l;
		backgroundColor = bg;
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(super.getPositionX(), super.getPositionY(), super.getWidth(), super.getHeight());
		g.setColor(label.getFontColor());
		
		int labelWidth = g.getFontMetrics().stringWidth(label.getText());
		int labelHeight = g.getFontMetrics().getHeight();

		int centerX = super.getPositionX() + (super.getPositionY() - labelWidth) / 2;
		int centerY = super.getPositionY() + (super.getHeight() + labelHeight) / 2;
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
