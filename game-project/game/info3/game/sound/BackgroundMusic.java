package info3.game.sound;

public enum BackgroundMusic {
	MainMenu("Town8.ogg"),
	Game("Town1.ogg");
	
	private String fileName;
	
	BackgroundMusic(String string) {
		fileName = string;
	}
	
	public String getFileName() {
		return fileName;
	}
}
