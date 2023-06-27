/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package info3.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.KeyBuffered.EasterEggs;
import info3.game.KeyBuffered.KeyBuffered;
import info3.game.graphics.GameCanvasListener;
import info3.game.modele.GameModele;
import info3.game.sound.SoundEffect;
import info3.game.sound.SoundTool;
import info3.game.vue.GameView;
import info3.game.vue.toolkitUI.UIComponent;

public class Controller implements GameCanvasListener {

	private static GameModele gameModele;
	GameView gameView;
	static KeyBuffered buffer = new KeyBuffered(); // Variable indiquant les inputs du clavier actif ( Pour chaque index
													// == le code le touche )
	UIComponent focus; // focus is the UIComponent currently hovered on the game canvas
	EasterEggs eggs;

	public Controller() throws Exception {
		try {
			gameModele = new GameModele();
			gameView = new GameView(gameModele, this);
			gameModele.setGameview(gameView);
			eggs = new EasterEggs();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) { // when the mouse is clicked
		if (focus != null) {
			focus.clicked(e.getX(), e.getY()); // calls to the focus'clicked behavior
			SoundTool.playSoundEffect(SoundEffect.Confirm, 0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (GameModele.onSea && GameModele.pirateBoat != null) {
			GameModele.pirateBoat.startFire(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (focus != null) {
			focus.pressed(e.getX(), e.getY()); // calls to the focus'pressed behavior
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Correction d'un bug : parfois gameView est nul (je ne sais pas pourquoi mais
		// le problème de NullException est maitnenant réglé).
		if (gameView != null) {

			UIComponent newFocus = gameView.getCurrentView().getHoveredComponent(e.getX(), e.getY());

			if (focus != newFocus) {
				if (focus != null) {
					focus.mouseOut(e.getX(), e.getY()); // calls to the focus'mouseOut behavior
				}
				if (newFocus != null) {
					newFocus.mouseIn(e.getX(), e.getY()); // calls to the focus'mouseIn behavior
				}
				focus = newFocus;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.eggs.checker(e.getExtendedKeyCode());
		Controller.buffer.buff(e.getExtendedKeyCode());
		if (focus != null) {
			focus.keyPressed(e); // calls to the focus'keyPressed behavior
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Controller.buffer.unBuff(e.getExtendedKeyCode());
	}

	@Override
	public void tick(long elapsed) {
		if (gameModele != null && gameView != null) {
			gameModele.tick(elapsed);
			gameView.tick(elapsed);
		}
	}

	@Override
	public void paint(Graphics g) {
		if (gameModele != null && gameView != null) {
			gameView.paint(g);
		}
	}

	@Override
	public void windowOpened() {
		if (gameModele != null && gameView != null) {
		}

	}

	@Override
	public void exit() {
		if (gameModele != null && gameView != null) {
		}
	}

	// boolean m_expired;
	@Override
	public void endOfPlay(String name) {
		if (gameModele != null && gameView != null) {
			if (SoundTool.is_background(name))
				SoundTool.playBackgroundMusic();
		}
	}

	@Override
	public void expired() {
		// will force a change of music, after 6s of play
		// System.out.println("Forcing an ealy change of music");
		// m_expired = true;
		// game.loadMusic();
		if (gameModele != null && gameView != null) {
		}
	}

	public static KeyBuffered getBuffer() {
		return buffer;
	}

	public static GameModele getGameModele() {
		return gameModele;
	}

}
