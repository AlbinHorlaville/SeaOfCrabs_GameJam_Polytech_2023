package info3.game.vue.SpriteLoader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class SpriteLoader {

	public static BufferedImage[][] allSprites; // Tableau dans lequel on va charger tous les sprites

	public static int nbSpriteTypesMax = 50; // A modifier éventuellement
	public static int offset;

	private static HashMap<SpriteType, BufferedImage[]> TypeSpritesMap;

	/*
	 * Fonction appelée au début du jeu.
	 * 
	 * Charge tous les sprites dans le tableau allSprites
	 * 
	 * @return rien. Effet de bord: Remplit allSprites
	 */
	public static void initAllSprites() throws IOException {
		offset = 0;
		allSprites = new BufferedImage[nbSpriteTypesMax][];

		TypeSpritesMap = new HashMap<>();

		/*
		 * COMMENT RAJOUTER DES SPRITES DANS LE TABLEAU DE SPRITES: 
		 * -Aller dans SpriteType.java et rajouter un enumerable 
		 * -Ecrire ensuite load (SpriteType.Type, path/to/sprites, nb_lignes, nb_colonnes) 
		 * dans cette fonction
		 */

		/*
		 * Exemple pour les sprites cowboy : winchester-4x6.png contient 24 sprites, 4
		 * lignes de 6 colonnes de sprites
		 */

		load(SpriteType.Buttons, "assets/img/toolkit/buttons.png", 2, 6);
		load(SpriteType.Player1, "assets/img/player/" + "J1.png", 4, 4);
		load(SpriteType.Player2, "assets/img/player/" + "J1.png", 4, 4);
		load(SpriteType.PirateBoat, "assets/img/player/" + "Boat.png", 1, 2);
		load(SpriteType.Crabslair, "assets/img/BadGuys/" + "Crabslair.png", 1, 2);
		load(SpriteType.RedCross, "assets/img/autre/" + "RedCross.png", 1, 1);
		load(SpriteType.Palmer, "assets/img/autre/" + "Palmer.png", 2, 2);
		load(SpriteType.Cloud, "assets/img/autre/" + "Cloud.png", 1, 1);
		load(SpriteType.BasicCannonBall, "assets/img/autre/" + "Cloud.png", 1, 1);

	}

	/*
	 * Permet de récuperer tous les sprites pour un Spritetype particulier
	 * 
	 * @return BufferedImage[] contenant tous les sprites pour un SpriteType
	 * particulier. Par exemple, renvoit tous les sprites pour Haag
	 */
	public static BufferedImage[] get(SpriteType key) {
		return SpriteLoader.TypeSpritesMap.get(key);
	}

	/*
	 * Charge dans le tableau de sprites, tous les sprites pour un type particulier
	 */
	private static void load(SpriteType spriteType, String path, int nrows, int ncols) throws IOException {
		BufferedImage[] Sprites = loadSprite(path, nrows, ncols); // A adapter
		allSprites[offset] = Sprites;
		offset++;
		SpriteLoader.TypeSpritesMap.put(spriteType, Sprites);
		return;

	}

	/*
	 * Découpe une image selon ses sous-images et les charge dans un BufferedImage[]
	 * 
	 */
	private static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
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

}
