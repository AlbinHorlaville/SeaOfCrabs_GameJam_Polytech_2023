package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;
import info3.game.modele.MoveableEntityClass.Ship;

public class ShipAvatar extends Avatar {

	public ShipAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.PirateBoatEnnemie);
		imageIndex = 0;
	}

	@Override
	public void tick(long elapsed) {

	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2;
		if (coeffX < width && coeffY < height && coeffX + width_painted > 0
				&& coeffY + heigth_painted > 0) {
			g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
		}
	}
}
