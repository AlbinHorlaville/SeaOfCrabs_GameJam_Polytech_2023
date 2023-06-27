package info3.game.sound;

public enum BackgroundMusic {
	MainMenu("Theme5.ogg"),
	Game("Theme3.ogg");
	
	private String fileName;
	
	BackgroundMusic(String string) {
		fileName = string;
	}
	
	public String getFileName() {
		return fileName;
	}
}
