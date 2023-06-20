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
	
	private int k;
	
	public SwordAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Sword);
		imageIndex = 0;
		k = 0;
	}

	@Override
	public void tick(long elapsed) {
		if (((Weapon)entity).getAttacking()) {
			System.out.println("Check passing attacking");
			if (k%10==0) {
				imageIndex = 1;
			}
			else if (k%20==0) {
				imageIndex = 2;
			}
			else if (k%60==0) {
				imageIndex = 0;
				k = 0;
				((Weapon)entity).setAttacking(false);
			}
			k++;
		}
		
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
