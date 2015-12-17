package src.main.java.com.evan.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by evan on 12/9/15.
 */
public class TicTacToeServer {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8901, 0, InetAddress.getByName("localhost"));
        System.out.println("Tic Tac Toe Server is Running");

        try {
            while (true) {

                MultiPlayerTicTacToeGame game = new MultiPlayerTicTacToeGame();

                Socket socket = listener.accept();

                MultiPlayerTicTacToeGame.Player playerX = game.new Player(socket);

                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                output.println("X");

//                MultiPlayerTicTacToeGame.Player playerO = game.new Player(listener.accept());



                playerX.start();
//                playerO.start();
            }
        } finally {
            listener.close();
        }
    }
}



class MultiPlayerTicTacToeGame {

    int[] board = { 0, 0, 0,
                    0, 0, 0,
                    0, 0, 0 };

    public synchronized boolean selectSquare(int index) {
        // Illegal move
        if (board[index] != 0) { return false; }

        board[index] = 1;

        return true;
    }

    public synchronized void reset() {
        Arrays.fill(board, 0);
    }

    //TODO check that this actually works
    public synchronized boolean checkDraw() {

        for (int space : board) {
            if (space == 0) { return false; }
        }

        return true;
    }

    public boolean threeInARowCheck(int x){
        if (board[0] == x && board[1] == x && board[2] == x ){
            return true;
        } else if (board[0] == x && board[3] == x && board[6] == x ){
            return true;
        } else if (board[0] == x && board[4] == x && board[8] == x ){
            return true;
        } else if (board[3] == x && board[4] == x && board[5] == x ){
            return true;
        } else if (board[6] == x && board[7] == x && board[8] == x ){
            return true;
        } else if (board[6] == x && board[4] == x && board[2] == x ){
            return true;
        } else if (board[1] == x && board[4] == x && board[7] == x ){
            return true;
        } else if (board[2] == x && board[5] == x && board[8] == x ){
            return true;
        }
        return false;
    }

    class Player extends Thread {
        Socket socket;
        BufferedReader input;
        PrintWriter output;


        public Player(Socket socket) {
            this.socket = socket;

            try {
                input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.print("Connection error");
            }


        }

        public void run() {
            try {

                while (true) {
                    String command = input.readLine();

                    if (command.startsWith("MOVE")) {
                        System.out.println(command);

                        //TODO make the split command more readable
                        if (selectSquare(Integer.parseInt(command.split(" ")[1]))) {
                            output.println("LEGAL_MOVE");
                            output.println("OPPONENT_MOVE: " + "NOPE");
                        } else {
                            output.println("ILLEGAL_MOVE");
                        }
                    } else if (command.startsWith("CHECK_STATUS")) {
                        if (threeInARowCheck(1)) {
                            output.println("WON");
                        } else if (threeInARowCheck(-1)){
                            output.println("LOST");
                        } else if (checkDraw()){
                            output.println("DRAW");
                        } else {
                            output.println("PLAYING");
                        }
                    } else if (command.startsWith("RESET")) {
                        reset();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

}