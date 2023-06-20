package info3.game.modele.map;

import info3.game.modele.GameModele;
import info3.game.vue.GameView;

/*
 * A tile of the map is characterized by it's type
 */
public class Tiles {
	private EnumTiles type;
	private int x;
	private int y;

	private int tileX;
	private int tileY;

	private int height;

	public Tiles(EnumTiles type, int tileX, int tileY) {
		this.type = type;
		this.tileX = tileX;
		this.tileY = tileY;
		this.height = 0;
	}

	/*
	 * Getter for the type
	 */
	public EnumTiles getType() {
		return this.type;
	}

	/*
	 * Setter for the type
	 */
	public void setType(EnumTiles type) {
		this.type = type;
	}

	public int getDisplayX() {
		return this.x;
	}

	public int getX() {
		return -this.x + GameView.screenWidth / 2;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getDisplayY(int imgHeight) {
		return this.y + this.height * (imgHeight / 4);
	}

	public int getY() {
		return -this.y + GameView.screenHeight / 2;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public int getTileX() {
		return this.tileX;
	}

	public int getTileY() {
		return this.tileY;
	}

	public void setCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Print an integer corresponding to the tile type, usefull for debugging only
	 */
	public void printTile() {
		switch (this.type) {
		case CALM_WATER:
			System.out.print("0");
			break;
		case SAND:
			System.out.print("1");
			break;
		case GRASS:
			System.out.print("2");
			break;
		default:
			System.out.print("3");
			break;
		}
	}

	public int getHeight() {
		return this.height;
	}

	public boolean isIslandObstacle() {
		return this.type == EnumTiles.CRAB_SPAWNER || this.type == EnumTiles.CRAB_SPAWNER_TRANSITION
				|| this.type == EnumTiles.TREE;
	}

	public boolean notIslandAndNotWater() {
		return this.type == EnumTiles.PONTOON || this.type == EnumTiles.HARBOR_SAND || this.type == EnumTiles.MOUTAIN;
	}

	public boolean isIsland() {
		return this.type == EnumTiles.SAND_WATER 
				|| this.type == EnumTiles.STORMY_SAND_WATER
				|| this.type == EnumTiles.RAGING_SAND_WATER 
				|| this.type == EnumTiles.SAND
				|| this.type == EnumTiles.GRASS 
				|| this.type == EnumTiles.BLUE_FLOWER
				|| this.type == EnumTiles.YELLOW_FLOWER 
				|| this.type == EnumTiles.RED_FLOWER
				|| this.type == EnumTiles.SHELLFISH_1 
				|| this.type == EnumTiles.SHELLFISH_2
				|| this.type == EnumTiles.SHELLFISH_3 
				|| this.type == EnumTiles.PONTOON
				|| this.type == EnumTiles.TREASUR 
				|| this.type == EnumTiles.GRASS_WITH_ROCK_1
				|| this.type == EnumTiles.GRASS_WITH_ROCK_2 
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_TOP_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_LEFT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_TOP_AND_UNDER_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_TOP_AND_ON_LEFT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_TOP_AND_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_LEFT_AND_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_TOP_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_RIGHT_AND_ON_TOP_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_RIGHT_AND_ON_LEFT_AND_ON_TOP_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ANGLE_SAND_TOP_LEFT
				|| this.type == EnumTiles.TRANSITION_GRASS_ANGLE_SAND_TOP_RIGHT
				|| this.type == EnumTiles.TRANSITION_GRASS_ANGLE_SAND_BOTTOM_LEFT
				|| this.type == EnumTiles.TRANSITION_GRASS_ANGLE_SAND_BOTTOM_RIGHT;

	}

	public boolean isWater() {
		return this.type == EnumTiles.CALM_WATER || this.type == EnumTiles.STORMY_WATER
				|| this.type == EnumTiles.RAGING_WATER || this.type == EnumTiles.KRAKEN_WATER
				|| this.type == EnumTiles.CALM_SEA_CHEST || this.type == EnumTiles.STORMY_SEA_CHEST
				|| this.type == EnumTiles.RAGING_SEA_CHEST || this.type == EnumTiles.CALM_SEA_ENNEMIE
				|| this.type == EnumTiles.STORMY_SEA_ENNEMIE || this.type == EnumTiles.RAGING_SEA_ENNEMIE;
	}

	public boolean isSand() {
		return this.type == EnumTiles.SAND_WATER || this.type == EnumTiles.STORMY_SAND_WATER
				|| this.type == EnumTiles.RAGING_SAND_WATER || this.type == EnumTiles.SAND
				|| this.type == EnumTiles.HARBOR_SAND || this.type == EnumTiles.SHELLFISH_1
				|| this.type == EnumTiles.SHELLFISH_2 || this.type == EnumTiles.SHELLFISH_3
				|| this.type == EnumTiles.TREASUR;
	}

	public boolean isMoutain() {
		return this.type == EnumTiles.MOUTAIN;
	}

	public boolean isTreasur() {
		return this.type == EnumTiles.TREASUR;
	}

	public boolean isPooton() {
		return this.type == EnumTiles.PONTOON;
	}

	public boolean isSeaChest() {
		return this.type == EnumTiles.CALM_SEA_CHEST || this.type == EnumTiles.STORMY_SEA_CHEST
				|| this.type == EnumTiles.RAGING_SEA_CHEST;
	}

	public boolean isSwpaner() {
		return this.type == EnumTiles.CRAB_SPAWNER || this.type == EnumTiles.CRAB_SPAWNER_TRANSITION;
	}

	public boolean isGrass() {
		return this.type == EnumTiles.TREE || this.type == EnumTiles.GRASS || this.type == EnumTiles.BLUE_FLOWER
				|| this.type == EnumTiles.YELLOW_FLOWER || this.type == EnumTiles.RED_FLOWER
				|| this.type == EnumTiles.GRASS_WITH_ROCK_1 || this.type == EnumTiles.GRASS_WITH_ROCK_2
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_TOP_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_LEFT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_TOP_AND_UNDER_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_TOP_AND_ON_LEFT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_TOP_AND_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_LEFT_AND_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_TOP_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_RIGHT_AND_ON_TOP_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_UNDER_AND_ON_LEFT_AND_ON_RIGHT_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ON_RIGHT_AND_ON_LEFT_AND_ON_TOP_OF_SAND
				|| this.type == EnumTiles.TRANSITION_GRASS_ANGLE_SAND_TOP_LEFT
				|| this.type == EnumTiles.TRANSITION_GRASS_ANGLE_SAND_TOP_RIGHT
				|| this.type == EnumTiles.TRANSITION_GRASS_ANGLE_SAND_BOTTOM_LEFT
				|| this.type == EnumTiles.TRANSITION_GRASS_ANGLE_SAND_BOTTOM_RIGHT;
	}

	public boolean isBoatEnnemi() {
		return this.type == EnumTiles.CALM_SEA_ENNEMIE || this.type == EnumTiles.RAGING_SEA_ENNEMIE
				|| this.type == EnumTiles.STORMY_SEA_ENNEMIE;
	}
}
