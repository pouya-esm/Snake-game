package Snake;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
public class Apple {
	Color color;
	Point applePoint;
	Random rand;
	
	public Apple(Color color) {
		rand=new Random();
		applePoint=new Point();
		applePoint.setX(rand.nextInt((int)(GameBoard.width/GameBoard.UNIT_SIZE))*GameBoard.UNIT_SIZE);
		applePoint.setY(rand.nextInt((int)(GameBoard.height/GameBoard.UNIT_SIZE))*GameBoard.UNIT_SIZE);
		this.color=color;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(applePoint.getX(), applePoint.getY(), GameBoard.UNIT_SIZE, GameBoard.UNIT_SIZE);
	}
}