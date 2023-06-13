package automate;

public enum EnumCategory {
	
	Adversary("V"),
	Clue("C"),
	Danger("D"),
	Gate("G"),
	Jumpable("J"),
	Missile("M"),
	Obstacle("O"),
	PickUp("P"),
	Team("T"),
	Void("V"),
	Ally("@"),
	Underscore("_");
	
	private String m_string;
	
	EnumCategory(String s) {
		this.m_string = s;
	}
	
}
