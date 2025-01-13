package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Handler implements KeyListener {
    public static boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed;
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            if(pausePressed) {
            pausePressed = false;
            Panel.music.play(0,true);
            Panel.music.loop();
        }else {
                pausePressed = true;
               Panel.music.stop();
            }}
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
