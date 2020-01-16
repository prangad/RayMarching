import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Square extends SceneObject {
	private double x, y, size;
	
	public Square(double x, double y, double size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	@Override
	public double getDistance(double x, double y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(255, 255, 255));
		g.drawRect((int)this.x/2, (int)this.y/2, (int)this.size, (int)this.size);
	}

}
