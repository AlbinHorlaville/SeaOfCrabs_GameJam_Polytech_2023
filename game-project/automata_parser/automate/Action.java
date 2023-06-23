package automate;

import java.util.ArrayList;
import java.util.Random;
import info3.game.modele.Entity;

public class Action {
	private ArrayList<FunCall> funcalls;
	
	public Action(ArrayList<FunCall> funcalls) {
		this.funcalls = funcalls;
	}
	
	
	public void exec(Entity e) {
		if(funcalls.size() > 1) {
			int total = 0;
			int rand = new Random().nextInt(100);
			for(FunCall f : funcalls) {
				total += f.probability;
				if(rand <= total) {
					f.exec(e);
					return;
				}
			}
		}
		else if(funcalls.size() == 1){
			funcalls.get(0).exec(e);
		}
	}
	
	public String toString() {
		String s = "";
		for(FunCall f : funcalls) {
			s += "\t " + f.toString();
		}
		return s;
	}


//	protected int luck;
//	
//	public abstract boolean exec(Entity e); // Entity e
//	
//	public int GetLuck() {
//		return this.getLuck();
//	}
//
//	public int getLuck() {
//		return luck;
//	}
//
//	public void setLuck(int luck) {
//		this.luck = luck;
//	}
}
