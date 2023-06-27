package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.Level;
import info3.game.modele.MoveableEntityClass.Crab;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class CrabAvatar extends Avatar {
	
	private int max_life;

	public CrabAvatar(Entity entity, int level) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Crab);
		imageIndex = 0;
		max_life = ((Crab)entity).getHealthPoints() + Level.getAugmentLifeCrab(level);
		
		System.out.println(max_life);
	}

	@Override
	public void tick(long elapsed) {

	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2;

		// Only draw them if they are on screen
		if (coeffX < width && coeffY < height && coeffX + width_painted > 0
				&& coeffY + heigth_painted > 0) {
			g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
		}
		g.setColor(Color.black);
		g.fillRect(coeffX-1, coeffY-1, 102, 12);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(coeffX, coeffY, 100, 10);
		g.setColor(Color.red);
		g.fillRect(coeffX, coeffY, ((Crab)entity).getHealthPoints()*100/max_life, 10);
	}
}
