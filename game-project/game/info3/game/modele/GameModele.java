package info3.game.modele;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

import info3.game.DAO;
import info3.game.Controller;
import info3.game.GameState;
import info3.game.Score;
import info3.game.SeaOfCrabes;
import info3.game.User;
import info3.game.modele.MoveableEntityClass.BoatPlayer;
import info3.game.modele.MoveableEntityClass.CrabKing;
import info3.game.modele.MoveableEntityClass.Kraken;
import info3.game.modele.MoveableEntityClass.Perroquet;
import info3.game.modele.MoveableEntityClass.PiratePlayer;
import info3.game.modele.MoveableEntityClass.Ship;
import info3.game.modele.MoveableEntityClass.Sword;
import info3.game.modele.MoveableEntityClass.Tentacle;
import info3.game.modele.StillEntityClass.CloudCluster;
import info3.game.modele.StillEntityClass.CrabLair;
import info3.game.modele.StillEntityClass.RedCross;
import info3.game.modele.StillEntityClass.SeaTreasure;
import info3.game.modele.StillEntityClass.Tree;
import info3.game.modele.map.EnumTiles;
import info3.game.modele.map.Map;
import info3.game.modele.map.MapSection;
import info3.game.modele.map.Tiles;
import info3.game.sound.BackgroundMusic;
import info3.game.sound.SoundEffect;
import info3.game.sound.SoundTool;
import info3.game.vue.GameView;
import info3.game.vue.avatar.BoatPlayerAvatar;
import info3.game.vue.avatar.Player1;
import info3.game.vue.avatar.Player2;
import info3.game.vue.avatar.RedCrossAvatar;
import info3.game.vue.avatar.SwordAvatar;
import info3.game.vue.view.BeforePlayingView;

public class GameModele {

	GameView gameview;

	public static ArrayList<Entity> entities = new ArrayList<>();

	public static ArrayList<Ship> seaEnnemie = new ArrayList<>();
	
	public static CrabKing king;

	public static PiratePlayer player1;

	public static PiratePlayer player2;

	public static BoatPlayer pirateBoat;

	public static Perroquet perroquet;

	public static GameTimer timer;

	public static boolean onSea = true;

	public static boolean solo = true;

	public static Map map;

	public static int seed;
	public Random rand = new Random();
	public static int section;

	public static int currentSection;

	int waveTick = 0;

	GameState currentState;

	public static User currentUser;
	public static Score currentScore;
	public static Score bestUserScore;
	public static boolean isUserBestScore;
	
	

	private static File userFile, scoreFile;

	public GameModele() throws Exception {

		if (SeaOfCrabes.connectedToDatabase) { // connection to database succeed

			userFile = new File("resources/.USER"); // file which contains the username
			scoreFile = new File("resources/.SCORE"); // file which contains the best score of the user

			if (!userFile.exists()) { // no user created

				currentState = GameState.Utilisateur; // we create a user through SetUpUserView
				bestUserScore = null;
			} else if (!scoreFile.exists() && userFile.exists()) { // no score created

				createScore(); // we create a new score file
				currentUser = new User(readUsernameFromFile()); // we load the current user from file
				currentState = GameState.Menu; // we display the menu
				bestUserScore = currentScore;
			} else if (userFile.exists() && scoreFile.exists()){ // both user and score are created so we only need to load them by reading files

				currentUser = new User(readUsernameFromFile()); // we load the current user from file
				currentScore = new Score(readScoreFromFile()); // we load the current score from file
				currentState = GameState.Menu; // we display the menu
				bestUserScore = currentScore;
			} else {
				currentState = GameState.Menu; // there is no user/score in local mode
			}

		} else { // local mode

			currentState = GameState.Menu; // there is no user/score in local mode

		}

	}

	public void setGameview(GameView gameview) {
		this.gameview = gameview;
	}

