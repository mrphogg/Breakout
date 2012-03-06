/*
 * Interface for the BreakoutEvent class
 */
package breakout;

/**
 *
 * @author Paul
 */
public interface BreakoutListener
{
    public void gameOver(BreakoutEvent e);
    
    public void gameComplete(BreakoutEvent e);
}
