package assignments.BonusAssignments.Sudoku;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.Scanner;

import java.io.PrintWriter;

import assignments.datastructures.Vector;

import java.util.Collections;

import java.util.Random;


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
                char ch = c < line.length() ? line.charAt(c) : ' ';

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

    private int maxSolutions = 100;
    private int solutions = 0;
    public void Solutions() {
        if (solutions >= maxSolutions){
            return;
        }

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


    private int[][] copy() {
        int[][] copy = new int[9][9];

        for (int r=0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                copy[r][c] = board[r][c];
            }
        }
        return copy;
    }


    public boolean generateSolved() {
        int[] empty = findBestSquare();

        if (empty == null) {
            return true;
        }

        int r = empty[0];
        int c = empty[1];

        Vector<Integer> vec = new Vector<>();

        for (int i = 1; i <= 9; i++) {
            vec.push(i);
        }

        Random rand = new Random();

        for (int i = vec.length() - 1; i > 0; i--) {

            int j = rand.nextInt(i + 1);

            int temp = vec.at(i);

            vec.set(i, vec.at(j));
            vec.set(j, temp);
        }

        for (int i = 0; i < vec.length(); i++) {
            int value = vec.at(i);

            if (doable(r, c, value)) {

                board[r][c] = value;

                if (generateSolved()) {
                    return true;
                }
                board[r][c] = 0;
            }
        }
        return false;
    }


    public void generatePuzzleBoard(int clues) {

        generateSolved();

        Random rand = new Random();

        int f = 81;

        while (f > clues) {

            int r = rand.nextInt(9);
            int c = rand.nextInt(9);

            if (board[r][c] == 0) {
                continue;
            }

            int temp = board[r][c];

            board[r][c] = 0;


            // Tests to ensure "solvability"
            Sudoku test = new Sudoku(copy());

            test.Solutions();

            if (test.getSolutionCount() != 1) {

                board[r][c] = temp;
            }
            else {
                f--;
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        
        Sudoku sudoku = new Sudoku("C:\\Users\\willi\\Downloads\\Solvable - Easy.txt");

        if (sudoku.checkContradiction()) {
            System.out.println("Puzzle contains a contradiction.");
        }


        else {

            sudoku.createOutputFile("solutions.txt");
            
            sudoku.Solutions();

            sudoku.closeOutputFile();

            if (sudoku.getSolutionCount() == 0) {
                System.out.println("Puzzle has no solution.");
            }

            System.out.println("Solutions found: " + sudoku.getSolutionCount());
        }
        
    }
}


