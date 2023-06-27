package info3.game.sound;

public enum SoundEffect {
	BoatAttack("pirateBoatAttack.ogg"),
	Start("UI/Start.ogg"),
	Confirm("UI/Confirm.ogg"),
	Cursor("UI/Cursor.ogg"),
	Victory("Victory.ogg"),
	Defeat("Defeat.ogg");

	private String fileName;
	
	SoundEffect(String string) {
		fileName = string;
	}
	
	public String getFileName() {
		return fileName;
	}
}
