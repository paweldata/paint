import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Frame
 * Here can open a file which was saved
 */
class Open extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;
	JLabel header;
	JTextField name;
	Paint paint;
	Surface surface;
	
	/**
	 * Class constructor
	 * 
	 * @param paint main frame
	 */
	Open(Surface surface, Paint paint) {
		this.paint = paint;
		this.surface = surface;
		
		setLocationRelativeTo(null);
		setSize(250,150);
		setLayout(new GridLayout(3,1));

		header =  new JLabel("Write file name");
		add(header);
		
		name = new JTextField();
		add(name);
		
		Button OpenButton = new Button("Open");
		OpenButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					openObject(name.getText(), surface, paint) ;
				} catch (IOException ex) {	
				} catch (ClassNotFoundException ex) {}
			}});
		
		add(OpenButton);
		setVisible(true);
	}
	
	/**
	 * Open a saved file
	 * 
	 * @param name name of file
	 * @param paint main frame
	 * @throws IOException exception
	 * @throws ClassNotFoundException exception
	 */
	private void openObject(String name, Surface surface, Paint paint)throws IOException,ClassNotFoundException{
		ObjectInputStream file = null;
		
		try {
			file = new ObjectInputStream(new FileInputStream(name));
			Surface newSurface = (Surface)file.readObject();

			setNewSurface(surface, paint, newSurface);

			//newSurface.createButtons(paint);
			//paint.add(new ButtonsClass(newSurface, paint), BorderLayout.EAST);

			paint.validate();
			paint.repaint();
	
			file.close();
			setVisible(false);

		} catch (EOFException ex) {
		} catch (ClassNotFoundException ex) {}
	}

	/**
	 * Set new surface to frame
	 * 
	 * @param surface current surface
	 * @param paint main frame
	 * @param newSurface next surface
	 */
	void setNewSurface(Surface surface, Paint paint, Surface newSurface) {
		paint.remove(surface.buttons);
		paint.remove(surface);

		paint.add(newSurface, BorderLayout.CENTER);

		newSurface.buttons = new ButtonsClass(newSurface, paint);
		paint.add(newSurface.buttons, BorderLayout.EAST);

		newSurface.frame = paint;
	}
}