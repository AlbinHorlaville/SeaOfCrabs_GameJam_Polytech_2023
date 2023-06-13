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
}
