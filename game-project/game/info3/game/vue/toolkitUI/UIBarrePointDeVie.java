package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.modele.PiratePlayer;

public class UIBarrePointDeVie extends UIComponent{
	private boolean state;

	public UIBarrePointDeVie() {
		super(0, 0, 50, 200);
		state = true;
	}
	
	public void changeShowing() {
		state = !state;
	}

	@Override
	public void paint(Graphics g) {
		int max_life = PiratePlayer.getCURRENT_MAX_LIFE_POINT();
		int current_life = PiratePlayer.getCURRENT_LIFE_POINT();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(getPositionX(), getPositionY(), getWidth()+8, getHeight()+8);
		g.setColor(new Color(255-current_life*255/max_life, current_life*255/max_life, 30));
		g.fillRect(getPositionX()+4, getPositionY()+4, current_life*getWidth()/max_life, getHeight());
	}
}
