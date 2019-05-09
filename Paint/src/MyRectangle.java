package Paint.src;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.JFrame;

/**
 * Here is all info about rectangle
 */
public class MyRectangle extends Rectangle2D.Float implements Serializable {
	private static final long serialVersionUID = 1L;
	public boolean ifActive;
	Color color;
	
	/**
	 * Class constructor
	 * 
	 * @param x x coordinate of upper left corner
	 * @param y	y coordinate of upper left corner
	 * @param width widht of new rectangle
	 * @param height height of new rectangle
	 */
	public MyRectangle(float x, float y, float width, float height) {
		setRect(x, y, width, height);
		color = Color.BLACK;
		
		ifActive = true;
	}

	/**
	 * Check if mouse is preesed on ellipse
	 * 
	 * @param x x coordinate of cursor
	 * @param y y coordinate of cursor
	 * @return true if mouse pressed on rectangle; false otherwise
	 */
	public boolean isHit(float x, float y) {
		return getBounds2D().contains(x, y);
	}


	/**
	 * Change x coordinate of upper left corner
	 * 
	 * @param x added length
	 */
	public void addX(float x) {
		this.x += x;
	}

	/**
	 * Change y coordinate of upper left corner
	 * 
	 * @param y added length
	 */
	public void addY(float y) {
		this.y += y;
	}

	/**
	 * Add width of rectangle
	 * 
	 * @param w added length
	 */
	public void addWidth(float w) {
		this.width += w;
	}

	/**
	 * Add height of rectangle
	 * 
	 * @param h added length
	 */
	public void addHeight(float h) {
		this.height += h;
	}
}

/**
 * Mouse adapter which is responsible for create new rectangle
 * Is added to button "Rectangle"
 */
class MyRectangleCreateAdapter extends MouseAdapter implements Serializable {
	private static final long serialVersionUID = 1L;
	Surface surface;
	JFrame frame;
	private int x;
	private int y;
	
	/**
	 * Class constructor
	 * 
	 * @param surface main panel
	 * @param frame	main frame (paint)
	 */
	MyRectangleCreateAdapter(Surface surface, JFrame frame) {
		this.surface = surface;
		this.frame = frame;
	}

	/**
	 * Activate when mouse is pressed
	 * Get coordinate of coursor and create there new rectangle
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();

		for(int i = 0; i < surface.FigureList.size(); i++) {
			if (surface.FigureList.get(i) instanceof MyRectangle) {
				
				MyRectangle temp = (MyRectangle) surface.FigureList.get(i);
				temp.ifActive = false;

			} else if (surface.FigureList.get(i) instanceof MyEllipse) {
				
				MyEllipse temp = (MyEllipse) surface.FigureList.get(i);
				temp.ifActive = false;

			} else if (surface.FigureList.get(i) instanceof MyPolygon) {

				MyPolygon temp = (MyPolygon) surface.FigureList.get(i);
				temp.ifActive = false;
			}
		}

		MyRectangle temp = new MyRectangle(x, y, 100, 50);
		surface.FigureList.add(temp);
		
		frame.validate();
		frame.repaint();
	}
}
