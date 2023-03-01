package Main;

public class Board {
    // a 2D array to represent the pieces on the board:
    char [][] board;

    // should instantiate the board to be a 3x3 array:
    public Board() {
        board = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = ' ';
            }
        }
    }

    // this method should take in a row, column, and a newChar and update the board accordingly:
    public boolean updateBoard(int row, int column, char newChar) {
        if(board[row][column] != ' '){
            System.out.println("Invalid move, Please try again.");
            return false;
        }else{
            board[row][column] = newChar;
            return true;
        }
    }

    // this method should return a string representation of the board:
    public String toString() {
        String boardString = "";
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardString += board[i][j] + " ";
            }
            boardString += "\n";
        }
        return boardString;
    }
}
