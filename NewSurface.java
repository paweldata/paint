import java.awt.BorderLayout;
import java.io.Serializable;

/**
 * Create new surface
 */
public class NewSurface implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Class constructor
     * 
     * @param surface current surface
     * @param paint main frame
     */
    NewSurface(Surface surface, Paint paint) {
        paint.remove(surface.buttons);
        paint.remove(surface);
        paint.add(new Surface(paint), BorderLayout.CENTER);

        paint.validate();
		paint.repaint();
    }
}