import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.*;

class Save extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;
	JTextField name;
	Paint paint;
	
	Save(Paint paint) {
		this.paint = paint;
		
		setLocationRelativeTo(null);
		setSize(250,150);
		setLayout(new GridLayout(3,1));
		add(new Label("Write file name"));
		
		name = new JTextField();
		add(name);
		
		Button SaveButton = new Button("Save");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveObject(name.getText(), paint) ;
				} catch (IOException ex) {}
			}});
		
		add(SaveButton);
		setVisible(true);
	}
	
	public static void saveObject(String name, Paint paint) throws IOException {
		ObjectOutputStream file = null;
		try{
			System.out.println("Save");
			file = new ObjectOutputStream(new FileOutputStream(name));
			file.writeObject(paint);
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