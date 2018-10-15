import java.io.*;
import java.util.*;
/**
 * Board class models the 9 by 9 rectangular array of squares.
 * Each square can be blank or have an integer value from 1 to 
 * 9. Each square also has a list of possible values that can be 
 * used for that square's value.
 * 
 * Susan King has permission to use all of the classes in this Sudoku
 * project.
 * @author Megan Tjandrasuwita
 * @version August 1, 2017
 */
public class Board 
{
    private Square[][] square;
    private Section[] sections;
    //puzzle 3 and 8 don't work
    //easy or medium
    private int[][] puzzle =   {{1, 0, 0, 8, 0, 0, 3, 0, 0},
                                {0, 0, 4, 0, 9, 3, 0, 0, 2},
                                {3, 0, 0, 1, 6, 0, 0, 0, 8},
                                {6, 8, 5, 3, 0, 0, 0, 4, 0},
                                {0, 2, 0, 0, 4, 0, 0, 8, 0},
                                {0, 4, 0, 0, 0, 9, 6, 1, 5},
                                {2, 0, 0, 0, 1, 5, 0, 0, 4},
                                {4, 0, 0, 9, 2, 0, 5, 0, 0},
                                {0, 0, 6, 0, 0, 7, 0, 0, 1}};
           
    //easy
    private int[][] puzzle2 =  {{0, 0, 2, 4, 0, 8, 5, 0, 7},
                                {0, 0, 1, 0, 0, 0, 6, 4, 0},
                                {0, 0, 0, 9, 0, 0, 0, 0, 0},
                                {0, 2, 0, 0, 0, 1, 0, 8, 4},
                                {0, 0, 0, 5, 8, 4, 0, 0, 0},
                                {7, 4, 0, 2, 0, 0, 0, 9, 0},
                                {0, 0, 0, 0, 0, 5, 0, 0, 0},
                                {0, 8, 3, 0, 0, 0, 4, 0, 0},
                                {1, 0, 4, 6, 0, 3, 2, 0, 0}};
           
    //hard** 
    private int[][] puzzle3 =  {{0, 7, 0, 8, 0, 0, 0, 0, 9},
                                {0, 0, 0, 0, 0, 4, 6, 0, 7},
                                {0, 9, 0, 0, 0, 0, 0, 8, 0},
                                {0, 0, 2, 0, 4, 1, 0, 0, 0},
                                {0, 0, 4, 0, 8, 0, 2, 0, 0},
                                {0, 0, 0, 5, 3, 0, 9, 0, 0},
                                {0, 6, 0, 0, 0, 0, 0, 7, 0},
                                {5, 0, 1, 4, 0, 0, 0, 0, 0},
                                {8, 0, 0, 0, 0, 5, 0, 3, 0}};
          
    //medium
    private int[][] puzzle4 =  {{0, 0, 5, 0, 0, 0, 4, 0, 0},
                                {0, 0, 0, 4, 0, 6, 0, 0, 0},
                                {2, 0, 4, 0, 0, 0, 5, 0, 3},
                                {0, 1, 6, 0, 0, 0, 2, 9, 0},
                                {0, 0, 0, 2, 0, 9, 0, 0, 0},
                                {7, 2, 9, 0, 0, 0, 1, 3, 4},
                                {0, 0, 0, 8, 0, 1, 0, 0, 0},
                                {1, 0, 0, 5, 0, 2, 0, 0, 9},
                                {5, 0, 0, 3, 0, 7, 0, 0, 2}};
   
    //medium
    private int[][] puzzle5 =  {{0, 2, 0, 0, 7, 9, 0, 0, 0},
                                {3, 0, 9, 0, 5, 0, 0, 8, 0},
                                {0, 4, 0, 0, 0, 0, 0, 0, 3},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {4, 9, 0, 0, 2, 0, 0, 6, 7},
                                {6, 0, 0, 0, 0, 0, 0, 4, 9},
                                {0, 0, 0, 0, 0, 0, 0, 7, 8},
                                {0, 8, 0, 0, 9, 5, 3, 0, 0},
                                {0, 0, 3, 0, 1, 4, 6, 0, 0}};
           
