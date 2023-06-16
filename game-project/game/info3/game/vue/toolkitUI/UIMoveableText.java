package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class UIMoveableText extends UIComponent {

	UIParagraph paragraph;
	private boolean move;

	public UIMoveableText(int x, int y, int h, int w, UIParagraph p) {
		super(x, y, h, w);
		paragraph = p;
		paragraph.setPositionX(x);
		paragraph.setPositionY(y + getHeight());
		paragraph.setWidth(w);
		paragraph.setHeight(h);
		move = true;
		this.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				move = false;

			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				move = true;

			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		if (move) {
			if (paragraph.getPositionY() < this.getPositionY() - paragraph.getHeight()) {
				paragraph.setPositionY(this.getPositionY() + this.getHeight());
			} else {
				paragraph.setPositionY(paragraph.getPositionY() - 1);
			}
			paragraph.paint(g);
		} else {
			paragraph.paint(g);
		}
	}

}
