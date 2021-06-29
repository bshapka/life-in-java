# Life in Java
This project is an implementation of Conway's Game of Life (or Life for short) in Java.

## About Life
For information about the game, see [About Life](https://github.com/bshapka/life-in-x/blob/main/ABOUT_LIFE.md).
For information about the creator of the game, see [About John Conway](https://github.com/bshapka/life-in-x/blob/main/ABOUT_CONWAY.md).

## Running the Project
The following is a set of recommended directions for running the project. Note that these directions assume a JRE exists 
and that it has been associated with the shell command `java`. The project was developed and tested with the Java 16.0.1 SDK 
and language level set to 14. Compatibility with older JREs may exist but is not guaranteed.
* Start a shell session and navigate to the directory `/out/artifacts/life_in_java_jar` in the project/cloned repo
* Run the command `java -jar life-in-java.jar` to start the application

## Implementation Details
This section assumes basic familiarity with Life. If you are not familiar with Life but want to 
read this section, please read the section [About Life](https://github.com/bshapka/life-in-x/blob/main/ABOUT_LIFE.md), 
or consult a source like Wikipedia on the game.

All source files are well-documented, so explanations of or details about these files will not be supplied here.

Common implementations of Life represent the state of a world as a 2D list of bits or booleans. Typically 
a live cell is truthy while a dead cell is falsy. While this approach is intuitive, dead calls don't need
to be stored, and doing so can have notable costs in terms of memory usage.

An alternative approach is to store only the coordinates of live cells, requiring significantly less
memory. By representing coordinates as tuples and storing them in a set, further efficiencies are 
gained via hashing.

This implementation uses a toroidal grid. As such, objects that leave the screen on one edge will 
re-enter the screen with the same trajectory and velocity at the equivalent location on the opposing
edge.
