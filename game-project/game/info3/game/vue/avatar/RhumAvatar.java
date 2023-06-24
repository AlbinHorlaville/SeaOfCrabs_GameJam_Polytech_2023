package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class RhumAvatar extends Avatar {

	public final static int ANIMATION_SPEED_RHUM = 200;
	
	private int nbImage;

	public RhumAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Rhum);
		imageIndex = 0;
		nbImage = m_images.length;
	}

	@Override
	public void tick(long elapsed) {
		imageElapsed += elapsed;
		if (imageElapsed > ANIMATION_SPEED_RHUM) {
			imageElapsed = 0;
			imageIndex = (imageIndex + 1) % nbImage;
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();
		// int Decalage_Tiles_X = (int) -32*6+14*6;
		// int Decalage_Tiles_Y = (int) -32*6+6*6;
		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2;
		if (coeffX < width && coeffY < height && coeffX + width_painted > 0
				&& coeffY + heigth_painted > 0) {
			g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
		}
	}

}
