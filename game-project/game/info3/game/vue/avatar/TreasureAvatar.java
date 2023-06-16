package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class TreasureAvatar extends Avatar{
	
	public final static int SCALE_IMG = 6;

	public TreasureAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.RedCross);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();
		g.drawImage(img,-entity.getX()+GameModele.player1.getX()+width/2, -entity.getY()+GameModele.player1.getY()+height/2, width_painted, heigth_painted, null);
	}

}
