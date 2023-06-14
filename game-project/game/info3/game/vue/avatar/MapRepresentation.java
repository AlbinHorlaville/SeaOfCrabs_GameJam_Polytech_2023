package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.modele.map.Map;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;

public class MapRepresentation {
	private MapSection[] map;
	private int sectionWidth;
	private int sectionHeight;
	private int nbSection;
	private double[][] wave;

	/*
	 * Save images for only one loading
	 */
	private BufferedImage grassImage;
	private BufferedImage sandImage;
	private BufferedImage waterImage;
	private BufferedImage sandWaterImage;

	private int scale;
	
	private int imgWidth;
	private int imgHeight;

	/*
	 * The representation of the map, it's here that we paint the map
	 */
	public MapRepresentation(Map m) throws IOException {

		this.scale = 6;

		/*
		 * Load tiles images
		 */
		File imageFile = new File("assets/img/tiles/grass.png");
		if (imageFile.exists()) {
			this.grassImage = ImageIO.read(imageFile);
			this.grassImage = resize(this.grassImage, this.grassImage.getWidth() * scale,
					this.grassImage.getHeight() * scale);
		}
		
		this.imgWidth = this.grassImage.getWidth();
		this.imgHeight = this.grassImage.getHeight();

		imageFile = new File("assets/img/tiles/sand.png");
		if (imageFile.exists()) {
			this.sandImage = ImageIO.read(imageFile);
			this.sandImage = resize(this.sandImage, this.sandImage.getWidth() * scale,
					this.sandImage.getHeight() * scale);
		}

		imageFile = new File("assets/img/tiles/water.png");
		if (imageFile.exists()) {
			this.waterImage = ImageIO.read(imageFile);
			this.waterImage = resize(this.waterImage, this.waterImage.getWidth() * scale,
					this.waterImage.getHeight() * scale);
		}

		imageFile = new File("assets/img/tiles/sand_water.png");
		if (imageFile.exists()) {
			this.sandWaterImage = ImageIO.read(imageFile);
			this.sandWaterImage = resize(this.sandWaterImage, this.sandWaterImage.getWidth() * scale,
					this.sandWaterImage.getHeight() * scale);
		}

		/*
		 * Set the coordinate of each tiles
		 */
		m.setImageSize(this.imgWidth, this.imgHeight);
		m.setCoordiate(this.waterImage.getWidth(), this.waterImage.getHeight());

		this.map = m.getMap();
		this.nbSection = m.getNbSection();
		this.sectionHeight = m.getSectionHeight();
		this.sectionWidth = m.getSectionWidth();
		this.wave = m.getWave();
	}

	/*
	 * Resize an image to the new dimension
	 * @param img : the image to resize
	 * @param newWidth and newHeight : The new dimension of the images
	 */
	private BufferedImage resize(BufferedImage img, int newWidth, int newHeight) throws IOException {
		BufferedImage outputImage = new BufferedImage(newWidth, newHeight, img.getType());

		// scales the input image to the output image
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(img, 0, 0, newWidth, newHeight, null);
		g2d.dispose();

		return outputImage;
	}

	/*
	 * Paint the map based on the player position
	 * 
	 * @param g : The screen
	 * 
	 * @param width and height : Screen dimension
	 * 
	 * @param playerX and playerY : player position
	 */
	public void paint(Graphics g, int width, int height, int playerX, int playerY) {
		BufferedImage img = waterImage; // Save the currentImage
		Tiles[][] section; // Save the section beeing treated

		/*
		 * Images position
		 */
		int imgWidth = img.getWidth();
		int imgHeight = img.getHeight();

		int playerStartX = this.map[0].getTiles()[this.sectionHeight - 5][this.sectionWidth / 2]
				.getX();
		int playerStartY = this.map[0].getTiles()[this.sectionHeight - 5][this.sectionWidth / 2]
				.getY();

		int tileX;
		int tileY;
		int positionX;
		int positionY;

		double waveOffset;

		Tiles currentTile;

		/*
		 * Draw each tiles of the map
		 */
		boolean sandWater;

		for (int i = 0; i < this.nbSection; i++) {
			section = this.map[(this.nbSection - i - 1)].getTiles();
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth; k++) {

					currentTile = section[j][k];

					tileX = currentTile.getX();
					positionX = tileX + playerX;// - playerStartX;

					tileY = currentTile.getY();
					positionY = tileY + playerY;// - playerStartY;

					// Only drawing the tiles on screen
					if (positionX < width + imgWidth && positionX > 0 - imgWidth
							&& positionY < height + imgHeight && positionY > 0 - imgHeight) {

						waveOffset = this.wave[i * this.sectionHeight + j][k];

						sandWater = false;

						switch (currentTile.getType()) {
						case CALM_WATER:
							img = waterImage;
							positionY += (int) waveOffset;
							break;
						case GRASS:
							img = grassImage;
							break;
						case SAND:
							img = sandImage;
							break;
						case SAND_WATER:
							img = sandImage;
							sandWater = true;
							break;
						default:
							img = waterImage;
							break;
						}

						g.drawImage(img, positionX, positionY, imgWidth, imgHeight, null);
						// If the tile to be drawed is sand water (special case since it is composed of
						// 2 tiles, a static sand and a moving half transparent water)
						if (sandWater && positionY + (int) waveOffset < positionY) {
							g.drawImage(this.sandWaterImage, positionX, positionY + (int) waveOffset, imgWidth,
									imgHeight, null);

						}
					}
				}
			}
		}
	}
	
	public int getImageWidth () {
		return this.imgWidth;
	}
	
	public int getImageHeight () {
		return this.imgHeight;
	}
}