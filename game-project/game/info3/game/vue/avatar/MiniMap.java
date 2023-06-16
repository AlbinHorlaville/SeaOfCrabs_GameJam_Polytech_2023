package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.modele.map.Map;
import info3.game.modele.map.MapSection;

public class MiniMap {

	private MapSection[] map; // The map
	private int sectionWidth; //The map dimension
	private int sectionHeight; //The map dimension

	public MiniMap(Map m) {
		this.map = m.getMap();
		this.sectionWidth = m.getSectionWidth();
		this.sectionHeight = m.getSectionHeight();
	}

	public void paint(Graphics g, int width, int height, int currentSection, int tilesX, int tilesY) {
		int mapPixelSize = 10;

		//We paint the background of the minimap (the water) for optimisation we don't draw each water tile individually
		g.setColor(new Color(0, 0, 255, 100));
		g.fillRect(width / 2 - (this.sectionWidth * mapPixelSize) / 2,
				height / 2 - (this.sectionHeight * mapPixelSize) / 2, this.sectionWidth * mapPixelSize,
				this.sectionHeight * mapPixelSize);

		//We paint the island
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 16; j < this.sectionWidth - 15; j++) {
				if (i == tilesY && j == tilesX) {
					g.setColor(Color.red);
					g.fillRect(width / 2 - (this.sectionWidth * mapPixelSize) / 2 + j * mapPixelSize,
							height / 2 - (this.sectionHeight * mapPixelSize) / 2 + i * mapPixelSize, mapPixelSize,
							mapPixelSize);
				} else {
					switch (this.map[currentSection].getTiles()[i][j].getType()) {
					case CALM_WATER:
						break;
					case SAND_WATER:
					case SAND:
						g.setColor(Color.yellow);
						g.fillRect(width / 2 - (this.sectionWidth * mapPixelSize) / 2 + j * mapPixelSize,
								height / 2 - (this.sectionHeight * mapPixelSize) / 2 + i * mapPixelSize, mapPixelSize,
								mapPixelSize);
						break;
					case GRASS:
					default:
						g.setColor(Color.green);
						g.fillRect(width / 2 - (this.sectionWidth * mapPixelSize) / 2 + j * mapPixelSize,
								height / 2 - (this.sectionHeight * mapPixelSize) / 2 + i * mapPixelSize, mapPixelSize,
								mapPixelSize);
						break;
					}
				}
			}
		}
		System.out.print("");
	}
}
