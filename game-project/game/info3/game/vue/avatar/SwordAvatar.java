package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.Weapon;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
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
		((Weapon) entity).player = GameModele.player1;
	}

	@Override
	public void tick(long elapsed) {
		if (((Weapon) entity).getAttacking()) {
			if (k == 100) {
				imageIndex = 0;
				k = 0;
				((Weapon) entity).setAttacking(false);
			} else if (k == 70) {
				imageIndex = 3;
			} else if (k == 50) {
				imageIndex = 2;
			} else if (k == 30) {
				imageIndex = 1;
			}

			k++;
		}

	}

	@Override
	public void paint(Graphics g, int width, int height) {

		int orientationX, orientationY;
		BufferedImage img;
		int iW = 0;
		int iH = 0;
		Weapon weapon = (Weapon) this.entity;
		if (weapon.player != null) {
			if (GameModele.solo) {
				switch (weapon.player.facing) {
				case N:
					img = m_imagesNS[imageIndex];
					orientationX = width / 2 - weapon.width / 2;// width / 2 -
																			// weapon.player.getAvatar().getWidth()
																			// / 2;
					orientationY = height / 2 - weapon.range;
					iW = weapon.height;
					iH = weapon.range;
					break;
				case W:
					img = m_imagesEW[imageIndex + 4];
					orientationX = width / 2 - weapon.range;
					orientationY = height / 2 - weapon.height / 2 + weapon.player.getAvatar().getHeight() / 4;// (height / 2 -
																					// weapon.player.getAvatar().getHeight()
																					// / 2);
					iW = weapon.range;
					iH = weapon.height;
					break;
				case S:
					img = m_imagesNS[imageIndex + 4];
					orientationX = width / 2 - weapon.width / 2;// width / 2 -
																			// weapon.player.getAvatar().getWidth()
																			// / 2;
					orientationY = height / 2;
					iW = weapon.height;
					iH = weapon.range;
					break;
				case E:
					orientationX = width / 2;
					orientationY = height / 2 - weapon.height / 2 + weapon.player.getAvatar().getHeight() / 4;// height / 2 -
																					// weapon.player.getAvatar().getHeight()
																					// / 2;
					img = m_imagesEW[imageIndex];
					iW = weapon.range;
					iH = weapon.height;
					break;
				default:
					orientationX = 0;
					orientationY = 0;
					img = m_imagesEW[0];
					break;
				}
			}
			else {
				PiratePlayer entityBased = (weapon.player == GameModele.player1) ? GameModele.player2:GameModele.player1;
				switch (weapon.player.facing) {
				case N:
					img = m_imagesNS[imageIndex];
					//orientationX = width / 2 - entityBased.getAvatar().getWidth() / 2;
					orientationX = (entityBased.getX() + ((width-(GameModele.player1.getX()+GameModele.player2.getX()))/2)) - weapon.width / 2;// - (entityBased.getAvatar().getWidth()/2);
					
					//orientationY = height / 2 - weapon.range;
					orientationY = (entityBased.getY() + ((height-(GameModele.player1.getY()+GameModele.player2.getY()))/2)) - weapon.range;
					
					iW = weapon.height;
					iH = weapon.range;
					break;
				case W:
					img = m_imagesEW[imageIndex + 4];
					//orientationX = width / 2 - weapon.range;
					orientationX = (entityBased.getX() + ((width-(GameModele.player1.getX()+GameModele.player2.getX()))/2)) - weapon.range;
					
					//orientationY = (height / 2 - entityBased.getAvatar().getHeight() / 2);
					orientationY = (entityBased.getY() + ((height-(GameModele.player1.getY()+GameModele.player2.getY()))/2)) - weapon.height / 2 + entityBased.getAvatar().getHeight() / 4;// - entityBased.getAvatar().getHeight() / 2;// + entityBased.getAvatar().getHeight() / 4;
					
					
					iW = weapon.range;
					iH = weapon.height;
					break;
				case S:
					img = m_imagesNS[imageIndex + 4];
					//orientationX = width / 2 - entityBased.getAvatar().getWidth() / 2;
					orientationX = (entityBased.getX() + ((width-(GameModele.player1.getX()+GameModele.player2.getX()))/2)) - weapon.width / 2;// - entityBased.getAvatar().getWidth() / 2;
					
					
					//orientationY = height / 2;
					orientationY = (entityBased.getY() + ((height-(GameModele.player1.getY()+GameModele.player2.getY()))/2));
					
					
					iW = weapon.height;
					iH = weapon.range;
					break;
				case E:
					//orientationX = width / 2;
					orientationX = (entityBased.getX() + ((width-(GameModele.player1.getX()+GameModele.player2.getX()))/2));
					
					//orientationY = height / 2 - entityBased.getAvatar().getHeight() / 2;
					orientationY = (entityBased.getY() + ((height-(GameModele.player1.getY()+GameModele.player2.getY()))/2)) - weapon.height / 2 + entityBased.getAvatar().getHeight() / 4;//- entityBased.getAvatar().getHeight() / 2;// + entityBased.getAvatar().getHeight() / 4;
					
					
					img = m_imagesEW[imageIndex];
					iW = weapon.range;
					iH = weapon.height;
					break;
				default:
					orientationX = 0;
					orientationY = 0;
					img = m_imagesEW[0];
					break;
				}
			}

			// System.out.println(((Weapon) entity).getAttacking());

			if (((Weapon) entity).getAttacking())
				g.drawImage(img, orientationX, orientationY, iW, iH, null);
		}
		/*
		 * g.setColor(Color.white); // East g.fillRect(width / 2, (height/2 -
		 * GameModele.player1.getAvatar().getHeight()/2), ((Weapon) entity).range,
		 * ((Weapon) entity).height); // West g.setColor(Color.red); g.fillRect(width /
		 * 2 - ((Weapon) entity).range, (height/2 -
		 * GameModele.player1.getAvatar().getHeight()/2), ((Weapon) entity).range,
		 * ((Weapon) entity).height);
		 * 
		 * // North g.setColor(Color.yellow); g.fillRect(width / 2 -
		 * GameModele.player1.getAvatar().getWidth()/2, height/2, ((Weapon)
		 * entity).width, ((Weapon) entity).range); // South g.setColor(Color.blue);
		 * g.fillRect(width / 2 - GameModele.player1.getAvatar().getWidth()/2, height/2
		 * - ((Weapon) entity).range, ((Weapon) entity).width, ((Weapon) entity).range);
		 */

		/*
		 * if (((Weapon) entity).getAttacking()) { g.setColor(Color.white);
		 * g.fillRect(-((Weapon) entity).tempX + GameModele.getCurrentPlayerX() + width
		 * / 2, -((Weapon) entity).tempY + GameModele.getCurrentPlayerY() + height / 2,
		 * ((Weapon) entity).width, ((Weapon) entity).height);
		 * System.out.println(((Weapon) entity).tempX + " " + ((Weapon) entity).tempY +
		 * " " + ((Weapon) entity).width + " " + ((Weapon) entity).height); }
		 */
		/*
		 * if (!GameModele.solo) { g.drawImage(img, -entity.getX() +
		 * GameModele.getCurrentPlayerX() + width / 2, -entity.getY() +
		 * GameModele.player2.getY() + height / 2, width_painted, heigth_painted, null);
		 * }
		 */
	}

}
