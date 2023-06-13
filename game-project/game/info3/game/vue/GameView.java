package info3.game.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;

import info3.game.Controller;
import info3.game.GameState;
import info3.game.graphics.GameCanvas;
import info3.game.modele.GameModele;
import info3.game.sound.RandomFileInputStream;
import info3.game.sound.SoundTool;
import info3.game.vue.avatar.Avatar;
import info3.game.vue.view.MenuView;
import info3.game.vue.view.PlayingView;
import info3.game.vue.view.View;

public class GameView {
	JFrame frame;
	JLabel text;
	GameCanvas canvas;
	GameModele game;
	View currentView;
	Controller controller;

	HashMap<GameState, View> all_views;

	private long m_textElapsed;

	public GameView(GameModele game, Controller controller) throws IOException {
		this.game = game;
		init_view();
		update_view(game.getCurrentState());
		// creating the game canvas to render the game,
		// that would be a part of the view in the MVC pattern
		this.controller = controller;
		canvas = new GameCanvas(controller);
		
		SoundTool.initSoundTool(canvas); // INITIALISATION DE L OUTILS DE SON
		
		System.out.println("  - creating frame...");
		Dimension d = new Dimension(1024, 768);
		frame = canvas.createFrame(d);

		System.out.println("  - setting up the frame...");
		setupFrame();	
	}

	private void init_view() throws IOException{
		this.all_views = new HashMap<>();
		this.all_views.put(GameState.Menu, new MenuView());
		this.all_views.put(GameState.Jeu, new PlayingView());
	}

	public void update_view(GameState state) {
		this.currentView = this.all_views.get(state);
	}

	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */
	private void setupFrame() {

		frame.setTitle("GameModele");
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


	void changeView() {

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
	 * This request is to paint the GameModele Canvas, using the given graphics. This is
	 * called from the GameModeleCanvasListener, called from the GameModeleCanvas.
	 */
	public void paint(Graphics g) {

		// get the size of the canvas
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);

		this.currentView.paint(g,width,height);
	}
	
}
