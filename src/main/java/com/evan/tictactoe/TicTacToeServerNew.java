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
public class TicTacToeServerNew {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8901, 0, InetAddress.getByName("localhost"));
        System.out.println("Tic Tac Toe Server is Running");

        try {
            while (true) {

                MultiPlayerTicTacToeGameNew game = new MultiPlayerTicTacToeGameNew();
                MultiPlayerTicTacToeGameNew.Player playerX = game.new Player(listener.accept(), "X");
                MultiPlayerTicTacToeGameNew.Player playerO = game.new Player(listener.accept(), "O");

                playerX.setOpponent(playerO);
                playerO.setOpponent(playerX);

                game.currentPlayer = playerX;

                playerX.start();
                playerO.start();

            }
        } finally {
            listener.close();
        }
    }
}



class MultiPlayerTicTacToeGameNew {

    String[] board = { null, null, null,
            null, null, null,
            null, null, null };

    Player currentPlayer;
    boolean passedMove = false;

    public synchronized boolean legalMove(int index, Player playerWhoMoved) {
        // Illegal move
        if (board[index] != null || currentPlayer != playerWhoMoved) { return false; }

        board[index] = currentPlayer.team;
        currentPlayer = currentPlayer.opponent;
        currentPlayer.otherPlayerMoved(index);

        return true;
    }

    public synchronized void reset() {
        Arrays.fill(board, 0);
    }

    //TODO check that this actually works
    public synchronized boolean checkDraw() {

        for (String space : board) {
            if (space == null) { return false; }
        }

        return true;
    }

    public boolean threeInARowCheck(String x){
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
        String team;
        Player opponent;


        public Player(Socket socket, String team) {
            this.socket = socket;
            this.team = team;

            try {
                input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                output = new PrintWriter(socket.getOutputStream(), true);

                output.println("WELCOME " + team);

            } catch (IOException e) {
                System.out.print("Connection error");
            }


        }

        public void otherPlayerMoved(int index) {
            output.println("OPPONENT_MOVED " + index);
            // TODO check victory conditions
        }

        public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

        public void run() {
            String command;
            try {

                while (true) {

                    command = input.readLine();

                    if (command.startsWith("MOVE")) {
                        System.out.println(command);

                        //TODO make the split command more readable
                        if (legalMove(Integer.parseInt(command.split(" ")[1]), this)) {
                            output.println("LEGAL_MOVE");
                            // TODO check victory conditions
//                            command = input.readLine();
//                            if (command.startsWith("CHECK_STATUS")) {
//                                if (threeInARowCheck(team)) {
//                                    output.println("WON");
//                                } else if (threeInARowCheck(team)) {
//                                    output.println("LOST");
//                                } else if (checkDraw()) {
//                                    output.println("DRAW");
//                                } else {
//                                    output.println("PLAYING");
//                                }
//                            }
                        } else {
                            output.println("MESSAGE Illegal move");
                        }


                    } else if (command.startsWith("RESET")) {
                        reset();
                    }
//                    else if (command.startsWith("WAITING")) {
//                        System.out.println("NEED TO GIVE TO OPPONENT");
//
//                        while (!passedMove) {
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        System.out.println("READY TO PASS TO OPPONENT");
//                        output.println("OPPONENT_MOVE " + mostRecentLegalMove);
//                        passedMove = false;
//                    }
                }
//                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

}