package src.main.java.com.evan.tictactoe;

import java.util.Arrays;

/**
 * Created by evan on 12/17/15.
 */
public class SinglePlayerGameLogic {
    private AIStrategy computerStrategy = new AIStrategy();
    private int mostRecentComputerMove = -1;
    private int[] board = { 0, 0, 0,
                    0, 0, 0,
                    0, 0, 0 };

    public SinglePlayerGameLogic() {


    }

    public boolean selectSquare(int index) {
        // Illegal move
        if (board[index] != 0) { return false; }

        board[index] = 1;

        computerStrategy.setUserArray(index);

        if (!threeInARowCheck(1)) {
            // Computer selects move
            mostRecentComputerMove = computerStrategy.returnBestMove();
            computerStrategy.setComputerArray(mostRecentComputerMove);
            try {
                board[mostRecentComputerMove] = -1;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Game Over");
            }
        }

        return true;
    }

    public String checkStatus() {
        if (checkDraw()) { return "DRAW"; }

        if (threeInARowCheck(1)) { return "WIN"; }
        if (threeInARowCheck(-1)) { return "LOSE"; }

        return "PLAYING";
    }

    public int getComputersMostRecentMove() {
        return mostRecentComputerMove;
    }
    public void reset() {
        Arrays.fill(board, 0);
        computerStrategy = new AIStrategy();
        mostRecentComputerMove = -1;
    }

    //TODO check that this actually works
    private boolean checkDraw() {

        for (int space : board) {
            if (space == 0) { return false; }
        }

        return true;
    }

    private boolean threeInARowCheck(int x){
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

}
