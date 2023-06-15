package info3.game.modele.map;

/*
 * A tile of the map is characterized by it's type
 */
public class Tiles {
	private EnumTiles type;
	private int x;
	private int y;

	public Tiles(EnumTiles type) {
		this.type = type;
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

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
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

	public boolean isIsland() {
		return this.type == EnumTiles.SAND_WATER || this.type == EnumTiles.SAND || this.type == EnumTiles.GRASS
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
}
