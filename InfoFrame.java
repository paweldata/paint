import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel
 * Show info about author
 */
class InfoPanel extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;

		/**
		 * Class constructor
		 */
    InfoPanel() {
			add(new JLabel("Paint by Paweł Data"));
		}
}

/**
 * Frame
 * Show info about author
 */
public class InfoFrame extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;


		/**
		 * Class constructor
		 */
    InfoFrame() {
		setSize(300,200);
		setLocationRelativeTo(null);
		
		add(new InfoPanel());
		setVisible(true);
	}
}
