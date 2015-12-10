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
public class Board extends JPanel implements ActionListener {
    private static final int ARRAYSIZE = 3; //the dimensions of the board are 3x3

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
    public Board() throws IOException {
        boolean isMultiplayer;

        // creates the JButtons, sets their font size, adds a client property to indicate their position (0-8), and adds an action listener
        for (int i=0;i<ARRAYSIZE;i++) {
            for (int j=0;j<ARRAYSIZE;j++) {
                int setPropertyIndex = (i * 3) + j;
                buttonArray[i][j] = new JButton();
                buttonArray[i][j].setFont(bSize40);
                buttonArray[i][j].putClientProperty("index", setPropertyIndex);
                buttonArray[i][j].addActionListener(this);
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

        // Setup networking
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // select single or multi player mode
        int reply = JOptionPane.showConfirmDialog(null, "Connect for multiplayer game?", "Mode of Play", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            out.println("SINGLE");
        } else {
            out.println("MULTI");
        }


    }

    //method for when the user selects one of the buttons on the board
    public void actionPerformed(ActionEvent evt) {
        String response;
        try {
            Object source = evt.getSource(); //finds the source of the objects that triggers the event
            int indexPosition = (Integer) ((JComponent) evt.getSource()).getClientProperty("index"); //variable that represents the buttons 'index' (0-8)

            out.println("MOVE: " + indexPosition);

            response = in.readLine();

            System.out.println(response);

            if (response.startsWith("LEGAL_MOVE")) {
                ((AbstractButton) source).setText("X"); //Sets the user selected button as an 'X'

                response = in.readLine();

                setOpponentsMove(response);
            }

            // Check for win/loss
            out.println("CHECK_STATUS");

            response = in.readLine();
            System.out.println(response);

            if (response.startsWith("WON")) {
                endOfGame("You win!!");
            } else if (response.startsWith("LOST")) {
                endOfGame("Sorry you lose.");
            } else if (response.startsWith("DRAW")) {
                endOfGame("Cat's game... it's a draw!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //end-of-game method to go through and blank out all of the buttons when called and asks if the user wants a rematch
    public void endOfGame(String message) throws IOException {
        for (int i=0;i<ARRAYSIZE;i++){
            for (int j=0;j<ARRAYSIZE;j++){
                buttonArray[i][j].setOpaque(false);
                buttonArray[i][j].setContentAreaFilled(false);
                buttonArray[i][j].setBorderPainted(false);
                buttonArray[i][j].setEnabled(false);
            }
        }
        JOptionPane.showMessageDialog(null, message);
        int reply = JOptionPane.showConfirmDialog(null, "Play again?", "Rematch", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.NO_OPTION) {
            socket.close();
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

            out.println("RESET");
        }
    }

    private void setOpponentsMove(String response) {
        int index = Integer.parseInt(response.split(" ")[1]);
        System.out.println("Opponent Move: " + index);

        int row = index / 3;
        int column = index - (3 * row);
        try {
            buttonArray[row][column].setText("O");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Bad comp move: (" + row + ", " + column + ")");
        }

    }
}