## Description
This is a simple game that was made for my CSC 130 class. I was given a basic Java Gaming API that was created by my Professor, Michael Phillips. 
In the class we went through checkpoints until we have our final project finished which is a single room game.
## The requirements for the project are:
Algorithms

The project must DO the following (behavior, algorithms):

    Be a single screen “game” level (no scrolling)

    Have a character that moves in all four(4) directions (up, down, left, right)

    Character must have animation equal to that in Checkpoint #3 for EACH direction!

    The edges of the screen must be wall images that visually contain the “room”
    Room must have a "ground" image (an image under the character's feet). No solid black!

    These images must have bounding box collision detection to prevent the player from walking through them

    There must be at least two (2) items inside of the “level” that can be examined when nearby and facing the object. Examining is done by looking at the item nearby and pressing the space bar

    The program must display a description of the item examined when a user inspects it

    The program must be free of errors, crashes, or warnings for credit

Data Structures

The project must fulfill the minimum Data Structure requirements:

    Use at least one of the following Java Collections: ArrayList, Stack, or Queue for image data

    Create a custom data type for the bounding box collision object (single bounding box with behavior)

    Have a container that holds a collection of bounding boxes neatly


## Checkpoint 1
Checkpoint 1 was simple where I had to move a sprite that was given to me along with text from one corner of the screen to another. I also had to change the color and words in the text. 

## Checkpoint 2
In Checkpoint 2, I had to create a Vector2D class which held coordinates. This class would be used to move the sprite across the screen by transferring each Vector2D object from one queue (vecs1) to another (vecs2). 
After all the frames are transferred and the sprite went across the screen, the frames would go back to the first queue and start again. In this checkpoint I had to use Queue's, the Stopwatch from the Gaming API to move the sprite across the screen.