package tetramino;

import javax.swing.*;
import java.awt.*;

public class tetraBar extends tetramino {
    public tetraBar() {
        create(Color.MAGENTA);
    }

    public void setXY(int x, int y) {
        //
        //* * * *
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x - Block.SIZE;
        b[1].y = b[0].y;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + 2 * Block.SIZE; // Sửa lại để tạo ra 4 khối
        b[3].y = b[0].y;
    }

    public void getD1() {
        //
        //* * * *
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x - Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x + 2 * Block.SIZE; // Sửa lại để tạo ra 4 khối
        tempB[3].y = b[0].y;
        updateXY(1);
    }

    public void getD2() {
        //   *
        //   *
        //   *
        //   *
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - Block.SIZE;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - 2 * Block.SIZE; // Sửa lại để tạo ra 4 khối
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y - 3 * Block.SIZE; // Sửa lại để tạo ra 4 khối
        updateXY(2);
    }

    public void getD3() {
        getD1();
    }

    public void getD4() {
        getD2();
    }
}