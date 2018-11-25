package uet.oop.bomberman.graphics;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;

import java.awt.*;

/**
 * Xử lý render cho tất cả Entity và một số màn hình phụ ra Game Panel
 */
public class Screen {
	protected int _width, _height;
	public int[] _pixels;
	private int _transparentColor = 0xffff00ff;
	
	public static int xOffset = 0, yOffset = 0;
	
	public Screen(int width, int height) {
		_width = width;
		_height = height;
		
		_pixels = new int[width * height];
	}
	
	public void clear() {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = 0;
		}
	}
	
	public void renderEntity(int xp, int yp, Entity entity) { //save entity pixels
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < entity.getSprite().getSize(); y++) {
			int ya = y + yp; //add offset
			for (int x = 0; x < entity.getSprite().getSize(); x++) {
				int xa = x + xp; //add offset
				if(xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break; //fix black margins
				if(xa < 0) xa = 0; //start at 0 from left
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
				if(color != _transparentColor) _pixels[xa + ya * _width] = color;
			}
		}
	}
	
	public void renderEntityWithBelowSprite(int xp, int yp, Entity entity, Sprite below) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < entity.getSprite().getSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < entity.getSprite().getSize(); x++) {
				int xa = x + xp;
				if(xa < -entity.getSprite().getSize() || xa >= _width || ya < 0 || ya >= _height) break; //fix black margins
				if(xa < 0) xa = 0;
				int color = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
				if(color != _transparentColor) 
					_pixels[xa + ya * _width] = color;
				else
					_pixels[xa + ya * _width] = below.getPixel(x + y * below.getSize());
			}
		}
	}
	
	public static void setOffset(int xO, int yO) {
		xOffset = xO;
		yOffset = yO;
	}
	
	public static int calculateXOffset(Board board, Bomber bomber) {
		if(bomber == null) return 0;
		int temp = xOffset;
		
		double BomberX = bomber.getX() / 16;
		double complement = 0.5;
		int firstBreakpoint = board.getWidth() / 4;
		int lastBreakpoint = board.getWidth() - firstBreakpoint;
		
		if( BomberX > firstBreakpoint + complement && BomberX < lastBreakpoint - complement) {
			temp = (int)bomber.getX()  - (Game.WIDTH / 2);
		}
		
		return temp;
	}
	
	public void drawEndGame(Graphics g, int points) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());
		
		Font font = new Font("Arial", Font.PLAIN, 20 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("GAME OVER", getRealWidth(), getRealHeight(), g);
		
		font = new Font("Arial", Font.PLAIN, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.yellow);
		drawCenteredString("POINTS: " + points, getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE , g);
                drawCenteredString("Enter To Play" , getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE + Game.SCALE*23, g);
	}
        public void drawWinGame(Graphics g, int points) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());
		
		Font font = new Font("Arial", Font.PLAIN, 20 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("VICTORY", getRealWidth(), getRealHeight(), g);
		
		font = new Font("Arial", Font.PLAIN, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.yellow);
		drawCenteredString("POINTS: " + points, getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE , g);
                drawCenteredString("Enter To Play" , getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE + Game.SCALE*23, g);
	}
        public void drawExitGame(Graphics g, int points) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());
		
		Font font = new Font("Arial", Font.PLAIN, 20 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("DO YOU WANT EXIT ?", getRealWidth(), getRealHeight(), g);
		
		font = new Font("Arial", Font.PLAIN, 10 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.yellow);
		drawCenteredString("POINTS: " + points, getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE , g);
                drawCenteredString("Enter R To Resume" , getRealWidth(), getRealHeight() + (Game.TILES_SIZE * 2) * Game.SCALE + Game.SCALE*23, g);
	}

	public void drawChangeLevel(Graphics g, int level) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());
		
		Font font = new Font("Arial", Font.PLAIN, 20 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("LEVEL " + level, getRealWidth(), getRealHeight(), g);
		
	}
	public void drawStartGame(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getRealWidth(), getRealHeight());
 		Font font =  new Font("Times New Roman", Font.PLAIN, Game.SCALE * 30);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("Bomberman", getRealWidth() , getRealHeight() - Game.SCALE * 106, g);
 		font = new Font("Times New Roman", Font.PLAIN, 40);
                
                Font font1 =  new Font("Times New Roman", Font.PLAIN, Game.SCALE * 13);
               
 		font = new Font("Times New Roman", Font.PLAIN, 35);
                g.setColor(Color.WHITE);
                g.fillRoundRect(getRealWidth() / 2 - Game.SCALE * 100 /2, Game.SCALE * 87, Game.SCALE * 100, Game.SCALE * 13, Game.SCALE * 3, Game.SCALE * 3);
                g.setFont(font1);
		g.setColor(Color.BLACK);
		drawCenteredString("Start ", getRealWidth() , Game.SCALE * 186, g);
                
                g.setColor(Color.WHITE);
                g.fillRoundRect(getRealWidth() / 2 - Game.SCALE * 100 /2, Game.SCALE * 106, Game.SCALE * 100, Game.SCALE * 13, Game.SCALE * 3, Game.SCALE * 3);
                g.setFont(font1);
		g.setColor(Color.BLACK);
		drawCenteredString("Continue ", getRealWidth() , Game.SCALE * 225, g);
                
                g.setColor(Color.WHITE);
                g.fillRoundRect(getRealWidth() / 2 - Game.SCALE * 100 /2, Game.SCALE * 124, Game.SCALE * 100, Game.SCALE * 13, Game.SCALE * 3, Game.SCALE * 3);
                g.setFont(font1);
		g.setColor(Color.BLACK);
		drawCenteredString("Manual", getRealWidth() , Game.SCALE * 260, g);
                
                g.setColor(Color.WHITE);
                g.fillRoundRect(getRealWidth() / 2 - Game.SCALE * 100 /2, Game.SCALE * 142, Game.SCALE * 100, Game.SCALE * 13, Game.SCALE * 3, Game.SCALE * 3);
                g.setFont(font1);
		g.setColor(Color.BLACK);
		drawCenteredString("Exit ", getRealWidth() , Game.SCALE * 297, g);
                
	}
	public void drawPaused(Graphics g) {
		Font font = new Font("Arial", Font.PLAIN, 20 * Game.SCALE);
		g.setFont(font);
		g.setColor(Color.white);
		drawCenteredString("PAUSED", getRealWidth(), getRealHeight(), g);
		
	}

	public void drawCenteredString(String s, int w, int h, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	 }
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public int getRealWidth() {
		return _width * Game.SCALE;
	}
	
	public int getRealHeight() {
		return _height * Game.SCALE;
	}
}
