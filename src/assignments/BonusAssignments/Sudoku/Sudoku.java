package assignments.BonusAssignments.Sudoku;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.Scanner;

import java.io.PrintWriter;

import assignments.datastructures.Vector;

import java.util.Random;

/**
 * Reads an incomplete Sudoku board in from a .txt file,
 * then writes the solved board out to a new .txt file.
 * 
 * The board representation is backed by a 2D array.
 */
public class Sudoku {

    // Initialize an empty 2D array
    private int[][] board;

    /**
     * Creates a workable Sudoku board from the 2D array
     * 
     * @param board a 9-by-9 2D array to represent a Sudoku board where 0 represents an empty cell.
     */
    public Sudoku(int[][] board) { // [Row] , [Column]
        this.board = board;
    }

    /**
     * Reads in a Sudoku board from a text file.
     * 
     * The file must consist of 9 lines with 9 characters. 
     * Space characters represent empty cells.
     * 
     * @param filename the path to the file with the puzzle
     * @throws FileNotFoundException if the file does not exist
     */
    public Sudoku(String filename) throws FileNotFoundException {

        board = new int [9][9]; // represents the current board

        Scanner scan = new Scanner(new File(filename)); // open file reader

        // copies input file into our board representation
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
        scan.close(); // close the file reader
    }
    

