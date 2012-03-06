/*
 * Breakout class extends JFrame and creates all the necessary objects to make
 * a new Breakout game.
 */
package breakout;

import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Paul
 */
public class Breakout extends JFrame
{

    public final int HEIGHT = 500;
    public final int WIDTH = 560;
    private final int HEIGHT_PADDING = 30;
    private final int WIDTH_PADDING = 10;
    //Game contained
    private BreakoutGame breakoutGame;

    public Breakout()
    {
        //Setup Frame options
        super("Breakout");
        setSize(WIDTH + WIDTH_PADDING, HEIGHT + HEIGHT_PADDING);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Position in the middle of the screen
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - WIDTH / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - HEIGHT / 2);

        //Display the frame
        setVisible(true);
        //Create new game
        breakoutGame = new BreakoutGame(this);
        
        //Add game panel to this frame
        add(breakoutGame.getBreakoutPanel());
        

    }
    
    /**
     * Returns the breakoutGame class currently being used
     * @return breakoutGame currently being used
     */
    public BreakoutGame getBreakoutGame()
    {
        return breakoutGame;
    }
}
