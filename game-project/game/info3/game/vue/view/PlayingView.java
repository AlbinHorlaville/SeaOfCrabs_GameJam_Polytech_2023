package info3.game.vue.view;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.avatar.Avatar;

public class PlayingView extends View {

	public PlayingView(GameView gv) {
		super(gv);
	}


	@Override
	public void tick(long elapsed) {
		for (Entity entity : GameModele.entities) {
			entity.getAvatar().tick(elapsed);
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		GameModele.map.getRepresentation().paint(g, width, height, GameModele.entities.get(0).getX(),
				GameModele.entities.get(0).getY());

		for (Entity entity : GameModele.entities) {
			entity.getAvatar().paint(g, width, height);
		}
		
		//GameModele.map.getMiniMap().paint(g, width, height, 0);
	}

}
