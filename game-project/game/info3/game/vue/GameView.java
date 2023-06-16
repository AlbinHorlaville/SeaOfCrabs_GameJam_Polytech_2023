package info3.game.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import info3.game.Controller;
import info3.game.GameState;
import info3.game.graphics.GameCanvas;
import info3.game.modele.GameModele;
import info3.game.sound.RandomFileInputStream;
import info3.game.sound.SoundTool;
import info3.game.vue.avatar.Avatar;
import info3.game.vue.view.BeforePlayingView;
import info3.game.vue.view.GameModeView;
import info3.game.vue.view.CreditsView;
import info3.game.vue.view.MenuView;
import info3.game.vue.view.PlayingView;
import info3.game.vue.view.ScoreView;
import info3.game.vue.view.SettingsView;
import info3.game.vue.view.View;
import info3.game.vue.view.ControlsView;

public class GameView {
	JFrame frame;
	JLabel text;
	GameCanvas canvas;
	GameModele game;
	private View currentView;
	Controller controller;

	HashMap<GameState, View> all_views;

	BufferedImage backgroundImage;
	
	public static Font customFont;

	private long m_textElapsed;
	
	public static int screenWidth;
	public static int screenHeight;

	public GameView(GameModele game, Controller controller) throws IOException {
		try {
			this.game = game;

			this.controller = controller;
			canvas = new GameCanvas(controller);

			SoundTool.initSoundTool(canvas); // INITIALISATION DE L OUTILS DE SON
			SoundTool.playBackgroundMusic();

			// creating the game canvas to render the game,
			// that would be a part of the view in the MVC pattern

			System.out.println("  - creating frame...");
			Dimension d = new Dimension(1024, 768);
			
			this.screenWidth = 1024;
			this.screenHeight = 768;
			
			frame = canvas.createFrame(d);

			System.out.println("  - setting up the frame...");
			File backgroundImageFile = new File("resources/img/background.jpg");
			try {
				backgroundImage = ImageIO.read(backgroundImageFile);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setupFrame();
			
			System.out.println("  - Load font");
			String fontPath = "resources/font/Pixeltype.ttf";

	        try {
	            // Load the font file
	            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));

	            // Register the font with the graphics environment
	            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	            ge.registerFont(customFont);

	        } catch (FontFormatException | IOException e) {
	            e.printStackTrace();
	        }

			System.out.println("  - Init the view...");
			init_view();
			update_view(game.getCurrentState());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GameModele getGame() {
		return game;
	}

	public void setGame(GameModele game) {
		this.game = game;
	}

	public View getCurrentView() {
		return currentView;
	}

	public void setCurrentView(View currentView) {
		this.currentView = currentView;
	}

	private void init_view() throws IOException {
		this.all_views = new HashMap<>();
		this.all_views.put(GameState.Menu, new MenuView(this));
		this.all_views.put(GameState.Jeu, new PlayingView(this));
		this.all_views.put(GameState.Parametre, new SettingsView(this));
		this.all_views.put(GameState.Score, new ScoreView(this));
		this.all_views.put(GameState.Credits, new CreditsView(this));
		this.all_views.put(GameState.Commandes, new ControlsView(this));
		this.all_views.put(GameState.AvantJeu, new BeforePlayingView(this));
		this.all_views.put(GameState.ChoixGameplay, new GameModeView(this));
	}

	public void update_view(GameState state) {
		this.currentView = this.all_views.get(state);
	}

	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */
	private void setupFrame() {

		frame.setTitle("SeaOfCrabs");
		frame.setLayout(new BorderLayout());

		frame.add(canvas, BorderLayout.CENTER);

		text = new JLabel();
		text.setText("Tick: 0ms FPS=0");
		frame.add(text, BorderLayout.NORTH);

		// center the window on the screen
		frame.setLocationRelativeTo(null);

		// make the vindow visible
		frame.setVisible(true);
	}

	/*
	 * This method is invoked almost periodically, given the number of milli-seconds
	 * that elapsed since the last time this method was invoked.
	 */
	public void tick(long elapsed) {

		currentView.tick(elapsed);

		// Update every second
		// the text on top of the frame: tick and fps
		m_textElapsed += elapsed;
		if (m_textElapsed > 1000) {
			m_textElapsed = 0;
			float period = canvas.getTickPeriod();
			int fps = canvas.getFPS();

			String txt = "Tick=" + period + "ms";
			while (txt.length() < 15)
				txt += " ";
			txt = txt + fps + " fps   ";
			text.setText(txt);
		}
	}

	/*
	 * This request is to paint the GameModele Canvas, using the given graphics.
	 * This is called from the GameModeleCanvasListener, called from the
	 * GameModeleCanvas.
	 */
	public void paint(Graphics g) {

		// get the size of the canvas
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		
		/*
		 * if (this.game.getCurrentState() != GameState.Jeu) {
		 * g.drawImage(backgroundImage, 0, 0, 1024, 768, null); }
		 */

		this.currentView.paint(g, width, height);
	}

	public int getWidthCanvas() {
		return canvas.getWidth();
	}

	public int getHeightCanvas() {
		return canvas.getHeight();
	}

}
