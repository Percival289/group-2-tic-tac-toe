import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TileClickListener implements MouseListener {
    private Runnable callBackFunction;

    public TileClickListener(Runnable callBckFunc) {
        callBackFunction = callBckFunc;
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        callBackFunction.run();

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
}