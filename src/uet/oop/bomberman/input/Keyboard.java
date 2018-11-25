package uet.oop.bomberman.input;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import uet.oop.bomberman.Sound;

/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[120]; //120 is enough to this game
    public boolean up, down, left, right, space, newgame, resume, exit, pause, yes, no;
    Sound s;
    Sound s2;
    public Timer timer = new javax.swing.Timer(300, (ActionEvent e) -> {
        s = new Sound();
        s.setFile("res/sound/soundStep.wav",5);
        s.play(5);
     });
     public Timer timer2 = new javax.swing.Timer(300, (ActionEvent e) -> {
        s2 = new Sound();
        s2.setFile("res/sound/step2.wav",8);
        s2.play(8);
     });
    
    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_X];
        
        if(keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W] || keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S] ) {
           timer2.start();
        }
        if(keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A] || keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]) {
            timer.start();
        }
        

        newgame = keys[KeyEvent.VK_ENTER];
        exit = keys[KeyEvent.VK_ESCAPE];
        resume = keys[KeyEvent.VK_R];
        pause = keys[KeyEvent.VK_P];
        yes = keys[KeyEvent.VK_Y];
        no = keys[KeyEvent.VK_N];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        timer.stop();
        timer2.stop();
        keys[e.getKeyCode()] = false;

    }

}
