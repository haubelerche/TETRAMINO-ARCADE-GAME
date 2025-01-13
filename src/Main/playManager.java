package Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import tetramino.*;

public class playManager {
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    tetramino currentTetra;
    final int TETRA_START_X;
    final int TETRA_START_Y;
    tetramino nextTetra;
    final int NEXT_TETRA_X;
    final int NEXT_TETRA_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();
    public static int dropTetra = 60; // Thả rơi mỗi giây
    boolean effectCounterOn;
    int effectCounter;
    ArrayList<Integer> effectY = new ArrayList<>();
    boolean gameOver;
    int level = 1;
    int lines;
    int score;




    private tetramino pickTetra() {
        tetramino tetra = null;
        int i = new Random().nextInt(7);

        switch (i) {
            case 0:
                tetra = new tetraL_a();
                break;
            case 1:
                tetra = new tetraL_b();
                break;
            case 2:
                tetra = new tetraT();
                break;
            case 3:
                tetra = new tetraZ1();
                break;
            case 4:
                tetra = new tetraZ2();
                break;
            case 5:
                tetra = new tetraBar();
                break;
            case 6:
                tetra = new tetraSquare();
                break;
        }
        return tetra;
    }

    public playManager() {
        left_x = (Panel.WIDTH / 2) - (WIDTH / 2); // 1280/2 - 360/2 = 460
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;
        TETRA_START_X = left_x + (WIDTH / 2) - Block.SIZE;
        TETRA_START_Y = top_y + Block.SIZE;
        NEXT_TETRA_X = right_x +175;
        NEXT_TETRA_Y = top_y + 500;
        currentTetra = pickTetra();
        currentTetra.setXY(TETRA_START_X, TETRA_START_Y);
        nextTetra = pickTetra();
        nextTetra.setXY(NEXT_TETRA_X, NEXT_TETRA_Y);
    }

    public void update() {
        if(!currentTetra.active) {
            staticBlocks.add(currentTetra.b[0]);
            staticBlocks.add(currentTetra.b[1]);
            staticBlocks.add(currentTetra.b[2]);
            staticBlocks.add(currentTetra.b[3]);

            if(currentTetra.b[0].x == TETRA_START_X && currentTetra.b[0].y == TETRA_START_Y) {
                gameOver = true;
                Panel.soundEffect.play(4,false);
            }
            currentTetra.deactivating = false;
            currentTetra = nextTetra;
            currentTetra.setXY(TETRA_START_X, TETRA_START_Y);
            nextTetra = pickTetra();
            nextTetra.setXY(NEXT_TETRA_X, NEXT_TETRA_Y);

            checkDelete();
    }else {currentTetra.update();
        }}

    private void checkDelete() {
        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;
        while(x < right_x && y < bottom_y) {
            for(int i = 0; i <staticBlocks.size(); i++) {
                if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
                    blockCount++;
                }
            }
            x += Block.SIZE;
            if(x == right_x) {
                if (blockCount == 12) {
                    effectCounterOn = true;
                    effectY.add(y);

                    for(int i = staticBlocks.size()-1; i > -1; i--) {
                        if(staticBlocks.get(i).y==y) {
                            staticBlocks.remove(i);
                        }
                    }
                    lineCount++; //khi 1 lines match, thi dc diem
                    lines++;
                    // tang toc do, neu score tang, toc do tang
                    if (lines % 10 == 0 && dropTetra > 1){
                        level++;
                        if(dropTetra >10) {
                            dropTetra -= 10;
                        }else {
                            dropTetra -=1;
                        }
                    }
                    for (int i = 0; i <staticBlocks.size(); i++) {
                        if(staticBlocks.get(i).y<y) {
                            staticBlocks.get(i).y += Block.SIZE;
                        }
                    }
                }
                blockCount =0;
                x = left_x;
                y += Block.SIZE;
            }
        }
        if(lineCount > 0) {
            Panel.soundEffect.play(1, false);
            int singleLineScore = 10 * level;
            score += singleLineScore * lineCount;
        }
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
        g2.drawString("NEXT", x + 60, y + 60);


        //Score Frame

        g2.drawRect(x, top_y, 250, 300);
        x += 40;
        y = top_y + 90;
        g2.drawString("LEVEL: " + level, x, y); y+= 70;
        g2.drawString("LINES: "+ lines, x, y); y+= 70;
        g2.drawString("SCORE: "+ score, x, y);
        if (currentTetra != null) {
            currentTetra.draw(g2);
        }
        //ve tetra roi tiep theo
        nextTetra.draw(g2);

        for(int i = 0; i <staticBlocks.size(); i++) {
            staticBlocks.get(i).draw(g2);
        }

        if(effectCounterOn) {
            effectCounter++;
            g2.setColor(Color.white);
            for(int i = 0; i <effectY.size(); i++) {
                g2.fillRect(left_x, effectY.get(i), WIDTH, Block.SIZE);
            }
            if(effectCounter == 10) {
                effectCounterOn = false;
                effectCounter = 0;
                effectY.clear();
            }
        }

        g2.setColor(Color.getHSBColor(0.0f, 1.0f, 0.5f));
        g2.setFont(g2.getFont().deriveFont(50f));
        if(gameOver){
            x = left_x + 7;
            y = top_y + 320;
            g2.drawString("LOL DUMBASS", x, y);
        }
        else if (Handler.pausePressed) {
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("PAUSED", x, y);
        }

        x = 70;
        y = top_y + 320;
        g2.setColor(Color.white);
        g2.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        g2.drawString("Tetris Game", x, y);
    }
}
