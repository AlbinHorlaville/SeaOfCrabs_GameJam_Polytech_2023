package info3.game.vue.view;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIBarrePointDeVie;

public class PlayingView extends View {
	
	UIBarrePointDeVie life;
	int k;

	public PlayingView(GameView gv) {
		super(gv);
		//uiMap = new UIMap(GameModele.map.getRepresentation(), window_width, window_height) ;
		
		//addComponent(uiMap);
		
		life = new UIBarrePointDeVie();
		k=0;
	}


	@Override
	public void tick(long elapsed) {
		for (Entity entity : GameModele.entities) {
			entity.getAvatar().tick(elapsed);
		}
		k++;
		if (k%30==0 && life.getValue()>0) {
			life.updateLifePoint(life.getValue()-1);
			k=0;
		}
		if (life.getValue()==0) {
			life.updateLifePoint(100);
		}
		
		
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		GameModele.map.getRepresentation().paint(g, width, height, GameModele.entities.get(0).getX(),
				GameModele.entities.get(0).getY());
		//uiMap.paint(g);
		for (Entity entity : GameModele.entities) {
			entity.getAvatar().paint(g, width, height);
		}
		
		life.paint(g);
		
		//GameModele.map.getMiniMap().paint(g, width, height, 0);
	}

}
