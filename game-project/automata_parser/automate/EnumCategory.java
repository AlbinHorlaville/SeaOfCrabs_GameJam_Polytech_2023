package automate;

public enum EnumCategory {
	
	A("Adversary"),
	C("Clue"),
	D("Danger"),
	G("Gate"),
	J("Jumpable"),
	M("Missile"),
	O("Obstacle"),
	P("PickUp"),
	T("Team"),
	V("Void");
	
	private String m_string;
	
	EnumCategory(String s) {
		this.m_string = s;
	}
	
}
