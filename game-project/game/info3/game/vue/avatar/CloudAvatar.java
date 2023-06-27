package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class CloudAvatar extends Avatar {
	int size;

	/**
	 * Créer l'avatar d'un nuage
	 * 
	 * @param entity Ici, c'est un CloudCluster
	 * @param s      la taille du nuage
	 */
	public CloudAvatar(Entity entity, int s) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Cloud);
		imageIndex = 0;
		this.size = s;
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = img.getWidth() * size;
		int heigth_painted = img.getHeight() * size;

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2 - width_painted / 2;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2 - heigth_painted / 2;

		// Only draw them if they are on screen
		if (coeffX < width && coeffY < height && coeffX + width_painted > 0 && coeffY + heigth_painted > 0) {
			g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
		}
	}

}
