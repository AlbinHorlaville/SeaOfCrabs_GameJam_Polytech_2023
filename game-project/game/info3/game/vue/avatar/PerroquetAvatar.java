package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.Perroquet;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class PerroquetAvatar extends Avatar {
	private int k;

	public PerroquetAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Perroquet);
		imageIndex = 0;
		k = 0;
	}

	@Override
	public void tick(long elapsed) {

		int entityX = entity.getX();
		int closestX = ((Perroquet) entity).closestPlayer.x;

		int entityY = entity.getY();
		int closestY = ((Perroquet) entity).closestPlayer.y;

		if (++k == 2)
			k = 0;
		if (entityX > closestX && entityY < closestY) {
			imageIndex = 0;
		} else if (entityX < closestX && entityY < closestY) {
			imageIndex = 2;
		} else if (entityX > closestX && entityY > closestY) {
			imageIndex = 4;
		} else {
			imageIndex = 6;
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex + k];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2 - img.getWidth() / 2;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2 - img.getHeight() / 2;

		if (coeffX < width && coeffY < height && coeffX + width_painted > 0
				&& coeffY + heigth_painted > 0) {

			g.drawImage(img, coeffX, coeffY, width_painted * 2 / 3, heigth_painted * 2 / 3, null);
		}
	}
}
