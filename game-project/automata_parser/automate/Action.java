package automate;

import java.util.ArrayList;
import java.util.Random;

public class Action {
	private ArrayList<FunCall> funcalls;
	
	public Action(ArrayList<FunCall> funcalls) {
		this.funcalls = funcalls;
	}
	
	
	public void exec(Entity e) {
		if(funcalls.size() > 1) {
			int total = 100;
			int rand = new Random().nextInt(total);
			for(FunCall f : funcalls) {
				if(rand <= f.probability) {
					f.exec(e);
					return;
				}
				total -= f.probability;
			}
		}
		else {
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
