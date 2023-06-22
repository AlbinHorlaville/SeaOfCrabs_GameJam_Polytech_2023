package info3.game.KeyBuffered;

import java.util.HashMap;

public class KeyBuffered {
	HashMap<Integer, Boolean> keysBuff;
	
	public KeyBuffered() {
		keysBuff = new HashMap<>();	
	}
	
	public boolean isBuffered(int key) {
		if (!keysBuff.containsKey(key)) return false;
		return keysBuff.get(key);
	}
	
	public boolean isBuffered(String s) {
		return isBuffered(KeyCodeConvertor.stringToKeyCode(s));
	}
	
	public void buff(int key) {
		if (!keysBuff.containsKey(key)) keysBuff.put(key, true);
		keysBuff.replace(key, true);
	}
	
	public void unBuff(int key) {
		if (!keysBuff.containsKey(key)) return;
		keysBuff.replace(key, false);
	}
}
