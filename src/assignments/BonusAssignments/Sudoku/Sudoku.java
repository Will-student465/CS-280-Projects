package assignments.BonusAssignments.Sudoku;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.Scanner;

import java.io.PrintWriter;


public class Sudoku {

    private int[][] board;

    public Sudoku(int[][] board) { // [Row] , [Column]
        this.board = board;
    }


    public Sudoku(String filename) throws FileNotFoundException {

        board = new int [9][9];

        Scanner scan = new Scanner(new File(filename));


        for (int r = 0; r < 9; r++) {
            String line = scan.nextLine();

            for (int c = 0; c < 9; c++) {
                char ch = line.charAt(c);

                if (ch == ' ') {
                    board[r][c] = 0;
                }

                else board[r][c] = Character.getNumericValue(ch);
            }
        }
        scan.close();
    }
    

    private int[] findBestSquare() {
        
        int bestRow = -1;
        int bestCol = -1;
        int leastMoves = 10;


        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) {
                    
                    int moves = 0;

                    for (int i = 1; i <= 9; i++) {
                        if (doable(row, col, i)) {
                            moves++;
                        }
                    }

                    if (moves < leastMoves) {
                        leastMoves = moves;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        if (bestRow == -1) {
            return null;
        }
        return new int[] {bestRow, bestCol};
    }


    private boolean doable(int row, int col, int value) {

        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == value) {
                return false;
            }
        }

        int startRow = row - row %3;
        int startCol = col - col %3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[i][c] == value) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean checkContradiction() {

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                int value = board[r][c];

                if (board[r][c] != 0) {
                    board[r][c] = 0;

                    boolean boo = doable(r, c, value);

                    board[r][c] = value;

                    if (!boo) {
                        return true; // contradiction exists
                    }
                }
            }
        }
        return false; // contradiction does not exist
    }


    public boolean solve() {

        int[] empty = findBestSquare();

        if (empty == null) {
            return true;
        }

        int row = empty[0];

        int col = empty[1];

        for (int i = 1; i <= 9; i++) {
            if (doable(row, col, i)) {
                board[row][col] = i;

                if (solve()) {
                    return true;
                }

                board[row][col] = 0;
            }
        }
        return false;
    }


    public void printBoard() {

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(board[r][c] + " ");
            }

            System.out.println();
        }
    }


    private PrintWriter out;
    public void writeToFile() {

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                if (board[r][c] == 0) {
                    out.print(" ");
                }

                else {
                    out.print(board[r][c]);
                }
            }

            out.println();
        }
        out.println();
    }


    public void createOutputFile(String filename) throws FileNotFoundException {

        out = new PrintWriter(filename);
    }


    public void closeOutputFile() {

        if (out != null) {
            out.close();
        }
    }


    private int solutions = 0;
    public void Solutions() {

        int[] empty = findBestSquare();

        if (empty == null) {

            solutions++;

            writeToFile();

            return;
        }

        int r = empty[0];
        int c = empty[1];

        for (int i = 1; i <= 9; i++) {

            if (doable(r, c, i)) {
                board[r][c] = i;

                Solutions();

                board[r][c] = 0;
            }
        }
    }

    public int getSolutionCount() {
        return solutions;
    }


    public static void main(String[] args) throws FileNotFoundException {
        
        Sudoku sudoku = new Sudoku("C:\\Users\\willi\\Downloads\\Unsolvable - Degenerate.txt");


        if (sudoku.checkContradiction()) {
            System.out.println("Puzzle contains a contradiction.");
        }


        else {

            sudoku.createOutputFile("solutions.txt");
            
            sudoku.Solutions();

            sudoku.closeOutputFile();

            System.out.println("Solutions found: " + sudoku.getSolutionCount());
        }
        
    }
}


