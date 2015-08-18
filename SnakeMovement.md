#How the snake moves

# How the snake moves (internally) #

The snake is a list of grid coordinates, each representing a segment of the snake's body.

Each time the snake moves, the new head position is known:

For east: new X = current X + 1
For north: new Y = current Y + 1

and so forth.

To actually move the snake, we create a new SnakeSegment with the new co-ordinates and insert it at the head of the list.

We then remove the item at the tail of the list.

A snake with 3 segments may look like this:

10,10
10,9
10,8




The head is at position 10,10.

Let's move the snake East.

We know that the new head position will be 11,10.  We create a new SnakeSegment with those coordinates and add it to the head of the list.

11,10
10,10
10,9
10,8




But wait, we didn't want to grow the snake!  So we have to remove the tail segment

11,10
10,10
10,9




Now the snake has moved one east.

Now that we have moved the snake, we can test for collision using the Snake list as input.

# Growing the Snake #

The question has come up of how to grow the snake whe it eats a fruit.  Specifically, to which grid position the new segment will be assigned.

In the traditional snake game, the new segment is added to the tail.  The most logical position would be the former tail position, but that is no longer in the list of segments.

So, the best way to deal with this is to have the moveSnake method return the SnakeSegment which it removed from the tail of the list.  The SnakeSegment is retained through the collision tests and, if a fruit collision is detected, then the retained SnakeSegment is added back to the tail.