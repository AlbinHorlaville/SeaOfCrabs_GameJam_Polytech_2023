package info3.game.vue.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AffichageImage extends JFrame {

	private BufferedImage image;

	public AffichageImage(BufferedImage image) {
		this.image = image;

		setTitle("Affichage d'une BufferedImage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(image.getWidth(), image.getHeight());
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, null);
	}

	public static void main(String[] args) {
		// Charger l'image à partir d'un fichier
		BufferedImage image = null;
		try {
			
			SpriteLoader.initAllSprites();
        	BufferedImage [] tileSprites = SpriteLoader.TypeSpritesMap.get(SpriteType.Tile);
        	image = tileSprites[1];
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image != null) {
			// Créez une instance de AffichageImage et passez votre BufferedImage en
			// paramètre
			AffichageImage affichageImage = new AffichageImage(image);
            affichageImage.repaint();
		}
	}
}
