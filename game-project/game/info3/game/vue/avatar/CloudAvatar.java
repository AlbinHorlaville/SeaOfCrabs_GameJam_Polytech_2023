package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
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
		int width_painted = size * img.getWidth();
		int heigth_painted = size * img.getHeight();

		g.drawImage(img, entity.getX(), entity.getY(), width_painted, heigth_painted, null);
	}

}
