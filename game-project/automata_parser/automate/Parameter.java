package automate;

public abstract class Parameter extends Expression {
	
	public Parameter(String string) {
		super(string);
	}
	
	public String toString() {
		return this.m_string;
	}
	
	public abstract Object eval();
}
