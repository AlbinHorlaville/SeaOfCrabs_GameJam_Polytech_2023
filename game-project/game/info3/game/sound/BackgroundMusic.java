package info3.game.sound;

public enum BackgroundMusic {
	
	MainMenu("Town8.ogg"),
	Game("MainTheme.ogg");

	private final String fileName;

	private BackgroundMusic(String string) {
		this.fileName = string;
	}
	
	public String getFileName() {
		return fileName;
	}
}
