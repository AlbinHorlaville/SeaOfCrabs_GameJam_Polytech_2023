package info3.game.vue.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.Scythe;
import info3.game.modele.Sword;
import info3.game.modele.Weapon;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIBarrePointDeVie;
import info3.game.vue.toolkitUI.UIBox;
import info3.game.vue.toolkitUI.UIBoxes;
import info3.game.vue.toolkitUI.UIImage;

public class PlayingView extends View {

	UIBarrePointDeVie life;
	UIBoxes weapons;
	UIBox boxSword, boxScythe;

	int k;

	public PlayingView(GameView gv) {
		super(gv);
		// uiMap = new UIMap(GameModele.map.getRepresentation(), window_width,
		// window_height) ;

		// addComponent(uiMap);

		int windowWidth = (int) gameView.getWidthCanvas();
		int windowHeight = (int) gameView.getHeightCanvas();

		life = new UIBarrePointDeVie();

		weapons = new UIBoxes((windowWidth - 100) / 2, windowHeight - 114);
		boxSword = new UIBox(64, Sword.getInstance(), new UIImage(0, 0, "resources/img/Sword.png", 1F));
		boxScythe = new UIBox(64, Scythe.getInstance(), new UIImage(0, 0, "resources/img/Scythe.png", 1F));
		weapons.addBox(boxSword);
		weapons.addBox(boxScythe);

		k = 0;
		
		addComponent(weapons);
	}

	@Override
	public void tick(long elapsed) {
		for (Entity entity : GameModele.entities) {
			entity.getAvatar().tick(elapsed);
		}
		k++;
		if (k % 30 == 0 && life.getValue() > 0) {
			life.updateLifePoint(life.getValue() - 1);
			k = 0;
		}
		if (life.getValue() == 0) {
			life.updateLifePoint(100);
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		GameModele.map.getRepresentation().paint(g, width, height, GameModele.player1.getX(),
				 GameModele.player1.getY());
		if (GameModele.onSea) {
			GameModele.map.getRepresentation().paint(g, width, height, GameModele.pirateBoat.getX(),
					 GameModele.pirateBoat.getY());
		} else {
			if (GameModele.solo) {
				GameModele.map.getRepresentation().paint(g, width, height, GameModele.player1.getX(),
						 GameModele.player1.getY());
			} else {
		 		GameModele.map.getRepresentation().paint(g, width, height,(GameModele.player1.getX() + width + GameModele.player2.getX())/2,
						(GameModele.player1.getY() + height + GameModele.player2.getY())/2); 
			}

		}
		
		//uiMap.paint(g);
		for (Entity entity : GameModele.entities) {
			entity.getAvatar().paint(g, width, height);
		}

		life.paint(g);
		weapons.paint(g);

		// GameModele.map.getMiniMap().paint(g, width, height, 0);
	}

}
