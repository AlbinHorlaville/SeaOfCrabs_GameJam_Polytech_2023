package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.Tentacle;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class TentacleAvatar extends Avatar {

	private int k;
	private int imageI;

	public TentacleAvatar(Entity entity) {
		super(entity);
		this.m_images = SpriteLoader.get(SpriteType.Tentacle);
		k = 0;
		imageI = 0;
	}

	@Override
	public void tick(long elapsed) {
		k++;
		if (k == 30) {
			k = 0;
			imageI = 0;
		}
		if (k == 10)
			imageI++;
		if (k == 20)
			imageI++;
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[((Tentacle) this.entity).getNumber()*3+imageI];

		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();

		int Decalage_Tiles_X = 0;
		int Decalage_Tiles_Y = -32 * 3 * SCALE_IMG + 16 * SCALE_IMG
				+ (int) GameModele.map.getWaveOffset(entity.getX(), entity.getY());

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2 + Decalage_Tiles_X;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2 + Decalage_Tiles_Y;

		g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
	}

}
