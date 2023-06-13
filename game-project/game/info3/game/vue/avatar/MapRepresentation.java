package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.modele.map.EnumTiles;
import info3.game.modele.map.Map;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;

public class MapRepresentation {
	MapSection[] map; // The map itself

	/*
	 * Map parameters
	 */
	int nbSection;
	int sectionWidth;
	int sectionHeight;

	/*
	 * Save images for only one loading
	 */
	private BufferedImage grassImage;
	private BufferedImage sandImage;
	private BufferedImage waterImage;

	/*
	 * The representation of the map, it's here that we paint the map
	 */
	public MapRepresentation(Map m, double[][] wave) throws IOException {

		/*
		 * Load tiles images
		 */
		File imageFile = new File("resources/tiles/grass.png");
		if (imageFile.exists()) {
			this.grassImage = ImageIO.read(imageFile);
		}

		imageFile = new File("resources/tiles/sand.png");
		if (imageFile.exists()) {
			this.sandImage = ImageIO.read(imageFile);
		}

		imageFile = new File("resources/tiles/water.png");
		if (imageFile.exists()) {
			this.waterImage = ImageIO.read(imageFile);
		}

		/*
		 * Set the coordinate of each tiles
		 */
		m.setCoordiate(this.waterImage.getWidth(), this.waterImage.getHeight());

		this.map = m.getMap();

		this.nbSection = map.length;
		this.sectionHeight = this.map[0].getSectionHeight();
		this.sectionWidth = this.map[0].getSectionWidth();
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

		/*
		 * Draw each tiles of the map
		 */
		for (int i = 0; i < this.nbSection; i++) {
			section = this.map[i].getTiles();
			for (int j = 0; j < this.sectionHeight; j++) {
				for (int k = 0; k < this.sectionWidth; k++) {
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
					default:
						img = waterImage;
						break;
					}

					// Only drawing the tiles on screen
					if (!(section[j][k].getX() + playerX > width) && !(section[j][k].getY() + playerY > height)) {
						g.drawImage(img, section[j][k].getX() + playerX, section[j][k].getY() + playerY, imgWidth,
								imgHeight, null);
					}
				}
			}
		}
	}
}