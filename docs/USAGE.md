# Usage Guide

## Starting the game

```bash
java -cp bin Snake.Game
```

A 600×600 window titled "Snake" opens. The snake starts in the top-left area of the board and immediately begins moving to the right.

## Controls

Use the arrow keys to steer the snake:

- **↑ / ↓ / ← / →** — change direction

You cannot reverse directly into yourself: for example, while moving right, pressing left is ignored. This prevents an instant, unavoidable collision with your own body.

## Objective

- Red apples appear at random positions on the board.
- Guide the snake's head (yellow square) onto an apple to eat it.
- Each apple eaten:
  - Increases your score by 1 (shown at the top of the board)
  - Grows the snake by one body segment (green squares)

## Game over

The game ends when the snake's head:

- Hits any of the four walls, or
- Collides with its own body

When this happens, the timer stops and a **"Game Over"** screen is displayed, showing your final score and the **Max Score** (the highest score reached since the program started).

## Playing again

From the "Game Over" screen, you can start a new game instantly, without closing the window:

- Click the **Play Again** button, or
- Press **Enter**, or
- Press **Space**

The board, snake and score all reset, while the Max Score is kept and only replaced once you beat it.
