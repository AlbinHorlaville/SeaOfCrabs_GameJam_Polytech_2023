package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.MoveableEntityClass.Player;

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
		int max_life = Player.DEFAULT_MAX_PLAYERS_LIFE;
		int current_life = GameModele.player1.getHealthPoints();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(getPositionX(), getPositionY(), getWidth()+8, getHeight()+8);
		g.setColor(new Color(255-current_life*255/max_life, current_life*255/max_life, 30));
		g.fillRect(getPositionX()+4, getPositionY()+4, current_life*getWidth()/max_life, getHeight());
	}
}
