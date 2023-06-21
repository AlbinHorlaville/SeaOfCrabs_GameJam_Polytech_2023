package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.game.modele.GameModele;
import info3.game.modele.map.EnumTiles;
import info3.game.modele.map.Map;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;

public class MiniMap {

	private MapSection[] map; // The map
	private int sectionWidth; // The map dimension
	private int sectionHeight; // The map dimension

	private int iconWidth;
	private int iconHeight;

	private BufferedImage boatIcon;
	private BufferedImage[] playerIcon;

	public MiniMap(Map m) throws IOException {
		this.map = m.getMap();
		this.sectionWidth = m.getSectionWidth();
		this.sectionHeight = m.getSectionHeight();

		File imageFile = new File("resources/img/logo-boat.png");
		if (imageFile.exists()) {
			this.boatIcon = ImageIO.read(imageFile);
		}

		this.iconWidth = this.boatIcon.getWidth();
		this.iconHeight = this.boatIcon.getHeight();

		playerIcon = new BufferedImage[2];

		imageFile = new File("resources/img/logo-pirate-1.png");
		if (imageFile.exists()) {
			this.playerIcon[0] = ImageIO.read(imageFile);
		}

		imageFile = new File("resources/img/logo-pirate-2.png");
		if (imageFile.exists()) {
			this.playerIcon[1] = ImageIO.read(imageFile);
		}
	}

	public void paint(Graphics g, int width, int height) {
		int tilesX = GameModele.map.getTileUnderEntity(GameModele.getCurrentPlayerX(), GameModele.getCurrentPlayerY())
				.getTileX();
		int tilesY = GameModele.map.getTileUnderEntity(GameModele.getCurrentPlayerX(), GameModele.getCurrentPlayerY())
				.getTileY();
		int currentSection = GameModele.map.getSectionOfEntity(GameModele.getCurrentPlayerX(),
				GameModele.getCurrentPlayerY());

		int mapPixelSize = 8;

		// We paint the background of the minimap (the water) for optimisation we don't
		// draw each water tile individually
		g.setColor(new Color(0, 0, 255, 100)); // ROUGE

		g.fillRect(width / 2 - ((this.sectionWidth - 16) * mapPixelSize) / 2,
				height / 2 - (this.sectionHeight * mapPixelSize) / 2, (this.sectionWidth - 16) * mapPixelSize,
				this.sectionHeight * mapPixelSize);

		// We paint the island
		for (int i = 0; i < this.sectionHeight; i++) {
			for (int j = 8; j < this.sectionWidth - 8; j++) {
				if (!(i == tilesY && j == tilesX)) {
					Tiles tile = this.map[currentSection].getTiles()[i][j];
					if (tile.isIsland() || tile.notIslandAndNotWater() || tile.isIslandObstacle()
							|| tile.isSeaChest() || tile.isBoatEnnemi()) {
						if (tile.isGrass()) {
							g.setColor(Color.green);
						} else if (tile.isTreasur()) {
							g.setColor(Color.red);
						} else if (tile.isSand()) {
							g.setColor(Color.yellow);
						} else if (tile.isMoutain()) {
							g.setColor(Color.gray);
						} else if (tile.isSeaChest()) {
							g.setColor(Color.magenta);
						} else if (tile.isSwpaner()) {
							g.setColor(Color.gray);
						} else if (tile.isPooton()) {
							g.setColor(new Color(88, 41, 0));
						}else if (tile.isBoatEnnemi()) {
							g.setColor(Color.orange);
						} else if (tile.isCrabLand()) {
							g.setColor(new Color(255, 140, 0));
						} else {
							g.setColor(Color.black);
						}
						g.fillRect(width / 2 - (this.sectionWidth * mapPixelSize) / 2 + j * mapPixelSize,
								height / 2 - (this.sectionHeight * mapPixelSize) / 2 + i * mapPixelSize, mapPixelSize,
								mapPixelSize);
					}
				}
			}
		}
		if (GameModele.onSea) {
			g.drawImage(this.boatIcon,
					width / 2 - (this.sectionWidth * mapPixelSize) / 2 + tilesX * mapPixelSize
							- this.iconWidth / 2,
					height / 2 - (this.sectionHeight * mapPixelSize) / 2 + tilesY * mapPixelSize
							- this.iconHeight / 2,
					iconWidth, iconHeight, null);
		} else {
			//if (GameModele.solo) {
				g.drawImage(this.playerIcon[0],
						width / 2 - (this.sectionWidth * mapPixelSize) / 2 + tilesX * mapPixelSize
								- this.iconWidth / 2,
						height / 2 - (this.sectionHeight * mapPixelSize) / 2 + tilesY * mapPixelSize
								- this.iconHeight / 2,
						iconWidth, iconHeight, null);
			/*} else {
				g.drawImage(this.playerIcon[0],
						width / 2 - (this.sectionWidth * mapPixelSize) / 2 + j * mapPixelSize
								- this.iconWidth / 2,
						height / 2 - (this.sectionHeight * mapPixelSize) / 2 + i * mapPixelSize
								- this.iconHeight / 2,
						iconWidth, iconHeight, null);
				g.drawImage(this.playerIcon[1],
						width / 2 - (this.sectionWidth * mapPixelSize) / 2 + j * mapPixelSize
								- this.iconWidth / 2,
						height / 2 - (this.sectionHeight * mapPixelSize) / 2 + i * mapPixelSize
								- this.iconHeight / 2,
						iconWidth, iconHeight, null);
			}*/
		}
	}
}
