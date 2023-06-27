package info3.game.vue.toolkitUI;

public abstract class UIBarrePointDeVie extends UIComponent {

	private boolean state;

	public UIBarrePointDeVie(int x, int y) {
		super(x, y, 10, 75);
		state = false;
	}

	public void changeShowing(boolean s) {
		state = s;
	}
	
	public boolean getState() {
		return this.state;
	}
}
