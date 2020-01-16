import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SceneObject {
	private double x, y, r;
	
	public Circle(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	@Override
	public double getDistance(double x, double y) {
		double xDiff = (x - this.x);
		double yDiff = (y - this.y);
		double distToCenter =  Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
		return distToCenter - (this.r);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(185, 185, 185));
		g.fillOval((int)(this.x-(this.r)) + 1, (int)(this.y-(this.r)) + 1, (int)(this.r*2), (int)(this.r*2));
		g.setColor(new Color(255, 255, 255));
		g.drawOval((int)(this.x-(this.r)) + 1, (int)(this.y-(this.r)) + 1, (int)(this.r*2), (int)(this.r*2));
	}
}
