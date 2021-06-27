package ui;

// launching point for the application
public class Main {

    // EFFECTS: plays the game with the specified cellSize
    public static void main(String[] args) {
        Game game = new Game(5);
        game.play();
    }
}