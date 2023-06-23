package automate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

import info3.game.modele.Entity;

public class Automate {
	public String name;
	public ArrayList<State> states;
	public State initial_state;
	
	
	public Automate(String name){
		this.name = name;
		states = new ArrayList<State>();
	}
	
	public State addState(automate.State state, boolean initial) {
		for(State s : states) {
			if(s == state)
				return state;
		}
		states.add(state);
		if(initial) {
			initial_state = state;
		}
		return state;
	}
	
	public State step(Entity e, State state) {
		return state.step(e);
	}
	
	public void print() {
//		System.out.println("Voici l'automate : " + name);
//		System.out.println("Etat initial : " + initial_state.name());
//		for(State s : states) {
//			s.print();
//		}
		
		System.out.println(this.name + "(" + this.initial_state.name + "){\n");
		for(State s : states) {
			s.print();
		}
		System.out.println("}\n");

		
	}

	// step effectue un pas dans l'automate. Il effectue une action et mets à jour l'état courant.
	// Return : Le nouveau état courant
//	public State step(Entity e) { // Entity e
//		
//		// On itère sur les transitions de l'état actuel
//		ArrayList<Transition> list_trans = e.current.GetTransitions();
//		Iterator<Transition> iter_Transition = list_trans.iterator();
//		
//		while (iter_Transition.hasNext()) {
//			
//			Transition current_Transition = (Transition)iter_Transition.next();
//			
//			if (current_Transition.GetCondition().eval()) {
//				
//				int hasard = (int) (java.lang.Math.random()*100);
//				
//				int current_luck = 0;
//				
//				// Quand une condition est vrai, on itère sur ses actions
//				Iterator<Action> iter_Action = current_Transition.GetActions().iterator();
//				while (iter_Action.hasNext()) {
//					Action current_Action = iter_Action.next();
//					
//					current_luck += current_Action.GetLuck();
//					// L'action désigner par le tire au sort est exécutée et l'état actuel est mis à jour
//					if (hasard < current_luck) {
//						current_Action.exec(e);
//						e.current = current_Transition.GetDestination();
//						break;
//					}
//					
//				}
//				break;
//			}
//		}
//		return e.current;
//	}
	
}
