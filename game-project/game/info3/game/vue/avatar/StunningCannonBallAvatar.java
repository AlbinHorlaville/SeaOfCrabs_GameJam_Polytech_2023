package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class StunningCannonBallAvatar extends Avatar{

	
	public static int SCALE_CANNON_BALL = GameView.SCALE/2;

	public StunningCannonBallAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.CannonBall);
		imageIndex = 2;
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

