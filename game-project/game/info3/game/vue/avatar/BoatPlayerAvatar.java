package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.BoatPlayer;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class BoatPlayerAvatar extends Avatar {

	public BoatPlayerAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.PirateBoat);
	}

	@Override
	public void tick(long elapsed) {
		switch (((BoatPlayer) this.entity).getFacing()) {
		case E:
		case SE:
		case NE:
			this.imageIndex = 1;
			break;
		case SW:
		case NW:
		case W:
			this.imageIndex = 0;
			break;
		case S:
			this.imageIndex = 2;
			break;
		case N:
			this.imageIndex = 3;
			break;
		default:
			break;
		}
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		this.width = width;
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();
		g.drawImage(img, width/2-width_painted/2,(int) ((height/2-heigth_painted/2)+GameModele.map.getWaveOffset(entity.getX(), entity.getY())), width_painted, heigth_painted, null);
	}

}
