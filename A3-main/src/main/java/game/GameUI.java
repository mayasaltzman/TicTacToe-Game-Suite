package game;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import boardgame.SaveToFile;

import tictactoe.TicTacToeView;
import numericaltictactoe.NumericalTicTacToeView;

/**
This class builds the GUI for the homepage of the game.
It also handles the functionality of all elements of the home
page that the user can interact with.
 @author Maya Saltzman
*/
public class GameUI extends JFrame{
    private JPanel gameContainer;
    private JMenuBar menuBar;
    private JLabel playerProfileWelcome;
    private String username = "Maya";
    private JFileChooser fileChooser;
    private SaveToFile save = new SaveToFile();

    public GameUI(String title){
        super(); //call class constructor
        this.setSize(WIDTH, HEIGHT); //size of JFrame
        makeMenu(); //creates menu
        setJMenuBar(menuBar); //setsMenu
        gameContainer = new JPanel();
        fileChooser = new JFileChooser();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(gameContainer, BorderLayout.CENTER); //adds JPanel for game
        add(makeHomePageButtonPanel(),BorderLayout.WEST); //adds buttons on homepagw
        homePage(); //displays home page

    }

    /**
    makeHomePageButtonPanel: builds button panel for home page
    in: 
    out: JPanel
    */
    private JPanel makeHomePageButtonPanel(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.add(ticTacToeButton());
        buttonPanel.add(numericalTicTacToeButton());
        return buttonPanel;
    }

    /**
    makePlayerProfileButtonPanel: builds button panel for player profile
    in: 
    out: JPanel
    */
    private JPanel makePlayerProfileButtonPanel(){
       JPanel buttonPanel = new JPanel();
       buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
       buttonPanel.add(addPlayerName());
       buttonPanel.add(loadStats());
       buttonPanel.add(homeGameButton());
       return buttonPanel;
    }

    /**
    welcomeMessage: adds text to the Jlabel for the message
    in: 
    out: JPanel
    */
    private JPanel welcomeMessage(){
        JPanel msg = new JPanel();
        msg.add(new JLabel("Welcome To My Games"));
        return msg;
    }

    /**
    playerProfileMessage: adds text to the Jlabel for the player profile message
    in: 
    out: JLabel
    */
    private JLabel playerProfileMessage(){
        playerProfileWelcome = new JLabel("Welcome");
        return playerProfileWelcome;
    }

    /**
    ticTacToeButton: makes button that takes user to tictactoe game
    in: 
    out: JButton
    */
    private JButton ticTacToeButton(){
        JButton button = new JButton("Tic Tac Toe");
        button.addActionListener(e->ticTacToe());
        return button;
    }

    /**
    addPlayerName: makes button that allows user to update thier username
    in: 
    out: JButton
    */
    private JButton addPlayerName(){
        JButton button = new JButton("Update Username");
        button.addActionListener(e->updatePlayerName());
        return button;   
    }

    /**
    loadStats: makes button that allows user to load thier player profile
    in: 
    out: JButton
    */
    private JButton loadStats(){
      JButton button = new JButton("Load Statistics");
      button.addActionListener(e->displayStats());
      return button;
    }

    /**
    numericalTicTacToeButton: makes button that takes user to numerical tictactoe game
    in: 
    out: JButton
    */
    private JButton numericalTicTacToeButton(){
        JButton button = new JButton("Numerical Tic Tac Toe");
        button.addActionListener(e->numericalTicTacToe());
        return button;
    }

    /**
    homeGameButton: makes button that takes user to home page
    in: 
    out: JButton
    */
    private JButton homeGameButton(){
       JButton button = new JButton("Home");
       button.addActionListener(e->homePage());
       return button; 
    }

    /**
    makeMenu: adds all menu options and then makes menu
    in: 
    out: void
    */
    public void makeMenu(){
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem playerProfile = new JMenuItem("Player Profile");
        menu.add(playerProfile);
        playerProfile.addActionListener(e->playerProfile());
        menuBar.add(menu);

    }

    /**
    homePage: sets standard hompage
    in: 
    out: void
    */
    public void homePage(){
        gameContainer.removeAll();
        gameContainer.add(welcomeMessage());
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    /**
    ticTacToe: adds tictactoe game to the game container
    in: 
    out: void
    */
    protected void ticTacToe(){
        gameContainer.removeAll();
        gameContainer.add(new TicTacToeView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }
    
    /**
    numericalTicTacToe: adds numerical tictactoe game to the game container
    in: 
    out: void
    */
    protected void numericalTicTacToe(){
        gameContainer.removeAll();
        gameContainer.add(new NumericalTicTacToeView(3,3,this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }
 
    /**
    playerProfile: adds buttons for player profile to the game container
    in: 
    out: void
    */
    protected void playerProfile(){
        gameContainer.removeAll();
        gameContainer.add(playerProfileMessage(),BorderLayout.NORTH);
        gameContainer.add(makePlayerProfileButtonPanel(), BorderLayout.EAST);
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    /**
    getUsername: returns players username
    in: 
    out: String
    */
    public String getUsername(){
        return username;
    }

    /**
    updatePlayerName: allows player to update their username
    in: 
    out: void
    */
    protected void updatePlayerName(){
        String name = JOptionPane.showInputDialog("Enter a username");
        username = name;
    }

    /**
    displayStats: load player profile info from file of users choice
    in: 
    out: void
    */
    protected void displayStats(){
       String toDisplay = "";
       if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                if (fileChooser.getSelectedFile().canRead()){
                    try{
                       toDisplay = save.loadFromPlayerProfile(fileChooser.getSelectedFile().getName());
                    }catch(Exception e){
                      JOptionPane.showMessageDialog(this,"Failed to read file");
                    }  
                }

                JOptionPane.showMessageDialog(this,toDisplay);
        }
    }

    public static void main(String[] args){
        GameUI myGame = new GameUI("Maya's Games");
        myGame.setVisible(true);
    }
} 

