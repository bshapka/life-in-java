package ui;

import model.World;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;
import java.util.stream.IntStream;

// represents an instance of Conway's Game of Life
public class Game implements ActionListener {

    private int cellSize;
    private Dimension screenDimensions;
    private World world;
    private JFrame frame;
    private GamePanel gamePanel;
    private Timer timer;

    // EFFECTS: instantiates a Game. Sets screenDimensions. If cellSize is invalid, throws exception. If cellSize is
    // valid, then sets cellSize, and then sets world to an instance with a random state.
    public Game(int cellSize) throws IllegalArgumentException {
        screenDimensions = getScreenDimensions();
        validateCellSize(cellSize);
        this.cellSize = cellSize;
        Set<Point> randomState = getRandomState(0.075f);
        world = new World(randomState);
    }

    // EFFECTS: instantiates a Game. Sets screenDimensions. If cellSize is invalid, throws exception. If cellSize is
    // valid, then sets cellSize, and then sets world to an instance with given state initial state.
    public Game(int cellSize, Set<Point> initialState) throws IllegalArgumentException {
        screenDimensions = getScreenDimensions();
        validateCellSize(cellSize);
        this.cellSize = cellSize;
        world = new World(initialState);
    }

    // EFFECTS: returns screen dimensions in pixels as a Point
    private Dimension getScreenDimensions() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    // EFFECTS: throws IllegalArgumentException if cellSize is negative or is too big for screen
    private void validateCellSize(int cellSize) throws IllegalArgumentException {
        if (cellSize < 0) {
            throw new IllegalArgumentException("The cellSize cannot be negative.");
        }
        if (cellSize > screenDimensions.getWidth()) {
            throw new IllegalArgumentException("The cellSize cannot exceed the width of the screen.");
        }
        if (cellSize > screenDimensions.getHeight()) {
            throw new IllegalArgumentException("The cellSize cannot exceed the height of the screen.");
        }
    }

    // EFFECTS: returns a random state scaled to fill the screen when rendered
    private Set<Point> getRandomState(float density) {
        int screenArea = (int) (screenDimensions.getWidth() * screenDimensions.getHeight());
        int cellArea = cellSize * cellSize;
        int totalCells = screenArea / cellArea;
        int desiredCells = (int) (totalCells * density);
        int maxXCoordinate = (int) screenDimensions.getWidth() / cellSize;
        int maxYCoordinate = (int) screenDimensions.getHeight() / cellSize;
        List<Point> candidates = new ArrayList<Point>();
        IntStream.rangeClosed(0, maxXCoordinate).forEach(i -> {
            IntStream.rangeClosed(0, maxYCoordinate).forEach(j -> {
                candidates.add(new Point(i, j));
            });
        });
        Collections.shuffle(candidates);
        HashSet<Point> randomState = new HashSet<Point>(candidates.subList(0, desiredCells));
        return randomState;
    }

    // MODIFIES: this
    // EFFECTS: plays Conway's Game of Life
    //
    // continually renders each state of this.world with a delay of delay seconds on a JPanel attached to a JFrame
    // surface until the user closes the window
    public void play(float delay) {
        initializeFrame();
        timer = new Timer((int) (1000 * delay), this);
        timer.start();
    }

    // MODIFIES: this
    // EFFECTS: performs initial configuration of frame and renders the initial state of the world
    private void initializeFrame() {
        frame = new JFrame();
        frame.setSize(screenDimensions);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel(world.getState(), cellSize, screenDimensions);
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        world.nextState();
        Set<Point> nextState = world.getState();
        gamePanel.setState(nextState);
        gamePanel.repaint();
    }
}