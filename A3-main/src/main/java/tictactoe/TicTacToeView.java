package tictactoe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import boardgame.ui.PositionAwareButton;
import game.GameUI;
import boardgame.SaveToFile;
import javax.swing.JFileChooser;

/**
This class build the GUI for the tic tac toe game.
It has methods needed for game functionality and user interaction.
 @author Maya Saltzman
*/
public class TicTacToeView extends JPanel{

    private TicTacToeGame game;
    private PositionAwareButton[][] buttons;
    private JPanel buttonPanel;
    private GameUI head;
    private String player = "";
    private JLabel message;
    private SaveToFile save = new SaveToFile();
    private int turnCount = 0;
    private JFileChooser fileChooser;
    private String playerProfileString = "";
    
    public TicTacToeView(int wide, int tall, GameUI gameFrame){
        super();    
        setLayout(new BorderLayout());
        head = gameFrame;

        setGameController(new TicTacToeGame(wide,tall));
        fileChooser = new JFileChooser();
        add(gameStateMessages(), BorderLayout.NORTH);
        add(buttonPanel(),BorderLayout.EAST);
        add(grid(tall,wide), BorderLayout.CENTER);
        setGrid();
    }

    /**
    setGameController: sets TicTacToeGame class methods control game play
    in: TicTacToeGame
    out: void
    */
    public void setGameController(TicTacToeGame controller){
        this.game = controller;
    }

    /**
    gameMessages: Adds game state messages to the message panel
    in: 
    out: JPanel
    */
    private JPanel gameMessages(){
        JPanel msgPanel = new JPanel();
        msgPanel.setLayout(new BoxLayout(msgPanel, BoxLayout.Y_AXIS));
        msgPanel.add(gameStateMessages());
        return msgPanel;
    }

    /**
    buttonPanel: Adds buttons to button panel
    in: 
    out: JPanel
    */
    private JPanel buttonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(restartButton());
        buttonPanel.add(saveGameButton());
        buttonPanel.add(continueGameButton());
        buttonPanel.add(homeGameButton());
        return buttonPanel;
    }

    /**
    gameStateMessages: set the message label to be the game state message
    in: 
    out: JLabel
    */
    private JLabel gameStateMessages(){
        message = new JLabel(game.getGameStateMessage());
        return message;
    }

    /**
    restartButton: make restart button
    in: 
    out: JButton
    */
    private JButton restartButton(){
        JButton button = new JButton("Restart");
        button.addActionListener(e->newGame());
        return button;
    }

    /**
    saveGameButton: make save button
    in: 
    out: JButton
    */
    private JButton saveGameButton(){
       JButton button = new JButton("Save Game");
       button.addActionListener(e->saveGame());
       return button; 
    }

    /**
    continueGameButton: make continue game button
    in: 
    out: JButton
    */
    private JButton continueGameButton(){
       JButton button = new JButton("Continue a Previous Game");
       button.addActionListener(e->loadPrevious());
       return button;
    }

    /**
    homeGameButton: make home button
    in: 
    out: JButton
    */
    private JButton homeGameButton(){
       JButton button = new JButton("Home");
       button.addActionListener(e->head.homePage());
       return button; 
    }

    /**
    grid: makes grid of buttons that is the board.
    in: int, int
    out: JPanel
    */
    private JPanel grid(int h, int w){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[h][w];
        panel.setLayout(new GridLayout(w,h));
        for (int y=0; y<w; y++){
            for (int x=0; x<h; x++){ 
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x+1);
                buttons[y][x].setDown(y+1);
                buttons[y][x].addActionListener(e->{
                                         placePiece(e);
                                         checkWinner();
                                         });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }

    /**
    checkWinner: checks if a player has won and if they have ends the game and prompts to save player profile
    in: 
    out: void
    */
    private void checkWinner(){
        int playAgain = 0;
        int playerProfile = 0;
        JOptionPane endGame = new JOptionPane();
        JOptionPane profile = new JOptionPane();
        if (game.isDone() || game.getTurnCount() == 9){
           turnCount = 0;
           game.setTurn(player);
           game.getTurn();
           playAgain = endGame.showConfirmDialog(null,
           game.getGameStateMessage(), "Play Again", JOptionPane.YES_NO_OPTION);
           if (playAgain == JOptionPane.NO_OPTION){
            playerProfile = profile.showConfirmDialog(null,
           "Save Statistics to Player Profile", "", JOptionPane.YES_NO_OPTION);
               if (playerProfile == JOptionPane.NO_OPTION) {
                    head.homePage();
                    newGame();
               }else{
                    saveToPlayerProfile();
                    newGame();
               }
           }else{
               newGame();
           }
        }
    }

    /**
    placePiece: places player piece on board and validates game play
    in: ActionEvent
    out: void
    */
    private void placePiece(ActionEvent e){
        player = game.getTurn();
        PositionAwareButton place = ((PositionAwareButton)(e.getSource()));
        message.setText(game.getGameStateMessage());
        if(game.takeTurn(place.getAcross(), place.getDown(),player)){
            place.setText(game.getCell(place.getAcross(),place.getDown()));
            turnCount++;
            game.setTurnCount(turnCount);
        }
        game.setTurn(player);
        player = game.getTurn();
        message.setText(game.getGameStateMessage());
    }

    /**
    updateGrid: updates grid with current stare of buttons
    in: 
    out: void
    */
    private void updateGrid(){
       for (int y=0; y<game.getHeight(); y++){
            for (int x=0; x<game.getWidth(); x++){  
                buttons[y][x].setText(game.getCell(buttons[y][x].getAcross(),buttons[y][x].getDown())); 
            }
        }
    }

    /**
    newGame: resets board and player for new game
    in: 
    out: void
    */
    protected void newGame(){
        game.newGame();
        game.setTurn(player);
        player = game.getTurn();
        turnCount = 0;
        message.setText(game.getGameStateMessage());
        updateGrid();
    }

    /**
    setGrid: calls set board
    in: 
    out: void
    */
    private void setGrid(){
       game.setBoard();
    }

    /**
    saveGame: user can save game to file of their choosing
    in: 
    out: void
    */
    protected void saveGame(){
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                if (fileChooser.getSelectedFile().canRead()){
                    try{
                        save.save(game, fileChooser.getSelectedFile().getName(), "assets");
                    }catch(Exception e){
                       JOptionPane.showMessageDialog(this,"Failed to read file");
                    }
                   
                }
        }
    }

    /**
    loadPrevious: user can continue a game from a file of their choosing
    in: 
    out: void
    */
    protected void loadPrevious(){
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                if (fileChooser.getSelectedFile().canRead()){
                    try{
                        save.load(game, fileChooser.getSelectedFile().getName(), "assets");
                    }catch(Exception e){
                       JOptionPane.showMessageDialog(this,"Failed to read file");
                    }
                   
                }
        }
        updateGrid();
        JOptionPane.showMessageDialog(this,"This does not work on the GUI :(");
        newGame();
    }

    /**
    saveToPlayerProfile: user can save their statistics to player profile file
    in: 
    out: void
    */
    protected void saveToPlayerProfile(){
        playerProfileString = "Username: "+ head.getUsername() + " Games Played: " + game.getGameCount();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                if (fileChooser.getSelectedFile().canRead()){
                    try{
                      save.savePlayerProfile(fileChooser.getSelectedFile().getName(), playerProfileString);
                    }catch(Exception e){
                      JOptionPane.showMessageDialog(this,"Failed to read file");
                    }
                   
                }
        }
    }

}