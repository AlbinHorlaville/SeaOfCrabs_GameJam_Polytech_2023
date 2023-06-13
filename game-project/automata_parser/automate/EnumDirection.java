package automate;

public enum EnumDirection {

	N("N"),
	S("S"),
	W("W"),
	E("E");
	
	private String m_string;
	
	private EnumDirection(String s) {
		this.m_string = s;
	}
}