	private static String readUsernameFromFile() {
		Scanner myReader;
		try {
			myReader = new Scanner(userFile);
			if (myReader.hasNextLine()) {
				return myReader.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String[] readScoreFromFile() {
		Scanner myReader;
		String[] score;
		try {
			myReader = new Scanner(scoreFile);
			if (myReader.hasNextLine()) {
				score = myReader.nextLine().split(":");
			} else {
				score = new String[] { "noscore" };
			}
			return score;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean createUser(String name) {
		if (DAO.getInstance().addUser(name)) {
			try {
				if (userFile.createNewFile()) {
					BufferedWriter writer = new BufferedWriter(new FileWriter(userFile.getPath()));
					writer.write(name);
					writer.close();
					currentUser = new User(name);
					userFile.setWritable(false);
				}
				if (createScore()) {
					return true;
				} else {
					return false;
				}
			} catch (IOException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	private static boolean createScore() {
		try {
			if (scoreFile.createNewFile()) {
				currentScore = null;
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
	}

	private static void updateScoreFile() {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(scoreFile.getPath()));
			writer.write(timer.toSQLStringFormat());
			writer.close();
			currentScore = new Score(timer.getHeures(), timer.getMinutes(), timer.getSecondes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tick(long elapsed) {
		if (currentState == GameState.Jeu) {
			
			if (this.player1.invincible) {
				this.player1.updateInvincible();
			}
			
			if (this.pirateBoat.invincible) {
				this.pirateBoat.updateInvincible();
			}
			
			if (this.onSea) {
				this.currentSection = this.pirateBoat.getCurrentSection();
				if (this.map.getTileUnderEntity(this.pirateBoat.getCenterX(), this.pirateBoat.getCenterY())
						.isWaterDamaging()) {
					this.pirateBoat.takeDamage(20);
				}
				
				if (this.map.getTileUnderEntity(this.pirateBoat.getCenterX(), this.pirateBoat.getCenterY())
						.isPoison()) {
					this.pirateBoat.takePoison();
				}
			}
			this.map.updateDamagingTick();
			if (waveTick++ == 10) {
				map.cicleWaveNorth();
				waveTick = 0;
			}
		}
		ArrayList<Entity> tempEntities = (ArrayList) entities.clone();
		for (Entity entity : tempEntities) {
			if (entity instanceof CloudCluster) {
				for (Entity cloud : ((CloudCluster) entity).getClouds()) {
					cloud.step();
					cloud.tick(elapsed);
				}
			} else {
				entity.step();
				entity.tick(elapsed);
			}
		}

		ArrayList<Entity> newEntities = new ArrayList<>();
		for (Entity entity : entities) {
			if (entity instanceof CloudCluster) {
				for (Entity cloud : ((CloudCluster) entity).getClouds()) {
					if (!cloud.current_state.isDead())
						newEntities.add(cloud);
				}
			} else {
				if (!entity.current_state.isDead())
					newEntities.add(entity);
			}
		}
		entities = newEntities;
	}

	public GameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GameState state) {
		this.currentState = state;
		this.gameview.update_view(state);
	}

	public void start() throws Exception {
		seed = rand.nextInt();
		if (currentState == GameState.AvantJeu) {
			SoundTool.changeBackgroundMusic(BackgroundMusic.Game);
			setCurrentState(GameState.Jeu);

			map = new Map(seed, section);

			if (!solo) {
				player2 = new PiratePlayer(GameEntity.PlayerMulti2);
				player2.setAvatar(new Player2(player2));
				player2.setWeapon(BeforePlayingView.weapon2);
				BeforePlayingView.weapon2.setPlayer(player2);
				entities.add(BeforePlayingView.weapon2);
				BeforePlayingView.weapon2.setAvatar(new SwordAvatar(BeforePlayingView.weapon2));

				player1 = new PiratePlayer(GameEntity.PlayerMulti1);
				player1.setAvatar(new Player1(player1));
				player1.setWeapon(BeforePlayingView.weapon1);
				BeforePlayingView.weapon1.setPlayer(player1);
				entities.add(BeforePlayingView.weapon1);
				BeforePlayingView.weapon1.setAvatar(new SwordAvatar(BeforePlayingView.weapon1));

			} else {
				player1 = new PiratePlayer(GameEntity.Player1);
				player1.setAvatar(new Player1(player1));
				player1.setWeapon(BeforePlayingView.weapon1);
				BeforePlayingView.weapon1.setPlayer(player1);
				entities.add(BeforePlayingView.weapon1);
				BeforePlayingView.weapon1.setAvatar(new SwordAvatar(BeforePlayingView.weapon1));
			}

			perroquet = BeforePlayingView.perroquet;
			if (perroquet != null)
				GameModele.entities.add(perroquet);

			pirateBoat = new BoatPlayer(
					map.getMap()[0].getTiles()[this.map.getSectionHeight() - 13][map.getSectionWidth() / 2].getX(),
					map.getMap()[0].getTiles()[this.map.getSectionHeight() - 13][map.getSectionWidth() / 2].getY());
			pirateBoat.setAvatar(new BoatPlayerAvatar(pirateBoat));
			GameModele.entities.add(pirateBoat);

			map = new Map(seed, section);

			genereEntity(map);
			if (perroquet != null) {
				perroquet.setX(GameModele.pirateBoat.getX());
				perroquet.setY(GameModele.pirateBoat.getY());
			}
		}
	}

	public void param() throws IOException {
		if (currentState == GameState.Menu) {
			setCurrentState(GameState.Parametre);
		}
	}

	public void credits() throws IOException {
		if (currentState == GameState.Menu) {
			setCurrentState(GameState.Credits);
		}
	}

	public void score() throws IOException {
		if (currentState == GameState.Menu) {
			setCurrentState(GameState.Score);
		}
	}

	public void commandes() throws IOException {
		if (currentState == GameState.AvantJeu) {
			setCurrentState(GameState.Commandes);
		}
	}

	public void beforePlaying() throws IOException {
		if (currentState == GameState.ChoixGameplay) {
			setCurrentState(GameState.AvantJeu);
		}
	}

	public void choiceGameplay() throws IOException {
		if (currentState == GameState.Menu) {
			setCurrentState(GameState.ChoixGameplay);
		}
	}

	public static int getCurrentPlayerX() {
		if (onSea) {
			return pirateBoat.getX();
		} else {
			if (solo) {
				return player1.getX();
			} else {
				return (player1.getX() + player2.getX()) / 2;
			}
		}
	}

	public static int getCurrentPlayerY() {
		if (onSea) {
			return pirateBoat.getY();
		} else {
			if (solo) {
				return player1.getY();
			} else {
				return (player1.getY() + player2.getY()) / 2;
			}
		}
	}

	void genereEntity(Map map) throws IOException {
		
		

		System.out.println("Seed : " + seed);

		Kraken kraken = new Kraken();
		int tentacle_number = 0;

		int nbSection = map.getNbSection();
		int mapWidth = map.getSectionWidth();
		int mapHeight = map.getSectionHeight();

		Tiles[][] tiles;

		for (int k = 0; k < nbSection; k++) {
			boolean crab = false; // Boolean to spawn only once
			boolean treasure = false; // Boolean to spawn only once

			MapSection section = map.getMap()[k];
			tiles = section.getTiles();

			for (int i = 0; i < mapHeight; i++) {
				for (int j = 0; j < mapWidth; j++) {
					Tiles current = tiles[i][j];

					if (!current.isBasicTiles()) {
						Entity newEntity;
						if (treasure == false && current.isTreasur()) {
							treasure = true;
							newEntity = new RedCross(map.getMap()[k]);
							newEntity.setLocation(current.getX(), current.getY());
							newEntity.setAvatar(new RedCrossAvatar(newEntity)); // TODO Mettre dans le constructeur
							entities.add(newEntity);
						} else if (crab == false && current.isSwpaner()) {
							crab = true;
							newEntity = new CrabLair(k, section, current.getX(), current.getY()); // Créer 10
																									// crabes
																									// de niveau k
																									// (le numéro
																									// //
																									// de section)
																									// avec 20
																									// points de vie
							// newEntity.setLocation(Current.getX(),Current.getY());
							entities.add(newEntity);
						} else if (current.isTree()) {
							newEntity = new Tree(k);
							newEntity.setLocation(current.getX(), current.getY());
							entities.add(newEntity);
						} else if (current.isSeaChest()) {
							newEntity = new SeaTreasure(section, current.getX(), current.getY());// de section) avec
																									// 20 points de
																									// vie

							entities.add(newEntity);
							newEntity = new CloudCluster(current.getX() - 200, current.getY() - 200, map.getMap()[k]);
							entities.add(newEntity);
						} else if (current.isCloud()) {

							newEntity = new CloudCluster(current.getX(), current.getY(), map.getMap()[k]); // Créer 10
																											// crabes de
																											// niveau k
							// (le
							// numéro
							entities.add(newEntity);
						} else if (current.isBoatEnnemi()) {
							newEntity = new Ship(section);
							newEntity.setLocation(current.getX(), current.getY());
							entities.add(newEntity);
							this.seaEnnemie.add((Ship) newEntity);
						} else if (current.getType() == EnumTiles.CRAB_KING) {
							king = new CrabKing(k, 1500, current.getX(), current.getY(), 200); // TODO CHANGE PARAM
							GameModele.entities.add(king);
							// entities.add(newEntity);
						} else if (current.getType() == EnumTiles.KRAKEN_TENTACLE) {
							kraken.addTentacle(current.getX(), current.getY(), tentacle_number++);
						}
					}

				}
			}
		}
	}

	/**
	 * Fonction pour partie perdu
	 */
	public void gameover() {
		reset();
		SoundTool.playSoundEffect(SoundEffect.Defeat, 0);
		gameview.getGame().setCurrentState(GameState.GameOver);
	}

	/**
	 * Compare le score réalisé avec celui du fichier .SCORE
	 * 
	 * @return
	 */
	public boolean checkScore() {
		if (currentScore == null) {
			return true;
		} else {
			return (GameModele.timer.getHeures() < currentScore.getHeures())
					|| (GameModele.timer.getHeures() == currentScore.getHeures()
							&& GameModele.timer.getMinutes() < currentScore.getMinutes())
					|| (GameModele.timer.getHeures() == currentScore.getHeures()
							&& GameModele.timer.getMinutes() == currentScore.getMinutes()
							&& GameModele.timer.getSecondes() < currentScore.getSecondes());
		}
	}

	/**
	 * Fonction pour la victoire
	 */
	public void victory() {
		reset();
		SoundTool.playSoundEffect(SoundEffect.Victory, 0);
		gameview.getGame().setCurrentState(GameState.Victory);
		if (SeaOfCrabes.connectedToDatabase) {
			if (checkScore()) {

				if (GameModele.solo) {
					DAO.getInstance().updateScoreSolo(currentUser, GameModele.timer.toSQLStringFormat(), seed);
					updateScoreFile();
				} /*
					 * else { DAO.getInstance().updateScoreDuo(currentUser,
					 * GameModele.timer.toSQLStringFormat(), seed); }
					 */
			}
		}
	}

	public static void reset() {
		SoundTool.changeBackgroundMusic(BackgroundMusic.MainMenu);
		entities.clear();
		timer.resetTimer();
		onSea = true;
		pirateBoat = null;
	}
}
