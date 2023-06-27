package info3.game.KeyBuffered;

import info3.game.sound.SoundEffect;
import info3.game.sound.SoundTool;

public class EasterEggs {

	// KONAMI CODE
	private final int[] CODE = { 38, 38, 40, 40, 37, 39, 37, 39, 66, 65, 10 };

	int current_code[];
	int code_index;

	public EasterEggs() {
		current_code = new int[11];
		code_index = 0;
	}

	public void checker(int key_code) {
		if (key_code == CODE[code_index]) {
			current_code[code_index] = key_code;
			code_index++;
			if (codeBon()) {
				trigger();
				reset();
				return;
			}
		} else {
			reset();
		}

	}

	private void trigger() {
		SoundTool.playSoundEffect(SoundEffect.Egg, 0);
	}

	private boolean codeBon() {
		for (int i = 0; i < CODE.length; i++) {
			if (CODE[i] != current_code[i])
				return false;
		}
		return true;
	}

	private void reset() {
		this.code_index = 0;
		current_code = new int[11];
	}
}
