import java.util.Scanner;
import java.util.Arrays;

public class TicTacToe {
    private static final char EMPTY_BOX = ' ';
    private static final char PLAYER_ONE_SYMBOL = 'X';
    private static final char PLAYER_TWO_SYMBOL = 'O';

    private final char[][] gameBoard = new char[3][3];

    private final Scanner input = new Scanner(System.in);

    private String playerOne;
    private String playerTwo;
    private String currentPlayer;
    private String whoWonTheGame;

    public void startGame() {
        initializeBoard();
        askForUserName();

        while (isGameNotOver()) {
            drawBoard();
            printPlayerTurn();
            askForManeuver();
        }

        printGameOver();
    }

    private void initializeBoard() {
        for (char[] chars : gameBoard) {
            Arrays.fill(chars, EMPTY_BOX);
        }
    }

    private void askForUserName() {
        System.out.println("Welcome to Tic Tac Toe Games !");
        System.out.println("What's your name?");
        playerOne = input.nextLine();

        System.out.println("Who are you playing with?");
        playerTwo = input.nextLine();
        System.out.println("Who is playing first? Press" + "\n 1 for " + playerOne + "\n 2 for " + playerTwo);
        
        int player;
        do {
            player = input.nextInt();
        } while (player != 1 && player != 2); // Ensuring correct input
        
        input.nextLine(); // Consume leftover newline
        currentPlayer = player == 1 ? playerOne : playerTwo;}

    private boolean isGameNotOver() {
        return !(isBoardFull() || hasAnyPlayerWon());
    }

    private void drawBoard() {
        System.out.println(" |---|---|---| ");
        for (char[] chars : gameBoard) {
            System.out.printf(" | %c | %c | %c |%n", chars[0], chars[1], chars[2]);
            System.out.println(" |---|---|---|");
        }
    }

    private void printPlayerTurn() {
        System.out.println(whoIsPlaying() + "'s turn");
    }

    private void askForManeuver() {
        int row;
        int col;

        do {
            System.out.println("Enter a row number (0, 1, or 2): ");
            row = input.nextInt();
            System.out.println("Enter a column number (0, 1, or 2): ");
            col = input.nextInt();
        } while (!validateInput(row, col));

        if (whoIsPlaying().equals(playerOne)) {
            gameBoard[row][col] = PLAYER_ONE_SYMBOL;
            currentPlayer = playerTwo;
        } else {
            gameBoard[row][col] = PLAYER_TWO_SYMBOL;
            currentPlayer = playerOne;
        }
    }

    private void printGameOver() {
        drawBoard();
        System.out.println(" :( Game is over! :( ");

        if (whoWonTheGame != null) {
            System.out.println(" ");
            System.out.println(whoWonTheGame + " won the game, Congratulations! :) ");
            System.out.println(" ");
            System.out.println("This Game is made by Talha :)");
        } else {
            System.out.println("Sounds like it's a tie! Play again :( ");
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == EMPTY_BOX) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasAnyPlayerWon() {
        char cross = ' ';

        // Check each row
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][0] != EMPTY_BOX) {
                cross = gameBoard[i][0];
            }
        }

        // Check each column
        for (int j = 0; j < 3; j++) {
            if (gameBoard[0][j] == gameBoard[1][j] && gameBoard[1][j] == gameBoard[2][j] && gameBoard[0][j] != EMPTY_BOX) {
                cross = gameBoard[0][j];
            }
        }

        // Check the diagonals
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[0][0] != EMPTY_BOX) {
            cross = gameBoard[0][0];
        }
        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2] && gameBoard[2][0] != EMPTY_BOX) {
            cross = gameBoard[2][0];
        }

        if (cross == PLAYER_ONE_SYMBOL) {
            whoWonTheGame = playerOne;
        } else if (cross == PLAYER_TWO_SYMBOL) {
            whoWonTheGame = playerTwo;
        }
        return whoWonTheGame != null;
    }

    private String whoIsPlaying() {
        return currentPlayer;
    }

    private boolean validateInput(int row, int col) {
        if (row < 0 || col < 0 || row > 2 || col > 2) {
            System.out.println("The position is off the bounds of the board, try again");
            return false;
        } else if (gameBoard[row][col] != EMPTY_BOX) {
            System.out.println("Someone has already made a move at this position, try again");
            return false;
        }
        return true;
    }
}
