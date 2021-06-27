package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

// represents a panel on which live cells are drawn
public class GamePanel extends JPanel {

    private Set<Point> state;
    private int cellSize;
    private Dimension screenDimensions;

    // EFFECTS: instantiates a GamePanel
    public GamePanel(Set<Point> state, int cellSize, Dimension screenDimensions) {
        this.state = state;
        this.cellSize = cellSize;
        this.screenDimensions = screenDimensions;
    }

    // MODIFIES: this
    // EFFECTS: sets state to given state
    public void setState(Set<Point> state) {
        this.state = state;
    }

    @Override
    // MODIFIES: this
    // EFFECTS: renders all points in state as cells on panel
    public void paintComponent(Graphics g) {
        for(Point point: state) {
            Point scaledPoint = getScaledPoint(point);
            g.drawRect(scaledPoint.x, scaledPoint.y, cellSize, cellSize);
            g.setColor(Color.BLUE);
        }
    }

    // EFFECTS: Scales the x and y coordinates of the given point by cellSize, then takes the x coordinate and y
    // coordinate modulo screen width and screen height respectively. Returns these scaled components as a new point.
    private Point getScaledPoint(Point point) {
        int scaledXCoordinate = Math.abs((point.x * cellSize) % (int) screenDimensions.getWidth());
        int scaledYCoordinate = Math.abs((point.y * cellSize) % (int) screenDimensions.getHeight());
        Point scaledPoint = new Point(scaledXCoordinate, scaledYCoordinate);
        return scaledPoint;
    }
}
