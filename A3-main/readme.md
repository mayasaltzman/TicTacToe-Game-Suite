# CIS 2430 A3

This project contain an object oriented version of tic tac toe and numerical tic tac toe. Tictactoe can be ran on the terminal as a text game or on the GUI. Numerical tic tac toe is run on the GUI.

## Description

This project is divided into four packages. The boardgame package contains the code provided by Professor McCuaig that is overwritten in my other classes. This package also holds the SaveToFile class which handles the file IO. The game package has the GameUI class which builds the home screen for my GUI game. The numericaltictactoe package has all the classes needed for a functional numerical tic tac toe game. This includes NumericalTicTacToeGame which extends BoardGame and implements Savable. This class holds all the methods needed for game play. The NumericalTicTacToeView class creates the GUI the game is played on. Lastly the tictactoe package has all the classes needed for a functional tic tac toe game. Including TicTacToe which also which extends BoardGame and implements Savable. This class has the methods for tic tac toe game play. In addition to a TicTacToeTextUI so the text version of the tic tac toe game can be played. Finally, the TicTacToeView class which creates the tic tac toe GUI.

## Getting Started

### Dependencies
- gradle

- docker

- pip3

- java


### Executing program

* How to build and run the program
* Open your the terminal
* type the following commands in the terminal:

1. 
```
gradle build
```
* output:
```
BUILD SUCCESSFUL in 4s
3 actionable tasks: 3 up-to-date
```
2. 
* to run program:
```
java -jar build/libs/A3.jar
```
* output: GUI will run

* to run textUI by class:
```
java -cp build/classes/java/main tictactoe.TicTacToeTextUI
```
* output: tic tac toe grid will print on terminal

* to run GUI by class:
```
java -cp build/classes/java/main game.GameUI
```
* output: GUI will run



## Limitations

1. Loading files does not work on the GUI but does work in the TextUI
2. FILES ONLY SAVE AND LOAD FROM ASSETS FOLDER

## Note about player profile and saving/loading files
Information for player profile is saved in a txt file.
It holds the players username and the number of games played.
It does not need a specified path. 
File should just be in A3 folder.
I included an example file in my submission.

## Author Information

Maya Saltzman - saltzmam@uoguelph.ca

## Development History
* 0.11
    * Finished player profile. See [d6ab37d6]
* 0.10
    * Fixing bugs and adding file chooser. See [33ef70d7]
* 0.9
    * Working numerical tic tac toe on GUI. See [389aad7a]
* 0.8
    * Fixing tic tac toe GUI bugs. See [5e5a646a]
* 0.7
    * GUI saves to file. See [28b726a1]
* 0.6
    * GUI home screen. See [cd1b0797]
* 0.5
    * Fxing tic tac toe and numerical tic tac toe bugs. See [d0af0208]
* 0.4
    * Loading files works. See [536929ae]
* 0.3
    * File saving works. See [45c1468e]
* 0.2
    * TextUI finished. See [9516f956]
* 0.1
    * Skeleton of tic tac toe game See [9135ea1b]

## Acknowledgments
https://gitlab.socs.uoguelph.ca/examples/exampleguiproject
Used Professors McCuaig's exampleproject as inspiration for my code especially GUI layout.
Specifically I use the contents of the method makeButtonGrid in KakuroView.java. See [934f57a6]
Other methods that were used as inspiration were:
SetGameControllor in KakuroView.java. See [934f57a6]
GetString in Grid.java. See [9516f956]
