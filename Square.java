import java.io.*;
import java.util.*;
/**
 * Represents a square in the Sudoku board.
 * 
 * @author Megan Tjandrasuwita
 * @version May 11, 2017
 */
public class Square
{
    private ArrayList<Integer> pValues;
    private ArrayList<Integer> savedPValues;
    private int value;
    private int row;
    private int column;
    private int region;
    /**
     * Constructor for objects of class Square
     * 
     * @param val the Square's value
     * @param r the row the Square is in
     * @param c the column the Square is in
     */
    public Square(int val, int r, int c)
    {
        value = val;
        savedPValues = new ArrayList<Integer>();
        if (val == 0)
        {
            pValues = new ArrayList<Integer>();
            for (int i = 1; i <= 9; i++)
            {
                pValues.add(i);
            }
        }
        row = r;
        column = c;
        determineRegion();
    }

    /**
     * Determines which region the Square is in based on its row
     * and column
     * 
     */
    public void determineRegion()
    {
        if (row >= 0 && row < 3)
        {
            if (column >= 0 && column < 3)
            {
                region = 0;
            }
            if (column >= 3 && column < 6)
            {
                region = 1;
            }
            if (column >= 6 && column < 9)
            {
                region = 2;
            }
        }
        if (row >= 3 && row < 6)
        {
            if (column >= 0 && column < 3)
            {
                region = 3;
            }
            if (column >= 3 && column < 6)
            {
                region = 4;
            }
            if (column >= 6 && column < 9)
            {
                region = 5;
            }
        }
        if (row >= 6 && row < 9)
        {
            if (column >= 0 && column < 3)
            {
                region = 6;
            }
            if (column >= 3 && column < 6)
            {
                region = 7;
            }
            if (column >= 6 && column < 9)
            {
                region = 8;
            }
        }
    }
    
    /**
     * Returns the value of the Square
     * 
     * @return the value of the Square
     */
    public int getValue()
    {
        return value;
    }
    
    /**
     * Sets the value of the Square
     * 
     * @param v the Square's new value
     */
    public void setValue(int v)
    {
        value = v;
        //removes all entries in pValues
        for (int i = pValues.size() - 1; i >= 0; i--)
        {
            pValues.remove(i);
        }
    }
    
    /**
     * Sets the value of the Square, but still saves its possible 
     * values.
     * 
     * @param v the Square's new value
     */
    public void softSetValue(int v)
    {
        value = v;
    }
    
    /**
     * Sets the value of the Square back to 0.
     */
    public void reset()
    {
        value = 0;
    }
    
    /**
     * Returns the row the Square is in
     * 
     * @return the row the Square is in
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Returns the column the Square is in
     * 
     * @return the column the Square is in
     */
    public int getCol()
    {
        return column;
    }
    
    /**
     * Returns the region the Square is in
     * 
     * @return the region the Square is in
     */
    public int getRegion()
    {
        return region;
    }
    
    /**
     * Returns an ArrayList of the Square's possible value
     * 
     * @return an ArrayList of the Square's possible value
     */
    public ArrayList<Integer> getPValues()
    {
        return pValues;
    }
    
    /**
     * Removes a possible value 
     * 
     * @param a the value to remove from the ArrayList of 
     *        possible values
     */
    public void removePossibleValue(int a)
    {
        for (int i = pValues.size() - 1; i >= 0; i--)
        {
            if (pValues.get(i) == a)
            {
                pValues.remove(i);
            }
        }
    }
}
