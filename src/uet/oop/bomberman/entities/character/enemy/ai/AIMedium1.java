package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AIMedium1 extends AI {

    Bomber _bomber;
    Enemy _e;
    private AILow AiLow;

    public AIMedium1(Bomber bomber, Enemy e) {
        _bomber = bomber;
        _e = e;
        AiLow = new AILow();
        
    }
    @Override
    public int calculateDirection() {
        // @todo: cài đặt thuật toán tìm đường đi
        int random = (int) (Math.random() * 3);
        if (random == 0) return calculateColDirection();
        if (random == 1) return calculateRowDirection();

        return AiLow.calculateDirection();
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
