package info3.game.vue.view;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.avatar.Avatar;

public class PlayingView extends View{

	static ArrayList<Avatar> avatars;

	public PlayingView() throws IOException {
		avatars = new ArrayList<>();
	}

	@Override
	public void tick(long elapsed) {
		for (Entity entity : GameModele.entities) {
			entity.getAvatar().tick(elapsed);
		}	
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		for (Entity entity : GameModele.entities) {
			entity.getAvatar().paint(g, width, height);
		}
	}

}
