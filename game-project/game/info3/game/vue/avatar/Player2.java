package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import info3.game.Controller;
import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.PiratePlayer;

public class Player2 extends Avatar {

	BufferedImage[] m_images_back;
	BufferedImage[] m_images_face;
	BufferedImage[] m_images_left;
	BufferedImage[] m_images_right;
	
	public final static int SCALE_IMG = 4;
	
	public Player2(Entity entity) {
		super(entity);
		try {
			m_images = loadSprite("assets/img/player/" + "J1.png", 5, 4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m_images_face = Arrays.copyOfRange(this.m_images, 0, 3);
		m_images_back = Arrays.copyOfRange(this.m_images, 4, 7);
		m_images_right = Arrays.copyOfRange(this.m_images, 8, 11);
		m_images_left = Arrays.copyOfRange(this.m_images, 12, 15);
	}

	@Override
	public void tick(long elapsed) {
		switch (((PiratePlayer) this.entity).getFacing()) {
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
		//g.drawImage(img, width/2-width_painted/2,height/2-heigth_painted/2, width_painted, heigth_painted, null);
		g.drawImage(img, (width-width_painted)-GameModele.player2.getX(), (height-heigth_painted)-GameModele.player2.getY(), width_painted, heigth_painted, null);
		
	}
	
	/**
	 * Method permettant de savoir si le joueur est entrain  de bouger
	 * TODO CHANGER LA METHODE D'EMPLACEMENT ???
	 * @return boolean
	 */
	private boolean isMoving() {
		boolean[] buffer = Controller.getBuffer();
		return buffer[38] || buffer[39] || buffer[40] || buffer[37];
	}

}
