package info3.game.vue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import info3.game.GameState;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIChecker;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UICursor;
import info3.game.vue.toolkitUI.UILabel;

public class MenuView extends View {
	
	UIButton buttonPlay;
	UIButton buttonSettings;
	UIButton buttonScore;
	UIButton buttonCredits;
	UICursor cursor;
	UILabel label;
	UIChecker checker;
	
	private int window_width;
	private int window_height;
	
	private Color c1;
	private Color c2;
	private Color c3;
	
	private static final Font FONT1 = new Font("TimesRoman", Font.BOLD, 20);
	private static final Font FONT2 = new Font("IMPACT", Font.BOLD, 100);

	public MenuView(GameView gv) {
		super(gv);
		window_width = 1024;
		window_height = 768;
		c1 = new Color(255, 100, 100);
		c2 = new Color(255, 255, 102);
		c3 = new Color(255, 218, 185);
		
		
		
		buttonPlay = new UIButton(450, 300, new UILabel(0, 0, "Play", FONT1, c2), c3);
		buttonSettings = new UIButton(450, 400, new UILabel(0, 0, "Settings", FONT1, c2), c3);
		buttonScore = new UIButton(450, 500, new UILabel(0, 0, "Score", FONT1, c2), c3);
		buttonCredits = new UIButton(450, 600, new UILabel(0, 0, "Credits", FONT1, c2), c3);
		
		//cursor = new UICursor(300, 300, 100, 400, Color.black, Color.red);
		label = new UILabel(250, window_height/5, "SEA OF CRABES", FONT2, Color.white);
		//checker = new UIChecker(400, 400, new UILabel(0, 0, "Checker", FONT1, Color.white), Color.black);
		

		buttonPlay.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				try {
					gameView.getGame().start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn() {
				buttonPlay.setBackgroundColor(c1);
				buttonPlay.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				buttonPlay.setBackgroundColor(c2);
				buttonPlay.setForegroundColor(c1);
			}

			public void onComponentPressed(int x, int y) {
			};

		});
		
		buttonSettings.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				try {
					gameView.getGame().start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn() {
				buttonSettings.setBackgroundColor(c1);
				buttonSettings.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				buttonSettings.setBackgroundColor(c2);
				buttonSettings.setForegroundColor(c1);
			}

			public void onComponentPressed(int x, int y) {
			};

		});
		
		buttonScore.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				try {
					gameView.getGame().start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn() {
				buttonScore.setBackgroundColor(c1);
				buttonScore.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				buttonScore.setBackgroundColor(c2);
				buttonScore.setForegroundColor(c1);
			}

			public void onComponentPressed(int x, int y) {
			};

		});
		
		buttonCredits.setUIComponentListener(new UIComponentListener() {
			@Override
			public void onComponentClicked() {
				try {
					gameView.getGame().start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onComponentMouseIn() {
				buttonCredits.setBackgroundColor(c1);
				buttonCredits.setForegroundColor(c2);
			}

			@Override
			public void onComponentMouseOut() {
				buttonCredits.setBackgroundColor(c2);
				buttonCredits.setForegroundColor(c1);
			}

			public void onComponentPressed(int x, int y) {
			};

		});
/*
		cursor.setUIComponentListener(new UIComponentListener() {

			public void onComponentPressed(int x, int y) {
				cursor.move(x, y);
			}

			public void onComponentClicked() {
			}

			@Override
			public void onComponentMouseIn() {
				// TODO Auto-generated method stub
				cursor.setColorCursor(Color.blue);
			}

			@Override
			public void onComponentMouseOut() {
				// TODO Auto-generated method stub
				cursor.setColorCursor(Color.yellow);

			};
		});
		*/
		
		/*checker.setUIComponentListener(new UIComponentListener() {

			public void onComponentPressed(int x, int y) {
			}

			public void onComponentClicked() {
				checker.check();
			}

			@Override
			public void onComponentMouseIn() {
				// TODO Auto-generated method stub
			}

			@Override
			public void onComponentMouseOut() {
				// TODO Auto-generated method stub
			};
		});
		*/

		// on ajoute les différents components
		addComponent(buttonPlay);
		addComponent(buttonScore);
		addComponent(buttonSettings);
		addComponent(buttonCredits);
		//addComponent(cursor);
		addComponent(label);
		//addComponent(checker);
	}

	@Override
	public void tick(long elapsed) {
		// TODO Auto-generated method stub

	}

}
