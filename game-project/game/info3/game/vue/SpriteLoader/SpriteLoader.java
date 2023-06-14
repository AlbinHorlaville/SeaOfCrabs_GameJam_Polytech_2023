package info3.game.vue.SpriteLoader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class SpriteLoader {

	public static BufferedImage[][] allSprites;

	public final static String pathP = "assets/img/player/";
	public final static String pathT = "assets/img/tiles/";
	public static int nbSpriteTypes = 2;
	public static int nbSpritesMaxPerType = 50;
	public static int playerOffset = 0;
	public static int tileOffset = 0;
	public static int loadMode = 0;
	public final static int PLAYER = 0;
	public final static int TILE = 1;

	public static HashMap<SpriteType, BufferedImage[]> TypeSpritesMap; // Voir entre public et static

	/*
	 * Charge tous les sprites dans le tableau allSprites
	 * 
	 * @return rien. Effet de bord: Remplit allSprites
	 */
	public static void initAllSprites() throws IOException {
		allSprites = new BufferedImage[nbSpriteTypes][nbSpritesMaxPerType];
		
		tileOffset = 0;
		playerOffset = 0;
		
		BufferedImage[] playerSprites = loadPlayerSprites();
		BufferedImage[] tileSprites = loadTileSprites();

		TypeSpritesMap = new HashMap<>();
		SpriteLoader.TypeSpritesMap.put(SpriteType.Player, playerSprites);
		SpriteLoader.TypeSpritesMap.put(SpriteType.Tile, tileSprites);

		allSprites[0] = playerSprites;
		allSprites[1] = tileSprites;

	}

	/*
	 * Charge dans une array de BufferedImage tous les Sprites pour les players
	 * (hors bateaux).
	 * 
	 * @return un BufferedImage [] contenant tous les sprites des players
	 */
	public static BufferedImage[] loadPlayerSprites() throws IOException {
		BufferedImage[] playerSprites = new BufferedImage[nbSpritesMaxPerType];
		switchLoadMode(PLAYER);
		loadSprite(pathP + "winchester-4x6.png", 4, 6, playerSprites);
		return playerSprites;

	}

	/*
	 * Charge dans une array de BufferedImage tous les Sprites pour les tiles.
	 * 
	 * @return un BufferedImage [] contenant tous les sprites des tiles
	 */
	public static BufferedImage[] loadTileSprites() throws IOException {
		BufferedImage[] tileSprites = new BufferedImage[nbSpritesMaxPerType];
		switchLoadMode(TILE);
		loadSprite(pathT + "grass.png", 1, 1, tileSprites);
		loadSprite(pathT + "sand_water.png", 1, 1, tileSprites);
		loadSprite(pathT + "sand.png", 1, 1, tileSprites);
		loadSprite(pathT + "water.png", 1, 1, tileSprites);
		return tileSprites;

	}

	// lo
	/*
	 * Découpe une image selon ses sous-images et les charge dans un BufferedImage[]
	 * 
	 * @return rien: Effet de bord: Stock les sous-images de l'image de nom filename
	 * dans le BufferedImage[] images.
	 * 
	 */
	public static void loadSprite(String filename, int nrows, int ncols, BufferedImage[] images) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			switch (loadMode) {
			case PLAYER:
				for (int i = 0; i < nrows; i++) {
					for (int j = 0; j < ncols; j++) {
						int x = j * width;
						int y = i * height;

						images[playerOffset] = image.getSubimage(x, y, width, height);
						playerOffset++;
						
					}
				}
				break;
	
			case TILE:
				for (int i = 0; i < nrows; i++) {
					for (int j = 0; j < ncols; j++) {
						int x = j * width;
						int y = i * height;

						images[tileOffset] = image.getSubimage(x, y, width, height);
						tileOffset++;
					}
				}
				break;

			}
			return;
		}

		return; // A modifier
	}

	public static void switchLoadMode(int mode) {
		if (mode > 1 || mode < 0) {
			throw new IllegalArgumentException(" mode is either 0 or 1");
		}
		loadMode = mode;
	}

}