    /**
     * Finds the empty cell with the least legal moves avaliable.
     * 
     * This simulates how I would solve a Sudoku puzzle myself.
     * 
     * @return the row and column of the best square, or null if the board is full.
     */
    private int[] findBestSquare() {
        
        int bestRow = -1; 
        int bestCol = -1;
        int leastMoves = 10; // start with worst case

        // checks every single square on board in turn
        for (int row = 0; row < 9; row++) { 
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) {
                    
                    int moves = 0;

                    // Check how many moves are possible for the current square
                    for (int i = 1; i <= 9; i++) {
                        if (doable(row, col, i)) {
                            moves++;
                        }
                    }

                    // change bestRow, bestCol, and leastMoves to accuratly represent current best square
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


    /**
     * Asks whether a value can legally be placed in an empty cell.
     * 
     * A move is legal only if a duplicate value does not exist
     * in the same row, column, or 3x3 square.
     * 
     * @param row the row index
     * @param col the column index
     * @param value the value to test for legality
     * @return true if the move is legal, otherwise false
     */
    private boolean doable(int row, int col, int value) {

        // check if value is already present in this Row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        // check if value is already present in this Column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == value) {
                return false;
            }
        }

        int startRow = row - row %3;
        int startCol = col - col %3;

        // Checks if value is already present in this 3x3 board section
        for (int i = startRow; i < startRow + 3; i++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[i][c] == value) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Checks to see if there is a contradictioin in the Sudoku board.
     * 
     * Each occupied cell's value is briefly removed and tested to see if 
     * it can legally exist there.
     * 
     * @return true if there is a contradiction, otherwise false
     */
    public boolean checkContradiction() {

        // checks every square on the board
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                int value = board[r][c]; // stores current value

                if (board[r][c] != 0) {
                    board[r][c] = 0; // remove value from current square

                    boolean boo = doable(r, c, value); // check if the value that was there is allowed to exist there

                    board[r][c] = value; // places value back into board

                    if (!boo) {
                        return true; // contradiction exists
                    }
                }
            }
        }
        return false; // contradiction does not exist
    }


    /**
     * Finds a solution to the Sudoku puzzle.
     * 
     * @return true if a solution was found, otherwise false
     */
    public boolean solve() {

        int[] empty = findBestSquare();

        if (empty == null) { // return true if board is already solved
            return true;
        }

        int row = empty[0];

        int col = empty[1];

        // place values into board until a single solution is found
        for (int i = 1; i <= 9; i++) {
            if (doable(row, col, i)) {
                board[row][col] = i;

                if (solve()) { // recursive call until board is solved
                    return true;
                }

                board[row][col] = 0;
            }
        }
        return false; // only returns false if board cannot be solved
    }


    /**
     * Prints the Sudoku board to the terminal.
     */
    public void printBoard() {

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                System.out.print(board[r][c] + " ");
            }

            System.out.println();
        }
    }


    private PrintWriter out;
    /**
     * Writes the Sudoku board to an output file.
     * 
     * 0s are converted to spaces.
     */
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


    /**
     * creates an output file for writing solutions.
     * 
     * !!OVERWRITES EXISTING FILES!!
     * 
     * @param filename the path to the output file
     * @throws FileNotFoundException if a file fails to be created
     */
    public void createOutputFile(String filename) throws FileNotFoundException {

        out = new PrintWriter(filename);
    }


    /**
     * Close the output file if open
     */
    public void closeOutputFile() {

        if (out != null) {
            out.close();
        }
    }

    private int maxSolutions = 100; // Prevents the program from using up too much space and computer reasorces.
    private int solutions = 0;
    /**
     * Finds up to 100 solutions for a given Sudoku board.
     * 
     * Uses recursive backtracking.
     */
    public void Solutions() {
        if (solutions >= maxSolutions){
            return;
        }

        int[] empty = findBestSquare();

        if (empty == null) { // board is complete

            solutions++; 

            if (out != null) {
                writeToFile();
            }

            return; // return from current call
        }

        int r = empty[0]; 
        int c = empty[1];

        for (int i = 1; i <= 9; i++) { // examine every possibility

            if (doable(r, c, i)) { // check if this move would violate sudoku rules
                board[r][c] = i; 

                Solutions(); // recursively calls until puzzle is completely solved

                board[r][c] = 0; // This resets the board after every possibility begining with this choice is exhausted. i.e. Backtracking
            }
        }
    }


    /**
     * Returns the number of solutions found.
     * 
     * @return the number of solutions found
     */
    public int getSolutionCount() {
        return solutions;
    }


    /**
     * Creates a copy of a Sudoku board.
     * 
     * @return a copy of the oridional 2D array
     */
    private int[][] copy() {
        int[][] copy = new int[9][9];

        for (int r=0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                copy[r][c] = board[r][c];
            }
        }
        return copy;
    }


    /**
     * Generates a valid fully solved Sudoku board using 
     * random number selection on each square.
     * 
     * assumes current board has no contradictions
     * 
     * 
     * @return true if the board is successfully generated, otherwise false
     */
    public boolean generateSolved() {
        int[] empty = findBestSquare();

        if (empty == null) {
            return true; // return true when solved
        }

        int r = empty[0];
        int c = empty[1];

        Vector<Integer> vec = new Vector<>(); // store values 1-9 for randomized inseartion
        for (int i = 1; i <= 9; i++) { 
            vec.push(i);
        }

        Random rand = new Random();

        // Shuffle the values in the vector for randomized order
        for (int i = vec.length() - 1; i > 0; i--) {

            int j = rand.nextInt(i + 1);

            int temp = vec.at(i);

            vec.set(i, vec.at(j));
            vec.set(j, temp);
        }

        // Try insearting values 1-9 in random order
        for (int i = 0; i < vec.length(); i++) {
            int value = vec.at(i);

            if (doable(r, c, value)) {

                board[r][c] = value;

                if (generateSolved()) {
                    return true;
                }

                board[r][c] = 0; // Backtrack if this choice does not lead to a solution
            }
        }
        return false; // return false if no valid value can be added to current square
    }


    /**
     * Removes values randomly from a solved Sudoku board to create 
     * a puzzle board with a single solution.
     * 
     * @param clues the number of cells to be completed or "clues" to be in the resulting board.
     */
    public void generatePuzzleBoard(int clues) {

        generateSolved(); //generate a fully solved board

        Random rand = new Random();

        int f = 81; // number of solved squares

        while (f > clues) { 

            int r = rand.nextInt(9);
            int c = rand.nextInt(9);

            if (board[r][c] == 0) { // check if square is already empty
                continue;
            }

            int temp = board[r][c];

            board[r][c] = 0; // Set board square to empty


            // Tests to ensure "solvability"
            Sudoku test = new Sudoku(copy()); 

            test.Solutions();

            if (test.getSolutionCount() != 1) {

                board[r][c] = temp; // Place value back if puzzle is invalid after removing it.
            }
            else {
                f--; // deincrement f
            }
        }
    }


    /**
     * Runs the Sudoku solver.
     * 
     * Prompts for user to either input a puzzle file or choose the difficulty of the generated puzzle,
     * Then solves the puzzle and writes solutions to selected .txt file.
     * 
     * @param args command-line args
     * @throws FileNotFoundException if a file cannot be used
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner input = new Scanner(System.in);

        System.out.println("Would you like to use a custome Sudoku puzzle, or a generated one? (enter \"Yes\" for custom puzzle or \"No\" for generated one)");
        String anwser = input.nextLine();

        Sudoku sudoku;

        if (anwser.equals("Yes")) {
            System.out.println("Enter Sudoku puzzle file path (Without quotations): ");
            String fileIn = input.nextLine();
            sudoku = new Sudoku(fileIn);
        }

        else {
            System.out.println("Enter number of clues to be on generated board: ");
            int clues = Integer.parseInt(input.nextLine()); // allows input to be an Integer
            sudoku = new Sudoku(new int[9][9]);
            sudoku.generatePuzzleBoard(clues);
        }

        System.out.println("Enter output file: ");
        String fileOut = input.nextLine();


        if (sudoku.checkContradiction()) {
            System.out.println("Puzzle contains a contradiction.");
        }


        else {

            sudoku.createOutputFile(fileOut);

            File location = new File(fileOut);
            System.out.println("Output file location: " + location.getAbsolutePath());
            
            sudoku.Solutions();

            sudoku.closeOutputFile();

            if (sudoku.getSolutionCount() == 0) {
                System.out.println("Puzzle has no solution.");
            }

            System.out.println("Solutions found: " + sudoku.getSolutionCount());
        }
        input.close();
        
    }
}


