import java.io.*;
import java.util.*;
/**
 * Represents a 3 by 3 square region on a Sudoku board.
 * 
 * @author Megan Tjandrasuwita
 * @version June 1, 2017
 */
public class Region implements Section
{
    /*
     * Regions are labeled 0 to 8 from left to right, starting at the
     * top left region.
     */
    private int regionNum;
    private int row;
    private int col;
    /**
     * Constructor for objects of class Region
     * 
     * @param num the region num
     */
    public Region(int num)
    {
        regionNum = num;
        if (num == 0)
        {
            row = 0;
            col = 0;
        }
        else if (num == 1)
        {
            row = 0;
            col = 3;
        }
        else if (num == 2)
        {
            row = 0;
            col = 6;
        }
        else if (num == 3)
        {
            row = 3;
            col = 0;
        }
        else if (num == 4)
        {
            row = 3;
            col = 3;
        }
        else if (num == 5)
        {
            row = 3;
            col = 6;
        }
        else if (num == 6)
        {
            row = 6;
            col = 0;
        }
        else if (num == 7)
        {
            row = 6;
            col = 3;
        }
        else if (num == 8)
        {
            row = 6;
            col = 6;
        }
    }

    /**
     * Updates the state of a square
     * 
     * @param square a 2-D array of Squares representing each
     *        Square on the sudoku board
     * @param aSquare one Square on the sudoku board
     */
    public void update(Square [][] square, Square aSquare)
    {
        if (aSquare.getRegion() == regionNum)
        {
            for (int r = row; r < row + 3; r++)
            {
                for (int c = col; c < col + 3; c++)
                {
                    if (square[r][c].getValue() == 0)
                    {
                        square[r][c].removePossibleValue(aSquare.getValue());
                    }
                }
            }
        }
    }
    
    /**
     * Returns a pair of possible values that at least two squares in the same
     * section have as their only possible values.
     * 
     * @param square a 2-D array of Squares
     * @return an array of int of length two that contains the first pair of
     *         possible values that occur in two squares of this section;
     *         null if no such pair exists
     */
    public int[] getPair(Square [][] square)
    {
        ArrayList<Square> squares = new ArrayList<Square>();
        for (int r = row; r < row + 3; r++)
        {
            for (int c = col; c < col + 3; c++)
            {
                Square current = square[r][c];
                if (current.getPValues().size() == 2)
                {
                    squares.add(current);
                }
            }
        }
        int [] pair = new int[2];
        for (int i = 0; i < squares.size(); i++)
        {
            Square a = squares.get(i);
            ArrayList<Integer> fPair = a.getPValues();
            for (int j = i + 1; j < squares.size(); j++)
            {
                Square b = squares.get(j);
                ArrayList<Integer> sPair = b.getPValues();
                if (fPair.get(0) == sPair.get(0) &&
                    fPair.get(1) == sPair.get(1))
                {
                    pair[0] = fPair.get(0);
                    pair[1] = fPair.get(1);
                    return pair;
                }
            }
        }
        return null;
    }
    
    /**
     * Removes a pair of possible values from the squares in the same
     * section that are not a square that only has the two possible values
     * 
     * @param pair an array of int of length two that contains pair of possible
     *        values
     * @param square a 2-D array from which the two possible values are removed 
     *        from all squares in the same row not having the pair as
     *        their only possible values
     * @return whether this method made progress in solving the
     *         puzzle by removing any possible value
     */
    public boolean processPair(Square [][] square, int [] pair)
    {
        boolean b = false;
        for (int r = row; r < row + 3; r++)
        {
            for (int c = col; c < col + 3; c++)
            {
                Square current = square[r][c];
                ArrayList<Integer> values = current.getPValues();
                if (notSquare(pair[0], pair[1], current))
                {
                    for (int i = values.size() - 1; i >= 0; i--)
                    {
                        if (values.get(i) == pair[0] ||
                            values.get(i) == pair[1])
                        {
                            current.removePossibleValue(values.get(i));
                            b = true;
                        }
                        values = current.getPValues();
                    }
                }
            }
        }
        return b;
    }
    
    /**
     * Determines whether a square has the same two values and only
     * these two values as its possible values
     * 
     * @param a one of the possible values
     * @param b the other possible value in the pair
     * @param s a Square whose possible values are being compared
     *        to a and b
     * @return true if the square has the parameters a and b as its only
     *         possible values; otherwise, false
     */
    public boolean notSquare(int a, int b, Square s)
    {
        ArrayList<Integer> values = s.getPValues();
        if (values.size() == 2)
        {
            if (values.get(0) == a && values.get(1) == b)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Returns the possible value that is unique to one square in the section,
     * if there is such a value.
     * 
     * @param squares the 2-D array of Squares
     * @param s the Square
     * @return an integer between 1 to 9 that is a unique 
     *         possible value; otherwise, if that value doesn't exist,
     *         returns -1
     */
    public int findUniqueValue(Square [][] squares, Square s)
    {
        ArrayList<Integer> arr = s.getPValues();
        boolean b = true;
        for (int i = 0; i < arr.size(); i++)
        {
            int value = arr.get(i);
            for (int r = row; r < row + 3; r++)
            {
                for (int c = col; c < col + 3; c++)
                {
                    Square x = squares[r][c];
                    ArrayList<Integer> arr2 = x.getPValues();
                    if (s.getCol() != c || s.getRow() != r)
                    {
                        for (int j = 0; j < arr2.size(); j++)
                        {
                            if (arr2.get(j) == value)
                            {
                                b = false;
                            }
                        }
                    }
                }      
            }
            if (b == true)
            {
                return value;
            }
            b = true;
        }
        return -1;
    }
    
    /**
     * Determines a Square in a section already has a certain value 
     * 
     * @param squares a 2-D array of Squares representing each
     *        Square on the sudoku board
     * @param v a value from 1 to 9
     * @return true if the section already has the value; otherwise,
     *         false
     */
    public boolean hasValue(Square [][] squares, int v)
    {
        for (int r = row; r < row + 3; r++)
        {
            for (int c = col; c < col + 3; c++)
            {
                Square s = squares[r][c];
                if (v == s.getValue())
                {
                    return true;
                }
            }
        }
        return false;
    }
}
