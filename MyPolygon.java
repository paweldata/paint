import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Here are all info about polygon
 */
public class MyPolygon extends Polygon implements Serializable {
	private static final long serialVersionUID = 1L;
	public boolean ifActive;
	Color color;
	GeneralPath createPolygon;
	ArrayList<Point2D.Float> PointList;
	Point2D.Float midPoint;
	
	/**
	 * Class constructor
	 */
	public MyPolygon() {
		PointList = new ArrayList<Point2D.Float>();
		color = Color.BLACK;
		
		ifActive = false;
	}

	/**
	 * Create polygon ready to draw
	 * 
	 * @return polygon ready to draw
	 */
	GeneralPath drawPolygon() {
		GeneralPath createPolygon = new GeneralPath();
		
		createPolygon.moveTo(PointList.get(0).x, PointList.get(0).y);
		
		for (Point2D.Float Point : PointList)
			createPolygon.lineTo(Point.x, Point.y);
		
		createPolygon.closePath();
		
		return createPolygon;
	}
	
	/**
	 * Added new point to polygon when is creating on panel
	 * 
	 * @param x x coordinate of cursor
	 * @param y y coordinate of cursor
	 */
	public void addPoint(float x, float y) {
		PointList.add(new Point2D.Float(x, y));
		midPoint = findMidPoint();
	}
	
	/**
	 * Check if mouse is preesed on polygon
	 * 
	 * @param x x coordinate of cursor
	 * @param y y coordinate of cursor
	 * @return true if mouse pressed on polygon; false otherwise
	 */
	public boolean isHit(float x, float y) {
		GeneralPath createPolygon = new GeneralPath();
		
		createPolygon.moveTo(PointList.get(0).x, PointList.get(0).y);
		
		for (Point2D.Float Point : PointList)
			createPolygon.lineTo(Point.x, Point.y);
		
		createPolygon.closePath();
		
		return createPolygon.getBounds2D().contains(x, y);
	}
	
	/**
	 * Change x coordinate of every points
	 * 
	 * @param x x coordinate of cursor
	 */
	public void addX(float x) {
		for (int i = 0; i < PointList.size(); i++)
			PointList.get(i).x += x;
	}

	/**
	 * Change y coordinate of every points
	 * 
	 * @param y y coordinate of cursor
	 */
	public void addY(float y) {
		for (int i = 0; i < PointList.size(); i++)
			PointList.get(i).y += y;
	}
	
	/**
	 * Count mid point of polygon
	 * 
	 * @return mid point of polygon
	 */
	Point2D.Float findMidPoint() {
		float x = 0;
		float y = 0;

		for (int i = 0; i < PointList.size(); i++) {
			 x += PointList.get(i).x;
			 y += PointList.get(i).y;
		}
		
		x /= PointList.size();
		y /= PointList.size();
		
		return new Point2D.Float(x, y);
	}
	
	/**
	 * Find the farthest point from the center
	 * It's necessary to change size of polygon
	 * 
	 * @param mid center of polygon
	 * @return the farthest point from the center
	 */
	Point2D.Float max(Point2D.Float mid) {
		Point2D.Float maxPoint;
		maxPoint = PointList.get(0);

		for (int i = 0; i < PointList.size(); i++) {
			 if (mid.distance(PointList.get(i)) > mid.distance(maxPoint))
				 maxPoint = PointList.get(i);
		}
		
		return maxPoint;
	}
	
	/**
	 * Change size of polygon
	 * 
	 * @param dx x distance to change size
	 * @param dy y distance to change size
	 */
	public void changeSize(float dx, float dy) {
		Point2D.Float maxPoint = max(midPoint);
		
		for (int i = 0; i < PointList.size(); i++) {
			PointList.get(i).x += dx * midPoint.distance(PointList.get(i)) / midPoint.distance(maxPoint);
			PointList.get(i).y += dy * midPoint.distance(PointList.get(i)) / midPoint.distance(maxPoint);
		}
	}
}

/**
 * Mouse adapter which is responsible for create new polygon
 * Is added to button "Polygon"
 */
class MyPolygonCreateMouseAdapter extends MouseAdapter implements Serializable {
	private static final long serialVersionUID = 1L;
	Surface surface;
	JFrame frame;
	MyPolygon polygon;
	
	/**
	 * Class constructor
	 * Set every figure ifActive to false
	 * 
	 * @param surface main panel
	 * @param frame main frame (paint)
	 */
	MyPolygonCreateMouseAdapter(Surface surface, JFrame frame) {
		this.surface = surface;
		this.frame = frame;
		
		surface.ifCreatePolygon = true;
		
		for(int i = 0; i < surface.FigureList.size(); i++) {
			if (surface.FigureList.get(i) instanceof MyRectangle) {
				
				MyRectangle temp = (MyRectangle)surface.FigureList.get(i);
				temp.ifActive = false;
			}
			else if (surface.FigureList.get(i) instanceof MyEllipse) {
				
				MyEllipse temp = (MyEllipse)surface.FigureList.get(i);
				temp.ifActive = false;
			} else if (surface.FigureList.get(i) instanceof MyPolygon) {
				
				MyPolygon temp = (MyPolygon)surface.FigureList.get(i);
				temp.ifActive = false;
			}
		}
		
		polygon = new MyPolygon();
		surface.FigureList.add(polygon);
		frame.repaint();
	}

	/**
	 * Activate when mouse is pressed
	 * If was click left button
	 * 		Get coordinate of coursor and add new point to ellipse
	 * If was click right button
	 * 		End create pollygon
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			
			float x = e.getX();
			float y = e.getY();
			polygon.addPoint(x, y);
			
		} else if(e.getButton() == MouseEvent.BUTTON3) {
			
			surface.ifCreatePolygon = false;
			polygon.ifActive = true;
			
			surface.removeMouseMotionListener(this);
			surface.removeMouseListener(this);
		}
		
		frame.repaint();
	}
}
