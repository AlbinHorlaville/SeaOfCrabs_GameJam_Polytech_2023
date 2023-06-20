package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.StillEntityClass.CrabLair;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class CrabslairAvatar extends Avatar {

	BufferedImage[] m_images;

	public CrabslairAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Crabslair);
		imageIndex = 0;
	}

	@Override
	public void tick(long elapsed) {
		if(((CrabLair)this.entity).isDead()) {
			this.imageIndex = 1;
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {

		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();

		int Decalage_Tiles_X = (int) (-32 + 14) * SCALE_IMG;
		int Decalage_Tiles_Y = (int) (-32 + 6) * SCALE_IMG;

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2 + Decalage_Tiles_X;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2 + Decalage_Tiles_Y;

		g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
	}
}
