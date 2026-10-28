# Snake

A classic Snake game implemented in Java with Swing. Built as a university assignment to practice object-oriented design, Java AWT/Swing graphics, event handling, and basic game-loop mechanics.

## Features

- Classic grid-based Snake gameplay on a 600×600 board
- Keyboard controls (arrow keys) with reversal prevention (can't instantly turn back into yourself)
- Apples spawn at random grid positions; eating one grows the snake and increases the score
- Collision detection against the walls and the snake's own body
- Live score display, a session high score ("Max Score"), and a "Game Over" screen
- Restart instantly after "Game Over" — click **Play Again**, or press **Enter** / **Space**
- Game loop driven by a `javax.swing.Timer`

## Project structure

```
Snake/
├── src/
│   └── Snake/
│       ├── Game.java        # Entry point: JFrame, game loop (Timer/ActionListener)
│       ├── GameBoard.java   # JPanel: rendering, collision checks, key handling
│       ├── Snake.java       # The snake's body segments and movement
│       ├── Apple.java       # The apple: random position, rendering
│       └── Point.java       # Small reusable 2D point/coordinate helper
├── docs/
│   ├── USAGE.md              # How to play
│   └── ARCHITECTURE.md       # Class design and how the game loop works
├── .gitignore
└── README.md
```

## Requirements

- Java Development Kit (JDK) 8 or newer (uses only `java.awt`/`javax.swing`, no external dependencies)

## Build & run

From the project root:

```bash
# Compile
mkdir -p bin
javac -d bin src/Snake/*.java

# Run
java -cp bin Snake.Game
```

You can also open the project in Eclipse (or any Java IDE) and run `Game.java` directly.

## Controls

| Key | Action |
|-----|--------|
| ↑ | Move up |
| ↓ | Move down |
| ← | Move left |
| → | Move right |
| Enter / Space | Play again (after "Game Over") |

See [docs/USAGE.md](docs/USAGE.md) for more on gameplay and scoring, and [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) for an overview of how the classes fit together.

## Known limitations

- Board size and game speed are fixed constants (`Game.java`), not configurable from the UI.
- No pause option.
- The "Max Score" is only kept for the current run of the program — it resets when the window is closed and reopened (no persistence to disk).
