package ui;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

// represents an instance of Conway's Game of Life
public class Game {

    // EFFECTS: instantiates a Game. If cellSize is valid, then sets cellSize and screenDimensions. Also sets World to
    // an instance with a random state
    public Game(int cellSize) {
        // stub
    }

    // EFFECTS: instantiates a Game. If cellSize is valid, then sets cellSize and screenDimensions. Also sets World to
    // an instance with given initialState
    public Game(int cellSize, Set<Point> initialState) {
        // stub
    }

    public void play() {
        // stub
    }

    // EFFECTS: returns screen dimensions in pixels as a Point
    private Point getScreenDimensions() {
        return new Point(0, 0);
    }

    // EFFECTS: throws IllegalArgumentException if cellSize is negative or is too big for screen
    private void validateCellSize(int cellSize) throws IllegalArgumentException {
        // stub
    }

    // EFFECTS: returns a random state scaled to fill the screen when rendered
    private Set<Point> getRandomState() {
        return new HashSet<Point>();
    }
}