package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.CrabKing;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;
import info3.game.vue.view.BeforePlayingView;

public class CrabKingAvatar extends Avatar {
	private int k;
	private boolean visible;
	private int max_life;

	public CrabKingAvatar(Entity entity, int level) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.CrabKing);
		imageIndex = 0;
		k = 0;
		visible = false;
		max_life = ((CrabKing) entity).getHealthPoints();
	}

	@Override
	public void tick(long elapsed) {
		if (++k % 20 == 0) {
			if (k == 80) {
				k = 0;
				imageIndex = 0;
			} else {
				imageIndex++;
			}
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2;
		// Only draw them if they are on screen
		if (coeffX < width && coeffY < height && coeffX + width_painted > 0 && coeffY + heigth_painted > 0) {
			g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
			if (BeforePlayingView.ShowLifeCrabs) {
				g.setColor(Color.black);
				g.fillRect(coeffX - 1 + 30, coeffY - 1, 102, 12);
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(coeffX + 30, coeffY, 100, 10);
				g.setColor(Color.red);
				g.fillRect(coeffX + 30, coeffY, ((CrabKing) entity).getHealthPoints() * 100 / max_life, 10);
			}
		}
	}

}
