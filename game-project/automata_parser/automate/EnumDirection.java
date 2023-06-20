package automate;

public enum EnumDirection {

	N("N"),
	S("S"),
	W("W"),
	E("E"),
	NE("NE"),
	SE("SE"),
	SW("SW"),
	NW("NW"),
	F("F"),
	R("R"),
	L("L"),
	B("B"),	
	H("H");
	
	private String m_string;
	
	private EnumDirection(String s) {
		this.m_string = s;
	}
}
