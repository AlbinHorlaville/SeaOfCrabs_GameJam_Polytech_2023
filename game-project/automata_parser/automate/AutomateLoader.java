package automate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import info3.game.automata.ast.AST;
import info3.game.automata.parser.AutomataParser;
import info3.game.modele.GameEntity;
import visitor.Visitor;

public class AutomateLoader {

	private static HashMap<GameEntity, Automate> automateLoader;

	private static ArrayList<String> list_Automate_name; // Use for View Config Automate

	private static LinkedList<Object> l;

	public static void initAutomateLoader() {
		try {
			AST ast = AutomataParser.from_file("./automata_parser/automata.gal");
			Visitor v = new Visitor();
			l = (LinkedList<Object>) ast.accept(v);

			initHashMap(l);
			initAutomateNameListe(l);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initAutomateNameListe(LinkedList<Object> l) {
		list_Automate_name = new ArrayList<>();
		for (Object temp : l) {
			Automate tempAuto = (Automate) temp;
			list_Automate_name.add(tempAuto.name);
		}
	}

	/**
	 * 
	 * @param enen ( Afin d'ï¿½tre compatible avec l'implï¿½mentation
	 *             prï¿½cï¿½dente)
	 * @return
	 */
	public static Automate findAutomate(GameEntity entity) {
		Automate res = automateLoader.get(entity);
		if (res == null)
			new Exception("AUTOMATA BAD CONFIG : SHOULD NOT APPEND").printStackTrace();
		return res;
	}

	/**
	 * Permet d'initialiser les automates selon si il y a un fichier de config ou
	 * non
	 * 
	 * @param l
	 * @throws Exception
	 */
	private static void initHashMap(LinkedList<Object> l) {
		File config = new File("resources/CONFIG");
		try {
			Scanner myReader = new Scanner(config);
			initWithConfig(myReader, l);
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
	 * 
	 * @param myReader
	 * @param l
	 * @throws Exception
	 */
	private static void initWithConfig(Scanner myReader, LinkedList<Object> l) throws Exception {
		automateLoader = new HashMap<>();
		while (myReader.hasNextLine()) {
			String line = myReader.nextLine();
			String[] words = line.trim().split(":");
			Automate a = getAutomateByName(words[1]);
			if (a == null) // Automate inconnue
				throw new Exception("BAD FILE CONFIG : NO AUTOMATA FOR " + words[1] + "\nDEFAULT CONFIG USED !!");
			automateLoader.put(GameEntity.valueOf(words[0]), a);
		}

		// Test si toute les entitées on bien un automate
		// Si ce n'est pas le cas, init without config
		for (GameEntity entity : GameEntity.values()) {
			if (!automateLoader.containsKey(entity)) {
				throw new Exception(
						"BAD FILE CONFIG : NO AUTOMATA FOR " + entity.toString() + "\nDEFAULT CONFIG USED !!");
			}
		}
	}

	/**
	 * Return the corresponding Automate object from name
	 * 
	 * @param name
	 * @return Automate or null
	 */
	private static Automate getAutomateByName(String name) {
		for (Object temp : l) {
			Automate tempAuto = (Automate) temp;
			if (tempAuto.name.equals(name)) {
				return tempAuto;
			}
		}
		return null;
	}

	/**
	 * Return the corresponding association <String,Automate> where String.keySet()
	 * = name
	 * 
	 * @param name
	 * @return Object[] or null
	 */
	public static Object[] getHashMapValueByName(GameEntity entity) {
		return new Object[] { entity.toString(), findAutomate(entity).name };
	}

	/**
	 * Init Automate Without Config File
	 * 
	 * @param l
	 */
	private static void initWithoutConfig(LinkedList<Object> l) {
		automateLoader = new HashMap<>();

		for (GameEntity entity : GameEntity.values()) {
			for (Object temp : l) {
				Automate a = (Automate) temp;
				if (a.name.equals(entity.toString())) {
					automateLoader.put(entity, a);
					break;
				}
			}
		}
	}

	public static void updateConfig(Object[][] data) {
		String filePath = "resources/CONFIG"; // Specify the path to your file
		automateLoader = new HashMap<>();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

			for (int i = 0; i < data.length; i++) {
				writer.write(data[i][0].toString() + ":" + data[i][1].toString());
				writer.newLine();
			}

			writer.close();
			initHashMap(l);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getList_Automate_name() {
		return list_Automate_name;
	}

	public static HashMap<GameEntity, Automate> getAutomateLoader() {
		return automateLoader;
	}

}
