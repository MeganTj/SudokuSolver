
/**
 * Represents a portion of the board that must be updated 
 * when a board square contained within this section is modified
 * 
 * @author Megan Tjandrasuwita
 * @version May 11, 2017
 */
public interface Section
{
    /**
     * Updates the state of a square
     * 
     * @param square a 2-D array of Squares representing each
     *        Square on the sudoku board
     * @param aSquare one Square on the sudoku board
     */
    void update(Square [][] square, Square aSquare);
    
    /**
     * Returns a pair of possible values that at least two squares in the same
     * section have as their only possible values.
     * 
     * @param square a 2-D array of Squares
     * @return array of int of length two that contains the first pair of
     *         possible values that occur in two squares of this section;
     *         null if no such pair exists
     */
    int[] getPair(Square [][] square);
    
    /**
     * Removes a pair of possible values from the squares in the
     * same section that are not a square that only has the two 
     * possible values
     * 
     * @param pair an array of int of length two that contains pair of possible
     *        values
     * @param square a 2-D array from which the two values are removed 
     *        from all squares in the same row not having the pair as
     *        their only possible values
     * @return whether this method made progress in solving the
     *         puzzle by removing any possible value.
     */
    boolean processPair(Square [][] square, int [] pair);
    
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
    public int findUniqueValue(Square [][] squares, Square s);
    
    /**
     * Determines a Square in a section already has a certain value 
     * 
     * @param squares a 2-D array of Squares representing each
     *        Square on the sudoku board
     * @param v a value from 1 to 9
     * @return true if the section already has the value; otherwise,
     *         false
     */
    public boolean hasValue(Square [][] squares, int v);
}
