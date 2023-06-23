package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.Perroquet;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class PerroquetAvatar extends Avatar{
	private int k;

	public PerroquetAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Perroquet);
		imageIndex = 0;
		k = 0;
	}

	@Override
	public void tick(long elapsed) {
		k++;
		if (k==2)
			k=0;
		if (entity.getX() > ((Perroquet)entity).closestPlayer.x && entity.getY() < ((Perroquet)entity).closestPlayer.y) {
			imageIndex = 0;
		}
		if (entity.getX() < ((Perroquet)entity).closestPlayer.x && entity.getY() < ((Perroquet)entity).closestPlayer.y) {
			imageIndex = 2;
		}
		if (entity.getX() > ((Perroquet)entity).closestPlayer.x && entity.getY() > ((Perroquet)entity).closestPlayer.y) {
			imageIndex = 4;
		}
		if (entity.getX() < ((Perroquet)entity).closestPlayer.x && entity.getY() > ((Perroquet)entity).closestPlayer.y) {
			imageIndex = 6;
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex+k];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();
		int Decalage_Tiles_X = (int) -32*6+14*6;
		int Decalage_Tiles_Y = (int) -32*6+6*6;
		g.drawImage(img,-entity.getX()+GameModele.getCurrentPlayerX()+width/2 - img.getWidth()/2, -entity.getY()+GameModele.getCurrentPlayerY()+height/2 - img.getHeight()/2, width_painted*2/3, heigth_painted*2/3, null);
		//g.setColor(Color.red);
		//g.fillRect(-entity.getX()+GameModele.getCurrentPlayerX()+width/2, -entity.getY()+GameModele.getCurrentPlayerY()+height/2, 100, 100);
	}
}
