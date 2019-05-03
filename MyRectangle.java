import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JFrame;

public class MyRectangle extends Rectangle2D.Float implements Serializable {
	private static final long serialVersionUID = 1L;
	public boolean ifActive;
	Color color;
	
	public MyRectangle(float x, float y, float width, float height) {
		setRect(x, y, width, height);
		color = Color.BLACK;
		
		ifActive = true;
	}

	public boolean isHit(float x, float y) {
		return getBounds2D().contains(x, y);
	}

	public void addX(float x) {
		this.x += x;
	}

	public void addY(float y) {
		this.y += y;
	}

	public void addWidth(float w) {
		this.width += w;
	}

	public void addHeight(float h) {
		this.height += h;
	}
}

class MyRectangleCreateAdapter extends MouseAdapter implements Serializable {
	private static final long serialVersionUID = 1L;
	Surface surface;
	JFrame frame;
	private int x;
	private int y;
	
	MyRectangleCreateAdapter(Surface surface, JFrame frame) {
		this.surface = surface;
		this.frame = frame;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		MyRectangle temp = new MyRectangle(x, y, 100, 50);
		surface.FigureList.add(temp);
		
		frame.validate();
		frame.repaint();
	}
}