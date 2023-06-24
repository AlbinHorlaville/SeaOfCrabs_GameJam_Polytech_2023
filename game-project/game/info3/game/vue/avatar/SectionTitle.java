package info3.game.vue.avatar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import info3.game.modele.GameModele;
import info3.game.modele.map.Map;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UITitle;

public class SectionTitle {

	private boolean display;
	private int frame;
	private int section;

	private boolean fadeIn;

	String textTitle;

	private static final Font FONT = new Font(GameView.customFont.getFontName(), Font.PLAIN, 60);

	UITitle title;

	public SectionTitle() throws IOException {
		this.frame = 0;
		this.display = true;
		this.section = 0;
		this.fadeIn = true;
		this.textTitle = "HARBOUR";
	}

	public void paint(Graphics g, int width, int height) {

		if (GameModele.pirateBoat.getCurrentSection() != this.section) {
			this.setTitle(GameModele.pirateBoat.getCurrentSection());
		}

		if (display) {

			if (this.fadeIn) {
				title = new UITitle(GameView.screenWidth, GameView.screenHeight, this.textTitle, FONT,
						new Color(0, 0, 0, frame));
			} else {
				title = new UITitle(GameView.screenWidth, GameView.screenHeight, this.textTitle, FONT,
						new Color(0, 0, 0, 255 - frame));
			}
			title.paint(g);
			g.setColor(Color.white);
			g.fillRect(title.getX() - 10, title.getY() - 20 - title.getDrawHeight() / 2, title.getDrawWidth() + 20,
					title.getDrawHeight() + 20);
			title.paint(g);
			frame += 2;
			if (frame >= 250) {
				frame = 0;
				if (this.fadeIn) {
					this.fadeIn = false;
				} else {
					this.display = false;
				}
			}
		}
	}

	private void setTitle(int section) {
		if (this.section < section) {
			switch (section) {
			case 0:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "HARBOUR";
				break;
			case 1:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "CALM SEA";
				break;
			case 2:
				break;
			case 3:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "STORMY SEA";
				break;
			case 4:
				break;
			case 5:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "RAGING SEA";
				break;
			case 6:
				break;
			case 7:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "THE DESOLATE LANDS OF THE KING CRABS";
				break;
			case 8:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "THE KRAKEN'S LAIR";
				break;
			default:
				break;
			}
		} else {
			switch (section) {
			case 0:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "HARBOUR";
				break;
			case 1:
				break;
			case 2:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "CALM SEA";
				break;
			case 3:
				break;
			case 4:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "STORMY SEA";
				break;
			case 5:
				break;
			case 6:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "RAGING SEA";
				break;
			case 7:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "THE DESOLATE LANDS OF THE KING CRABS";
				break;
			case 8:
				this.display = true;
				this.fadeIn = true;
				this.textTitle = "THE KRAKEN'S LAIR";
				break;
			default:
				break;
			}
		}
		this.section = section;
	}
}
