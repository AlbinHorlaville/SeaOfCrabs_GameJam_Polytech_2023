package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.Weapon;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class SwordAvatar extends Avatar {

	public static int SCALE_SWORD = 5;
	
	BufferedImage[] m_imagesNS;
	BufferedImage[] m_imagesEW;

	private int k;

	public SwordAvatar(Entity entity) {
		super(entity);
		m_imagesEW = SpriteLoader.get(SpriteType.SwordEW);
		m_imagesNS = SpriteLoader.get(SpriteType.SwordNS);
		imageIndex = 0;
		k = 0;
	}

	@Override
	public void tick(long elapsed) {
		if (((Weapon) entity).getAttacking()) {
			System.out.println(k);
			k++;
			if (k % 30 == 0) {
				imageIndex = 1;
			}
			if (k % 60 == 0) {
				imageIndex = 2;
			}
			if (k % 90 == 0) {
				imageIndex = 0;
				k = 0;
				((Weapon) entity).setAttacking(false);
			}
		}

	}

	@Override
	public void paint(Graphics g, int width, int height) {

		int orientationX, orientationY;
		BufferedImage img;
		switch(GameModele.player1.facing){
		case N:
			img = m_imagesNS[imageIndex];
			orientationX = - img.getWidth();
			orientationY = -GameModele.player1.getAvatar().getHeight();
			break;
		case W:
			img = m_imagesEW[imageIndex+4];
			orientationX = -GameModele.player1.getAvatar().getWidth()/2 - img.getWidth() ;
			orientationY = 0;
			break;
		case S:
			img = m_imagesNS[imageIndex+4];
			orientationX = 0;
			orientationY = img.getHeight();
			break;
		case E:
			orientationX = GameModele.player1.getAvatar().getWidth()/2;
			orientationY = 0;
			img = m_imagesEW[imageIndex];
			break;
		default:
			orientationX = 0;
			orientationY = 0;
			img = m_imagesEW[0];
			break;
		}

		int width_painted = SCALE_SWORD * img.getWidth();
		int heigth_painted = SCALE_SWORD * img.getHeight();
		if (((Weapon) entity).getAttacking())
			g.drawImage(img, orientationX + width / 2, orientationY + height / 2, width_painted, heigth_painted, null);
		/*if (!GameModele.solo) {
			g.drawImage(img, -entity.getX() + GameModele.getCurrentPlayerX() + width / 2,
					-entity.getY() + GameModele.player2.getY() + height / 2, width_painted, heigth_painted, null);
		}*/
	}

}
