import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

public class MyPolygon extends Polygon implements Serializable {
	private static final long serialVersionUID = 1L;
	public boolean ifActive;
	Color color;
	GeneralPath createPolygon;
	ArrayList<Point2D.Float> PointList;
	Point2D.Float midPoint;
	
	public MyPolygon() {
		PointList = new ArrayList<Point2D.Float>();
		color = Color.BLACK;
		
		ifActive = false;
	}
	
	GeneralPath drawPolygon() {
		GeneralPath createPolygon = new GeneralPath();
		
		createPolygon.moveTo(PointList.get(0).x, PointList.get(0).y);
		
		for (Point2D.Float Point : PointList)
			createPolygon.lineTo(Point.x, Point.y);
		
		createPolygon.closePath();
		
		return createPolygon;
	}
	
	public void addPoint(float x, float y) {
		PointList.add(new Point2D.Float(x, y));
		midPoint = findMidPoint();
	}
	
	public boolean isHit(float x, float y) {
		GeneralPath createPolygon = new GeneralPath();
		
		createPolygon.moveTo(PointList.get(0).x, PointList.get(0).y);
		
		for (Point2D.Float Point : PointList)
			createPolygon.lineTo(Point.x, Point.y);
		
		createPolygon.closePath();
		
		return createPolygon.getBounds2D().contains(x, y);
	}
	
	public void addX(float x) {
		for (int i = 0; i < PointList.size(); i++)
			PointList.get(i).x += x;
	}

	public void addY(float y) {
		for (int i = 0; i < PointList.size(); i++)
			PointList.get(i).y += y;
	}
	
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
	
	Point2D.Float max(Point2D.Float mid) {
		Point2D.Float maxPoint;
		maxPoint = PointList.get(0);

		for (int i = 0; i < PointList.size(); i++) {
			 if (mid.distance(PointList.get(i)) > mid.distance(maxPoint))
				 maxPoint = PointList.get(i);
		}
		
		return maxPoint;
	}
	
	public void changeSize(float dx, float dy) {
		Point2D.Float maxPoint = max(midPoint);
		
		for (int i = 0; i < PointList.size(); i++) {
			PointList.get(i).x += dx * midPoint.distance(PointList.get(i)) / midPoint.distance(maxPoint);
			PointList.get(i).y += dy * midPoint.distance(PointList.get(i)) / midPoint.distance(maxPoint);
		}
	}
}

class MyPolygonCreateMouseAdapter extends MouseAdapter implements Serializable {
	private static final long serialVersionUID = 1L;
	Surface surface;
	JFrame frame;
	MyPolygon polygon;
	
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