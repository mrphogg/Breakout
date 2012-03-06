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
public class Paddle extends GameEntity
{

    private PaddleDirection paddleDirection;
    private int speed;
    private BreakoutGame game;
    
    public Paddle(BreakoutGame game, int x, int y, int speed)
    {
        super(x, y, "paddle.png");
        this.game = game;
        this.speed = speed;
        //Set initial paddle direction
        paddleDirection = PaddleDirection.STATIONARY;
    }

    /**
     * Set the direction the paddle is moving in
     */
    public void setDirection(PaddleDirection newDir)
    {
        paddleDirection = newDir;
    }

    /**
     * get the direction of the paddle
     */
    public PaddleDirection getPaddleDirection()
    {
        return paddleDirection;
    }

    @Override
    public void move()
    {
        if (paddleDirection == PaddleDirection.LEFT) {
            setX(getX() - speed);
        } else if (paddleDirection == PaddleDirection.RIGHT) {
            setX(getX() + speed);
        }

        //Stop moving off of screen
        if (getX() < 0) {
            setX(0);
        } else if(getX() > 480) {
            setX(480);
        }
        
    }
}
