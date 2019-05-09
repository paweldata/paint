package Paint.src;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Class is responsible for moving all figures
 * Override mousePressed and mouseDragged
 */
public class MoveAdapter extends MouseAdapter implements Serializable {
	private static final long serialVersionUID = 1L;
    Surface surface;
	private int x;
	private int y;

	/**
	 * Class constructor
	 * 
	 * @param surface main panel
	 */
	MoveAdapter(Surface surface) {
		this.surface = surface;
	}

	/**
	 * Activate when mouse is pressed
	 * Find a figure which is pressed and set ifActive true for this figure
	 * When figure is not pressed, set ifActive false
	 * ifActive is using in function mouseDragged
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		for(int i = 0; i < surface.FigureList.size(); i++) {
			if (surface.FigureList.get(i) instanceof MyRectangle) {
				MyRectangle temp = (MyRectangle)surface.FigureList.get(i);
				
				if (temp.isHit(x, y))
					temp.ifActive = true;
				else
					temp.ifActive = false;
			}
			else if (surface.FigureList.get(i) instanceof MyEllipse) {
				MyEllipse temp = (MyEllipse)surface.FigureList.get(i);
				
				if (temp.isHit(x, y))
					temp.ifActive = true;
				else
					temp.ifActive = false;
			} else if (surface.FigureList.get(i) instanceof MyPolygon) {
				MyPolygon temp = (MyPolygon)surface.FigureList.get(i);
				
				if (temp.isHit(x, y)) {
					System.out.println("OK");
					temp.ifActive = true;
				}
				else
					temp.ifActive = false;
			}
		}
	}

	/**
	 * Move a figure which mouse is pressed
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		int dx = e.getX() - x;
		int dy = e.getY() - y;
		
		for(int i = 0; i < surface.FigureList.size(); i++) {
			if (surface.FigureList.get(i) instanceof MyRectangle) {
				MyRectangle temp = (MyRectangle)surface.FigureList.get(i);
				
				if (temp.ifActive && temp.isHit(x, y)) {
					
					temp.addX(dx);
					temp.addY(dy);
					surface.repaint();
				}
			} else if (surface.FigureList.get(i) instanceof MyEllipse) {
				MyEllipse temp = (MyEllipse)surface.FigureList.get(i);
				
				if (temp.ifActive && temp.isHit(x, y)) {
					
					temp.addX(dx);
					temp.addY(dy);
					surface.repaint();
				}
			} else if (surface.FigureList.get(i) instanceof MyPolygon) {
				MyPolygon temp = (MyPolygon)surface.FigureList.get(i);
				
					if (temp.ifActive && temp.isHit(x, y)) {
					
					temp.addX(dx);
					temp.addY(dy);
					surface.repaint();
				}
			}
		}

		x += dx;
		y += dy;            
	}
}
