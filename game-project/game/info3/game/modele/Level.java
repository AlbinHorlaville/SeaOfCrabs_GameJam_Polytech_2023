package info3.game.modele;

public class Level {

	private int level;
	
	public Level(int level) {
		this.level = level;
	}
	
	public float getCoeffBasedOnLevel(){
		float coeff = 1;
		
		// The goal : Level 1 = 1 ; Level 2 = 1,2 Level; Level 3 = 1,4 Level; Level 4 = 1,6 Level
		if(level >  1)
			coeff += level * 2.0f;
		
		return coeff;
	}
}
