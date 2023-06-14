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
	
	/**
	 * The UITextInput is an text input 
	 * @param x
	 * @param y
	 * @param w
	 * @param bg
	 * @param br
	 * @param fg
	 */

	public UITextInput(int x, int y, int w, String placeholder, Color bg, Color br, Color fg) {
		super(x, y, 0, w);
		backgroundColor = bg;
		savedBackgroundColor = bg;
		borderColor = br;
		foregroundColor = fg;
		inputText = placeholder;
		charCounter = inputText.length();
		label = new UILabel(0, 0, inputText, FONT, foregroundColor);

		// The UITextInput's behavior is implemented here because it has a only and
		// specific usage unlike others UIComponent
		this.setUIComponentListener(new UIComponentListener() {

			@Override
			public void onComponentClicked(int x, int y) {
				eraseText(); // when clicked, the text input erase its contents
			}

			@Override
			public void onComponentMouseIn(int x, int y) {
				isSelected = true;
			}

			@Override
			public void onComponentMouseOut(int x, int y) {
				isSelected = false;
			}

			@Override
			public void onComponentPressed(int x, int y) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onKeyPressed(KeyEvent e) {
				if (isSelected) {
					updateTextInput(e); // update the text input string from a KeyEvent
				}
			}

		});
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(backgroundColor);
		g.setFont(label.getFont());
		int labelWidth = g.getFontMetrics().stringWidth(label.getText()); // the width of the label (text input string)
		int labelHeight = g.getFontMetrics().getHeight(); // the height of the label (text input string)
		int rectHeight = labelHeight + 2 * label.getFont().getSize(); // the height of the input rectangle is calculated
																		// from the size of the font of the text input
		this.setHeight(rectHeight);
		g.setColor(borderColor);
		g.fillRect(this.getPositionX() - 2, this.getPositionY() - 2, this.getWidth() + 4, rectHeight + 4); // we first
																											// draw a
																											// border
		g.setColor(backgroundColor);
		g.fillRect(super.getPositionX(), super.getPositionY(), this.getWidth(), rectHeight); // then we draw the
																				// rectangle
		g.setColor(backgroundColor);
		g.drawString("Click to erase", super.getPositionX(), super.getPositionY()+rectHeight+20);
		g.setColor(foregroundColor);
		int centerX = this.getPositionX() + 20; // the text input string is not centered but starts from the x-position
												// of the rectangle + 20 (margin)
		int centerY = this.getPositionY() + rectHeight - labelHeight;
		maxChar = ((getWidth() - 40) / g.getFontMetrics().stringWidth("a")); // maxChar is calculated from the width of
																				// the text input and from the height of
																				// a char
		g.drawString(label.getText(), centerX, centerY); // we draw the text input string
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

	/**
	 * This function update the text input depending on the KeyEvent
	 */
	public void updateTextInput(KeyEvent e) {

		int keyCode = e.getKeyCode(); // the key code of the KeyEvent
		char keyChar = e.getKeyChar(); // the key char of the KeyEvent
		switch (keyCode) {
		case 8: // if KeyCode is 'backspace' we clear the last char of the text input string
			if (inputText.length() > 0) { // only if the text input string is not empty
				inputText = inputText.substring(0, inputText.length() - 1); // we remove the last char of the string
				charCounter--; // the char's counter is decremented
			}
			break;
		case 16: // if KeyCode is 'shift'
			break; // we do nothing
		case 32: // if KeyCode is 'space'
			if (charCounter < maxChar) {
				inputText += "_"; // we add a underscore
				charCounter++; // the char's counter is incremented
			}
			break;
		default: // by default we add the char corresponding to the KeyCode
			if (charCounter < maxChar) { // only if we do not exceed the limit of char number
				inputText += keyChar;
				charCounter++;
			}
		}
		this.setInputText(inputText); // update the input text string and label

	}

	/**
	 * This function erase the text input
	 */
	public void eraseText() {
		this.setInputText("");
		charCounter = 0; // the number of char is reinitialized
	}

	/**
	 * This function returns true if the text input is empty
	 * 
	 * @return boolean
	 */
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
