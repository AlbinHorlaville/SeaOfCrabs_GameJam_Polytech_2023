package automate;

public class Category extends Parameter{

	public Category(String string) {
		super(string);
	}	
	
	public EnumCategory eval() {
		return EnumCategory.valueOf(m_string);
	}
	
}
