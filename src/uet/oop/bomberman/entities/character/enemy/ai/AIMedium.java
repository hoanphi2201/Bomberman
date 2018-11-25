package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Wall;

public class AIMedium extends AI {

    Bomber _bomber;
    Enemy _e;
    Board _board;
    private AILow AiLow;

    public AIMedium(Bomber bomber, Enemy e) {
        _bomber = bomber;
        _e = e;
        AiLow = new AILow();
        
    }

    @Override
    public int calculateDirection() {
        if (_bomber == null) {
            return random.nextInt(4);
        }
        int vertical = random.nextInt(4);

        if (vertical == 1) {
            int v = calculateRowDirection();
            if (v != -1) {
                return v;
            } else {
                return calculateColDirection();
            }

        } else {
            int h = calculateColDirection();

            if (h != -1) {
                return h;
            } else {
                return calculateRowDirection();
            }

        }
    }
    protected int calculateColDirection() {
        if (_bomber.getXTile() < _e.getXTile()) {
            return 3;
        } else if (_bomber.getXTile() > _e.getXTile()) {
            return 1;
        }

        return -1;
    }

    protected int calculateRowDirection() {
        if (_bomber.getYTile() < _e.getYTile()) {
            return 0;
        } else if (_bomber.getYTile() > _e.getYTile()) {
            return 2;
        }
        return -1;
    }

}
