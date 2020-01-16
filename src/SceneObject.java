import java.awt.Graphics;

public abstract class SceneObject {	
	public abstract double getDistance(double x, double y);
	public abstract void draw(Graphics g);
}