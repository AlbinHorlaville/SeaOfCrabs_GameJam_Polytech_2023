package info3.game.KeyBuffered;

import java.awt.event.KeyEvent;

public class KeyCodeConvertor {

	public static int stringToKeyCode(String s) {
		if (s.equals("FU"))
			return 38;
		if (s.equals("FD"))
			return 40;
		if (s.equals("FR"))
			return 39;
		if (s.equals("FL"))
			return 37;
		if (s.equals("1"))
			return 150;
		if (s.equals("2"))
			return 16777449;
		if (s.equals("3"))
			return 152;
		if (s.equals("4"))
			return 222;
		if (s.equals("5"))
			return 519;
		if (s.equals("6"))
			return 45;
		if (s.equals("7"))
			return 16777448;
		return KeyEvent.getExtendedKeyCodeForChar(s.charAt(0));
	}
}
