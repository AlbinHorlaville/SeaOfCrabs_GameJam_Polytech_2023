package info3.game.modele;

public class Level {
	
	public static int getAugmentDamageCrab(int section) {
		switch(section) {
		case 1:
		case 2:
			return 0;
		case 3:
			return 5;
		case 4:
			return 20;
		case 5:
			return 30;
		case 6:
			return 50;
		case 7:
			return 80;
		default: 
			return 80;
		}
	}
	
	public static int getAugmentLifeCrab(int section) {
		return 50 * (section - 1);
	}
	
	public static int getNbCrabsToEgg(int section) {
		return 3 * section;
	}
}
