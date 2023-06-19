package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;

import info3.game.modele.GameModele;
import info3.game.modele.map.EnumTiles;
import info3.game.modele.map.Map;
import info3.game.modele.map.MapSection;

public class MiniMap {

	private MapSection[] map; // The map
	private int sectionWidth; // The map dimension
	private int sectionHeight; // The map dimension

	public MiniMap(Map m) {
		this.map = m.getMap();
		this.sectionWidth = m.getSectionWidth();
		this.sectionHeight = m.getSectionHeight();
	}

	public void paint(Graphics g, int width, int height) {
		int tilesX = GameModele.map.getTileUnderEntity(GameModele.getCurrentPlayerX(), GameModele.getCurrentPlayerY()).getTileX();
		int tilesY = GameModele.map.getTileUnderEntity(GameModele.getCurrentPlayerX(), GameModele.getCurrentPlayerY()).getTileY();
		int currentSection = GameModele.map.getSectionOfEntity(GameModele.getCurrentPlayerX(), GameModele.getCurrentPlayerY());

		int mapPixelSize = 8;

		// We paint the background of the minimap (the water) for optimisation we don't
		// draw each water tile individually
		g.setColor(new Color(0, 0, 255, 100)); // ROUGE

		g.fillRect(width / 2 - ((this.sectionWidth-16) * mapPixelSize) / 2,
				height / 2 - (this.sectionHeight * mapPixelSize) / 2, (this.sectionWidth-16) * mapPixelSize,
				this.sectionHeight * mapPixelSize);

		// We paint the island
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 8; j < this.sectionWidth - 8; j++) {
				if (i == tilesY && j == tilesX) {
					g.setColor(Color.red);
					g.fillRect(width / 2 - (this.sectionWidth * mapPixelSize) / 2 + j * mapPixelSize,
							height / 2 - (this.sectionHeight * mapPixelSize) / 2 + i * mapPixelSize, mapPixelSize,
							mapPixelSize);
				} else {
					EnumTiles tile = this.map[currentSection].getTiles()[i][j].getType();
					if (this.map[currentSection].getTiles()[i][j].isIsland() || tile == EnumTiles.TREE
							|| tile == EnumTiles.CRAB_SPAWNER || tile == EnumTiles.CRAB_SPAWNER_TRANSITION || tile == EnumTiles.RAGING_SEA_CHEST || tile == EnumTiles.STORMY_SEA_CHEST || tile == EnumTiles.CALM_SEA_CHEST) {
						if (tile == EnumTiles.SAND || tile == EnumTiles.SAND_WATER || tile == EnumTiles.STORMY_SAND_WATER
								|| tile == EnumTiles.RAGING_SAND_WATER || tile == EnumTiles.SHELLFISH_1
								|| tile == EnumTiles.SHELLFISH_2 || tile == EnumTiles.SHELLFISH_3) {
							g.setColor(Color.yellow);
						}  else {
							g.setColor(Color.green);
						}
						g.fillRect(width / 2 - (this.sectionWidth * mapPixelSize) / 2 + j * mapPixelSize,
								height / 2 - (this.sectionHeight * mapPixelSize) / 2 + i * mapPixelSize, mapPixelSize,
								mapPixelSize);
					}
				}

			}
		}
		System.out.print("");
	}
}
