/*
 * A block in breakout
 */
package breakout.entities;



/**
 *
 * @author Paul
 */
public class Block extends GameEntity
{
    private final int MIN_BLOCK_NUMBER = 1;
    private final int MAX_BLOCK_NUMBER = 2;
    
    /**
     * Makes a block object
     * 
     * @param x                 x position on screen
     * @param y                 y position on screen
     * @param blockNumber       type of block to draw (File nameblock[blockNumber].jpg
     */
    public Block(int x, int y, int blockNumber)
    {
        super(x, y, "block"+blockNumber+".png");
    }
    
    public void remove()
    {
        setX(1000);
        setY(1000);
    }
}
