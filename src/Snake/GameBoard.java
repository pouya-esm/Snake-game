package Snake;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class GameBoard extends JPanel {
	Snake snake;
	Apple apple;
	static int width;
	static int height;
	static int UNIT_SIZE;
	boolean over=false;
	int score=0;
	int maxScore=0;
	Color appleColor;
	Color snakeColor;
	JButton playAgainButton;
	RestartListener restartListener;

	public interface RestartListener {
		void onRestart();
	}

	public GameBoard(int width, int height, int UNIT_SIZE, Color appleColor, Color snakeColor) {
		GameBoard.width=width;
		GameBoard.height=height;
		GameBoard.UNIT_SIZE=UNIT_SIZE;
		this.appleColor=appleColor;
		this.snakeColor=snakeColor;
		setLayout(null);
		playAgainButton=new JButton("Play Again");
		playAgainButton.setBounds(width/2-75, height/2+100, 150, 40);
		playAgainButton.setFocusable(false);
		playAgainButton.setVisible(false);
		playAgainButton.addActionListener(e -> restart());
		this.add(playAgainButton);
		Start(appleColor,snakeColor);
	}

	public void Start(Color appleColor,Color snakeColor) {
		apple=new Apple(appleColor);
		snake=new Snake(snakeColor);
	}

	public void setOver(boolean over) {
		this.over=over;
		playAgainButton.setVisible(over);
	}

	public void setRestartListener(RestartListener restartListener) {
		this.restartListener=restartListener;
	}

	public void restart() {
		score=0;
		Snake.direction='R';
		Start(appleColor,snakeColor);
		setOver(false);
		requestFocusInWindow();
		if (restartListener!=null)
			restartListener.onRestart();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (over) {
			gameOver(g);
		} else {
			apple.draw(g);
			snake.draw(g);
			scorePoints(g);
		}
	}
	
	public void move() {
		snake.move();
	}
	
	public void checkApple() {
		if ((snake.x[0]==apple.applePoint.getX()) && (snake.y[0]==apple.applePoint.getY())) {
			score++;
			if (score>maxScore)
				maxScore=score;
			snake.bodyParts++;
			apple = new Apple(apple.color);
		}
	}
	
	public boolean checkCollisions() {
		if (snake.x[0]<0)
			return false;
		else if (snake.x[0]>=width)
			return false;
		else if (snake.y[0]<0)
			return false;
		else if (snake.y[0]>=height)
			return false;
		return true;
	}
	
	public boolean checkBody() {
		for (int i=snake.bodyParts; i>0 ; i--) {
			if ((snake.x[0] == snake.x[i]) && (snake.y[0]==snake.y[i]))
				return false;
		}
		return true;
	}
	
	public void gameOver(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("ink free",Font.BOLD,70));
		FontMetrics titleMetrics=getFontMetrics(g.getFont());
		String title="Game Over";
		g.drawString(title, (width-titleMetrics.stringWidth(title))/2, height/2);

		g.setFont(new Font("ink free",Font.BOLD,40));
		FontMetrics scoreMetrics=getFontMetrics(g.getFont());
		String finalScore="Score: "+score;
		g.drawString(finalScore, (width-scoreMetrics.stringWidth(finalScore))/2, height/2+scoreMetrics.getHeight());

		String bestScore="Max Score: "+maxScore;
		g.drawString(bestScore, (width-scoreMetrics.stringWidth(bestScore))/2, height/2+scoreMetrics.getHeight()*2);
	}

	public void scorePoints(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("ink free",Font.BOLD,40));
		FontMetrics metrics=getFontMetrics(g.getFont());
		String text="Score: "+score;
		g.drawString(text, (width-metrics.stringWidth(text))/2, metrics.getHeight());
	}
	
	public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (over) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode()==KeyEvent.VK_SPACE)
                    restart();
                return;
            }
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (Snake.direction != 'R')
                        Snake.direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    if (Snake.direction != 'L')
                        Snake.direction = 'R';
                    break;
                case KeyEvent.VK_DOWN:
                    if (Snake.direction != 'U')
                        Snake.direction = 'D';
                    break;
                case KeyEvent.VK_UP:
                    if (Snake.direction != 'D')
                        Snake.direction = 'U';
                    break;

            }
        }
    }
}
