import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class MoveAdapter extends MouseAdapter implements Serializable {
	private static final long serialVersionUID = 1L;
    Surface surface;
	private int x;
	private int y;
	
	MoveAdapter(Surface surface) {
		this.surface = surface;
	}

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