    //medium
    private int[][] puzzle6 =  {{8, 0, 0, 0, 0, 6, 4, 0, 0},
                                {0, 2, 4, 0, 0, 0, 9, 0, 6},
                                {0, 0, 0, 0, 1, 0, 0, 0, 7},
                                {0, 0, 0, 2, 5, 1, 6, 0, 0},
                                {0, 6, 0, 0, 9, 0, 0, 5, 0},
                                {0, 0, 8, 6, 7, 4, 0, 0, 0},
                                {3, 0, 0, 0, 4, 0, 0, 0, 0},
                                {2, 0, 6, 0, 0, 0, 5, 4, 0},
                                {0, 0, 7, 9, 0, 0, 0, 0, 1}};
    //medium
    private int[][] puzzle7 =  {{0, 1, 0, 0, 0, 0, 0, 4, 0},
                                {0, 0, 7, 0, 0, 0, 0, 1, 0},
                                {0, 6, 0, 0, 0, 2, 0, 0, 0},
                                {8, 4, 0, 0, 2, 0, 9, 0, 5},
                                {7, 0, 0, 0, 0, 0, 0, 0, 6},
                                {0, 0, 0, 0, 0, 0, 4, 0, 3},
                                {0, 5, 0, 0, 8, 3, 0, 0, 0},
                                {0, 0, 0, 4, 1, 0, 0, 3, 0},
                                {0, 0, 6, 0, 0, 0, 0, 0, 0}};
    //hard
    private int[][] puzzle8 =  {{0, 0, 0, 0, 0, 0, 0, 2, 6},
                                {0, 0, 0, 0, 0, 9, 3, 0, 0},
                                {6, 7, 2, 0, 0, 0, 0, 0, 5},
                                {0, 0, 0, 0, 8, 0, 4, 0, 0},
                                {0, 0, 0, 4, 9, 0, 0, 0, 0},
                                {8, 9, 0, 5, 1, 0, 0, 0, 0},
                                {0, 1, 0, 0, 0, 0, 5, 0, 0},
                                {7, 0, 0, 2, 3, 0, 8, 0, 0},
                                {0, 8, 0, 0, 0, 0, 0, 7, 0}};
    /**
     * Constructs a new Board with the given dimensions.
     */
    public Board()
    {
        square = new Square[9][9];
        for (int r = 0; r < square.length; r++)
        {
            for (int c = 0; c < square[0].length; c++)
            {
                square[r][c] = new Square(0, r, c);
            }
        }
        sectionsInIt();
        //in order to change the puzzle that you want to solve, go to the 
        //implementation of initialCondition()
        initialCondition();
    }

    /**
     * Initialize all sections (row, columns, and regions) so each
     * type of section is initialized with consecutive integer values 
     * beginning with 0.
     *  
     */
    private void sectionsInIt()
    {
        sections = new Section[27];
        for (int i = 0; i < 27; i++)
        {
            if (i >= 0 && i < 9)
            {
                sections[i] = new Row(i);
            }
            if (i >= 9 && i < 18)
            {
                sections[i] = new Column(i - 9);
            }
            if (i >= 18 && i < 27)
            {
                sections[i] = new Region(i - 18);
            }
        }
    }

