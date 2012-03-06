/*
 * Contains the logic for the game, 
 * Has an array of GameEntities used in the game
 * Extends Thread to move all the gameEntities
 */
package breakout;

import breakout.entities.GameEntity;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import breakout.entities.*;

/**
 *
 * @author Paul
 */
public class BreakoutGame extends Thread
{
    //Refreshes the screen every n milliseconds

    private final int REFRESH_TIME = 30;
    //Number of rows of blocks to draw
    private final int NUMBER_OF_ROWS = 10;
    //Number of lives
    private final int MAX_LIVES = 3;
    
    //ArrayList of all the GameEntities currently used
    private ArrayList<GameEntity> gameEntities;
    //The ball the game uses
    private Ball ball;
    //Paddle for the player
    private Paddle paddle;
    //Lives left;
    private int lives;
    //JPanel that draws everything
    private BreakoutPanel breakoutPanel;
    //Breakout object that is running the game
    private Breakout breakout;
    //Array of BreakoutEvent listeners
    private ArrayList<BreakoutListener> breakoutListeners;
    //Whether the game is running
    private boolean gameRunning;
    
    public BreakoutGame(Breakout breakout)
    {
        //Breakout that the game is running in
        this.breakout = breakout;
        
        //Create a new array of type GameEntity containing all the things for the game
        gameEntities = new ArrayList<GameEntity>();
        
        //Create a new BreakoutPanel and pass the current BreakoutGame as a parameter
        breakoutPanel = new BreakoutPanel(this);
        
        //Create all the entities for the game and add them to the array
        createGameEntities();
        
        //Set number of lives
        lives = MAX_LIVES;
        //Set that the game is running
        gameRunning = true;
        
        //Initialise the array of listeners of this game
        breakoutListeners = new ArrayList<BreakoutListener>();
        
        //Add a PaddleController KeyListener to the breakout game to control the paddle
        breakout.addKeyListener(new PaddleController(this, paddle));
        
        //Start the thread running
        start();
    }

    public void createGameEntities()
    {
        //Add a background image
        gameEntities.add(new GameEntity(0, 0, "background.jpg")
        {
        });

        //Create blocks
        //Calculate how many columns of blocks to add
        int numCol = (int) Math.floor(breakout.WIDTH / 80);
        for (int y = 0; y < NUMBER_OF_ROWS; y++) {
            for (int x = 0; x < numCol; x++) {
                Block b = new Block(x * 80, y * 20, (x*y % 3) + 1);
                //Add it to the list of all the entities
                gameEntities.add(b);
            }
        }

        //Create ball
        ball = new Ball(this, 280, 350, 4, -15);
        gameEntities.add(ball);

        //Create paddle
        paddle = new Paddle(this, 240, breakout.HEIGHT - 20, 10);
        gameEntities.add(paddle);
        
        //Create lives display
        gameEntities.add(new TextDisplay(this, 10, 400, TextDisplayType.LIVES));
    }

    /**
     * Detect collisions and stuff in the game
     */
    public void detectCollisions()
    {
        
        //Ball going below screen (lose life)
        if(ball.getY() > breakout.HEIGHT) {
            ball.reset();
            lives --;
        }
        
        //Ball vs Block collisions
        for (GameEntity ge : gameEntities) {
            if (ge instanceof Block) {
                if (ball.intersects(ge)) {
                    ball.flipY();
                    gameEntities.remove(ge);
                    break;
                }
            } else if (ge instanceof Ball) {
                if (paddle.intersects(ge)) {
                    ((Ball) ge).flipY();
                    ((Ball) ge).setXSpeed(((Ball) ge).getXSpeed() + (ge.getX() - paddle.getX() - 30) / 10);
                    
                }
            }

        }
        
    }
    
    public void detectGameState()
    {
        //Count the number of blocks left
        int numBlock = 0;
        for(GameEntity ge : gameEntities)
        {
            if(ge instanceof Block) {
                numBlock++;
            }
        }
        if(numBlock == 0) {
            //Game Won
            gameStateChanged(GameState.WON);
            //Stop the thread running to stop generating exceptions which cause loops
            gameRunning = false;
        }
        
        //No lives left, game over
        if(lives < 0) {
            //Make new game lost event to be listened by BreakoutManager
            gameStateChanged(GameState.LOST);
            //Stop the thread running to stop generating exceptions which cause loops
            gameRunning = false;
        }
    }
    

    public Paddle getPaddle()
    {
        //That's a paddlin'
        return paddle;
    }

    public Ball getBall()
    {
        return ball;
    }
    
    public BreakoutPanel getBreakoutPanel()
    {
        return breakoutPanel;
    }
    
    public ArrayList<GameEntity> getGameEntities()
    {
        return gameEntities;
    }
    
    public Breakout getBreakout ()
    {
        return breakout;
    }
    
    public int getLives()
    {
        return lives;
    }
    
    /**
     * Calls move() for every GameEntity in gameEntities
     */
    public void moveGameEntities()
    {
        for (GameEntity gE : gameEntities) {
            gE.move();
        }
    }
    
    /**
     * Runs the game
     */
    @Override
    public void run()
    {
        while (gameRunning ) {
            //Move all the game objects
            moveGameEntities();
            //Detect any new collisions and deal with them
            detectCollisions();
            //Detect if the game is won or lost
            detectGameState();
            //Redraw the Breakout JFrame
            breakout.repaint();
            try {
                Thread.sleep(REFRESH_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(BreakoutGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Adds a BreakoutListener to the listeners array
     */
    public synchronized void addBreakoutListener(BreakoutListener l)
    {
        breakoutListeners.add(l);
    }
    
    /**
     * Removes a BreakoutListener from the array
     */
    public synchronized void removeBreakoutListener(BreakoutListener l)
    {
        breakoutListeners.remove(l);
    }
    
    /**
     * Notify all listeners of a change in the game
     */
    public synchronized void gameStateChanged(GameState newState)
    {
        for(BreakoutListener l : breakoutListeners) {
            if(newState == GameState.WON) {
                l.gameComplete(new BreakoutEvent(this));
            } else if(newState == GameState.LOST) {
                l.gameOver(new BreakoutEvent(this));
            }
        }
    }
}
