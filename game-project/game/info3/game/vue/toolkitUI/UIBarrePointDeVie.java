package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

public class UIBarrePointDeVie extends UIComponent{
	private int value;
	private boolean state;

	public UIBarrePointDeVie() {
		super(0, 0, 50, 200);
		value = 100;
		state = true;
	}
	
	public int getValue() {
		return value;
	}
	
	public void updateLifePoint(int newValue) {
		value = newValue;
	}
	
	public void changeShowing() {
		state = !state;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(getPositionX(), getPositionY(), getWidth()+8, getHeight()+8);
		g.setColor(new Color(255-value*255/100, value*255/100, 30));
		g.fillRect(getPositionX()+4, getPositionY()+4, value*getWidth()/100, getHeight());
	}
}
