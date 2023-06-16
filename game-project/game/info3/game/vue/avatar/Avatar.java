package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.modele.Entity;


public abstract class Avatar {
	BufferedImage[] m_images;
	int imageIndex;
	long imageElapsed;
	long moveElapsed;
	int width;

	Entity entity;
	
	public final static int SCALE_IMG = 4;

	public Avatar(Entity entity) {
		this.entity = entity;
	}

	/*
	 * Simple animation here, the cowbow 
	 */
	public abstract void tick(long elapsed);

	public abstract void paint(Graphics g, int width, int height);

	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return images;
		}
		return null;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public int getWidth() {
		return m_images[imageIndex].getWidth() * SCALE_IMG;
	}
	
	public int getHeight() {
		return m_images[imageIndex].getHeight() * SCALE_IMG;
	}

}
