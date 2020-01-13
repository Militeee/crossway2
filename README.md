[![Build Status](https://travis-ci.org/Militeee/crossway2.svg?branch=master)](https://travis-ci.org/Militeee/crossway2)

# Crossway

Crossway is a game played with a Go set designed by Mark Steere in 2007.

The goal of the game is to create a continous route of same color stones between two parallel sides of the board. 
Respectively connecting the left side to the right one for the white player and the up to the bottom for the black player.
Each stone in the sequence must be connected to neighboring stones in the sequence via horizontal, vertical, or diagonal adjacencies. 

Players take turns adding their stones to the board, one stone per turn. 
A player must never complete the formation shown in Figure or a 90 degree rotation of this formation. 

![Illegal move](crossway.png)

White has the option of switching colors with Black, and claiming Black’s first move as his own.

Original rules can be found [at the author website](https://boardgamegeek.com/boardgame/30517/crossway).


### Excution

Clone the folder with `git clone`

Build with `javac /crossway2/src/main/java/dssc/crossway/Main.java `

`cd crossway2/build/classes/java/main`

Run with 
`java dssc.crossway2.Main`