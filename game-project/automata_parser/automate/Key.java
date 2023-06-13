package automate;

public class Key extends Parameter{
	
	public Key(String s) {
		super(s);
	}

	@Override
	public Object eval() {
		return this.m_string;
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
