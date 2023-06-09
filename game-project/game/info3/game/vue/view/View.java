package info3.game.vue.view;

import java.awt.Graphics;

public abstract class View {

	//Mettre la liste des compenent
	
	public abstract void tick(long elapsed);

	public abstract void paint(Graphics g, int width, int height) ;
}
