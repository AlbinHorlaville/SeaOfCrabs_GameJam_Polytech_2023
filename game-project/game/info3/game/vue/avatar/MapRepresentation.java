package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import info3.game.modele.map.Map;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;
import info3.game.vue.GameView;

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
	private BufferedImage calmWaterImage;
	private BufferedImage stormyWaterImage;
	private BufferedImage ragingWaterImage;
	private BufferedImage krakenWaterImage;
	private BufferedImage pontoonImage;
	private BufferedImage moutainImage;
	private BufferedImage defaultImage;
	private BufferedImage crabKingLandImage;

	private BufferedImage[] grassTransitionOneSide;
	private BufferedImage[] grassTransitionTwoSide;
	private BufferedImage[] grassTransitionThreeSide;
	private BufferedImage[] grassTransitionAngle;
	private BufferedImage[] grassVariante;
	private BufferedImage[] sandVariante;
	private BufferedImage[] sandWaterImage;
	private BufferedImage[] stoneImage;
	private BufferedImage[] waterPreDamageImage;
	private BufferedImage[] waterDamagingImage;

	private int scale;

	private int imgWidth;
	private int imgHeight;

	/*
	 * The representation of the map, it's here that we paint the map
	 */
	public MapRepresentation(Map m) throws IOException {

		this.scale = GameView.SCALE;

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
		
		imageFile = new File("assets/img/tiles/default.png");
		if (imageFile.exists()) {
			this.defaultImage = ImageIO.read(imageFile);
			this.defaultImage = resize(this.defaultImage, this.defaultImage.getWidth() * scale,
					this.defaultImage.getHeight() * scale);
		}
		
		imageFile = new File("assets/img/tiles/crabKingLand.png");
		if (imageFile.exists()) {
			this.crabKingLandImage = ImageIO.read(imageFile);
			this.crabKingLandImage = resize(this.crabKingLandImage, this.crabKingLandImage.getWidth() * scale,
					this.crabKingLandImage.getHeight() * scale);
		}
		
		imageFile = new File("assets/img/tiles/moutain.png");
		if (imageFile.exists()) {
			this.moutainImage = ImageIO.read(imageFile);
			this.moutainImage = resize(this.moutainImage, this.moutainImage.getWidth() * scale,
					this.moutainImage.getHeight() * scale);
		}

		imageFile = new File("assets/img/tiles/water.png");
		if (imageFile.exists()) {
			this.calmWaterImage = ImageIO.read(imageFile);
			this.calmWaterImage = resize(this.calmWaterImage, this.calmWaterImage.getWidth() * scale,
					this.calmWaterImage.getHeight() * scale);
		}

		imageFile = new File("assets/img/tiles/stormy_water.png");
		if (imageFile.exists()) {
			this.stormyWaterImage = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/pontoon.png");
		if (imageFile.exists()) {
			this.pontoonImage = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/raging_water.png");
		if (imageFile.exists()) {
			this.ragingWaterImage = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/kraken_water.png");
		if (imageFile.exists()) {
			this.krakenWaterImage = ImageIO.read(imageFile);
		}

		this.sandWaterImage = new BufferedImage[3];

		imageFile = new File("assets/img/tiles/sand_water.png");
		if (imageFile.exists()) {
			this.sandWaterImage[0] = ImageIO.read(imageFile);
			this.sandWaterImage[0] = resize(this.sandWaterImage[0], this.sandWaterImage[0].getWidth() * scale,
					this.sandWaterImage[0].getHeight() * scale);
		}

		imageFile = new File("assets/img/tiles/stormy_sand_water.png");
		if (imageFile.exists()) {
			this.sandWaterImage[1] = ImageIO.read(imageFile);
			this.sandWaterImage[1] = resize(this.sandWaterImage[1], this.sandWaterImage[1].getWidth() * scale,
					this.sandWaterImage[1].getHeight() * scale);
		}

		imageFile = new File("assets/img/tiles/raging_sand_water.png");
		if (imageFile.exists()) {
			this.sandWaterImage[2] = ImageIO.read(imageFile);
			this.sandWaterImage[2] = resize(this.sandWaterImage[2], this.sandWaterImage[2].getWidth() * scale,
					this.sandWaterImage[2].getHeight() * scale);
		}

		this.stoneImage = new BufferedImage[2];

		imageFile = new File("assets/img/tiles/stone.png");
		if (imageFile.exists()) {
			this.stoneImage[0] = ImageIO.read(imageFile);
			this.stoneImage[0] = resize(this.stoneImage[0], this.stoneImage[0].getWidth() * scale,
					this.stoneImage[0].getHeight() * scale);
		}

		imageFile = new File("assets/img/tiles/stoneTransition.png");
		if (imageFile.exists()) {
			this.stoneImage[1] = ImageIO.read(imageFile);
			this.stoneImage[1] = resize(this.stoneImage[1], this.stoneImage[1].getWidth() * scale,
					this.stoneImage[1].getHeight() * scale);
		}
		
		this.waterPreDamageImage = new BufferedImage[4];

		imageFile = new File("assets/img/tiles/water_pre_damage.png");
		if (imageFile.exists()) {
			waterPreDamageImage[0] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/stormy_water_pre_damage.png");
		if (imageFile.exists()) {
			waterPreDamageImage[1] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/raging_water_pre_damage.png");
		if (imageFile.exists()) {
			waterPreDamageImage[2] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/kraken_water_pre_damage.png");
		if (imageFile.exists()) {
			waterPreDamageImage[3] = ImageIO.read(imageFile);
		}
		
		this.waterDamagingImage = new BufferedImage[4];

		imageFile = new File("assets/img/tiles/water_damaging.png");
		if (imageFile.exists()) {
			waterDamagingImage[0] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/stormy_water_damaging.png");
		if (imageFile.exists()) {
			waterDamagingImage[1] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/raging_water_damaging.png");
		if (imageFile.exists()) {
			waterDamagingImage[2] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/kraken_water_damaging.png");
		if (imageFile.exists()) {
			waterDamagingImage[3] = ImageIO.read(imageFile);
		}

		this.grassTransitionAngle = new BufferedImage[4];

		imageFile = new File("assets/img/tiles/grassBottomLeftSand.png");
		if (imageFile.exists()) {
			grassTransitionAngle[0] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassBottomRightSand.png");
		if (imageFile.exists()) {
			grassTransitionAngle[1] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassTopLeftSand.png");
		if (imageFile.exists()) {
			grassTransitionAngle[2] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassTopRightSand.png");
		if (imageFile.exists()) {
			grassTransitionAngle[3] = ImageIO.read(imageFile);
		}

		this.grassTransitionOneSide = new BufferedImage[4];

		imageFile = new File("assets/img/tiles/grassOnTopOfSand.png");
		if (imageFile.exists()) {
			grassTransitionOneSide[0] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassOnLeftOfSand.png");
		if (imageFile.exists()) {
			grassTransitionOneSide[1] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassOnRightOfSand.png");
		if (imageFile.exists()) {
			grassTransitionOneSide[2] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassUnderOfSand.png");
		if (imageFile.exists()) {
			grassTransitionOneSide[3] = ImageIO.read(imageFile);
		}

		this.grassTransitionTwoSide = new BufferedImage[6];

		imageFile = new File("assets/img/tiles/grassOnLeftAndOnRightOfSand.png");
		if (imageFile.exists()) {
			grassTransitionTwoSide[0] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassOnTopAndOnLeftOfSand.png");
		if (imageFile.exists()) {
			grassTransitionTwoSide[1] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassOnTopAndOnRightOfSand.png");
		if (imageFile.exists()) {
			grassTransitionTwoSide[2] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassOnTopAndUnderSand.png");
		if (imageFile.exists()) {
			grassTransitionTwoSide[3] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassUnderAndOnLeftOfSand.png");
		if (imageFile.exists()) {
			grassTransitionTwoSide[4] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassUnderAndOnRightOfSand.png");
		if (imageFile.exists()) {
			grassTransitionTwoSide[5] = ImageIO.read(imageFile);
		}

		this.grassTransitionThreeSide = new BufferedImage[4];

		imageFile = new File("assets/img/tiles/grassOnTopAndOnLeftAndOnRightOfSand.png");
		if (imageFile.exists()) {
			grassTransitionThreeSide[0] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassUnderAndOnLeftAndOnRightOfSand.png");
		if (imageFile.exists()) {
			grassTransitionThreeSide[1] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassUnderAndOnLeftAndOnTopOfSand.png");
		if (imageFile.exists()) {
			grassTransitionThreeSide[2] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grassUnderAndOnRightAndOnTopOfSand.png");
		if (imageFile.exists()) {
			grassTransitionThreeSide[3] = ImageIO.read(imageFile);
		}

		this.grassVariante = new BufferedImage[6];

		imageFile = new File("assets/img/tiles/blue_flower.png");
		if (imageFile.exists()) {
			grassVariante[0] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/yellow_flower.png");
		if (imageFile.exists()) {
			grassVariante[1] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/red_flower.png");
		if (imageFile.exists()) {
			grassVariante[2] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grass_rock_1.png");
		if (imageFile.exists()) {
			grassVariante[3] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/grass_rock_2.png");
		if (imageFile.exists()) {
			grassVariante[4] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/tree.png");
		if (imageFile.exists()) {
			grassVariante[5] = ImageIO.read(imageFile);
		}

		this.sandVariante = new BufferedImage[3];

		imageFile = new File("assets/img/tiles/shellfish1.png");
		if (imageFile.exists()) {
			sandVariante[0] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/shellfish2.png");
		if (imageFile.exists()) {
			sandVariante[1] = ImageIO.read(imageFile);
		}

		imageFile = new File("assets/img/tiles/shellfish3.png");
		if (imageFile.exists()) {
			sandVariante[2] = ImageIO.read(imageFile);
		}

		/*
		 * Set the coordinate of each tiles
		 */
		m.setImageSize(this.imgWidth, this.imgHeight);
		m.setCoordiate(this.imgWidth, this.imgHeight);

		this.map = m.getMap();
		this.nbSection = m.getNbSection();
		this.sectionHeight = m.getSectionHeight();
		this.sectionWidth = m.getSectionWidth();
		this.wave = m.getWave();
	}

	/*
	 * Resize an image to the new dimension
	 * 
	 * @param img : the image to resize
	 * 
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
		BufferedImage img = calmWaterImage; // Save the currentImage
		Tiles[][] section; // Save the section beeing treated

		/*
		 * Images position
		 */
		int imgWidth = img.getWidth();
		int imgHeight = img.getHeight();

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

					tileX = currentTile.getDisplayX();
					positionX = tileX + playerX;

					tileY = currentTile.getDisplayY(this.calmWaterImage.getHeight());
					positionY = tileY + playerY;

					// Only drawing the tiles on screen
					if (positionX < width + imgWidth && positionX > 0 - imgWidth && positionY < height + imgHeight
							&& positionY > 0 - imgHeight) {

						waveOffset = this.wave[i * this.sectionHeight + j][k];

						sandWater = false;

						switch (currentTile.getType()) {
						case CALM_WATER:
						case CALM_SEA_ENNEMIE:
						case CALM_SEA_CHEST:
							img = calmWaterImage;
							positionY += (int) waveOffset;
							break;
						case STORMY_WATER:
						case STORMY_SEA_ENNEMIE:
						case STORMY_SEA_CHEST:
							img = stormyWaterImage;
							positionY += (int) waveOffset;
							break;
						case RAGING_WATER:
						case RAGING_SEA_ENNEMIE:
						case RAGING_SEA_CHEST:
							img = ragingWaterImage;
							positionY += (int) waveOffset;
							break;
						case KRAKEN_WATER:
						case KRAKEN_TENTACLE:
							img = krakenWaterImage;
							positionY += (int) waveOffset;
							break;
						case GRASS:
							img = grassImage;
							break;
						case MOUTAIN:
							img = moutainImage;
							break;
						case SAND:
						case HARBOR_SAND:
						case TREASUR:
							img = sandImage;
							break;
						case SAND_WATER:
						case STORMY_SAND_WATER:
						case RAGING_SAND_WATER:
							img = sandImage;
							sandWater = true;
							break;
						case CRAB_SPAWNER:
							img = this.stoneImage[0];
							break;
						case CRAB_SPAWNER_TRANSITION:
							img = this.stoneImage[1];
							break;
						case PONTOON:
							img = this.pontoonImage;
							break;
						case BLUE_FLOWER:
							img = this.grassVariante[0];
							break;
						case YELLOW_FLOWER:
							img = this.grassVariante[1];
							break;
						case RED_FLOWER:
							img = this.grassVariante[2];
							break;
						case GRASS_WITH_ROCK_1:
							img = this.grassVariante[3];
							break;
						case GRASS_WITH_ROCK_2:
							img = this.grassVariante[4];
							break;
						case TREE:
							img = this.grassVariante[5];
							break;
						case SHELLFISH_1:
							img = this.sandVariante[0];
							break;
						case SHELLFISH_2:
							img = this.sandVariante[1];
							break;
						case SHELLFISH_3:
							img = this.sandVariante[2];
							break;
						case CRAB_KING_LAND:
						case CRAB_KING:
							img = this.crabKingLandImage;
							break;
						case TRANSITION_GRASS_ANGLE_SAND_TOP_RIGHT:
							img = this.grassTransitionAngle[0];
							break;
						case TRANSITION_GRASS_ANGLE_SAND_BOTTOM_RIGHT:
							img = this.grassTransitionAngle[1];
							break;
						case TRANSITION_GRASS_ANGLE_SAND_TOP_LEFT:
							img = this.grassTransitionAngle[2];
							break;
						case TRANSITION_GRASS_ANGLE_SAND_BOTTOM_LEFT:
							img = this.grassTransitionAngle[3];
							break;
						case TRANSITION_GRASS_ON_RIGHT_AND_ON_LEFT_AND_ON_TOP_OF_SAND:
							img = this.grassTransitionThreeSide[0];// grassOnTopAndOnLeftAndOnRightOfSand
							break;
						case TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_RIGHT_OF_SAND:
							img = this.grassTransitionThreeSide[1];// grassUnderAndOnLeftAndOnRightOfSand
							break;
						case TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_TOP_OF_SAND:
							img = this.grassTransitionThreeSide[2];// grassUnderAndOnLeftAndOnTopOfSand
							break;
						case TRANSITION_GRASS_UNDER_AND_ON_RIGHT_AND_ON_TOP_OF_SAND:
							img = this.grassTransitionThreeSide[3];// grassUnderAndOnRightAndOnTopOfSand
							break;
						case TRANSITION_GRASS_ON_TOP_OF_SAND:
							img = this.grassTransitionOneSide[0];// grassOnTopOfSand
							break;
						case TRANSITION_GRASS_ON_LEFT_OF_SAND:
							img = this.grassTransitionOneSide[1];// grassOnLeftOfSand
							break;
						case TRANSITION_GRASS_ON_RIGHT_OF_SAND:
							img = this.grassTransitionOneSide[2];// grassOnRightOfSand
							break;
						case TRANSITION_GRASS_UNDER_SAND:
							img = this.grassTransitionOneSide[3];// grassUnderOfSand
							break;
						case TRANSITION_GRASS_ON_LEFT_AND_ON_RIGHT_OF_SAND:
							img = this.grassTransitionTwoSide[0];// grassOnLeftAndOnRightOfSand
							break;
						case TRANSITION_GRASS_ON_TOP_AND_ON_LEFT_OF_SAND:
							img = this.grassTransitionTwoSide[1];// grassOnTopAndOnLeftOfSand
							break;
						case TRANSITION_GRASS_ON_TOP_AND_ON_RIGHT_OF_SAND:
							img = this.grassTransitionTwoSide[2];// grassOnTopAndOnRightOfSand
							break;
						case TRANSITION_GRASS_ON_TOP_AND_UNDER_OF_SAND:
							img = this.grassTransitionTwoSide[3];// grassOnTopAndUnderSand
							break;
						case TRANSITION_GRASS_UNDER_AND_ON_LEFT_OF_SAND:
							img = this.grassTransitionTwoSide[4];// grassUnderAndOnLeftOfSand
							break;
						case TRANSITION_GRASS_UNDER_AND_ON_RIGHT_OF_SAND:
							img = this.grassTransitionTwoSide[5];// grassUnderAndOnRightOfSand
							break;
						case CALM_WATER_PRE_DAMAGE:
							img = this.waterPreDamageImage[0];
							break;
						case STORMY_WATER_PRE_DAMAGE:
							img = this.waterPreDamageImage[1];
							break;
						case RAGING_WATER_PRE_DAMAGE:
							img = this.waterPreDamageImage[2];
							break;
						case KRAKEN_WATER_PRE_DAMAGE:
							img = this.waterPreDamageImage[3];
							break;
						case CALM_WATER_DAMAGING:
							img = this.waterDamagingImage[0];
							break;
						case STORMY_WATER_DAMAGING:
							img = this.waterDamagingImage[1];
							break;
						case RAGING_WATER_DAMAGING:
							img = this.waterDamagingImage[2];
							break;
						case KRAKEN_WATER_DAMAGING:
							img = this.waterDamagingImage[3];
							break;
						default:
							img = defaultImage;
							break;
						}

						g.drawImage(img, positionX, positionY, imgWidth, imgHeight, null);
						// If the tile to be drawed is sand water (special case since it is composed of
						// 2 tiles, a static sand and a moving half transparent water)
						if (sandWater && positionY + (int) waveOffset < positionY) {
							switch (currentTile.getType()) {
							case SAND_WATER:
								img = this.sandWaterImage[0];
								break;
							case STORMY_SAND_WATER:
								img = this.sandWaterImage[1];
								break;
							default:
								img = this.sandWaterImage[2];
								break;
							}
							g.drawImage(img, positionX, positionY + (int) waveOffset, imgWidth, imgHeight, null);

						}
					}
				}
			}
		}
	}

	public int getImageWidth() {
		return this.imgWidth;
	}

	public int getImageHeight() {
		return this.imgHeight;
	}
}