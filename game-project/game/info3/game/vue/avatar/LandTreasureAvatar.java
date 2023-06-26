package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class LandTreasureAvatar extends Avatar {

	public LandTreasureAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.LandTreasure);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();

		int Decalage_Tiles_X = -SCALE_IMG;
		int Decalage_Tiles_Y = 2 * SCALE_IMG;

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2 + Decalage_Tiles_X;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2 + Decalage_Tiles_Y;

		if (coeffX < width && coeffY < height && coeffX + width_painted > 0
				&& coeffY + heigth_painted > 0) {
			g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
		}
	}

}
