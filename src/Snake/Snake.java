package Snake;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {
    Color color;
    public static char direction = 'R';
    final int[] x = new int[GameBoard.width * GameBoard.height / GameBoard.UNIT_SIZE];
    final int[] y = new int[GameBoard.width * GameBoard.height / GameBoard.UNIT_SIZE];
    int bodyParts = 6;

    public Snake(Color color) {
        this.color = color;
    }

    public void draw(Graphics g) {
        for (int i = bodyParts; i >= 0; i--) {
            if (i == 0) {
                g.setColor(Color.YELLOW);
                g.fillRect(x[i], y[i], GameBoard.UNIT_SIZE, GameBoard.UNIT_SIZE);
            } else {
                g.setColor(Color.GREEN);
                g.fillRect(x[i], y[i], GameBoard.UNIT_SIZE, GameBoard.UNIT_SIZE);
            }
        }
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (direction) {
            case 'U':
                y[0]-=GameBoard.UNIT_SIZE;
                break;
            case 'D':
                y[0]+=GameBoard.UNIT_SIZE;
                break;
            case 'R':
                x[0]+=GameBoard.UNIT_SIZE;
                break;
            case 'L':
               x[0]-=GameBoard.UNIT_SIZE;
                break;
        }
    }
}
