package info3.game.vue.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import info3.game.GameState;
import info3.game.SeaOfCrabes;
import info3.game.modele.GameModele;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIButton;
import info3.game.vue.toolkitUI.UIComponent;
import info3.game.vue.toolkitUI.UIComponentListener;
import info3.game.vue.toolkitUI.UILabel;
import info3.game.vue.toolkitUI.UITextInput;
import info3.game.vue.toolkitUI.UITitle;

public class SetupUserView extends View {

	private UITextInput usernameInput;
	private UIButton buttonContinue;
	private UITitle title;
	private boolean continueEnabled;
	private UILabel errorMessage;

	public SetupUserView(GameView gv) {
		super(gv);
		int windowWidth = gv.getWidthCanvas();
		int windowHeight = gv.getHeightCanvas();

		continueEnabled = false;

		title = new UITitle(windowWidth, windowHeight, "Welcome to Sea Of Crabs!", FONT2, Color.black);

		buttonContinue = new UIButton(774, windowHeight - 100, 200, 70,
				new UILabel(0, 0, "Continue", FONT1, Color.black), UIButton.BACKGROUND_COLOR_GREEN);

		usernameInput = new UITextInput(windowWidth / 2 - 100, windowHeight / 2, 200, "Enter a username",
				UIButton.BACKGROUND_COLOR_YELLOW, Color.black);

		buttonContinue.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN_HOVER);

		errorMessage = new UILabel(windowWidth / 2 - 200, windowHeight / 2 + 150, "", FONT1,
				Color.red);

		buttonContinue.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				if (continueEnabled) {
					if (GameModele.createUser(usernameInput.getInputText())) {
						gameView.update_view(GameState.Menu);
						gameView.getGame().setCurrentState(GameState.Menu);
					} else {
						errorMessage.setText("This username alreay exists");
					}
				}
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				if (continueEnabled) {
					buttonContinue.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN_HOVER);
				}
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				if (continueEnabled) {
					buttonContinue.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN);
				}
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

		addComponent(title);
		addComponent(new UILabel(windowWidth / 2 - 250, 150, "Define a username before playing!", FONT3, Color.black));
		addComponent(
				new UILabel(windowWidth / 2 - 260, 200, "It will be used to record your score", FONT3, Color.black));
		addComponent(new UILabel(windowWidth / 2 - 140, 250, "in the world ranking!", FONT3, Color.black));
		addComponent(usernameInput);
		addComponent(buttonContinue);
		addComponent(errorMessage);
	}
	
	public void paint(Graphics g, int width, int height) {
		for (UIComponent c : components) {
			c.paint(g);
		}
		if (GameModele.currentScore!=null) {
			if (SeaOfCrabes.connectedToDatabase) {
				addComponent(new UILabel(10, 30, "Connected to database", FONT4, Color.green));
				addComponent(new UILabel(10, 50, "@"+GameModele.currentUser.getUsername(), FONT4, Color.black));
			} else {
				addComponent(new UILabel(10, 30, "Not connected to database", FONT4, Color.red));
			}
		}
	}

	@Override
	public void tick(long elapsed) {
		String u = usernameInput.getInputText();
		if ((!u.equals("Enter a username")) && (u.length() <= 15) && (u.length() != 0)) {
			continueEnabled = true;
			buttonContinue.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN);
		} else {
			continueEnabled = false;
			buttonContinue.setBackgroundColor(UIButton.BACKGROUND_COLOR_GREEN_HOVER);
		}

	}

}
