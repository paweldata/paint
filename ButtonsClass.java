import static javax.swing.JColorChooser.showDialog;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.Serializable;

import javax.swing.JColorChooser;
import javax.swing.JLabel;

/**
 * Add all buttons to frame in container
 */
public class ButtonsClass extends Container implements Serializable {
	private static final long serialVersionUID = 1L;
	final static int buttonNumber = 11;
	MouseAdapter myAdapter;

	/**
	 * Class constructor
	 * 
	 * @param surface main panel
	 * @param frame main frame (paint)
	 */
	ButtonsClass(Surface surface, Paint frame) {
		setLayout(new GridLayout(buttonNumber * 2, 1));

		Button InfoButton = new Button("Info");
		InfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InfoFrame();
			}
		});
		add(InfoButton);

		Button NewButton = new Button("New");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surface.removeMouseMotionListener(myAdapter);
				surface.removeMouseListener(myAdapter);

				new NewSurface(surface, frame);
			}
		});
		add(NewButton);

		Button SaveButton = new Button("Save");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surface.removeMouseMotionListener(myAdapter);
				surface.removeMouseListener(myAdapter);

				new Save(surface);
			}
		});
		add(SaveButton);

		Button OpenButton = new Button("Open");
		OpenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surface.removeMouseMotionListener(myAdapter);
				surface.removeMouseListener(myAdapter);

				new Open(surface, frame);
			}
		});
		add(OpenButton);

		add(new JLabel("Figure"));

		Button RectangleButton = new Button("Rectangle");
		RectangleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surface.removeMouseMotionListener(myAdapter);
				surface.removeMouseListener(myAdapter);

				myAdapter = new MyRectangleCreateAdapter(surface, frame);

				surface.addMouseMotionListener(myAdapter);
				surface.addMouseListener(myAdapter);
			}
		});
		add(RectangleButton);

		Button EllipseButton = new Button("Ellipse");
		EllipseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surface.removeMouseMotionListener(myAdapter);
				surface.removeMouseListener(myAdapter);

				myAdapter = new MyEllipseCreateAdapter(surface, frame);

				surface.addMouseMotionListener(myAdapter);
				surface.addMouseListener(myAdapter);
			}
		});
		add(EllipseButton);

		Button PolygonButton = new Button("Polygon");
		PolygonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surface.removeMouseMotionListener(myAdapter);
				surface.removeMouseListener(myAdapter);

				myAdapter = new MyPolygonCreateMouseAdapter(surface, frame);

				surface.addMouseMotionListener(myAdapter);
				surface.addMouseListener(myAdapter);

				frame.validate();
				frame.repaint();
			}
		});
		add(PolygonButton);

		add(new JLabel("Options"));

		Button MoveButton = new Button("Move");
		MoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surface.removeMouseMotionListener(myAdapter);
				surface.removeMouseListener(myAdapter);

				myAdapter = new MoveAdapter(surface);

				surface.addMouseMotionListener(myAdapter);
				surface.addMouseListener(myAdapter);
			}
		});
		add(MoveButton);

		Button ResizeButton = new Button("Resize");
		ResizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surface.removeMouseMotionListener(myAdapter);
				surface.removeMouseListener(myAdapter);

				myAdapter = new ResizeAdapter(surface);

				surface.addMouseMotionListener(myAdapter);
				surface.addMouseListener(myAdapter);
			}
		});
		add(ResizeButton);

		Button ColorButton = new Button("Color");
		ColorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				surface.removeMouseMotionListener(myAdapter);
				surface.removeMouseListener(myAdapter);

				new JColorChooser();

				Color color = showDialog(null, "Choose color", null);
				
				for(int i = 0; i < surface.FigureList.size(); i++) {
					if (surface.FigureList.get(i) instanceof MyRectangle) {
						MyRectangle temp = (MyRectangle)surface.FigureList.get(i);
						
						if (temp.ifActive)
							temp.color = color;
					}
					else if (surface.FigureList.get(i) instanceof MyEllipse) {
						MyEllipse temp = (MyEllipse)surface.FigureList.get(i);
						
						if (temp.ifActive)
							temp.color = color;
					} else if (surface.FigureList.get(i) instanceof MyPolygon) {
						MyPolygon temp = (MyPolygon)surface.FigureList.get(i);
						
						if (temp.ifActive)
							temp.color = color;
					}
				}
				
				surface.repaint();
			}
		});
		add(ColorButton);
	}

}
