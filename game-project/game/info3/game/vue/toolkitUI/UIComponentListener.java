package info3.game.vue.toolkitUI;

import java.awt.event.KeyEvent;

/* The UIComponentListener is an interface which defines UIComponent's behaviors.
 * These behaviors are implemented in the differents Views when UIComponents are 
 * instantiated. You are able to implements differents behaviors for each 
 * UIComponent.
 */

public interface UIComponentListener {

	void onComponentClicked(); // when the UIComponent is clicked

	void onComponentMouseIn(); // when the UICompoent is entered

	void onComponentMouseOut(); // when the UIComponent is exited

	void onComponentPressed(int x, int y); // when the UIComponent is pressed

	void onKeyPressed(KeyEvent e); // when a key is pressed on the UIComponent
}
