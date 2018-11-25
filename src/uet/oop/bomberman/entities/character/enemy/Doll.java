/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium2;
import uet.oop.bomberman.entities.character.enemy.ai.AIHigh;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium1;
import uet.oop.bomberman.graphics.Sprite;

/**
 *
 * @author phixuanhoan
 */
public class Doll extends Enemy{
     public Doll(int x, int y, Board board) {
        super(x, y, board, Sprite.doll_dead, Game.getBomberSpeed(), 200);

        _sprite = Sprite.doll_left1;
            
        _ai = new AIMedium2(_board.getBomber(), this, _board);
        _direction = _ai.calculateDirection();
    }
     @Override
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, _animate, 60);
                } else {
                    _sprite = Sprite.doll_left1;
                }
                break;
            case 2:
            case 3:
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, _animate, 60);
                } else {
                    _sprite = Sprite.doll_left1;
                }
                break;
        }
    }
}