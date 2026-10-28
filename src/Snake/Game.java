package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

//import Snake.GamePanel.MyKeyAdapter;

public class Game extends JFrame implements ActionListener{
	
	GameBoard myBoard;
	Color appleColor=Color.RED;
	Color snakeColor=Color.GREEN;
	static final int DELAY=90;
	static final int SCREEN_HEIGHT=600;
	static final int SCREEN_WIDTH=600;
	static final int UNIT_SIZE=25;
	boolean running=false;
	Timer timer;
	
	public static void main(String[] args) {
		Game frame=new Game();
		frame.setTitle("Snake");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public Game() {
		myBoard=new GameBoard(SCREEN_WIDTH,SCREEN_HEIGHT,UNIT_SIZE,appleColor,snakeColor);
		myBoard.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		myBoard.setBackground(Color.black);
		myBoard.addKeyListener(myBoard.new MyKeyAdapter());
		myBoard.setFocusable(true);
		myBoard.setRestartListener(() -> {
			running=true;
			timer.start();
		});
		this.add(myBoard);
		timer = new Timer(DELAY, this);
		timer.start();
		running=true;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			myBoard.move();
			myBoard.checkApple();
			if (!myBoard.checkCollisions() || !myBoard.checkBody()) {
				running=false;
				timer.stop();
				myBoard.setOver(true);
			}
		}
		repaint();
	}
	
	
}
