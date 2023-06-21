package info3.game.modele.MoveableEntityClass;

import java.util.ArrayList;

import info3.game.Controller;

public class Kraken {
	
	
	ArrayList<Tentacle> tentacles;

	public Kraken() {		
		this.tentacles = new ArrayList<>();
	}
	
	public void tentacleDead(Tentacle tentacle) {
		tentacles.remove(tentacle);
		
		if (this.tentacles.isEmpty()) {
			Controller.getGameModele().victory();
		}
	}
	
	public void addTentacle(int x,int y,int number) {
		this.tentacles.add(new Tentacle(x,y,this,number));
		
	}

}
