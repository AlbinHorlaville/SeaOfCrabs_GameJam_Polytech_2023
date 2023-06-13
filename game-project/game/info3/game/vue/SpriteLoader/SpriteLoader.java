package info3.game.vue.SpriteLoader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;




public class SpriteLoader {
	
	public static BufferedImage[][] allSprites ;
	
	public final static String pathP= "assets/img/player";
	public final static String pathT = "assets/img/tiles";
	public static  int nbTypeSprites = 2; 
	public static int nbSpritesMaxPerType = 50;
	
	private static HashMap<SpriteType, BufferedImage[]> TypeSpritesMap;
	
	
	
	
	
	public void initAllSprites () throws IOException {
		allSprites = new BufferedImage[nbTypeSprites][nbSpritesMaxPerType];
		
		BufferedImage[] playerSprites = loadPlayerSprites();
		BufferedImage[] tileSprites = loadTileSprites();
		
		TypeSpritesMap = new HashMap<>();
		SpriteLoader.TypeSpritesMap.put(SpriteType.Player,playerSprites);
		SpriteLoader.TypeSpritesMap.put(SpriteType.Tile,tileSprites);
		
		
		allSprites[0] = playerSprites;
		allSprites[1] = tileSprites;
		
	}
	
	public BufferedImage[] loadPlayerSprites () throws IOException {
		BufferedImage[] playerSprites = new BufferedImage[nbSpritesMaxPerType];
		loadSprite( pathP + "winchester-4x6.png", 4, 6, playerSprites);
		return playerSprites;
		
	}
	
	public BufferedImage[] loadTileSprites () throws IOException {
		BufferedImage[] tileSprites = new BufferedImage[nbSpritesMaxPerType];
		loadSprite(  pathT + "grass.png", 1, 1, tileSprites);
		loadSprite( pathT + "sand_water.png", 1, 1, tileSprites);
		loadSprite( pathT + "sand.png", 1, 1, tileSprites);
		loadSprite( pathT + "water.png", 1, 1, tileSprites);
		return tileSprites;
		
	}

	
	
	public static void loadSprite(String filename, int nrows, int ncols, BufferedImage[] images) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return;
		}
	
		return; // A modifier
	}
	
}
