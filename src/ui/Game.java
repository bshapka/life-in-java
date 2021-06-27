package ui;

import model.World;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.IntStream;

// represents an instance of Conway's Game of Life
public class Game {

    private int cellSize;
    private Dimension screenDimensions;
    private World world;

    // EFFECTS: instantiates a Game. If cellSize is valid, then sets cellSize and screenDimensions. Also sets World to
    // an instance with a random state
    public Game(int cellSize) throws IllegalArgumentException {
        screenDimensions = getScreenDimensions();
        validateCellSize(cellSize);
        this.cellSize = cellSize;
        Set<Point> randomState = getRandomState(0.075f);
        world = new World(randomState);
    }

    // EFFECTS: instantiates a Game. If cellSize is valid, then sets cellSize and screenDimensions. Also sets World to
    // an instance with given initialState
    public Game(int cellSize, Set<Point> initialState) throws IllegalArgumentException {
        screenDimensions = getScreenDimensions();
        validateCellSize(cellSize);
        this.cellSize = cellSize;
        world = new World(initialState);
    }

    public void play() {
        // stub
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
        int screenArea = (int) screenDimensions.getWidth() * (int) screenDimensions.getHeight();
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
}