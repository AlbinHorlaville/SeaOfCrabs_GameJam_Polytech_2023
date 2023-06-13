package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.modele.map.Map;
import info3.game.modele.map.Tiles;

public class MapRepresentation {
	Map map; // The map itself

	/*
	 * Save images for only one loading
	 */
	private BufferedImage grassImage;
	private BufferedImage sandImage;
	private BufferedImage waterImage;
	private BufferedImage sandWaterImage;

	private int scale;

	/*
	 * The representation of the map, it's here that we paint the map
	 */
	public MapRepresentation(Map m) throws IOException {

		/*
		 * Load tiles images
		 */
		File imageFile = new File("assets/img/tiles/grass.png");
		if (imageFile.exists()) {
			this.grassImage = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/sand.png");
		if (imageFile.exists()) {
			this.sandImage = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/water.png");
		if (imageFile.exists()) {
			this.waterImage = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/sand_water.png");
		if (imageFile.exists()) {
			this.sandWaterImage = ImageIO.read(imageFile);
		}
		this.scale = 3;
		/*
		 * Set the coordinate of each tiles
		 */
		m.setCoordiate(this.waterImage.getWidth() * scale, this.waterImage.getHeight() * scale);

		this.map = m;

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

		int playerStartX = this.map.getMap()[this.map.getNbSection() - 1].getTiles()[this.map.getSectionHeight()
				- 5][this.map.getSectionWidth() / 2].getX();
		int playerStartY = this.map.getMap()[this.map.getNbSection() - 1].getTiles()[this.map.getSectionHeight()
				- 5][this.map.getSectionWidth() / 2].getY();

		int tileX;
		int tileY;

		/*
		 * Draw each tiles of the map
		 */
		boolean sandWater;
		for (int i = 0; i < this.map.getNbSection(); i++) {
			section = this.map.getMap()[i].getTiles();
			for (int j = 0; j < this.map.getSectionHeight(); j++) {
				for (int k = 0; k < this.map.getSectionWidth(); k++) {
					sandWater = false;
					switch (section[j][k].getType()) {
					case CALM_WATER:
						img = waterImage;
						break;
					case SAND:
						img = sandImage;
						break;
					case GRASS:
						img = grassImage;
						break;
					case SAND_WATER:
						img = sandImage;
						sandWater = true;
						break;
					default:
						img = waterImage;
						break;
					}

					tileX = section[j][k].getX();
					tileY = section[j][k].getY();

					// Only drawing the tiles on screen
					if (!(tileX + playerX - playerStartX > width + imgWidth * 2
							|| tileX + playerX - playerStartX < 0 - imgWidth * 3)
							&& !(tileY + playerY - playerStartY > height + imgHeight * 2
									|| tileY + playerY - playerStartY < 0 - imgHeight * 2)) {
						// If the tile to be drawed is sand water (special case since it is composed of
						// 2 tiles, a static sand and a moving half transparent water)
						if (sandWater) {
							// If the sand is on top of the water
							if (tileY + playerY - playerStartY > map.transpoYCoordinateToIsometric(imgWidth * scale, imgHeight * scale, k,
									i * this.map.getSectionHeight() + j) + playerY - playerStartY) {
								// We only draw the sand since the water is under
								// We have to recalculate the coordinate to remove the wave offset since the
								// sand is not moving and this offset is only applied to the transparent water
								// when she is on top of the sand
								g.drawImage(img,
										map.transpoXCoordinateToIsometric(imgWidth * scale, imgHeight * scale, k,
												i * this.map.getSectionHeight() + j) + playerX - playerStartX,
										map.transpoYCoordinateToIsometric(imgWidth * scale, imgHeight * scale, k,
												i * this.map.getSectionHeight() + j) + playerY - playerStartY,
										imgWidth * scale, imgHeight * scale, null);
							} else { // If the water is on top of the sand
								// We first draw the sand and then the water (which is on top)
								g.drawImage(img,
										map.transpoXCoordinateToIsometric(imgWidth * scale, imgHeight * scale, k,
												i * this.map.getSectionHeight() + j) + playerX - playerStartX,
										map.transpoYCoordinateToIsometric(imgWidth * scale, imgHeight * scale, k,
												i * this.map.getSectionHeight() + j) + playerY - playerStartY,
										imgWidth * scale, imgHeight * scale, null);

								img = sandWaterImage;

								g.drawImage(img, tileX + playerX - playerStartX, tileY + playerY - playerStartY,
										imgWidth * scale, imgHeight * scale, null);

							}
						} else {
							// We can directly draw the tiles
							g.drawImage(img, tileX + playerX - playerStartX, tileY + playerY - playerStartY,
									imgWidth * scale, imgHeight * scale, null);
						}
					}
				}
			}
		}
	}
}