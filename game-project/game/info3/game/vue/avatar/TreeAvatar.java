package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class TreeAvatar extends Avatar {

	BufferedImage[] m_images;
	private int k;

	private int width_painted;
	private int heigth_painted;

	public TreeAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Palmer);
		imageIndex = (int) (java.lang.Math.random() * 4);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		if (++k % 30 == 0) {
			k = 0;
			imageIndex = (imageIndex + 1) % 4;
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();

		int Decalage_Tiles_X = (-20) * SCALE_IMG; // nb pixel entre la souche et le bord droit du sprite
		int Decalage_Tiles_Y = (-46) * SCALE_IMG; // 64 (Taille du palmier) - 18 (hauteur de la souche sur le
													// sprite 32x32)

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2 + Decalage_Tiles_X;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2 + Decalage_Tiles_Y;

		if (coeffX < width && coeffY < height && coeffX + width_painted > 0 && coeffY + heigth_painted > 0) {
			// Aligne a taton le sprite et la tile dedie
			g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
		}
	}
}
