package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TestWorld {

    Set<Point> startingState;

    @BeforeEach
    void setup() {
        startingState = new HashSet<Point>();
    }

    @Test
    void testRowStartState() {
        startingState = Stream.of(
                new Point(0, 1), new Point(0, 2), new Point(0, 3), new Point(0, 6))
                .collect(Collectors.toCollection(HashSet::new));
        World world = new World(startingState);
        world.nextState();
        Set<Point> nextState = world.getState();
        Set<Point> expectedState = Stream.of(new Point(-1, 2), new Point(0, 2), new Point(1, 2))
                .collect(Collectors.toCollection(HashSet::new));
        assertEquals(expectedState, nextState);
        world.nextState();
        nextState = world.getState();
        expectedState = Stream.of(new Point(0, 1), new Point(0, 2), new Point(0, 3))
                .collect(Collectors.toCollection(HashSet::new));
        assertEquals(expectedState, nextState);
    }

    @Test
    void testColumnStartState() {
        startingState = Stream.of(
                new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(6, 0))
                .collect(Collectors.toCollection(HashSet::new));
        World world = new World(startingState);
        world.nextState();
        Set<Point> nextState = world.getState();
        Set<Point> expectedState = Stream.of(new Point(2, -1), new Point(2, 0), new Point(2, 1))
                .collect(Collectors.toCollection(HashSet::new));
        assertEquals(expectedState, nextState);
        world.nextState();
        nextState = world.getState();
        expectedState = Stream.of(new Point(1, 0), new Point(2, 0), new Point(3, 0))
                .collect(Collectors.toCollection(HashSet::new));
        assertEquals(expectedState, nextState);
    }

    @Test
    void testSingletonState() {
        startingState = Stream.of(new Point(0, 0)).collect(Collectors.toCollection(HashSet::new));
        World world = new World(startingState);
        world.nextState();
        Set<Point> nextState = world.getState();
        Set<Point> expectedState = new HashSet<Point>();
        assertEquals(expectedState, nextState);
    }

    @Test
    void testEmptyState() {
        startingState = new HashSet<Point>();
        World world = new World(startingState);
        world.nextState();
        Set<Point> nextState = world.getState();
        Set<Point> expectedState = new HashSet<Point>();
        assertEquals(expectedState, nextState);
    }

}
