package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class TreeAvatar extends Avatar{
	
	BufferedImage[] m_images;
	Entity crabslair;


	public TreeAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Palmer);
		imageIndex = 0;
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
		
		g.drawImage(img,entity.getX(), entity.getY(), width_painted, heigth_painted, null);
	}

}
