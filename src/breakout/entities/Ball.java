/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout.entities;

import breakout.BreakoutGame;

/**
 *
 * @author Paul
 */
public class Ball extends GameEntity
{

    private int xSpeed, ySpeed;
    private int startX, startY;
    private int startXSpeed, startYSpeed;
    private BreakoutGame game;
    private boolean isOnPaddle;
    
    public Ball(BreakoutGame game, int x, int y, int xSpeed, int ySpeed)
    {
        super(x, y, "ball.png");
        isOnPaddle = true;
        this.game = game;
        this.startX = x;
        this.startY = y;
        this.xSpeed = startXSpeed = xSpeed;
        this.ySpeed = startYSpeed = ySpeed;
    }
    
    /**
     * Flip the ySpeed of the ball
     */
    public void flipY()
    {
        ySpeed *= -1;
    }
    
    /**
     * Flip the xSpeed of the ball
     */
    public void flipX()
    {
        xSpeed *= -1;
    }
    
    /**
     * Resets the ball
     */
    public void reset()
    {
        isOnPaddle = true;
    }
    
    public void setXSpeed(int newXSpeed)
    {
        xSpeed = newXSpeed;
    }
    
    public int getXSpeed()
    {
        return xSpeed;
    }

    public boolean isOnPaddle()
    {
        return isOnPaddle;
    }
    
    public void launch()
    {
        isOnPaddle = false;
        xSpeed = 0;
        ySpeed = -Math.abs(startYSpeed);
    }
    /**
     * Override the move method to move the ball
     */
    @Override
    public void move()
    {
        if(!isOnPaddle) {
            //Update x & y values with xSpeed and ySpeed
            setX(getX() + xSpeed);
            setY(getY() + ySpeed);
        } else {
            setX(game.getPaddle().getX()+30);
            setY(game.getPaddle().getY()-20);
        }
        //Deal with bouncing of top, left and right of screen
        if (getY() < 0) {
            flipY();
        }
        if (getX() < 0) {
            flipX();
        }
        if (getX() > 550) {
            flipX();
        }
        
    }

}
