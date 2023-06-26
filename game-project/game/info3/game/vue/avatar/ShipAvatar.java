package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;
import info3.game.modele.MoveableEntityClass.Ship;

public class ShipAvatar extends Avatar {

	public ShipAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.PirateBoatEnnemie);
		imageIndex = 0;
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub
		/*switch (((Ship) this.entity).getFacing()) {
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
		}*/

	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();
		int Decalage_Tiles_X = (int) -32*6+14*6;
		int Decalage_Tiles_Y = (int) -32*6+6*6;
		g.drawImage(img,-entity.getX()+GameModele.getCurrentPlayerX()+width/2, -entity.getY()+GameModele.getCurrentPlayerY()+height/2, width_painted, heigth_painted, null);
	}

}
