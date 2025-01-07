package tetramino;

import Main.Handler;
import Main.playManager;

import java.awt.*;

public class tetramino {
    public Block[] b = new Block[4];
    public Block[] tempB = new Block[4];
    int autoDrop = 0;
    public int direction = 1; //four directions :))
    public void create(Color c) {
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);

    }
    public void setXY(int x, int y) {}
    public void updateXY(int direction) {
        this.direction = direction;
        b[0].x = tempB[0].x;
        b[0].y = tempB[0].y;
        b[1].x = tempB[1].x;
        b[1].y = tempB[1].y;
        b[2].x = tempB[2].x;
        b[2].y = tempB[2].y;
        b[3].x = tempB[3].x;
        b[3].y = tempB[3].y;

    }
    public void getD1() {}
    public void getD2() {}
    public void getD3() {}
    public void getD4() {}

    public void update() {

        if(Handler.downPressed) {

            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;
            autoDrop = 0;
            Handler.downPressed = false;
        }
        if(Handler.upPressed) {
            switch(direction) {
                case 1: getD2();break;
                case 2: getD3();break;
                case 3: getD4();break;
                case 4: getD1();break;
            } Handler.upPressed = false;
        }


        if(Handler.leftPressed) {
            b[0].x-= Block.SIZE;
            b[1].x -= Block.SIZE;
            b[2].x -= Block.SIZE;
            b[3].x -= Block.SIZE;

            Handler.leftPressed = false;
        }


        if(Handler.rightPressed) {
            b[0].x += Block.SIZE;
            b[1].x += Block.SIZE;
            b[2].x += Block.SIZE;
            b[3].x += Block.SIZE;

            Handler.rightPressed = false;
        }






        autoDrop++;
        if (autoDrop == playManager.dropTetra) {
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;
            autoDrop = 0;
        }
    }
    public void draw(Graphics2D g2) {
        int margin = 2;
        g2.setColor(b[0].c);
        g2.fillRect(b[0].x+margin, b[0].y+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(b[1].x+margin, b[1].y+margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(b[2].x+margin, b[2].y+margin,  Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(b[3].x+margin, b[3].y+margin,  Block.SIZE-(margin*2), Block.SIZE-(margin*2));
    }
}
