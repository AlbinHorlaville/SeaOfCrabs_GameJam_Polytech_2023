package info3.game.modele;

public class GameTimer {

	private int heures;
	private int minutes;
	private int secondes;
	private int milisecondes;
	private boolean paused;

	public GameTimer() {
		heures = 0;
		minutes = 0;
		secondes = 0;
		paused = false;
	}

	public void updateTimer(long e) {
		if (!paused) {
			milisecondes += e;
			if (milisecondes >= 1000) {
				secondes += 1;
				milisecondes = 0;
			} else if (secondes >= 60) {
				minutes += 1;
				secondes = 0;
			} else if (minutes >= 60) {
				heures += 1;
				minutes = 0;
			} else if (heures >= 24) {
				secondes = 0;
				minutes = 0;
				heures = 0;
			}
		}
	}

	public void resetTimer() {
		secondes = 0;
		minutes = 0;
		heures = 0;
	}

	public int getHeures() {
		return heures;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getSecondes() {
		return secondes;
	}

	public int getMiliSecondes() {
		return this.milisecondes;
	}

	public void setPaused(boolean p) {
		paused = p;
	}

	public boolean getPaused() {
		return paused;
	}

	public String toString() {
		String time = new String("");
		if (heures > 0) {
			time = new String(
					Integer.toString(heures) + "'" + Integer.toString(minutes) + "'" + Integer.toString(secondes));
		} else if (minutes > 0) {
			time = new String(Integer.toString(minutes) + "'" + Integer.toString(secondes));
		} else {
			time = new String("'" + Integer.toString(secondes));
		}
		return time;
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
