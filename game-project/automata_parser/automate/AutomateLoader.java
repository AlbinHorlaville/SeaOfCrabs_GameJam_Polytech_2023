package automate;
import java.util.HashMap;
import java.util.LinkedList;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import visitor.Visitor;

public class AutomateLoader {
	
	private static HashMap<String,Automate> automateLoader; 
	
	public AutomateLoader() {
		try {
			automateLoader = new HashMap<>();
			AST ast = AutomataParser.from_file(System.getProperty("user.dir") + "/../gal/exemples/exemples.gal");
			Visitor v = new Visitor();
			LinkedList<Object> l = (LinkedList<Object>) ast.accept(v);
			for(Object temp : l) {
				Automate a = (Automate)temp;
				automateLoader.put(a.name, a);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static Automate findAutomate(String name){
		return automateLoader.get(name);
	}
}
