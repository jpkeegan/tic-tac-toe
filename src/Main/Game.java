package Main;
import java.util.Scanner;

enum Status {
    // there is a row, column or diagonal consisting of only player1's game pieces:
    PLAYER1_WON,
    // there is a row, column or diagonal consisting of only player2's game pieces:
    PLAYER2_WON,
    // there is no row, column, or diagonal consisting of a single player's pieces:
    TIE,
    // there are spots on the board which are uninitialized;
    UNFINISHED
}

public class Game {
    Player player1;
    Player player2;
    Board board;

    // this function should prompt the players for names and gamePiece and assign it
    // to the player objects:
    private void initPlayers() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter player 1's name: ");
        String name1 = sc.next();
        System.out.println("Enter player 1's game piece: ");
        char gamePiece1 = sc.next().charAt(0);

        System.out.println("Enter player 2's name: ");
        String name2 = sc.next();
        System.out.println("Enter player 2's game piece: ");
        char gamePiece2 = sc.next().charAt(0);

        player1 = new Player(name1, gamePiece1);
        player2 = new Player(name2, gamePiece2);
    }

    // this method should let each player pick a spot and also print the board between selections:
    private boolean round() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(player1.getName() + "'s turn.");
            System.out.println(board.toString());
            System.out.println("Enter row: ");
            int row = sc.nextInt();
            System.out.println("Enter column: ");
            int column = sc.nextInt();

            if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
                if (board.updateBoard(row, column, player1.getGamePiece())) {
                    if (getStatus() == Status.PLAYER1_WON) {
                        System.out.println(board.toString());
                        System.out.println(player1.getName() + " wins!");
                        return true;
                    } else if (getStatus() == Status.TIE) {
                        System.out.println(board.toString());
                        System.out.println("Tie game!");
                        return true;
                    }
                    break;
                } else {
                    System.out.println("Invalid move. Please try again.");
                }
            } else {
                System.out.println("Invalid row or column. Please try again.");
            }
        }
        while (true) {
            System.out.println(player2.getName() + "'s turn.");
            System.out.println(board.toString());
            System.out.print("Enter row: ");
            int row = sc.nextInt();
            System.out.print("Enter column: ");
            int column = sc.nextInt();

            if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
                if (board.updateBoard(row, column, player2.getGamePiece())) {
                    if (getStatus() == Status.PLAYER2_WON) {
                        System.out.println(board.toString());
                        System.out.println(player2.getName() + " wins!");
                        return true;
                    } else if (getStatus() == Status.TIE) {
                        System.out.println(board.toString());
                        System.out.println("Tie game!");
                        return true;
                    }
                    break;
                } else {
                    System.out.println("Invalid move. Please try again.");
                }
            } else {
                System.out.println("Invalid row or column. Please try again.");
            }
        }
        return false;
    }

    // This should return a status of the board, using the enum Status:
    private Status getStatus() {
        // check rows
        for(int i = 0; i < 3; i++){
            if(board.board[i][0] == board.board[i][1] && board.board[i][1] == board.board[i][2]){
                if(board.board[i][0] == player1.getGamePiece()){
                    return Status.PLAYER1_WON;
                } else if(board.board[i][0] == player2.getGamePiece()){
                    return Status.PLAYER2_WON;
                }
            }
        }
        // check columns
        for(int i = 0; i < 3; i++){
            if(board.board[0][i] == board.board[1][i] && board.board[0][i] == board.board[2][i]){
                if(board.board[0][i] == player1.getGamePiece()){
                    return Status.PLAYER1_WON;
                } else if(board.board[0][i] == player2.getGamePiece()){
                    return Status.PLAYER2_WON;
                }
            }
        }
        // check diagonals
        if(board.board[0][0] == board.board[1][1] && board.board[1][1] == board.board[2][2]){
            if(board.board[0][0] == player1.getGamePiece()){
                return Status.PLAYER1_WON;
            } else if(board.board[0][0] == player2.getGamePiece()){
                return Status.PLAYER2_WON;
            }
        }
        if(board.board[0][2] == board.board[1][1] && board.board[1][1] == board.board[2][0]){
            if(board.board[0][2] == player1.getGamePiece()){
                return Status.PLAYER1_WON;
            } else if (board.board[0][2] == player2.getGamePiece()){
                return Status.PLAYER2_WON;
            }
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board.board[i][j] == ' '){
                    return Status.UNFINISHED;
                }
            }
        }

        return Status.TIE;

    }

    // 1. Set up, ask players for names and game piece
    // 2. For each round, let each player pick a spot
    // 3. Print the board after each move:
    // 4. When the game is over, announce the winner or that it is a tie
    public static void main(String[] args) {
        Game game = new Game();
        game.player1 = new Player("", ' ');
        game.player2 = new Player("", ' ');
        game.board = new Board();
        game.initPlayers();
        while(true){
            if(game.round()){
                break;
            }
        }
    }


}
