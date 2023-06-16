package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class BasicCannonBallAvatar extends Avatar{

	
	public static int SCALE_CANNON_BALL = 2;

	public BasicCannonBallAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.BasicCannonBall);
		imageIndex = 0;
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
			
			g.drawImage(img,-entity.getX()+GameModele.pirateBoat.getX()+width/2, -entity.getY()+GameModele.pirateBoat.getY()+height/2, width_painted, heigth_painted, null);
		
	}

}

