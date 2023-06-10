package info3.game.modele;

/*
 * A tile of the map is characterized by it's type
 */
public class Tiles {
	private EnumTiles type;

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
}
