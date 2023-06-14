package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class UIBoxes extends UIComponent {

	ArrayList<UIBox> boxes;
	UIBox selectedBox;

	public UIBoxes(int x, int y) {
		super(x, y, 0, 0);
		boxes = new ArrayList<>();
		this.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				if (getSelectedBox(x, y) != null) {
					selectedBox = getSelectedBox(x, y);
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {

			}

			@Override
			public void onComponentMouseOut(int x, int y) {

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

	public UIBox getSelectedBox(int x, int y) {
		for (UIBox b : boxes) {
			if (b.mouseOnComponent(x, y)) {
				return b;
			}
		}
		return null;
	}

	public void addBox(UIBox b) {
		boxes.add(b);
	}

	@Override
	public void paint(Graphics g) {
		int i = 0;
		int widthCounter = 0;
		int heightCounter = 0;
		for (UIBox b : boxes) {
			if (selectedBox != null) {
				if (b == selectedBox) {
					b.setBorderColor(Color.green);
				} else {
					b.setBorderColor(Color.black);
				}
			} else {
				if (i == 0) {
					b.setBorderColor(Color.green);
				} else {
					b.setBorderColor(Color.black);
				}
			}
			b.setPositionX(this.getPositionX() + i * b.getWidth());
			b.setPositionY(this.getPositionY());
			b.paint(g);
			widthCounter += b.getWidth();
			heightCounter = b.getHeight();
			i += 1;
		}
		this.setWidth(widthCounter);
		this.setHeight(heightCounter);
	}

}
