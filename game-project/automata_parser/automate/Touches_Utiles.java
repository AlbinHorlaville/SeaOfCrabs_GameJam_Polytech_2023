package automate;

public enum Touches_Utiles {
	Left_Letter(81), Up_Letter(90), Right_Letter(68), Down_Letter(83),

	Left_Arrow(37), Up_Arrow(38), Right_Arrow(39), Down_Arrow(40);

	private int value;

	Touches_Utiles(int a) {
		this.value = a;
	}

	public static boolean Get(int a) {
		for (Touches_Utiles t : Touches_Utiles.values()) {
			if (t.value == a) {
				return true;
			}
		}
		return false;
	}

}
