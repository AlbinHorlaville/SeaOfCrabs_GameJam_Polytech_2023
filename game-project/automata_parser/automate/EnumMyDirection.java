package automate;

public enum EnumMyDirection {

	MoveFront("F"), MoveBack("B"), MoveRight("R"), MoveLeft("L");

	private String m_string;

	private EnumMyDirection(String s) {
		this.m_string = s;
	}
}
