package model;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Point> neighbours = generateNeighbours(state);
        Set<Point> nextState = neighbours.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)))
                .entrySet().stream()
                .filter(e -> e.getValue().equals(3) || (e.getValue().equals(2) && state.contains(e.getKey())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(HashSet::new));
        state = nextState;
    }

    // EFFECTS: returns the coordinates of the neighbours of the coordinates in the given list
    //
    // The neighbourhood around a given coordinate is defined to be the the Moore neighbourhood
    // around that coordinate. In other words, the neighbourhood around a given coordinate is
    // the 8 closest other coordinates.
    private static List<Point> generateNeighbours(Set<Point> state) {
        List<Point> neighbours = new ArrayList<Point>();
        Supplier<IntStream> offsetSupplier = () -> IntStream.rangeClosed(-1, 1);
        state.forEach(point -> {
            offsetSupplier.get().forEach(xOffset -> {
                offsetSupplier.get().forEach(yOffset -> {
                    if (!(xOffset == 0 && yOffset == 0)) {
                        neighbours.add(new Point(point.x + xOffset, point.y + yOffset));
                    }
                });
            });
        });
        return neighbours;
    }

    // EFFECTS: returns state
    public Set<Point> getState() {
        return state;
    }
}
