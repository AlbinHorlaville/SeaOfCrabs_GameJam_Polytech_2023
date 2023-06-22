package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import info3.game.Controller;
import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class Player2 extends Avatar {

	BufferedImage[] m_images_back;
	BufferedImage[] m_images_face;
	BufferedImage[] m_images_left;
	BufferedImage[] m_images_right;



	public Player2(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Player2);

		m_images_face = Arrays.copyOfRange(this.m_images, 0, 3);
		m_images_back = Arrays.copyOfRange(this.m_images, 4, 7);
		m_images_right = Arrays.copyOfRange(this.m_images, 8, 11);
		m_images_left = Arrays.copyOfRange(this.m_images, 12, 15);
	}

	@Override
	public void tick(long elapsed) {
		PiratePlayer entityBased = GameModele.player1;
		switch (entityBased.getFacing()) {
		case SW:
		case S:
		case SE:
			this.m_images = m_images_face;
			break;
		case N:
		case NE:
		case NW:
			this.m_images = m_images_back;
			break;
		case E:
			this.m_images = m_images_right;
			break;	
		case W:
			this.m_images = m_images_left;
			break;
		default:
			break;
		}

		if (isMoving()) {
			imageElapsed += elapsed;
			if (imageElapsed > 200) {
				imageElapsed = 0;
				imageIndex = (imageIndex + 1) % m_images.length;
			}
		} else {
			imageIndex = 0;
		}		
	}

	@Override
	public void paint(Graphics g, int width, int height) {
		this.width = width;
		BufferedImage img = m_images[imageIndex];
		int width_painted = SCALE_IMG * img.getWidth();
		int heigth_painted = SCALE_IMG * img.getHeight();
		int posX = (entity.getX() + ((width-(GameModele.player1.getX()+GameModele.player2.getX()))/2)) - (width_painted/2);
		int posY = (entity.getY() + ((height-(GameModele.player1.getY()+GameModele.player2.getY()))/2)) - (heigth_painted/2);
		g.drawImage(img, posX,posY, width_painted, heigth_painted, null);
	}

	/**
	 * Method permettant de savoir si le joueur est entrain  de bouger
	 * @return boolean
	 */
	private boolean isMoving() {
		return Controller.getBuffer().isBuffered("FU") || Controller.getBuffer().isBuffered("FD")  || Controller.getBuffer().isBuffered("FL")  || Controller.getBuffer().isBuffered("FR");
	}

}
