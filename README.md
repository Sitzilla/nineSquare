# nineSquare
A single player Tic-Tac-Toe game with a computer opponent that implements a minimax algorithm to play against the user.

## Technologies Used
- Java 8
- Swing
- MinMax Algorithm

## Summary
As one of my first major programming projects, this was a good introduction to Java and some of its standard packages.  I gained a firmer understanding of both event driven and object oriented programming, and implemented one of my first more complex algorithms.

The game is a simple tic-tac-toe board where the player goes first, and turns alternate between the player and the computer.  The computer is unbeatable and the best-case outcome is a tie-game with the human, and will often result in a computer-win if the the human player is not careful.  The computer will even employ basic strategies of blocking and forking whenever possible, which lead to its success in gameplay

The minmax algorithm is fairly basic due to the simplicity of the game. It recursively searches the gameboard to a depth equal to the remaining maximum moves to be played. Different point values are assigned to different game states (winning/losing, 2 consecutive pieces, etc.) and assigns points based on the outcomes. It opts for the choice that leads to the highest number of points.
