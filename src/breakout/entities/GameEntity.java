/*
 * Abstract class for entity
 * An entity is anything that is drawn in the game (Paddle, Ball, Block)
 */
package breakout.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

/**

 @author Paul
 */
public abstract class GameEntity
{
    //Position
    private int x, y;
    //Filename of the image
    private String imageName;
    //Image to draw
    private BufferedImage image;
    
    /**
     * Constructor for a GameEntity
     * 
     * @param x         x position
     * @param y         y position
     * @param imageName file name of the image
     */
    public GameEntity(int x, int y, String imageName)
    {
        this.x = x;
        this.y = y;
        this.imageName = imageName;
        //Load file at resources/imageName into image variable
        try {
            image = ImageIO.read(new File("resources/"+imageName));
        } catch (IOException ex) {
            Logger.getLogger(GameEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Getter for x
     * @return x position of image (top left corner)
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Getter for y
     * @return y position of entity (top left corner)
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * Setter for x
     * @param x The new x position of the object
     */
    public void setX(int newX)
    {
        x = newX;
    }
    
    /**
     * Setter for y
     * @param newY The new y position for the entity
     */
    public void setY(int newY)
    {
        y = newY;
    }
    
    /**
     * Returns the bounding Rectangle of the entity
     */
    public Rectangle getBoundingRectangle()
    {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }
    
    /**
     * Calculates whether this GameEntity intersects another one
     * (Using rectangular hit boxes
     * @param testGameEntity        GameEntity to test intersection with
     * @return True if intersecting with object, false otherwise
     */
    public boolean intersects(GameEntity testGameEntity)
    {
        return getBoundingRectangle().intersects(testGameEntity.getBoundingRectangle());
    }
    
    /**
     * Empty method for subclasses to override to enable moving of entities
     */
    public void move()
    {
        
    }
    
    /**
     * Method that draws the image at the specified position with the image
     * to a Graphics object
     * 
     * @param g     Graphics object to draw on
     */
    public void paintComponent(Graphics g)
    {
        g.drawImage(image, x, y, null);
    }
    
}
