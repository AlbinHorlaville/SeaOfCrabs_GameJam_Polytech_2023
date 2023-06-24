package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class CrabKingAvatar extends Avatar{
	private int k;
	private boolean visible;
	
	public CrabKingAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.CrabKing);
		imageIndex = 0;
		k = 0;
		visible = false;
	}

	@Override
	public void tick(long elapsed) {
		if (++k % 20 == 0) {
			if (k==80) {
				k = 0;
				imageIndex = 0;
			} else {
				imageIndex++;
			}
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();

		int coeffX = -entity.getX()+GameModele.getCurrentPlayerX()+width/2;
		int coeffY = -entity.getY()+GameModele.getCurrentPlayerY()+height/2;
		// Only draw them if they are on screen
		if (coeffX < width && coeffY < height && coeffX + width_painted > 0
				&& coeffY + heigth_painted > 0) {
		g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
		}
	}
	
}
