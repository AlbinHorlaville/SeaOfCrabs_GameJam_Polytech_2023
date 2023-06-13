package automate;
import java.util.HashMap;
import java.util.LinkedList;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import visitor.Visitor;

public class AutomateLoader {
	
	private final static String automatePlayer = "Player";
	
	private static HashMap<String,Automate> automateLoader; 
	
	
	private static Automate findAutomate(String name) throws Exception {
		AST ast = AutomataParser.from_file(System.getProperty("user.dir") + "/../gal/exemples/exemples.gal");
		Visitor v = new Visitor();
		LinkedList<Object> l = (LinkedList<Object>) ast.accept(v);
		for(Object a : l) {
			if(((automate.Automate)a).name.equals(name) ) {
				return (automate.Automate)a;
			}
		}
		return null;
	}
	
	public static void initAutomateLoader() throws Exception {
		automateLoader = new HashMap<>();
		automateLoader.put(automatePlayer, findAutomate(automatePlayer));
	}
	
	public static Automate getPiratePlayerAutomate() {
		return automateLoader.get(automatePlayer);
	}
}
