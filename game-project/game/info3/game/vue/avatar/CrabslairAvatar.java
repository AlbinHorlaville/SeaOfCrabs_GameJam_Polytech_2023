package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class CrabslairAvatar extends Avatar{
	
	BufferedImage[] m_images;
	Entity crabslair;

	public CrabslairAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Crabslair);
		imageIndex = 0;
	}

	@Override
	public void tick(long elapsed) {
		if (true) { // Tous les crabes sont morts
			imageIndex = 1;
		}
		
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();
		
		g.drawImage(img,100, 100, 1000, 1000, null);
		
	}

}
