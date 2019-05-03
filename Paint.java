import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class Open extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;
	JTextField name;
	Paint paint;
	
	Open(Paint paint) {
		this.paint = paint;
		
		setLocationRelativeTo(null);
		setSize(250,150);
		setLayout(new GridLayout(3,1));
		add(new Label("Write file name"));
		
		name = new JTextField();
		add(name);
		
		Button OpenButton = new Button("Open");
		OpenButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					openObject(name.getText(), paint) ;
				} catch (IOException ex) {	
				} catch (ClassNotFoundException ex) {}
			}});
		
		add(OpenButton);
		setVisible(true);
	}
	
	public static void openObject(String name, Paint paint)throws IOException,ClassNotFoundException{
		ObjectInputStream file = null;
		Paint newPaint = null;
		
		try{
			file = new ObjectInputStream(new FileInputStream(name));
			newPaint=(Paint)file.readObject();

		} catch (EOFException ex) {
			System.out.println("End of file");
		}

		finally{
			//System.out.println(newPaint);
			if(file != null) {
				paint = newPaint;
				file.close();
			}
		}
	}
}

public class Paint extends JFrame implements Serializable {
	
	private static final long serialVersionUID = 1L;

	Paint() {
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		add(new Surface(this), BorderLayout.CENTER);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Paint paint = new Paint();
	}
}