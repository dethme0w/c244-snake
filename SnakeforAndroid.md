# Introduction #

This project is an Android version of the very old Snake game.

# History #

According to Wikipedia editors, Snake is a casual video game that originated during the late 1970s in arcades and has maintained popularity since then, becoming something of a classic.  After it became the standard pre-loaded game on Nokia mobile phones in 1998, Snake found a massive new audience.

# Basic Game Mechanics #

The game is played on a playing field ("the board") which is a grid of fixed size.  The snake is composed of body segments, each of which occupies a grid square on the board.  The snake starts out only one square in length, but as the game progresses, the snake's body length increases.

Typically, the board contains obstacles which the snake must never touch, and fruit which the snake consumes to earn points and grow.

If the snake touches a fruit, its body length grows by one square.

If the snake eats all the fruit, a new level starts, the board is reset with new fruit, and the snake's movement is faster, providing a greater challenge.  This continues until the game is over.

If the snake touches the edge of the board, or an obstacle, or itself, the game is over.

The object is to grow the snake as long as possible without touching the edge, an obstacle, or itself.

# Our implementation #

Use cases have been defined for the game's basic mechanics, including fruit, snake growth, and the end-game conditions.  Obstacles on the board are not currently in scope.

Our aim for this project is not merely to implement the venerable Snake game itself but to use Android orientation sensors as an input device to control the snake's movement.  If the player tilts the device downward to the left, the snake changes its direction left (west).  Tilt the device right and the snake moves right (east).  Tilt it forward and the snake moves up (north).  Back, down (south).

An on screen display will show the player's score, the current high score, and possibly the number of fruit consumed/remaining and snake speed and/or length.

# Use Case Discussion #

See the Use Cases Google Doc: https://docs.google.com/document/d/1kWm8EAyP88QuplWpUaXaSiMng1q73MfOzThh04xvObc/edit?hl=en-GB&forcehl=1