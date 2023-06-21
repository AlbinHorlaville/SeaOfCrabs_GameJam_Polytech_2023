package info3.game.sound;

public enum SoundEffect {
	SwordSound("Sword4.ogg"),
	Test("Sword4.ogg");

	private final String fileName;

	SoundEffect(String string) {
		this.fileName = string;
	}
	
	public String getFileName() {
		return fileName;
	}
}
