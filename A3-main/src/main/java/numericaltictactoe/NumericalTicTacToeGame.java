package numericaltictactoe;

import java.util.ArrayList;

/**
This class has methods needed to play a game
of numerical tictactoe.
 @author Maya Saltzman
*/
public class NumericalTicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{

    private ArrayList<String> board  = new ArrayList<>();
    private boolean isWinner = false;
    private String player = " ";
    private int val = 0;
    private int turnCount = 0;
    private int messageType = 0;
    private int position = 0;
    private int gameCount = 0;
    private boolean turnTaken = true;
    
    public NumericalTicTacToeGame(int w, int h){
       super(w,h);
       gameCount++;
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
       gameCount++;
    }

    /**
    getGameCount: returns number of times user has played a game
    in: 
    out: int
    */
    public int getGameCount(){
       return gameCount;
    }

    /**
    setBoard: initializes empty board
    in: 
    out: void
    */
    public void setBoard(){
        for (int i = 0; i < 9; i++) {
            board.add("-");
       }
    }

    /**
    setTurn: sets player turn to be even or odd
    in: String
    out: void
    */
    public void setTurn(String turn){
        if (turn  == "odd") {
            turn = "even";
        }else if (turn == "even") {
            turn = "odd";
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
    isEven: helper method to determine if a input is even and if it is not its odd
    in: int
    out: boolean
    */ 
    private boolean isEven(int num){
         boolean even = false;

         if (num % 2 == 0) {
             even = true;
         }else{
             even = false;
         }

         return even;
    }

    /**
    takeTurn: calculates position and lets user place their piece in that position, 
    returns false if piece cannot be placed
    in: int, int, String
    out: boolean
    */
    @Override
    public boolean takeTurn(int across, int down, String input){
        try{
            setValue(across,down,input);
        }catch(IndexOutOfBoundsException e){
            turnTaken = false;
        }
        position = (down -1) * 3 + (across) - 1; //calculating position on grid based of row and column
        try{
            if (player == "odd") { //checks to make sure odd player only places odd number
                if (!isEven(Integer.parseInt(input))) { //odd player must place odd num
                if (board.get(position) == "-") { //position empty
                    board.set(position,input);
                }
                }else{
                    turnTaken = false;
                }
             }else if (player == "even") { //checks to make sure even player only places even number
                if (isEven(Integer.parseInt(input))) { //even player must place even num
                if (board.get(position) == "-") { //position empty
                    board.set(position,input);
                }
                }else{
                    turnTaken = false;
                }
            }
        }catch(NumberFormatException e){
            turnTaken = false;
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
    checkInputRange: checks if input is between 0-9 and returns and if its in range or not
    in: int
    out: boolean
    */
    public boolean checkInputRange(int input){
        boolean inRange = true;
        if (input > 9 || input < 0) {
            inRange = false;
        }
        return inRange;
    }

    /**
    checkInputType: checks if String is non num and returns false if it is a non num
    in: String
    out: boolean
    */
    public boolean checkInputType(String input){
        boolean isNum = false;
        String test = " ";
        int temp = 0;

        while(!isNum){
            try{
                temp = Integer.parseInt(test); //will parse if integer
                isNum = true;
            }catch(NumberFormatException e){ //does not parse
                 isNum = false;
            }
        }
        return isNum;
    }

    /**
    checkAcross: checks across win conditions and returns true if one of them is satisfied
    in: 
    out: boolean
    */
    private boolean checkAcross(){
       int sum = 0;
       boolean test = false;
       if (board.get(0) != "-" && board.get(1) != "-" && board.get(2) != "-") { //upper row
           sum = Integer.parseInt(board.get(0)) + Integer.parseInt(board.get(1)) + Integer.parseInt(board.get(2));
           if (sum == 15) {
                test = true;
          }
        }else if (board.get(3) != "-" && board.get(4) != "-" && board.get(5) != "-") { //middle row
           sum = Integer.parseInt(board.get(3)) + Integer.parseInt(board.get(4)) + Integer.parseInt(board.get(5));
           if (sum == 15) {
                test = true;
          }
        }else if (board.get(6) != "-" && board.get(7) != "-" && board.get(8) != "-") { //bottom row
           sum = Integer.parseInt(board.get(6)) + Integer.parseInt(board.get(7)) + Integer.parseInt(board.get(8));
           if (sum == 15) {
                test = true;
          }
        }
        return test;
    }

    /**
    checkDown: checks down win conditions and returns true if one of them is satisfied
    in: 
    out: boolean
    */
    private boolean checkDown(){
       int sum = 0;
       boolean test = false;
       if (board.get(0) != "-" && board.get(3) != "-" && board.get(6) != "-") { //left col
           sum = Integer.parseInt(board.get(0)) + Integer.parseInt(board.get(3)) + Integer.parseInt(board.get(6));
           if (sum == 15) {
                test = true;
          }
        }else if (board.get(1) != "-" && board.get(4) != "-" && board.get(7) != "-") { //right col
           sum = Integer.parseInt(board.get(1)) + Integer.parseInt(board.get(4)) + Integer.parseInt(board.get(7));
           if (sum == 15) {
                test = true;
          }
        }else if (board.get(2) != "-" && board.get(5) != "-" && board.get(8) != "-") { //middle col
           sum = Integer.parseInt(board.get(2)) + Integer.parseInt(board.get(5)) + Integer.parseInt(board.get(8));
           if (sum == 15) {
                test = true;
          }
        }
        return test;
    }

    /**
    checkDiagnol: checks diagnol win conditions and returns true if one of them is satisfied
    in: 
    out: boolean
    */
    private boolean checkDiagnol(){
       int sum = 0;
       boolean test = false;
       if (board.get(0) != "-" && board.get(4) != "-" && board.get(8) != "-") { //down diagnol
           sum = Integer.parseInt(board.get(0)) + Integer.parseInt(board.get(4)) + Integer.parseInt(board.get(8));
           if (sum == 15) {
                test = true;
          }
        }else if (board.get(6) != "-" && board.get(4) != "-" && board.get(2) != "-") { //updiagnol
           sum = Integer.parseInt(board.get(6)) + Integer.parseInt(board.get(4)) + Integer.parseInt(board.get(2));
           if (sum == 15) {
                test = true;
          }
        }
        return test;
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
            if (player == "odd") {
                val = 1;
            }else if (player == "even") {
                val = 2;
            }
        }else if (!isWinner) {
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
        messageType = getWinner();
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
        //converts turn to a single letter representing the turn
        if (player == "odd") {
            tempString = "O" + "\n";        
        }else if (player == "even"){
            tempString = "E" + "\n";   
        }
        int i=0;
        for(String j: board){
            if (j != "-") {
                tempString = tempString + j + ","; //adding commas for empty spaces
            }else if(j != "\n"){
                tempString += ","; //adding commas for new lines
            }
            i++;
            if(i == 3){
                tempString = tempString + "\n"; //adding new lines for each row
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
        String tempStr = " "; //parse toLoad into tempStr
        int count = 0;
        if (toLoad.charAt(0) == 'E') {
            player = "even";
        }else if (toLoad.charAt(0) == 'O') {
            player = "odd";
        }
        for (int i = 2; i < toLoad.length()-1; i++) { //starts at 2 so newline after turn isnt read
           if (toLoad.charAt(i) == ',') { //checking if char is comma and next char is also comma
               if (toLoad.charAt(i+1) == ',' || toLoad.charAt(i+1) == '\n') {
                tempStr += "-";
               }
           }else if (toLoad.charAt(i) == '\n') { //bypass newline
               count = count;
           }else{
                tempStr += Character.toString(toLoad.charAt(i)); //add char to tempStr
           }
        }
        for (int i = 0; i < 9; i++) { //add valid chars in tempStr to baord
            if (!containsValidChar(toLoad.charAt(i))){
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
    containsValidChar: helper methods to check valid board chars
    in: char
    out: boolean
    */
    private boolean containsValidChar(char c){
       boolean isValid = true;
       if (c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' 
            && c != '7' && c != '8' && c != '9' && c != '-'){
            isValid = false;
       }
           return isValid;
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
            if(i == 3){
                tempString = tempString + "\n"; //looped through complete row add new line
                i = 0;
            }
        }
        return tempString;
    }
}