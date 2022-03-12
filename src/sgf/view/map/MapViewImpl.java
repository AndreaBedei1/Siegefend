package sgf.view.map;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import sgf.controller.map.MapController;
import sgf.model.map.GridPosition;
import sgf.model.map.Map;

/**
 * This class is responsible for the process of map showing. It involves 2 steps: a calculation 
 * and composition of a grid and the creation and appearance of the corresponding final map image.
 */
public class MapViewImpl extends AbstractMapView implements ComponentListener, MouseListener {
    private static final long serialVersionUID = -7141712951441617040L;
    private MapController mapController;
    private final Map map;
    private final int matrixSize;       // Number of tiles in each grid size.
    private BufferedImage completeMap;    // Map to be shown after creation process.
    //private Consumer<MouseEvent> mouseHandler;  // Manager for user click into grid tiles.
    private boolean isControllerSet;

    /**
     * Constructor that initializes fields and links this panel with mouse listener.
     * @param map The logic map of the current level
     */
    public MapViewImpl(final Map map) {
        this.map = map;
        this.matrixSize = map.getMapSize();
        this.addComponentListener(this);
        this.addMouseListener(this);   // Links this panel with a controller of mouse events.
    }

    /**
     * Simple getter for the matrix size field.
     * @return an integer that is the matrix number of tiles per dimension.
     */
    @Override
    public int getMatrixSize() {
        return this.matrixSize;
    }

    @Override
    public void componentResized(final ComponentEvent e) {
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        // Simple way to obtain and print mouse position and tile type when clicking.
        if (e.getButton() == MouseEvent.BUTTON1) {
            //this.mouseHandler.accept(e);
            final int gridColumn = this.convertCoordinate(e.getX(), this.getWidth());
            final int gridRow = this.convertCoordinate(e.getY(), this.getHeight());
            System.err.println(gridRow + ": " + gridColumn);
            System.out.println(this.map.getTileFromGridPosition(new GridPosition(gridRow, gridColumn)).getTileType());
        }
    }

    // Taken a value and the dimension in refers to, it returns an integer value that is the corresponding tile position in the dimension.
    private int convertCoordinate(final int x, final double dimension) {
        final double sizeOfASingleTile = dimension / this.getMatrixSize();
        return (int) (x / sizeOfASingleTile);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(completeMap, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void setController(final MapController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.mapController = controller;
            this.completeMap = this.mapController.getMapImage();
        }
    }

    @Override
    public void componentMoved(final ComponentEvent e) {
    }

    @Override
    public void componentShown(final ComponentEvent e) {
    }

    @Override
    public void componentHidden(final ComponentEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }
}