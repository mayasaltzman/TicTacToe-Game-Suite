package tictactoe;

import java.util.Arrays;
import java.util.ArrayList;

/**
This class has methods needed to play a game
of tictactoe.
 @author Maya Saltzman
*/
public class TicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{

    private ArrayList<String> board = new ArrayList<>();
    private boolean isWinner = false;
    private String player = "O";
    private int val = -1;
    private int turnCount = 0;
    private int ticTacToeGameCount = 0;

    public TicTacToeGame(int w, int h){
        super(w,h);
        ticTacToeGameCount++;
    }

    /**
    newGame: resets winner and board so player can start a new game
    in: 
    out: void
    */
    @Override
    public void newGame(){
       super.newGame();
       isWinner = false;
       board.removeAll(board);
       setBoard();
       ticTacToeGameCount++;
    }

    /**
    getGameCount: returns number of times user has played a game
    in: 
    out: int
    */
    public int getGameCount(){
        return ticTacToeGameCount;
    }

    /**
    setBoard: initializes empty board
    in: 
    out: void
    */
    public void setBoard(){
        for (int i = 0; i < 9; i++) {
            board.add("0");
        }

    }

    /**
    setTurn: sets player turn to X or O
    in: String
    out: void
    */
    public void setTurn(String turn){
        if(turn == "X"){
            turn = "O";
        }else if(turn == "O"){
            turn = "X";
         }
        player = turn;
    }

    /**
    getTurn: returns which players turn it is
    in: 
    out: String
    */
    public String getTurn(){
        return player;
    }

    /**
    takeTurn: calculates position and lets user place their piece in that position, 
    returns false if piece cannot be placed
    in: int, int, String
    out: boolean
    */
    @Override
    public boolean takeTurn(int across, int down, String input){
        boolean turnTaken = true;
        try{
            setValue(across,down,input);
            int position = (down -1) * 3 + (across) - 1; //calculating position based on row and column
            String temp = board.get(position);
        if (temp.equals("0")) { //position empty
            board.set(position,input);
        }else{
            turnTaken = false;
        }
        }catch(IndexOutOfBoundsException e){
        }
        return turnTaken;
    }
    
    /**
    NOT USED TAKE TURN METHOD
    */
    @Override
    public boolean takeTurn(int across, int down, int input){
        //not used
        setValue(across,down,input);
        return true;
    }

    /**
    setTurnCount: sets how many times players have placed their pieces
    in: int
    out: void
    */
    public void setTurnCount(int count){
         turnCount = count;
    }

    /**
    getTurnCount: returns turnCount
    in: 
    out: int
    */
    public int getTurnCount(){
        return turnCount;
    }

    /**
    checkAcross: checks across win conditions and returns true if one of them is satisfied
    in: 
    out: boolean
    */
    private boolean checkAcross(){
        boolean test = false;
        if (board.get(0).equals(board.get(1)) && board.get(0).equals(board.get(2)) 
            && board.get(2).equals(board.get(0)) && !board.get(0).equals("0") 
            && !board.get(1).equals("0") && !board.get(0).equals("2")){
           test = true;
         }else if (board.get(3).equals(board.get(4)) && board.get(4).equals(board.get(5)) 
            && board.get(5).equals(board.get(3)) && !board.get(3).equals("0")  
            && !board.get(4).equals("0") && !board.get(0).equals("5")){
           test = true;
        }else if (board.get(6).equals(board.get(7)) && board.get(7).equals(board.get(8)) 
            && board.get(8).equals(board.get(6)) && !board.get(6).equals("0") 
            && !board.get(7).equals("0") && !board.get(8).equals("0")){
           test = true;
        }
        return test;
    }

    /**
    checkDown: checks down win conditions and returns true if one of them is satisfied
    in: 
    out: boolean
    */
    private boolean checkDown(){
        boolean test = false; 
        if (board.get(0).equals(board.get(3)) && board.get(3).equals(board.get(6)) 
            && board.get(6).equals(board.get(0)) && !board.get(0).equals("0") 
            && !board.get(3).equals("0") && !board.get(6).equals("0")){
           test = true;
        }else if (board.get(1).equals(board.get(4)) && board.get(4).equals(board.get(7)) 
            && board.get(7).equals(board.get(1)) && !board.get(1).equals("0") 
            && !board.get(4).equals("0") && !board.get(7).equals("0")){
           test = true;
        }else if (board.get(2).equals(board.get(5)) && board.get(5).equals(board.get(8)) 
            && board.get(8).equals(board.get(2)) && !board.get(2).equals("0") 
            && !board.get(5).equals("0") && !board.get(8).equals("0")){
           test = true;
        }
        return test;
    }

