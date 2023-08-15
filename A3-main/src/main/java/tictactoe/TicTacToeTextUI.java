package tictactoe;

import java.util.Scanner;
import boardgame.SaveToFile;

/**
This class allows the user to player
a TextUI version of tictactoe in the terminal as well
as the GUI.
 @author Maya Saltzman
*/
public class TicTacToeTextUI{
    private TicTacToeGame myGame = new TicTacToeGame(3,3);
    private SaveToFile saveGame = new SaveToFile();
    private Scanner sc = new Scanner(System.in);
    private int turnCount = 0;
    private int down = 0;
    private int across = 0;
    private boolean isWinner  = false;
    private String fileNameToSave = "";
    private String fileNameToLoad = "";
    private char save = ' ';
    private char playAgain = ' ';

    /**
    printRules: prints rules
    in: 
    out: void
    */
    public void printRules(){
        System.out.println("Welcome to TicTacToe!\nFirst player to get 3 in a row wins");
        System.out.println("To quit enter 4 for the row and column values");
        System.out.println("To play from a previous game enter 5 for the row and column values\n");
    }

    /**
    setAcross: sets across input
    in: 
    out: void
    */
    public void setAcross(){
         boolean isNum = false;
         String test = " ";
         int temp = 0;
         System.out.println("Enter a column number between 1-3:");
         test = sc.next();

         while(!isNum){
            try{
               temp = Integer.parseInt(test);
               isNum = true;
               across = temp;
            }catch(NumberFormatException e){
                System.out.println("Enter a column number between 1-3:");
                test = sc.next();
            }
        }

    }

    /**
    getAcross: returns across value
    in: 
    out: int
    */
    public int getAcross(){
        return across;
    }

    /**
    setDown: sets down input
    in: 
    out: void
    */
    public void setDown(){
        boolean isNum = false;
        String test = " ";
        int temp = 0;
        System.out.println("Enter a row number between 1-3:");
        test = sc.next();

        while(!isNum){
            try{
               temp = Integer.parseInt(test);
               isNum = true;
               down = temp;
            }catch(NumberFormatException e){
                System.out.println("Enter a row number between 1-3:");
                test = sc.next();
            }
        }
    }

    /**
    getDown: returns down value
    in: 
    out: int
    */
    public int getDown(){
        return down;
    }

    /**
    setFileNameToSave: lets user decide if they want to save the game and ends the game
    in: 
    out: boolean
    */
    public boolean setFileNameToSave(){
        boolean isSave = false;
        System.out.println("Would you like to save your game?\nType y for yes and n for no");
        save = sc.next().charAt(0);

        if (save == 'y' || save == 'Y') {
            isSave = true;
            System.out.println("Enter the file you want to save your game to: ");
            fileNameToSave = sc.next();
        }
            System.out.println("Thanks for playing\nWould you like to play again?\nType y for yes and n for no");
            playAgain = sc.next().charAt(0);
            if (playAgain == 'y' || playAgain == 'Y') {
                myGame.newGame();
                playGame(1);   
            }
        
        return isSave;
    }

    /**
    getFileNameToSave: returns file name 
    in: 
    out: String
    */
    public String getFileNameToSave(){
        return fileNameToSave;
    }

    /**
    saveGame: saves game
    in: 
    out: void
    */
    public void saveGame(){
         myGame.getStringToSave();
        try{        
            saveGame.save(myGame, getFileNameToSave(), "assets");
        }catch(RuntimeException e){
            System.out.println("failed to write to file");
        }
    }

    /**
    setFileNameToLoad: lets user decide if they want to continue a previous game
    in: 
    out: void
    */
    public void setFileNameToLoad(){
        System.out.println("Enter the file you would like to load your previous game from: ");
        fileNameToLoad = sc.next();
    }

    /**
    getFileNameToLoad: returns fileNameToLoad
    in: 
    out: String
    */
    public String getFileNameToLoad(){
        return fileNameToLoad;
    }

    /**
    loadGame: continues saved game
    in: 
    out: void
    */
    public void loadGame(){
        setFileNameToLoad();
        try{
            saveGame.load(myGame, getFileNameToLoad(), "assets");
        }catch(RuntimeException e){
        }
        playGame(1);
    }

    /**
    checkRange: checks range of input
    in: 
    out: boolean
    */
    public boolean checkRange(){
        boolean endGame = false;
        setDown();
       while(getDown() < 1 || getDown() > 5){
            setDown();
        }
        setAcross();
        while(getAcross() < 1 || getAcross() > 5){
            setAcross();
        }
        if (getAcross() == 5 || getDown() == 5) {
            endGame = true;
            loadGame();
        }
        if (getDown() == 4 || getAcross() == 4) {
            endGame = true;
            if (setFileNameToSave()) {
                saveGame();         
            }
        }
        return endGame;
    }

    /**
    playGame: facilitates game play
    in: int
    out: void
    */
    public void playGame(int type){
        if (type == 0) {
            myGame.setBoard();  
        }
        myGame.setTurn(myGame.getTurn());
        printRules();
        System.out.println(myGame);
        while(turnCount != 9){
            System.out.println(myGame.getGameStateMessage());
            if (checkRange()) {
                break;
            }
            while(!myGame.takeTurn(getAcross(), getDown(), myGame.getTurn())){
            if (checkRange()) {
                break;
            }
            }
            System.out.println(myGame);
            turnCount++;
            myGame.setTurnCount(turnCount);
            if (myGame.isDone()) {
                myGame.getWinner();
                break;
            }
            myGame.setTurn(myGame.getTurn());
        }
        System.out.println(myGame.getGameStateMessage());
    }

    public static void main(String[] args){ 
        TicTacToeTextUI play = new TicTacToeTextUI();
        play.playGame(0);
    }

}