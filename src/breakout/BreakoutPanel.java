/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import breakout.entities.GameEntity;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Paul
 */
public class BreakoutPanel extends JPanel
{
    
    //Game that this panel is being used for
    private BreakoutGame breakoutGame;
    
    public BreakoutPanel(BreakoutGame breakoutGame)
    {
        super();
        this.breakoutGame = breakoutGame;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
         for(GameEntity gE : breakoutGame.getGameEntities())
         {
             gE.paintComponent(g);
         }
    }
    
}
