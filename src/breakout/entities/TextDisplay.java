/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout.entities;

import breakout.BreakoutGame;
import java.awt.Graphics;

/**
 *
 * @author User
 */
public class TextDisplay extends GameEntity
{
    
    private BreakoutGame game;
    private TextDisplayType type;
    private int x, y;
    
    public TextDisplay(BreakoutGame game, int x, int y, TextDisplayType type)
    {
        super(x, y, "ball.png");
        this.game = game;
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if(type == TextDisplayType.LIVES) {
            g.drawString("Lives: "+game.getLives(), x, y);
        }
    }
    
}
