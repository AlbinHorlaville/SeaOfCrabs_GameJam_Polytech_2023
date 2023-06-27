package automate;

public abstract class Expression {

	public String m_string;

	public Expression() {

	}

	public Expression(String string) {
		this.m_string = string;
	}

	public abstract String toString();
}
