package uet.oop.bomberman.entities.tile;

import java.util.logging.Level;
import java.util.logging.Logger;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Tile {

    protected Board _board;

    public Portal(int x, int y, Board board, Sprite sprite) {
        super(x, y, sprite);
        _board = board;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi vào
        if (e instanceof Bomber) {
            if (_board.detectNoEnemies() == false) {
                return false;
            }
            try {
                if (e.getXTile() == getX() && e.getYTile() == getY()) {
                    if (_board.detectNoEnemies()) {
                        Sound s2 = new Sound();
                        s2.setFile("res/sound/stageComplete.wav", 9);
                        s2.play(9);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        _board.nextLevel();
                    }
                }
            } catch (Exception ex) {
                _board.winGame();
            }

            return true;
        }
        return false;
    }

}
