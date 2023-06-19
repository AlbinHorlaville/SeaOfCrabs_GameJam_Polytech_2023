package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.Weapon;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class SwordAvatar extends Avatar{

	public static int SCALE_SWORD = 2;
	
	public SwordAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Sword);
		imageIndex = 0;
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		
		int width_painted = SCALE_SWORD * img.getWidth();
		int heigth_painted = SCALE_SWORD * img.getHeight();
		g.drawImage(img,-entity.getX()+GameModele.getCurrentPlayerX()+width/2, -entity.getY()+GameModele.player1.getY()+height/2, width_painted, heigth_painted, null);
		if(!GameModele.solo) {
			g.drawImage(img,-entity.getX()+GameModele.getCurrentPlayerX()+width/2, -entity.getY()+GameModele.player2.getY()+height/2, width_painted, heigth_painted, null);

		}
	}

}
