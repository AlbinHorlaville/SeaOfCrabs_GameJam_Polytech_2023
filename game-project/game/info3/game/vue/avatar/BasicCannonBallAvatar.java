package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.MoveableEntityClass.CannonBall;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class BasicCannonBallAvatar extends Avatar{

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
		if(((CannonBall)entity).fire) {
			BufferedImage img = m_images[imageIndex];
			
			int width_painted = SCALE_IMG * img.getWidth();
			int heigth_painted = SCALE_IMG * img.getHeight();
			
			g.drawImage(img,entity.getX(), entity.getY(), width_painted, heigth_painted, null);
		}
		
	}

}