package tetramino;
import Main.Handler;
import Main.Panel;
import Main.playManager;
import java.awt.*;

public class tetramino {
    public Block[] b = new Block[4]; // Các khối của Tetramino hiện tại
    public Block[] tempB = new Block[4]; // Khối tạm thời để kiểm tra va chạm
    int autoDrop = 0; // booj đếm tự động rơi
    public int direction = 1;// Hướng xoay của Tetramino
    boolean LColl, RColl, BColl; // Trạng thái va chạm: trái, phải, đáy
    public boolean active = true; // Trạng thái hoạt động của Tetramino
    public boolean deactivating;
    int deactivateCounter = 0;
    // Tạo khối với màu sắc
    public void create(Color c) {
        for (int i = 0; i < 4; i++) {
            b[i] = new Block(c);
            tempB[i] = new Block(c);
        }
    }

    // Đặt tọa độ ban đầu
    public void setXY(int x, int y) {
        for (int i = 0; i < 4; i++) {
            b[i].x = x + i * Block.SIZE;
            b[i].y = y;
        }
    }

    // Cập nhật vị trí theo hướng
    public void updateXY(int direction) {
        checkRotationColl();
        if (!LColl && !RColl && !BColl) {
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
    }

    // Xử lý va chạm
    public void checkMovementColl() {
        LColl = false;
        RColl = false;
        BColl = false;
        checkStaticBlockColl();

        //left border
        for (int i = 0; i < b.length; i++) {
            if (b[i].x == playManager.left_x) {
                LColl = true;
            }}

        //rigth bordr
        for (int i = 0; i < b.length; i++) {
            if (b[i].x + Block.SIZE == playManager.right_x) {
                RColl = true;
            }}

        //bt border
        for (int i = 0; i < b.length; i++) {
            if (b[i].y + Block.SIZE == playManager.bottom_y) {
                BColl = true;
            }}

    }

    // Kiểm tra va chạm khi xoay
    public void checkRotationColl() {
        LColl = false;
        RColl = false;
        BColl = false;
        checkStaticBlockColl();
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].x < playManager.left_x) {
                LColl = true;
            }}
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].x + Block.SIZE > playManager.right_x) {
                RColl = true;
            }}
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].y + Block.SIZE > playManager.bottom_y) {
                BColl = true;
            }
        }
    }
    private void checkStaticBlockColl() {
        for(int i =0; i < playManager.staticBlocks.size(); i++) {
            int  tX = playManager.staticBlocks.get(i).x;
            int  tY = playManager.staticBlocks.get(i).y;

            //check bottm
            for (int j = 0; j < b.length; j++ ) {
                if(b[j].y + Block.SIZE == tY && b[j].x == tX) {
                    BColl = true;
                }
            }
            //check left
            for (int j = 0; j < b.length; j++ ) {
                if(b[j].x + Block.SIZE == tX && b[j].y  == tY) {
                    LColl = true;
                }
            }
            //check right
            for (int j = 0; j < b.length; j++ ) {
                if(b[j].x + Block.SIZE == tX && b[j].y == tY) {
                    RColl = true;
                }
            }
        }
}
    // Cập nhật trạng thái Tetramino mỗi khung hình
    public void update() {
        if(deactivating) {
            deactivating() ;
        }

        if (Handler.upPressed) {
            switch (direction) {
                case 1: getD2(); break;
                case 2: getD3(); break;
                case 3: getD4(); break;
                case 4: getD1(); break;
            }
            Handler.upPressed = false;
            Panel.soundEffect.play(2,false);
        }

        if (Handler.downPressed) {
            if (!BColl) {
                for (int i = 0; i < 4; i++) {
                    b[i].y += Block.SIZE;
                }
                autoDrop = 0;
            }
            Handler.downPressed = false;

        }
        checkMovementColl();

        if (Handler.leftPressed) {
            if (!LColl) {
                for (int i = 0; i < 4; i++) {
                    b[i].x -= Block.SIZE;
                }
            }
            Handler.leftPressed = false;
        }

        if (Handler.rightPressed) {
            if (!RColl) {
                for (int i = 0; i < 4; i++) {
                    b[i].x += Block.SIZE;
                }
            }
            Handler.rightPressed = false;
        }

        if (BColl) {
            deactivating = true; // Tetramino dừng lại khi va chạm đáy
            Panel.soundEffect.play(3,false);
        } else {
            autoDrop++;
            if (autoDrop == playManager.dropTetra) {
                for (int i = 0; i < 4; i++) {
                    b[i].y += Block.SIZE;
                }
                autoDrop = 0;
            }
        }
    }

    private void deactivating() {
        deactivateCounter++;
        if(deactivateCounter == 45) {
            deactivateCounter = 0;
            checkStaticBlockColl();
            if(BColl) {
                active = false;
            }
        }
    }

    // Vẽ Tetramino lên màn hình
    public void draw(Graphics2D g2) {
        int margin = 2;
        g2.setColor(b[0].c);
        for (int i = 0; i < 4; i++) {
            g2.fillRect(b[i].x + margin, b[i].y + margin, Block.SIZE - (margin * 2), Block.SIZE - (margin * 2));
        }
    }

    // Các phương thức xoay khối
    public void getD1() {}
    public void getD2() {}
    public void getD3() {}
    public void getD4() {}
}
