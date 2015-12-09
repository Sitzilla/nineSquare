package src.main.java.com.evan.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by evan on 12/8/15.
 */
public class TicTacToeServer {

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(8901, 0, InetAddress.getByName("localhost"));
        System.out.println("Tic Tac Toe Server is Running");

        try {
            while (true) {
                TicTacToeGame game = new TicTacToeGame();
                TicTacToeGame.Player player1 = game.new Player(listener.accept());
                player1.start();
            }
        } finally {
            listener.close();
        }



    }

}

class TicTacToeGame {

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

                    if (command != null) {
                        System.out.println(command);
                        output.println("Sending move to client: " + command);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

}





