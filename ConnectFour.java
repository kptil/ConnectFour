import java.util.Arrays;
import java.util.Scanner;

public class ConnectFour {

    /*
    Prints the board by iterating through the elements stored in the char array passed to the method. Row 0 is
    printed at the bottom.
     */
    public static void printBoard(char[][] array) {
        for (int i = array.length - 1; i >= 0; --i) {
            for (int j = 0; j < array[i].length; ++j) {
                if (j == array[i].length - 1) {
                    System.out.print(array[i][j]);
                }
                else {
                    System.out.print(array[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }

    //Creates a new board by setting each element of the char array equal to '-'.
    public static void initializeBoard(char[][] array) {
        for (char[] rowArray : array) {
            Arrays.fill(rowArray, '-');
        }
    }

    /*
    Places the player's chipType at the next available space in the column of their choice. Iterates through the column
    using array[i][col]. The next available space is the first one with a '-'. That element of the array is changed
    to be the player's chipType. The row i is returned.
     */
    public static int insertChip(char[][] array, int col, char chipType) {
        int i;

        for (i = 0; i < array.length; ++i) {
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                break;
            }
        }
        return i;
    }

    /*
    After each token is added, checks if there are four instances of that chipType in a row, either
    horizontally or vertically, using two separate for loops. A count is initialized for each loop.
    If count equals four, the method returns true. Otherwise, it returns false.
     */
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {

        //Check row for 4 in a row.
        int rowCount = 0;
        for (int i = 0; i < array[row].length; ++i) {
            if (array[row][i] == chipType) {
                ++rowCount;
            }
            if (rowCount == 4) {
                return true;
            }
        }

        //Check column for 4 in a row.
        int colCount = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i][col] == chipType) {
                ++colCount;
            }
            if (colCount == 4) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        //Sets up scanner and gets board size from the user.
        Scanner scnr = new Scanner(System.in);
        System.out.print("What would you like the height of the board to be? ");
        int height = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int length = scnr.nextInt();

        //Initializes array with user-specified length and height. Prints the empty board.
        char[][] boardArray = new char[height][length];
        initializeBoard(boardArray);
        printBoard(boardArray);

        System.out.println("Player 1: x");
        System.out.println("Player 2: o");
        System.out.println();

        //Creates variables used in game play. boardSize is unnecessary but created for readability
        int boardSize = boardArray.length * boardArray[0].length;
        int colChoice;
        int rowNum;
        char chipType;
        boolean winner = false;

        /*
        For loop iterates the same number of times as there are spaces on the board. PLay continues
        until a call to checkIfWinner returns true or until the board is full, at which point the
        for loop has reached it's limit. If winner is still false when the loop ends, the game ends
        in a draw.
         */
        for (int i = 0; i < boardSize; ++i) {
            /*
            Alternates turns based on whether i is even or odd. Gets column choice from the player,
            updates and prints the board, then checks for a winner.
             */
            //Player 1's Turn
            if (i % 2 == 0) {
                chipType = 'x';
                System.out.print("Player 1: Which column would you like to choose? ");
                colChoice = scnr.nextInt();
                System.out.println();
                rowNum = insertChip(boardArray, colChoice, chipType);
                printBoard(boardArray);
                winner = checkIfWinner(boardArray, colChoice, rowNum, chipType);
                if (winner) {
                    System.out.println("Player 1 won the game!");
                    break;
                }
            }
            //Player 2's Turn
            else {
                chipType = 'o';
                System.out.print("Player 2: Which column would you like to choose? ");
                colChoice = scnr.nextInt();
                System.out.println();
                rowNum = insertChip(boardArray, colChoice, chipType);
                printBoard(boardArray);
                winner = checkIfWinner(boardArray, colChoice, rowNum, chipType);
                if (winner) {
                    System.out.println("Player 2 won the game!");
                    break;
                }
            }
        }

        //Game ends in draw if for loop ends and no one wins.
        if (!winner) {
            System.out.println("Draw. Nobody wins.");
        }

    }
}
