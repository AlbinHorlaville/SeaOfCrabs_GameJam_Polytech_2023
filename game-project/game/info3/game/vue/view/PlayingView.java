package info3.game.vue.view;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import info3.game.vue.GameView;
import info3.game.vue.avatar.Avatar;

public class PlayingView extends View {

	ArrayList<Avatar> avatars;

	public PlayingView(GameView gv) throws IOException {
		super(gv);
		avatars = new ArrayList<>();
	}

	@Override
	public void tick(long elapsed) {
		for (Avatar avatar : avatars) {
			avatar.tick(elapsed);
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		for (Avatar avatar : avatars) {
			avatar.paint(g, width, height);
		}
	}

	public void addAvatar(Avatar avatar) {
		this.avatars.add(avatar);
	}

}
