import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

class Surface extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;
    ArrayList<Object> FigureList;
	Paint frame;
	boolean ifCreatePolygon;
	
	
	Surface(Paint frame) {
		FigureList = new ArrayList<Object>();
		
		ifCreatePolygon = false;
		this.frame = frame;
		
		setLayout(new FlowLayout());
		
		frame.add(new ButtonsClass(this, frame), BorderLayout.EAST);
	} 
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		boolean ifNotActive = true;
		
		for(int i = 0; i < FigureList.size(); i++) {
			if (FigureList.get(i) instanceof MyRectangle) {
				
				MyRectangle temp = (MyRectangle) FigureList.get(i);
				g2d.setPaint(temp.color);
				g2d.fill(temp);
				
				if (temp.ifActive) {
					frame.add(new InfoFigurePanel(temp), BorderLayout.WEST);
					ifNotActive = false;
				}
			} else if (FigureList.get(i) instanceof MyEllipse) {
				
				MyEllipse temp = (MyEllipse)FigureList.get(i);
				g2d.setPaint(temp.color);
				g2d.fill(temp);
				
				if (temp.ifActive) {
					frame.add(new InfoFigurePanel(temp), BorderLayout.WEST);
					ifNotActive = false;
				}
			} else if (FigureList.get(i) instanceof MyPolygon) {
				
				MyPolygon temp = (MyPolygon)FigureList.get(i);
				
				if (temp.PointList.size() > 2) {
					GeneralPath createPolygon = temp.drawPolygon();
					
					g2d.setPaint(temp.color);
					g2d.fill(createPolygon);
				}
				if(!ifCreatePolygon)
					ifNotActive = false;
				
				if (temp.ifActive) {
					frame.add(new InfoFigurePanel(temp), BorderLayout.WEST);
					ifNotActive = false;
				}
			}
		}
		frame.validate();
		
		if (ifNotActive) 
			frame.add(new InfoFigurePanel(ifCreatePolygon), BorderLayout.WEST);
		
		System.out.println("OKComponent");
	}
}