package Paint.src;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Shows info about figure
 * which is moving or scalling now
 */
public class InfoFigurePanel extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
	ArrayList<JLabel> labelList;
	
	/**
	 * Class constructor
	 * Start, when none figure is active
	 * (none figure is scalling or moving)
	 * 
	 * @param ifCreatePolygon true, when polygon is creating
	 * When polygon is creating, panel show information about it
	 * When no, show only header
	 */
	InfoFigurePanel(boolean ifCreatePolygon) {
		setLayout(new GridLayout(30,1));
		add(new JLabel("Active figure info    "));
		
		if (ifCreatePolygon) {
			add(new JLabel("Press Right click"));
			add(new JLabel("to stop creating"));
			add(new JLabel("polygon"));
		}
		
		setBackground(Color.GRAY);
	}

	/**
	 * Class constructor
	 * Start, when figure is active
	 * (is moving or scalling)
	 * 
	 * @param figure active figure
	 */
	InfoFigurePanel(Object figure) {
		setLayout(new GridLayout(30,1));
		labelList = new ArrayList<JLabel>();
		
		labelList.add(new JLabel("Active figure info    "));
		
		if (figure instanceof MyRectangle) {
			
			MyRectangle temp = (MyRectangle)figure;
			
			labelList.add(new JLabel("Rectangle"));
			labelList.add(new JLabel("X : " + Double.toString(temp.getX())));
			labelList.add(new JLabel("Y : " + Double.toString(temp.getY())));
			labelList.add(new JLabel("Widht : " + Double.toString(temp.width)));
			labelList.add(new JLabel("Height : " + Double.toString(temp.height)));
			
		} else if (figure instanceof MyEllipse) {
			
			MyEllipse temp = (MyEllipse)figure;
			
			labelList.add(new JLabel("Ellipse"));
			labelList.add(new JLabel("X : " + Double.toString(temp.getX())));
			labelList.add(new JLabel("Y : " + Double.toString(temp.getY())));
			labelList.add(new JLabel("Widht : " + Double.toString(temp.width)));
			labelList.add(new JLabel("Height : " + Double.toString(temp.height)));
			
		} else if (figure instanceof MyPolygon) {
			
			MyPolygon temp = (MyPolygon)figure;
			
			labelList.add(new JLabel("Polygon"));
			labelList.add(new JLabel("Nodes : "));
			 
			for (Point2D.Float point : temp.PointList) {
				labelList.add(new JLabel("X: " + Double.toString(Math.round(point.x * 100) / 100) + 
										" Y:" + Double.toString(Math.round(point.x * 100) / 100)));
			}
		}
		
		for (JLabel label : labelList)
			add(label);
		
		setBackground(Color.GRAY);
	}
}
