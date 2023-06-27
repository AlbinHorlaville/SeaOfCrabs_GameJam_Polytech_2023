package info3.game.vue.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import info3.game.modele.Entity;
import info3.game.modele.GameModele;
import info3.game.modele.bonus.AttackSpeedbonus;
import info3.game.modele.bonus.DamageBonus;
import info3.game.modele.bonus.HealthBonus;
import info3.game.modele.bonus.RangeBonus;
import info3.game.modele.bonus.SpeedBonus;
import info3.game.vue.SpriteLoader.SpriteLoader;
import info3.game.vue.SpriteLoader.SpriteType;

public class BonusAvatar extends Avatar {

	public BonusAvatar(Entity entity) {
		super(entity);
		m_images = SpriteLoader.get(SpriteType.Bonus);
		if (entity instanceof AttackSpeedbonus) {
			imageIndex = 2;
		} else if (entity instanceof DamageBonus) {
			imageIndex = 0;
		} else if (entity instanceof HealthBonus) {
			imageIndex = 3;
		} else if (entity instanceof RangeBonus) {
			imageIndex = 5;
		} else if (entity instanceof SpeedBonus) {
			imageIndex = 4;
		} else {
			System.out.println("ERREUR L'entité n'est pas un bonus !");
		}
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[imageIndex];
		int width_painted = img.getWidth() * SCALE_IMG;
		int heigth_painted = img.getHeight() * SCALE_IMG;

		int coeffX = -entity.getX() + GameModele.getCurrentPlayerX() + width / 2;
		int coeffY = -entity.getY() + GameModele.getCurrentPlayerY() + height / 2;

		// Only draw them if they are on screen
		if (coeffX < width && coeffY < height && coeffX + width_painted > 0 && coeffY + heigth_painted > 0) {
			g.drawImage(img, coeffX, coeffY, width_painted, heigth_painted, null);
		}
	}

}
