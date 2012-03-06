/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import breakout.entities.Paddle;
import breakout.entities.PaddleDirection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author User
 */
public class PaddleController implements KeyListener
{
    //Current BreakoutGame class running
    private Paddle paddle;
    private BreakoutGame game;
    
    
    public PaddleController(BreakoutGame game, Paddle paddle)
    {
        this.paddle = paddle;
        this.game = game;
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            paddle.setDirection(PaddleDirection.LEFT);
        } else if (key == KeyEvent.VK_RIGHT) {
            paddle.setDirection(PaddleDirection.RIGHT);
        } else if(key == KeyEvent.VK_SPACE) {
            if(game.getBall().isOnPaddle()) {
                game.getBall().launch();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT) {
            if(paddle.getPaddleDirection() == PaddleDirection.LEFT) {
                paddle.setDirection(PaddleDirection.STATIONARY);
            }
        }
        if(key == KeyEvent.VK_RIGHT) {
            if(paddle.getPaddleDirection() == PaddleDirection.RIGHT) {
                paddle.setDirection(PaddleDirection.STATIONARY);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }
}
