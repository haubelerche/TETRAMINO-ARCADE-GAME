package Main;

import java.awt.*;
import tetramino.*;


public class  playManager {
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    tetramino currentTetra;
    final int TETRA_START_X;
    final int TETRA_START_Y;

    public static int dropTetra = 60; //thả rơi mỗi sec

    public playManager() {
        left_x = (Panel.WIDTH/ 2) - (WIDTH/2); //1280/2 - 360/2 = 460
        right_x = left_x + WIDTH;
        top_y = 50;
       bottom_y = top_y + HEIGHT;
       TETRA_START_X = left_x + (WIDTH/2) - Block.SIZE;
       TETRA_START_Y = top_y + Block.SIZE;

       currentTetra = new tetraL_a();
       currentTetra.setXY(TETRA_START_X, TETRA_START_Y);

    }
    public void update() {
        currentTetra.update();
    }
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);

        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x, y, 200, 200);
        g2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x+60, y+60);

        if(currentTetra != null) {
            currentTetra.draw(g2);
        }
    }
}
