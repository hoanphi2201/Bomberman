/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities.character.enemy.ai;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AIHigh extends AI {
    private AIMedium aiMedium;
    private AIEvade aiEvade;

    public AIHigh(Bomber bomber, Enemy e, Board b) {
        aiMedium = new AIMedium(bomber, e);
        aiEvade = new AIEvade(bomber, e, b);
    }

    @Override
    public int calculateDirection() {
        int directionEvade = aiEvade.calculateDirection();
        if (directionEvade != -1) return directionEvade;
        return aiMedium.calculateDirection();
    }
}