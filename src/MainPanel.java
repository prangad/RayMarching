import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import static java.awt.event.KeyEvent.VK_RIGHT;

public class MainPanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private int mouseX, mouseY;
	private int scrW, scrH;
	private RMRay[] FOV;
	private ArrayList<SceneObject> scene = new ArrayList<SceneObject>();
	
	private boolean drawScene = true;
	
	Timer animationTimer = new Timer((int)(1000/60), new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			
			for (int i = 0; i < 61; i++) {
				double theta = Math.atan2(mouseY - FOV[i].getY(),
						mouseX - FOV[i].getX()) * 180/Math.PI;
				FOV[i].setDir((theta - i) + 30);
			}

			for (RMRay r : FOV) {
				r.update(scene);
			}
			repaint();
		}
	});
	
	public MainPanel(int width, int height)
	{
		this.setBackground(new Color(25,25,25));
		this.scrW = width;
		this.scrH = height;
		
		this.FOV = new RMRay[61];
		for (int i = 0; i < 61; i++) {
			this.FOV[i] = new RMRay(new Point(720, 450));
		}
		
		populateScene();
		
		animationTimer.start();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	private void populateScene() {
		scene.clear();
		for (int i = 0; i < 50; i++) {
			scene.add(new Circle((Math.random() * (1440)),
					(Math.random() * (900)),
					(Math.random() * (40))));
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if (drawScene) {
			for (SceneObject o : scene) {
				o.draw(g);
			}
		}
		
		for (RMRay r : this.FOV) {
			r.draw(g);
		}
	}

	public void mouseMoved(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}
	public void mouseDragged(MouseEvent e) {
		this.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (RMRay r : this.FOV) {
			r.setPos(new Point(e.getX(), e.getY()));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Called");
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_R:
				populateScene();
			break;
			case KeyEvent.VK_SPACE:
				this.drawScene = !this.drawScene;
			break;
			case KeyEvent.VK_S:
				for (RMRay r : this.FOV)
					r.toggleSegs();
			break;
			case KeyEvent.VK_H:
				for (RMRay r : this.FOV)
					r.toggleHits();
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void keyTyped(KeyEvent keyEvent) {}
	@Override
	public void keyReleased(KeyEvent keyEvent) { }
}