    /**
     * Fills up the board and removes possible values based on the the initial
     * puzzle.
     */
    public void initialCondition()
    {
        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                //change the third parameter of the method below to
                //change the puzzle you want to solve
                updateSections(r, c, puzzle3[r][c]);
            }
        }
        System.out.println("Puzzle:");
        printCurrent();
        System.out.println();
    }

    /**
     * Updates all the sections a square is in when a square's value has
     * been determined.
     * 
     * @param r the row the Square is in
     * @param c the column the Square s in
     * @param v the Square's new value
     */
    public void updateSections(int r, int c, int v)
    {
        if (v != 0)
        {
            square[r][c].setValue(v);
            sections[r].update(square, square[r][c]);
            sections[9 + c].update(square, square[r][c]);
            int region = square[r][c].getRegion();
            sections[18 + region].update(square, square[r][c]);
        }
    }
    

    /**
     * Returns the value that is unique to a square compared to the other 
     * squares in any of its sections
     * 
     * @param s the Square whose possible values are checked
     * @return the square's unique possible value if it has one; otherwise,
     *         return -1
     */
    public int checkForUniqueValue(Square s)
    {
        int row = s.getRow();
        int col = s.getCol();
        int reg = s.getRegion();
        if (sections[row].findUniqueValue(square, s) != -1)
        {
            return sections[row].findUniqueValue(square, s);
        }
        if (sections[col + 9].findUniqueValue(square, s) != -1)
        {
            return sections[col + 9].findUniqueValue(square, s);
        }
        if (sections[reg + 18].findUniqueValue(square, s) != -1)
        {
            return sections[reg + 18].findUniqueValue(square, s);
        }
        return -1;
    }

    /**
     * Uses the process pairs method to update the possible values
     * of the squares in the sections 
     * 
     * @return whether this method made progress in solving the
     *         puzzle by removing any possible value
     */
    public boolean processPairs()
    {
        boolean b = false;
        for (int i = 0; i < 27; i++)
        {
            Section a = sections[i];
            int [] values = a.getPair(square);
            if (values != null)
            {
                if (b == false)
                {
                    b = a.processPair(square, values);
                }
                else
                {
                    a.processPair(square, values);
                }
            }
        }
        return b;
    }

    /**
     * Runs through the 2-D array of squares and sets the value for any square
     * that has a unique possible value.
     */
    public void updateForUniqueValues()
    {
        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                int unique = checkForUniqueValue(square[r][c]);
                if (unique != -1)
                {
                    updateSections(r, c, unique);
                }
            }
        }
    }

    /**
     * Solves the sudoku puzzle through eliminating possible values and
     * the process pairs method
     */
    public void solvePuzzle()
    {
        while (!hasFinished())
        {
            while (!moveOn())
            {
                for (int r = 0; r < 9; r++)
                {
                    for (int c = 0; c < 9; c++)
                    {
                        ArrayList<Integer> pVal = square[r][c].getPValues();
                        if (pVal.size() == 1)
                        {
                            updateSections(r, c, pVal.get(0));
                        }
                    }
                }
                updateForUniqueValues();
                printCurrent();
                System.out.println();
            }
            updateForUniqueValues();
            if (processPairs() == false)
            {
                String c = findNextSquare();
                backTrack(c);
            }
        }
        System.out.println("Final Solution:");
        printCurrent();
        System.out.println();
    }
    
    /**
     * Completes in the remaining puzzle by guessing the values of the empty
     * squares. If the guesses are wrong, the program will backtrack and guess
     * different values.
     * 
     * @param coordinates the coordinates of an empty square
     * @return true the guessed values are the correct ones; otherwise,
     *         false (backtrack to previous empty square)
     * 
     */
    public boolean backTrack(String coordinates)
    {
        if (coordinates.length() == 0)
        {
            return true;
        }  
        int row = Integer.parseInt(coordinates.substring(0, 1));
        int col = Integer.parseInt(coordinates.substring(2));
        Square s = square[row][col];
        ArrayList<Integer> values = s.getPValues();       
        for (int i = 0; i < values.size(); i++)
        {
            if (isSafe(values.get(i), s))
            {
                s.softSetValue(values.get(i));
                String c = findNextSquare();
                if (backTrack(c))
                {
                    return true;
                }  
                s.reset();
            }
        } 
        return false;
    }
    
    /**
     * Return whether a value is a possible value for a Square, given the 
     * current filled in Squares on the board.
     * 
     * @param v a value from 1 to 9
     * @param s a Square on the Sudoku board
     * @return true if v is a possible value for s; otherwise, false
     */
    public boolean isSafe(int v, Square s)
    {
        int row = s.getRow();
        int col = s.getCol();
        int region = s.getRegion();
        if (!sections[row].hasValue(square, v) &&
            !sections[9 + col].hasValue(square, v) &&
            !sections[18 + region].hasValue(square, v))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Returns coordinates of the next empty Square.
     * 
     * @return the coordinates of an empty Square; if there are no 
     *         empty Squares, return null
     */
    public String findNextSquare()
    {
        Square s1 = null; 
        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                Square s2 = square[r][c];
                ArrayList<Integer> list2 = s2.getPValues();
                if (s1 == null && s2.getValue() == 0 )
                {
                    s1 = s2;
                }
            }                       
        }
        if (s1 == null)
        {
            return "";
        }
        return s1.getRow() + " " + s1.getCol();
    }
    
    /**
     * Prints the current board.
     */
    public void printCurrent()
    {
        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                System.out.print(square[r][c].getValue() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Determines when the solver should move on to use the process
     * pairs method, and this occurs when all of the unfilled squares
     * have 2 or more possible values after all the possible values have
     * been eliminated based on the filled squares
     * 
     * @return true if the solver should move on to use process pairs;
     *         otherwise, false
     */
    public boolean moveOn()
    {
        boolean x = true;
        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                if (square[r][c].getPValues().size() == 1)
                {
                    x = false;
                }
            }
        }
        return x;
    }

    /**
     * Determines whether the puzzle is completely solved
     * 
     * @return true if the puzzle is completely solved; otherwise,
     *         false
     */
    public boolean hasFinished()
    {
        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                if (square[r][c].getValue() == 0 &&
                    square[r][c].getPValues().size() != 0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Solves a sudoku puzzle.
     * 
     * @param args  user's information from the command line
     */
    public static void main(String [] args)
    {
        Board b = new Board();
        b.solvePuzzle();
    }
}
