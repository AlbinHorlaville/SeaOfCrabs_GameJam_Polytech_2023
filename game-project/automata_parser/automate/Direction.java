package automate;

public class Direction extends Parameter{

	public Direction(String string) {
		super(string);
	}

	@Override
	public Object eval() {
		return EnumCategory.valueOf(m_string);
	}
	
	
	

}
