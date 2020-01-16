import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class RMRay {
	private double startX, startY, posX, posY, endX, endY;
	private double dir;
	private ArrayList<double[]> segs = new ArrayList<double[]>();
	private boolean drawSegs = false;
	private ArrayList<double[]> hits = new ArrayList<double[]>();
	private boolean drawHits = true;
	
	public RMRay(Point startPos) {
		this.startX = startPos.x;
		this.startY = startPos.y;
		this.posX = this.startX;
		this.posY = this.startY;
	}

	public void toggleSegs() {
		this.drawSegs = !this.drawSegs;
		if (!this.drawSegs)
			this.segs.clear();
	}

	public void toggleHits() {
		this.drawHits = !this.drawHits;
		if (!this.drawHits)
			this.hits.clear();
	}

	public void setDir(double angDeg) {
		this.dir = angDeg;
	}
	
	public void setPos(Point pos) {
		this.startX = pos.x;
		this.startY = pos.y;
	}
	
	public void update(ArrayList<SceneObject> scene) {
		boolean collided = false;
		this.posX = this.startX;
		this.posY = this.startY;
		double safeX, safeY;
		segs.clear();
		
		while (!collided) {
			double r = distToScene(scene);
			if (r <= .1
					|| this.posX >= 1440
					|| this.posY >= 900
					|| this.posX <= 0
					|| this.posY <= 0) {
				collided = true;
			}
			safeX = this.posX + r * Math.cos(dir * Math.PI / 180);
			safeY = this.posY + r * Math.sin(dir * Math.PI / 180);
			double oldX = this.posX;
			double oldY = this.posY;
			this.posX = safeX;
			this.posY = safeY;
			double xDiff = (oldX - this.posX);
			double yDiff = (oldY - this.posY);
			double dist =  Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
			segs.add(new double[] {oldX, oldY, xDiff/2, yDiff/2, dist});
		}
		this.endX = this.posX;
		this.endY = this.posY;
		this.hits.add(new double[] {this.endX, this.endY});
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(90, 90, 90));
		g.drawLine((int)this.startX, (int)this.startY, (int)this.endX, (int)this.endY);
		if (drawSegs) {
			g.setColor(new Color(255, 100, 100));
			for (double[] info : segs) {
				double d = (info[4]);
				g.drawOval((int)(info[0] - d), (int)(info[1] - d), (int)(d*2), (int)(d*2));
			}
		}
		if (drawHits) {
			g.setColor(new Color(255, 0, 0));
			for (double[] pos : hits) {
				g.drawOval((int)pos[0], (int)pos[1], 1, 1);
			}
		}
	}
	
	private double distToScene(ArrayList<SceneObject> scene) {
		double lowestDist = Double.MAX_VALUE;
		for (SceneObject o : scene) {
			double d = o.getDistance(this.posX, this.posY);
			if (d <= lowestDist || lowestDist < 0) {
				lowestDist = d;
			}
		}
		return lowestDist;
	}
	
	public double getX() {
		return this.startX;
	}
	
	public double getY() {
		return this.startY;
	}
}
