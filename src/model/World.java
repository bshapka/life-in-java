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
        Set<Point> nextState = state.stream()
                .map(World::region)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)))
                .entrySet().stream()
                .filter(e -> e.getValue().equals(3) || (e.getValue().equals(4) && state.contains(e.getKey())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(HashSet::new));
        state = nextState;
    }

    // EFFECTS: returns the list of coordinates of the region around the given coordinate
    //
    // The region around a given coordinate is defined here to be the union of a) the Moore neighbourhood around the
    // given coordinate and b) the given coordinate itself. In other words, the region around a coordinate is the
    // coordinate itself plus the 8 closest other coordinates.
    private static List<Point> region(Point point) {
        Supplier<IntStream> streamSupplier = () -> IntStream.rangeClosed(-1, 1);
        List<Point> region = new ArrayList<Point>();
        streamSupplier.get().forEach(xOffset -> {
            streamSupplier.get().forEach(yOffset -> {
                region.add(new Point(point.x + xOffset, point.y + yOffset));
            });
        });
        return region;
    }

    // EFFECTS: returns state
    public Set<Point> getState() {
        return state;
    }
}
