package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import info3.game.modele.GameModele;
import info3.game.modele.MoveableEntityClass.Ship;
import info3.game.modele.map.EnumTiles;
import info3.game.modele.map.Map;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;
import info3.game.vue.GameView;

public class MiniMap {

	private MapSection[] map; // The map
	private int sectionWidth; // The map dimension
	private int sectionHeight; // The map dimension

	private int iconWidth;
	private int iconHeight;

	private BufferedImage boatIcon;
	private BufferedImage[] playerIcon;

	private int mapPixelSize;

	private int waterX;
	private int topX;
	private int topY;
	private int minimapWidth;
	private int minimapHeight;

	private ArrayList<Ship> seaEnnemie;

	public MiniMap(Map m, ArrayList<Ship> seaEnnemie) throws IOException {
		this.map = m.getMap();
		this.sectionWidth = m.getSectionWidth();
		this.sectionHeight = m.getSectionHeight();

		this.seaEnnemie = seaEnnemie;

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

		this.mapPixelSize = 8;

		this.waterX = GameView.screenWidth / 2 - ((this.sectionWidth - 16) * mapPixelSize) / 2;
		this.topX = GameView.screenWidth / 2 - (this.sectionWidth * mapPixelSize) / 2;
		this.topY = GameView.screenHeight / 2 - (this.sectionHeight * mapPixelSize) / 2;
		this.minimapWidth = (this.sectionWidth - 16) * mapPixelSize;
		this.minimapHeight = this.sectionHeight * mapPixelSize;
	}

	public void paint(Graphics g, int width, int height) {

		int currentSection = GameModele.pirateBoat.getCurrentSection();

		// We paint the background of the minimap (the water) for optimisation we don't
		// draw each water tile individually
		g.setColor(new Color(0, 0, 255, 100)); // ROUGE

		g.fillRect(this.waterX, this.topY, this.minimapWidth, this.minimapHeight);

		// We paint the island
		for (int i = 0; i < this.sectionHeight; i++) {
			Tiles[][] tiles = this.map[currentSection].getTiles();
			for (int j = 8; j < this.sectionWidth - 8; j++) {
				Tiles tile = tiles[i][j];
				if (tile.isIsland() || tile.notIslandAndNotWater() || tile.isIslandObstacle() || tile.isCloud()) {
					if (tile.isGrass()) {
						g.setColor(Color.green);
					} else if (tile.isTreasur()) {
						g.setColor(Color.red);
					} else if (tile.isSand()) {
						g.setColor(Color.yellow);
					} else if (tile.isMoutain()) {
						g.setColor(Color.gray);
					} else if (tile.isCloud()) {
						g.setColor(Color.magenta);
					} else if (tile.isSwpaner()) {
						g.setColor(Color.gray);
					} else if (tile.isPooton()) {
						g.setColor(new Color(88, 41, 0));
					} else if (tile.isCrabLand()) {
						g.setColor(new Color(255, 140, 0));
					} else {
						g.setColor(Color.black);
					}
					g.fillRect(this.topX + j * mapPixelSize, this.topY + i * mapPixelSize, mapPixelSize, mapPixelSize);
				}
			}
		}

		g.setColor(Color.black);
		for (Ship seaShip : this.seaEnnemie) {
			if (GameModele.map.getSectionOfEntity(seaShip.getCenterX(),
					seaShip.getCenterY()) == GameModele.pirateBoat.getCurrentSection()) {
				Tiles tileUnder = GameModele.map.getTileUnderEntity(seaShip.getCenterX(),
						seaShip.getCenterY());
				int tilesX = tileUnder.getTileX();
				int tilesY = tileUnder.getTileY();
				
				g.fillRect(this.topX + tilesX * mapPixelSize, this.topY + tilesY * mapPixelSize, mapPixelSize, mapPixelSize);
			}
		}

		int currentX;
		int currentY;

		Tiles tileUnder;

		int tilesX;
		int tilesY;

		if (GameModele.onSea) {
			currentX = GameModele.pirateBoat.getCenterX();
			currentY = GameModele.pirateBoat.getCenterY();

			tileUnder = GameModele.map.getTileUnderEntity(currentX, currentY);

			tilesX = tileUnder.getTileX();
			tilesY = tileUnder.getTileY();
			g.drawImage(this.boatIcon, this.topX + tilesX * mapPixelSize - this.iconWidth / 2,
					this.topY + tilesY * mapPixelSize - this.iconHeight / 2, iconWidth, iconHeight, null);
		} else {
			currentX = GameModele.player1.getCenterX();
			currentY = GameModele.player1.getCenterY();

			tileUnder = GameModele.map.getTileUnderEntity(currentX, currentY);

			tilesX = tileUnder.getTileX();
			tilesY = tileUnder.getTileY();
			g.drawImage(this.playerIcon[0], this.topX + tilesX * mapPixelSize - this.iconWidth / 2,
					this.topY + tilesY * mapPixelSize - this.iconHeight / 2, iconWidth, iconHeight, null);
			if (!GameModele.solo) {
				currentX = GameModele.player2.getCenterX();
				currentY = GameModele.player2.getCenterY();

				tileUnder = GameModele.map.getTileUnderEntity(currentX, currentY);

				tilesX = tileUnder.getTileX();
				tilesY = tileUnder.getTileY();
				g.drawImage(this.playerIcon[1], this.topX + tilesX * mapPixelSize - this.iconWidth / 2,
						this.topY + tilesY * mapPixelSize - this.iconHeight / 2, iconWidth, iconHeight, null);
			}

		}
	}
}
