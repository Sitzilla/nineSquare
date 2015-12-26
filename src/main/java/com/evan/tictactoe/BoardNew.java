package src.main.java.com.evan.tictactoe;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;

@SuppressWarnings("serial")
public class BoardNew extends JPanel implements ActionListener {
    private static final int ARRAYSIZE = 3; //the dimensions of the board are 3x3
    private String team;
    private String opponant;
    boolean isMultiplayer;
    boolean myMove;
    JButton currentButton;
    SinglePlayerGameLogic singlePlayerGame;

    // Networking members
    private static int PORT = 8901;
    private Socket socket;
    private String serverAddress = "localhost";
    private BufferedReader in;
    private PrintWriter out;

    // creates the panes for each row
    JPanel panelOne = new JPanel();
    JPanel panelTwo = new JPanel();
    JPanel panelThree = new JPanel();

    // initializes JButton multidimensional array.  The first dimension is the columns and the second is the rows
    JButton[][] buttonArray = new JButton[ARRAYSIZE][ARRAYSIZE];

    private Font bSize40 = new Font("Arial", Font.PLAIN, 40); //font size for the button

    // constructor for the 'Board' class
    public BoardNew() throws IOException {

        // select single or multi player mode
        int reply = JOptionPane.showConfirmDialog(null, "Connect for multiplayer game?", "Mode of Play", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.NO_OPTION) {
            System.out.println("SINGLE");
            isMultiplayer = false;
            team = "X";
            opponant = "O";
            this.singlePlayerGame = new SinglePlayerGameLogic();
        } else {
            isMultiplayer = true;

            // Setup networking
            socket = new Socket(serverAddress, PORT);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String response = in.readLine();

            if (response.startsWith("WELCOME ")) {
                team = response.split(" ")[1];

                if ("X".equals(team)) {
                    opponant = "O";
                    myMove = true;
                } else {
                    opponant = "X";
                    myMove = false;

                }

                System.out.println("Team is " + team);
            }
        }

        // creates the JButtons, sets their font size, adds a client property to indicate their position (0-8), and adds an action listener
        for (int i = 0; i < ARRAYSIZE; i++) {
            for (int j = 0; j < ARRAYSIZE; j++) {
                int setPropertyIndex = (i * 3) + j;
                buttonArray[i][j] = new JButton();
                buttonArray[i][j].setFont(bSize40);
                buttonArray[i][j].putClientProperty("index", setPropertyIndex);
                final int finalJ = j;
                final int finalI = i;
                final int indexPosition = (i * 3) + j;

                if (isMultiplayer) {
                    buttonArray[i][j].addMouseListener(new MouseAdapter() {
                        public void mousePressed(MouseEvent e) {
                            currentButton = buttonArray[finalI][finalJ];
                            out.println("MOVE " + indexPosition);
                        }
                    });
                } else {
                    buttonArray[i][j].addActionListener(this);
                }
            }
        }

        // assigns three buttons to panel one
        panelOne.setLayout( new GridLayout(1,3));
        panelOne.add(buttonArray[0][0]);
        panelOne.add(buttonArray[0][1]);
        panelOne.add(buttonArray[0][2]);

        // assigns three buttons to panel two
        panelTwo.setLayout( new GridLayout(1,3));
        panelTwo.add(buttonArray[1][0]);
        panelTwo.add(buttonArray[1][1]);
        panelTwo.add(buttonArray[1][2]);

        // assigns three buttons to panel three
        panelThree.setLayout( new GridLayout(1,3));
        panelThree.add(buttonArray[2][0]);
        panelThree.add(buttonArray[2][1]);
        panelThree.add(buttonArray[2][2]);

        // adds all of the rows to the frame
        setLayout( new GridLayout(3,1));
        add(panelOne);
        add(panelTwo);
        add(panelThree);
    }

    // Method for controlling game flow
    public void play() throws IOException {
        String response;

        if (isMultiplayer) {
            while (true) {

                response = in.readLine();

                if (response.startsWith("LEGAL_MOVE")) {
                    currentButton.setText(team);
                } else if (response.startsWith("OPPONENT_MOVED")) {
                    setOpponentsMove(Integer.parseInt(response.split(" ")[1]));
                } else if (response.startsWith("ENDING")) {
                    String ending = response.split(" ")[1];
                    if ("WON".equals(ending)) {
                        endOfGame("You win!!");
                    } else if ("LOST".equals(ending)) {
                        endOfGame("Sorry you lose.");
                    } else if ("DRAW".equals(ending)) {
                        endOfGame("Cat's game... it's a draw!");
                    }
                } else if (response.startsWith("MESSAGE")) {

                }
            }
        } else {


        }
    }


    //method for when the user selects one of the buttons on the board
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource(); //finds the source of the objects that triggers the event
        int indexPosition = (Integer) ((JComponent) evt.getSource()).getClientProperty("index"); //variable that represents the buttons 'index' (0-8)
        singlePlayerMove(source, indexPosition);
    }

    public void singlePlayerMove(Object source, int indexPosition) {
        boolean legalMove = false;
        legalMove = singlePlayerGame.selectSquare(indexPosition);

        if (legalMove) {

            // Draw board moves on board
            setOpponentsMove(singlePlayerGame.getComputersMostRecentMove());
            ((AbstractButton) source).setText(team);

            String status = singlePlayerGame.checkStatus();

            // TODO make enum
            try {
                if ("WON".equals(status)) {
                    endOfGame("You win!!");
                } else if ("LOSE".equals(status)) {
                    endOfGame("Sorry you lose.");
                } else if ("DRAW".equals(status)) {
                    endOfGame("Cat's game... it's a draw!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    //end-of-game method to go through and blank out all of the buttons when called and asks if the user wants a rematch
    public void endOfGame(String message) throws IOException {
        for (int i = 0; i < ARRAYSIZE; i++){
            for (int j = 0; j < ARRAYSIZE; j++){
                buttonArray[i][j].setOpaque(false);
                buttonArray[i][j].setContentAreaFilled(false);
                buttonArray[i][j].setBorderPainted(false);
                buttonArray[i][j].setEnabled(false);
            }
        }
        JOptionPane.showMessageDialog(null, message);
        int reply = JOptionPane.showConfirmDialog(null, "Play again?", "Rematch", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            if (isMultiplayer == true) { socket.close(); }
            System.exit(0);
        } else {
            restartGame();
        }
    }

    //a method that resets the game back to its original state
    public void restartGame(){
        for (int i = 0; i < ARRAYSIZE; i++){

            for (int j = 0; j < ARRAYSIZE; j++){
                buttonArray[i][j].setOpaque(true);
                buttonArray[i][j].setContentAreaFilled(true);
                buttonArray[i][j].setBorderPainted(true);
                buttonArray[i][j].setEnabled(true);
                buttonArray[i][j].setText("");
            }

            if (isMultiplayer) {
                out.println("RESET");
            } else {
                singlePlayerGame.reset();
            }
        }
    }

    private void setOpponentsMove(int index) {
        //String response) {
        //int index = Integer.parseInt(response.split(" ")[1]);
        System.out.println("Opponent Move: " + index);

        int row = index / 3;
        int column = index - (3 * row);
        try {
            buttonArray[row][column].setText(opponant);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Bad comp move: (" + row + ", " + column + ")");
        }

    }
}