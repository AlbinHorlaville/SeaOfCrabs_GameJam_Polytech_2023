package automate;

public enum EnumDirection {

	MoveNorth("N"),
	MoveSouth("S"),
	MoveEast("E"),
	MoveWest("W");
	
	private String m_string;
	
	private EnumDirection(String s) {
		this.m_string = s;
	}
}
