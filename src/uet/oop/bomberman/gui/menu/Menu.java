package uet.oop.bomberman.gui.menu;

import javax.swing.JMenuBar;
import uet.oop.bomberman.gui.Frame;
import uet.oop.bomberman.gui.menu.Game;


public class Menu extends JMenuBar {
	
	public Menu(Frame frame) {
		add( new Game(frame) );
		add( new Options(frame) );
	}
	
}
