package Paint.src;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Frame
 * Here can save a file
 */
class Save extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;
	JTextField name;
	Surface surface;
	
	/**
	 * Class constructor
	 * 
	 * @param surface main panel
	 */
	Save(Surface surface) {
		this.surface = surface;
		
		setLocationRelativeTo(null);
		setSize(250,150);
		setLayout(new GridLayout(3,1));
		add(new JLabel("Write file name"));
		
		name = new JTextField();
		add(name);
		
		Button SaveButton = new Button("Save");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveObject(name.getText(), surface);
					setVisible(false);
				} catch (IOException ex) {}
			}});
		
		add(SaveButton);
		setVisible(true);
	}
	
	/**
	 * Save file
	 * 
	 * @param name     name of file
	 * @param surface main panel
	 * @throws IOException exception
	 */
	private void saveObject(String name, Surface surface) throws IOException {
		ObjectOutputStream file = null;
		try {
			System.out.println("Save");
			file = new ObjectOutputStream(new FileOutputStream(name));
			file.writeObject(surface);
			file.flush();
		}
		finally{
			if(file!=null) {
				file.close();
				System.out.println("OK");
			}
		}
	}
}
