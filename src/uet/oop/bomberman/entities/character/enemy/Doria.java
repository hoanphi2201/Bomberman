package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.enemy.ai.AIHigh;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium2;
import uet.oop.bomberman.graphics.Sprite;

public class Doria extends Enemy {

    public Doria(int x, int y, Board board) {
        super(x, y, board, Sprite.doria_dead, Game.getBomberSpeed(), 200);

        _sprite = Sprite.doria_left1;

        _ai = new AIHigh(_board.getBomber(), this, _board);
        _direction = _ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if (_moving)
                    _sprite = Sprite.movingSprite(Sprite.doria_right1, Sprite.doria_right2, Sprite.doria_right3, _animate, 60);
                else
                    _sprite = Sprite.doria_left1;
                break;
            case 2:
            case 3:
                if (_moving)
                    _sprite = Sprite.movingSprite(Sprite.doria_left1, Sprite.doria_left2, Sprite.doria_left3, _animate, 60);
                else
                    _sprite = Sprite.doria_left1;
                break;
        }
    }
}