    /**
    checkDiagnol: checks diagnol win conditions and returns true if one of them is satisfied
    in: 
    out: boolean
    */
    private boolean checkDiagnol(){
        boolean test = false;
        if (board.get(0).equals(board.get(4)) && board.get(4).equals(board.get(8)) 
            && board.get(0).equals(board.get(8)) && !board.get(0).equals("0") 
            && !board.get(4).equals("0") && !board.get(8).equals("0")){
           test = true;
        }else if (board.get(6).equals(board.get(4)) && board.get(4).equals(board.get(2)) 
            && board.get(6).equals(board.get(2)) && !board.get(2).equals("0") 
            && !board.get(4).equals("0") && !board.get(6).equals("0")){
           test = true;
        } 
        return test;
    }

    /**
    isDone: returns true if one of the win conditions is satisfied
    in: 
    out: boolean
    */
    @Override
    public boolean isDone(){
        if (checkAcross() || checkDiagnol() || checkDown()) {
            isWinner = true;
        }
        return isWinner;
    }

    /**
    getWinner: returns an int representation of if there was a winner, no winner, or a tie
    in: 
    out: int
    */
    @Override
    public int getWinner(){
        if (isWinner) {
            if (player == "X") {
                val = 1;
            }else if (player == "O") {
                val = 2;
            }
        }else if(!isWinner){
            if (turnCount == 9) { //tie
                val = 0;
            }else{
                val = -1;
            }
        }
        return val;
    }

    /**
    getGameStateMessage: returns game state message based on the int representation of the winner
    in: 
    out: String
    */
    @Override
    public String getGameStateMessage(){
        int messageType = getWinner();
        String message = "";
        if (messageType == 1 || messageType == 2) {
            message = "The winner is " + player;
        }else if (messageType == 0) {
            message = "Cats Game"; //tie
        }else if(messageType == -1){
            message = "Player " + player + "'s turn";
        }
        return message;
    }

    /**
    getStringToSave: returns string representation of board to save to file
    in: 
    out: String
    */
    @Override
    public String getStringToSave(){
        String tempString = "";
        tempString = player + "\n"; //puts player whose turn it is in top line of file
        int i=0;
        for(String j: board){
            if (j != "0") {
                tempString = tempString + j + ","; //adding comma to represent empty position
            }else if(j != "\n"){
                tempString += ","; //adding commas for newlines
            }
            i++;
            if(i == 3){
                tempString = tempString + "\n"; //adding new lines every time a full row has been looped through
                i = 0;
            }
        }
        return tempString;
    }


    /**
    loadSavedString: parses string from file back into gameboard
    in: String
    out: void
    */
    @Override
    public void loadSavedString(String toLoad){
        String tempStr = " "; //will parse toLoad into this temp string
        int count = 0;
        int none;
        player = Character.toString(toLoad.charAt(0)); //gets player turn from first line of file
        for (int i = 2; i < toLoad.length()-1; i++) { //starts at 2 to skip new line
           if (toLoad.charAt(i) == ',') { //if comma and next char is also a comma
               if (toLoad.charAt(i+1) == ',' || toLoad.charAt(i+1) == '\n') {
                tempStr += "0";  //empty space 
               }
           }else if (toLoad.charAt(i) == '\n') {
               none = 0;
           }else{
                tempStr += Character.toString(toLoad.charAt(i)); //add char to String
           }
        }
        //adding valid chars in tempStr to the board
        for (int i = 0; i < 9; i++) {
            if (tempStr.charAt(i) != 'X' && tempStr.charAt(i) != 'O' && tempStr.charAt(i) != '0') {
                count++;
            }
            try{
                board.set(i,Character.toString(tempStr.charAt(count)));
            }catch(StringIndexOutOfBoundsException e){
            }

            count++;
        }
    }

    /**
    toString: creates string represenatation of board
    in: 
    out: String
    */
    @Override
    public String toString(){
        String tempString = "";
        int i=0;
        for(String j: board){ //looping through board and adding elements to tempString
            tempString = tempString + " "+ j;
            i++;
            if(i == 3){ //looped through complete row add new line
                tempString = tempString + "\n";
                i = 0;
            }
        }
       return tempString;
    }

}