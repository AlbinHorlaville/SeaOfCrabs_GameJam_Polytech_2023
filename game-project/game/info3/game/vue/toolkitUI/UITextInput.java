package info3.game.vue.toolkitUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class UITextInput extends UIComponent {

	private UILabel label;
	private Color backgroundColor;
	private Color borderColor;
	private Color foregroundColor;
	private String inputText;
	private int maxChar;
	private int charCounter;
	private boolean isSelected;
	private Color savedBackgroundColor;

	private static final Font FONT = new Font("TimesRoman", Font.PLAIN, 12);

	public UITextInput(int x, int y, int w, Color bg, Color br, Color fg) {
		super(x, y, 0, w);
		backgroundColor = bg;
		savedBackgroundColor = bg;
		borderColor = br;
		foregroundColor = fg;
		inputText = new String("Enter text");
		charCounter = inputText.length();
		label = new UILabel(0, 0, inputText, FONT, foregroundColor);

		this.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked() {
				eraseText();
			}

			@Override
			public void onComponentMouseIn() {
				isSelected = true;
				setBackgroundColor(borderColor);
			}

			@Override
			public void onComponentMouseOut() {
				isSelected = false;
				setBackgroundColor(savedBackgroundColor);
			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				if (isSelected) {
					updateTextInput(e);
				}
			}

		});
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(backgroundColor);
		g.setFont(label.getFont());
		int labelWidth = g.getFontMetrics().stringWidth(label.getText());
		int labelHeight = g.getFontMetrics().getHeight();
		int rectHeight = labelHeight + 2 * label.getFont().getSize();
		this.setHeight(rectHeight);
		g.setColor(borderColor);
		g.fillRect(this.getPositionX() - 2, this.getPositionY() - 2, this.getWidth() + 4, rectHeight + 4);
		g.setColor(backgroundColor);
		g.fillRect(super.getPositionX(), super.getPositionY(), this.getWidth(), rectHeight);
		g.setColor(foregroundColor);
		int centerX = this.getPositionX() + 20;
		int centerY = this.getPositionY() + rectHeight - labelHeight;
		maxChar = ((getWidth() - 40) / g.getFontMetrics().stringWidth("a"));
		g.drawString(label.getText(), centerX, centerY);
	}

	public UILabel getLabel() {
		return label;
	}

	public void setLabel(UILabel label) {
		this.label = label;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setForegroundColor(Color foregroundColor) {
		this.label.setFontColor(foregroundColor);
		this.foregroundColor = foregroundColor;
	}

	public String getInputText() {
		return inputText;
	}

	public void setInputText(String inputText) {
		this.inputText = inputText;
		this.label.setText(inputText);
	}

	public void updateTextInput(KeyEvent e) {

		int keyCode = e.getKeyCode();
		char keyChar = e.getKeyChar();
		switch (keyCode) {
		case 8:
			if (inputText.length() > 0) {
				inputText = inputText.substring(0, inputText.length() - 1);
				charCounter--;
			}
			break;
		case 16:
			break;
		case 32:
			if (charCounter < maxChar) {
				inputText += "_";
				charCounter++;
			}
			break;
		default:
			if (charCounter < maxChar) {
				inputText += keyChar;
				charCounter++;
			}
		}
		this.setInputText(inputText);

	}

	public void eraseText() {
		this.setInputText("");
		charCounter = 0;
	}

	public boolean isTextEmpty() {
		return this.inputText.length() == 0;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

}
