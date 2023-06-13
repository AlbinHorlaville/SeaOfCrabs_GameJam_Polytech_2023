package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.modele.map.Map;

public class MiniMap {

	private Map map;

	public MiniMap(Map m) {
		this.map = m;
	}

	public void paint(Graphics g, int width, int height, int currentSection) {
		int mapPixelSize = 10;
		boolean water;

		g.setColor(new Color(0, 0, 255, 100));
		g.fillRect(width / 2 - (this.map.getSectionWidth() * mapPixelSize) / 2,
				height / 2 - (this.map.getSectionHeight() * mapPixelSize) / 2,
				this.map.getSectionWidth() * mapPixelSize, this.map.getSectionHeight() * mapPixelSize);

		for (int i = 0; i < this.map.getSectionHeight(); i++) {
			for (int j = 0; j < this.map.getSectionWidth(); j++) {
				water = false;
				switch (this.map.getMap()[this.map.getNbSection() - currentSection - 1].getTiles()[i][j].getType()) {
				case CALM_WATER:
					water = true;
					break;
				case SAND_WATER:
				case SAND:
					g.setColor(new Color(255, 255, 0, 100));
					break;
				case GRASS:
				default:
					g.setColor(new Color(0, 255, 0, 100));
					break;
				}
				if (!water) {
					g.fillRect(width / 2 - (this.map.getSectionWidth() * mapPixelSize) / 2 + j * mapPixelSize,
							height / 2 - (this.map.getSectionHeight() * mapPixelSize) / 2 + i * mapPixelSize,
							mapPixelSize, mapPixelSize);
				}
			}
		}
	}
}
