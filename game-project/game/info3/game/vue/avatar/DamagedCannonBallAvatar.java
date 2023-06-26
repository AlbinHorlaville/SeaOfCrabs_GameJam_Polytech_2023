package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class DamagedCannonBallAvatar extends Avatar {

	public static int SCALE_CANNON_BALL = GameView.SCALE / 2;

	public DamagedCannonBallAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.CannonBall);
		imageIndex = 1;
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];

		int width_painted = SCALE_CANNON_BALL * img.getWidth();
		int heigth_painted = SCALE_CANNON_BALL * img.getHeight();

		int coeffX = -entity.getX() + GameModele.pirateBoat.getX() + width / 2;
		int coeffY = -entity.getY() + GameModele.pirateBoat.getY() + height / 2;

		// Only draw them if they are on screen
		if (coeffX < width && coeffY < height && coeffX + width_painted > 0
				&& coeffY + heigth_painted > 0) {
			g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
		}
	}

}
