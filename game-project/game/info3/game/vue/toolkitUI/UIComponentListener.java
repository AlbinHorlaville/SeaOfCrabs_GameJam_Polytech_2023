package info3.game.vue.toolkitUI;

import java.awt.event.KeyEvent;

public interface UIComponentListener {

	void onComponentClicked();

	void onComponentMouseIn();

	void onComponentMouseOut();

	void onComponentPressed(int x, int y);
	
	void onKeyPressed(KeyEvent e);
}
