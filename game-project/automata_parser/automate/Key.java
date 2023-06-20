package automate;

import java.awt.event.KeyEvent;

public class Key extends Parameter{
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
		if(s.equals("FU"))
			return 38;
		if(s.equals("FD"))
			return 40;
		if(s.equals("FR"))
			return 39;
		if(s.equals("FL"))
			return 37;
		if(s.equals("z"))
			return 90;
		if(s.equals("q"))
			return 81;
		if(s.equals("s"))
			return 83;
		if(s.equals("d"))
			return 68;
		if(s.equals("b"))
			return 66;
		if(s.equals("r"))
			return 82;
		return s.codePointAt(0);
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
