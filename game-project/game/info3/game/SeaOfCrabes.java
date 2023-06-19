package info3.game;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import automate.AutomateLoader;
import info3.game.modele.GameModele;
import info3.game.sound.SoundTool;
import info3.game.vue.GameView;
import info3.game.vue.SpriteLoader.SpriteLoader;

public class SeaOfCrabes {

	static GameModele game;
	static GameView view;
	static Controller controller;

	public static void main(String[] args) {
		try {
			System.out.println("Game starting...");
			AutomateLoader.initAutomateLoader(); // Initialisation des automates
			SpriteLoader.initAllSprites();
			controller = new Controller();
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://sea-of-crabes-db-do-user-12793941-0.b.db.ondigitalocean.com:25060/defaultdb", "doadmin",
					"AVNS_Tt0S0KQowpxwevfrqPG");
			System.out.println("Connected to database");
			// here sonoo is database name, root is username and password
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery("SELECT * FROM Score");
			while (rs.next())
				System.out.println("OK");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
