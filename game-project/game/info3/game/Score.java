package info3.game;

public class Score {

	private int heures;
	private int minutes;
	private int secondes;

	public Score(int h, int m, int s) {
		heures = h;
		minutes = m;
		secondes = s;
	}

	public Score(String[] s) {
		if (!s[0].equals("noscore")) {
			heures = Integer.valueOf(s[0]);
			minutes = Integer.valueOf(s[1]);
			secondes = Integer.valueOf(s[2]);
		}
	}

	public int getHeures() {
		return heures;
	}

	public void setHeures(int heures) {
		this.heures = heures;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSecondes() {
		return secondes;
	}

	public void setSecondes(int secondes) {
		this.secondes = secondes;
	}

	public String toSQLStringFormat() {
		String time = new String("");
		String h = new String("");
		String m = new String("");
		String s = new String("");

		if (heures < 10) {
			h = '0' + Integer.toString(heures);
		} else {
			h = Integer.toString(heures);
		}

		if (minutes < 10) {
			m = '0' + Integer.toString(minutes);
		} else {
			m = Integer.toString(minutes);
		}

		if (secondes < 10) {
			s = '0' + Integer.toString(secondes);
		} else {
			s = Integer.toString(secondes);
		}

		time = new String(h + ":" + m + ":" + s);
		return time;
	}

}
