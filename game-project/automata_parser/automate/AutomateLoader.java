package automate;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import visitor.Visitor;

public class AutomateLoader {
	
	private static HashMap<String,Automate> automateLoader;
	
	public static void initAutomateLoader() {
		try {
			AST ast = AutomataParser.from_file(System.getProperty("user.dir") + "/../gal/exemples/exemples.gal");			
			Visitor v = new Visitor();
			LinkedList<Object> l = (LinkedList<Object>) ast.accept(v);

			initHashMap(l);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param name ( Afin d'être compatible avec l'implémentation précédente)
	 * @return
	 */
	public static Automate findAutomate(String name){
		Automate res =  automateLoader.get(name);
		if (res==null) new Exception("AUTOMATA BAD CONFIG : SHOULD NOT APPEND").printStackTrace();
		return res;
	}	
	
	/**
	 * Permet d'initialiser les automates selon si il y a un fichier de config ou non
	 * @param l
	 * @throws Exception 
	 */
	private static void initHashMap(LinkedList<Object> l){
		File score = new File("resources/CONFIG");
		try {
			Scanner myReader = new Scanner(score);
			initWithConfig(myReader,l);
			return;
		} catch (FileNotFoundException e) {
			System.out.println("NO FILE AUTOMATE CONFIG FOUND");
			initWithoutConfig(l);
		} catch (Exception e) {
			e.printStackTrace();
			initWithoutConfig(l);
		}	

	}
	
	/**
	 * Init Automate with the CONFIG FILE
	 * @param myReader
	 * @param l
	 * @throws Exception
	 */
	private static void initWithConfig(Scanner myReader, LinkedList<Object> l) throws Exception{
		automateLoader = new HashMap<>();
		while (myReader.hasNextLine()) {
			String line = myReader.nextLine();
			String[] words = line.trim().split(":");
			Automate a = null;
			for(Object temp : l) {
				Automate tempAuto = (Automate)temp;
				if (tempAuto.name.equals(words[1])) {
					a = tempAuto;
					break;
				}
			}
			if (a == null) throw new Exception("BAD FILE CONFIG : NO AUTOMATA FOR " + words[1] + "\nDEFAULT CONFIG USED !!");
			automateLoader.put(words[0],a);
		}
	}
	
	/**
	 * Init Automate Without Config File
	 * @param l
	 */
	private static void initWithoutConfig(LinkedList<Object> l){
		automateLoader = new HashMap<>();
		
		for(Object temp : l) {
			Automate a = (Automate)temp;
			automateLoader.put(a.name, a);
		}
	}
}
