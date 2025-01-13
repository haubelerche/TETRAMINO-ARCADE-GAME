package Main;

import javax.swing.*;
import java.awt.*;


public class Panel extends JPanel implements Runnable {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    final int FPS = 60;
    Thread gameThread;
    public static playManager pm;
    public static SoundTracks music = new SoundTracks();
    public static SoundTracks soundEffect = new SoundTracks();



    public Panel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(new Handler());
        pm = new playManager();
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
        music.play(0,true);
        music.loop();
    }
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >=1) {
                update();
                repaint();
                if (music.musicClip != null) {
                    music.loop();
                }
                delta--;
            }
        }
    }
    private void update() {
        if(Handler.pausePressed == false && pm.gameOver == false) {
            pm.update();
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        pm.draw(g2);
    }
}
