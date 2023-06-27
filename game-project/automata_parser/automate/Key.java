package automate;

import info3.game.KeyBuffered.KeyCodeConvertor;

public class Key extends Parameter {
	public int code;

	public Key(String s) {
		super(s);
		code = convertToInt(s);
	}

	@Override
	public Object eval() {
		return this.code;
	}

	public int convertToInt(String s) {
		return KeyCodeConvertor.stringToKeyCode(s);
	}

//	public void SetKeyPressed(int k) {
//		this.key_pressed = k;
//	}
//	
//	@Override
//	public boolean eval() {
//		return this.key_pressed == this.key_wanted;
//	}
//
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return "Key(" + Touches_Utiles.Get(key_wanted) + ")";
//	}
}
