package model;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

// represents a 2D world in grid form with each cell in the grid being live or dead (but not both)
public class World {

    private Set<Point> state;

    // EFFECTS: instantiates a World by setting state
    public World(Set<Point> startingState) {
        state = startingState;
    }

    // MODIFIES: this
    // EFFECTS: updates state by applying the rules of the game
    public void nextState() {
        // stub
    }

    // EFFECTS: returns state
    public Set<Point> getState() {
        return state;
    }

}
