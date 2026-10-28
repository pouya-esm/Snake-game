# Architecture

The project consists of five small classes, each with a single responsibility.

## `Point`

A minimal, reusable 2D coordinate helper (`x`, `y`) with getters/setters and a couple of distance utilities. Used by `Apple` to store its grid position.

## `Snake`

Holds the snake's body as two parallel arrays, `x[]` and `y[]` (pixel coordinates of each segment), plus a `bodyParts` counter and the current `direction` (`'U'`, `'D'`, `'L'`, `'R'`).

- **`move()`** shifts every body segment to the position of the segment in front of it (`x[i] = x[i-1]`), then advances the head (`x[0]`/`y[0]`) by one grid unit in the current direction.
- **`draw(Graphics)`** renders the head in yellow and the rest of the body in green.

## `Apple`

Represents the food item: a random grid-aligned position (`Point`). Re-created at a new random position each time it's eaten.

## `GameBoard`

A `JPanel` that owns the `Snake` and `Apple` instances, keeps the running `score` and the session-wide `maxScore`, hosts the "Play Again" `JButton`, and coordinates each frame:

- **`move()`** advances the snake.
- **`checkApple()`** detects if the snake's head reached the apple; if so, increments `score` (updating `maxScore` if beaten), grows the snake, and spawns a new apple (the score lives on the board rather than on the `Apple` instance, since a new `Apple` object is created on every bite).
- **`checkCollisions()`** detects wall collisions.
- **`checkBody()`** detects self-collisions (head touching any other body segment).
- **`paintComponent(Graphics)`** draws the apple, the snake and the score each frame, or the "Game Over" screen (final score + max score) once the game has ended.
- **`restart()`** resets the score, the snake, the apple and the `over` flag to start a fresh game, then notifies `Game` (via `RestartListener`) to resume the timer.
- **`playAgainButton`** — a `JButton`, hidden during normal play and shown only when `over` is true; its click handler calls `restart()`.
- **`MyKeyAdapter`** (inner class) — listens for arrow key presses and updates `Snake.direction` (ignoring input that would reverse the snake directly into itself); while the game is over, it instead listens for **Enter** or **Space** to call `restart()`.

## `Game`

The entry point (`main`): a `JFrame` that hosts the `GameBoard` and drives the game loop with a `javax.swing.Timer` firing every `DELAY` (90 ms). On every tick (`actionPerformed`), while the game is running, it advances the snake, checks for an apple and for collisions, and — if a collision occurred — stops the timer and marks the board as "game over". It then calls `repaint()`, which triggers `GameBoard.paintComponent` to redraw the current frame. `Game` also registers a `GameBoard.RestartListener` that resumes (`running=true`) and restarts the `Timer` whenever `GameBoard.restart()` is triggered.

## Game loop flow

```
Timer tick (every 90ms)
  → Game.actionPerformed
      → GameBoard.move()
      → GameBoard.checkApple()
      → GameBoard.checkCollisions() / checkBody()
          → if collision: stop timer, GameBoard.setOver(true)
      → repaint()
          → GameBoard.paintComponent()
              → draws apple, snake, score (or "Game Over" + final score + max score)

Play Again button click, or Enter/Space while game over
  → GameBoard.restart()
      → reset score, snake, apple, over=false
      → RestartListener.onRestart() → Game resumes the Timer
```
