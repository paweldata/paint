import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

class InfoPanel extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;

    InfoPanel() {
		add(new Label("Paint by Paweł Data"));
	}
}

public class InfoFrame extends JFrame implements Serializable {
	private static final long serialVersionUID = 1L;

    InfoFrame() {
		setSize(300,200);
		setLocationRelativeTo(null);
		
		add(new InfoPanel());
		setVisible(true);
	}
}
