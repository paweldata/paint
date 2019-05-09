package Paint.src;

/**
 * @author Paweł Data
 * It's a simple graphics editor
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.Serializable;

import javax.swing.JFrame;

/**
 * Paint is a main frame
 */
public class Paint extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Class constructor
	 */
	Paint() {
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		add(new Surface(this), BorderLayout.CENTER);

		setVisible(true);
	}

	/**
	 * main function
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            	new Paint();
            }
        });

	}
}


