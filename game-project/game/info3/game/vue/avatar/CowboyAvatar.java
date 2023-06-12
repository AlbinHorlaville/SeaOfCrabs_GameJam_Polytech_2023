package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import info3.game.modele.Entity;

public class CowboyAvatar extends Avatar {

	public CowboyAvatar(Entity cowboy) throws IOException {
		super(cowboy);
		m_images = loadSprite("assets/img/player/" + "winchester-4x6.png", 4, 6);
	}

	/*
	 * Simple animation here, the cowbow 
	 */
	public void tick(long elapsed) {
		imageElapsed += elapsed;
		if (imageElapsed > 200) {
			imageElapsed = 0;
			imageIndex = (imageIndex + 1) % m_images.length;
		}
	    moveElapsed += elapsed;
	    if (moveElapsed>24 & width!=0) {
	      moveElapsed=0;
	      this.entity.setX((entity.getX() +2)%width); 
	    }
	}

	public void paint(Graphics g, int width, int height) {
		this.width = width;
		BufferedImage img = m_images[imageIndex];
		int scale = 2;
		g.drawImage(img, this.entity.getX(), entity.getY(), scale * img.getWidth(), scale * img.getHeight(), null);
	}
}
