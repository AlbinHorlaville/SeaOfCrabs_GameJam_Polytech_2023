package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.MoveableEntityClass.Player;

public class UIBarreVieTerre extends UIBarrePointDeVie {

	private UIImage image;

	public UIBarreVieTerre(int x, int y) {
		super(x, y);
	}

	@Override
	public void paint(Graphics g) {
		int max_life = PiratePlayer.getACTUAL_MAX_PLAYERS_LIFE();
		int current_life = PiratePlayer.getACTUAL_PIRATEPLAYER_LIFE_POINT();
		g.setColor(Color.black);
		g.drawRect(getPositionX() - 1, getPositionY() - 1, getWidth() + 1, getHeight() + 1);
		g.setColor(new Color(255 - current_life * 255 / max_life, current_life * 255 / max_life, 30));
		g.fillRect(getPositionX(), getPositionY(), current_life * getWidth() / max_life, getHeight());
		if (GameModele.solo) {
			image = new UIImage(0, 0, "resources/img/logo-pirate-1.png", 1F);
		} else {
			image = new UIImage(0, 0, "resources/img/logo-pirates.png", 1F);
		}
		image.setPositionX((getPositionX() + current_life * getWidth() / max_life) - 16);
		image.setPositionY(getPositionY() - 10);
		image.paint(g);
	}
}
