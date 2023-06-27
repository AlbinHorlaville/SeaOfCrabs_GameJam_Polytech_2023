package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.BoatPlayer;
import info3.game.vue.view.View;

public class UIBarreVieMer extends UIBarrePointDeVie {

	private UIImage image;

	public UIBarreVieMer(int x, int y) {
		super(x, y);
		image = new UIImage(0, 0, "resources/img/logo-boat2.png", 1F);
	}

	@Override
	public void paint(Graphics g) {
		int max_life = BoatPlayer.DEFAULT_MAX_PLAYERS_LIFE;
		int current_life = GameModele.pirateBoat.getHealthPoints();
		g.setColor(Color.black);
		g.drawRect(getPositionX() - 1, getPositionY() - 1, getWidth() + 1, getHeight() + 1);
		g.setColor(new Color(118, 71, 35));
		g.fillRect(getPositionX(), getPositionY(), current_life * getWidth() / max_life, getHeight());
		g.setColor(Color.black);
		for (int i = 0; i < max_life; i += 100) {
			g.drawLine(getPositionX() + getWidth() * i / max_life, getPositionY(),
					getPositionX() + getWidth() * i / max_life, getPositionY() + getHeight());
		}
		image.setPositionX((getPositionX() + current_life * getWidth() / max_life) - 16);
		image.setPositionY(getPositionY() - 10);
		image.paint(g);
		
		if (getState()) {
			g.setFont(View.FONT1);
			g.setColor(Color.white);
			g.drawString(current_life + " / " + max_life, getPositionX() + getWidth() + 20, getPositionY()+15);
		}
	}

}
