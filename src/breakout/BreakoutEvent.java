/*
 * Custom event that deals with game overs and winning a game
 */
package breakout;

import java.util.EventObject;

/**
 * @author Paul
 */
public class BreakoutEvent extends EventObject
{
    
    public BreakoutEvent(Object source)
    {
        super(source);
    }
}
