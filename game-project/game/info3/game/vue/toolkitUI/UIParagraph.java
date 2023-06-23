package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import info3.game.vue.GameView;

public class UIParagraph extends UIComponent {

	private String[] text;
	public ArrayList<UILabel> labels;
	private Font font;
	private int height;
	private UIMoveableText panel;

	public int getHeight() {
		return height;
	}

	public UIParagraph(String t) {
		super(0, 0, 0, 0);
		text = t.split("\n");
		labels = new ArrayList<>();
		font = new Font(GameView.customFont.getFontName(), Font.PLAIN, 30);

		this.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				// TODO Auto-generated method stub

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

	public void initLabels() {
		int i = 0;
		height = 0;
		for (String s : this.text) {
			labels.add(new UILabel(getPositionX(), getPositionY() + i * 25, s, font, Color.black));
			i += 1;
		}
	}
	
	public void setPanel(UIMoveableText t) {
		this.panel = t;
	}

	public void setLabelsPositionsY(int y) {
		int i = 0;
		for (UILabel l : labels) {
			l.setPositionY(y + i * 25);
			i+=1;
		}
	}

	public void updateLabelsPositionsY(int y) {
		for (UILabel l : labels) {
			l.setPositionY(l.getPositionY() - y);
		}
	}

	public int getFirstLabelPositionY() {
		return this.labels.get(0).getPositionY();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.setFont(font);
		height = 0;
		for (UILabel l : this.labels) {
			if (l.getPositionY() < this.panel.getPositionY()+10 || l.getPositionY() > this.panel.getPositionY()+this.panel.getHeight()) {
				
			} else {
				l.paint(g);
			}
			height += g.getFontMetrics().getHeight();
		}
	}

}
