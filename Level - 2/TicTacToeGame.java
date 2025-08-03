import java.util.Scanner;

public class TicTacToeGame {

     static char board[] = {'1','2','3','4','5','6','7','8','9'};
     static char currentPlayer = 'X';

     public static void main( String [] args)
     {
        Scanner sc = new Scanner(System.in);
        boolean playAgain ;

        do {
            resetBoard();
            boolean gameEnded = false;

           while (!gameEnded) {
            printBoard();
            int move = getMove(sc);
            makeMove(move);
            gameEnded =  checkWin() || checkDraw();
            switchPlayer();
           }
           System.out.println("Do You want to play again ? (y/n)");
           playAgain = sc.next().equalsIgnoreCase("y");


        } while (playAgain);
        sc.close();
     }
     static void printBoard() {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + "\n");
     }
     static int getMove(Scanner sc)
     {
        int move ;
        while (true) {
            System.out.println("Player"+ currentPlayer+ ", enter your move(1-9): ");
            move = sc.nextInt();
            if(move >= 1 && move <= 9 && board[move - 1] != 'X' && board[move - 1] != 'O')
            {
                return  move;
            }
            else
            {
                System.out.println("Invalid move try again...!");
            }
            
        }
     }
     static void makeMove(int move) {
        board[move - 1] = currentPlayer;
     }

     static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    static boolean checkWin() {
        int[][] winBoxes = {
            {0,1,2}, {3,4,5}, {6,7,8}, // Rows
            {0,3,6}, {1,4,7}, {2,5,8}, // Columns
            {0,4,8}, {2,4,6}           // Diagonals
        };
        for(int box[] : winBoxes) 
        {
            if(board[box[0]] == board[box[1]] && board[box[1]] == board[box[2]])
            {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                return true;
            }
        }
        return false;

    }
    static boolean checkDraw() {
        for (char c : board) {
            if (c != 'X' && c != 'O') {
                return false;
            }
        }
        printBoard();
        System.out.println("It's a draw!");
        return true;
    }
    static void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = (char) ('1' + i);
        }
        currentPlayer = 'X';
    }

}
