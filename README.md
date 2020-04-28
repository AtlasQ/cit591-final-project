# The Sudoku Game - Team 99
___

### Names of the members of your team
Jinhuang Lin, linjin@seas.upenn.edu  
Qianfan Yu, qianfan@seas.upenn.edu  
Qixiu Quan, atlasq@seas.upenn.edu  
___

### High level description of the project
We plan to build a desktop Sudoku application that can random generate sudoku board based on the difficulty selected by the player and suggest next moves as per player’s request. 
___

### A short work breakdown
Jinhuang: GUI development, JUnit test;
Qianfan: Development of Sudoku number table, suggestor, input validator , number class;
Qixiu: GUI development and Game Logic.
___

### Recommended environment
#### OS
```
Windows 10
```
#### Java version
```
java version "1.8.0_241"
Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
```
#### Package dependencies
```
javax.swing
org.junit
```
___

### How to run the game
In order to run the game, first, we suggest user to setup your local environment as recommened above. And then simply compile and run the .java file: 
```
Sudoku/src/Sudoku.java
```
If successful, the following Game window will pop up. Enjoy the game!

![](images/screenshot1.PNG)


#### New Game menu
You can use this menu to create a new game with your desired difficulty.
#### Check Option menu
You choose what level of checks you want during the game:
1. Check for Complience  
This function will help to check if there is any duplicated number in each row, column and box.
1. Check for Mistake  
This function will help to check if there is any mistakes (compare your input and answer) you have made.
#### Clear All button
By clicking this button, all of the hints and user inputs on the Sudoku table will be erased.
#### Hint button
By clicking this button, the game engine will populate 1 answer per click to the cell of you choice. Note that if you created a hint-cell, the value within it cannot be erased by pressing "backspace", "delete" key on your keyboard, or by clicking the Erase button. But you can clear all hints by clicking Clear All button.  
The number of remained hint chances is presented in the button text.
#### Erase button
By clicking this button, the game engine will simply erase the user input value of the cell of you choice. 
___

