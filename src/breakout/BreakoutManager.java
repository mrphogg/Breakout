/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breakout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Paul
 */
public class BreakoutManager extends JFrame implements BreakoutListener
{
    private Breakout b;
    
    public BreakoutManager()
    {
        super("Breakout - Paul Hogg");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300, 200);
        setLocation((int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-getSize().getWidth()/2), (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-getSize().getHeight()/2));
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        //New game button, creates a new BreakoutGame object
        add(new JButton("New Game") {
            {
                this.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        newBreakoutGame();
                    }
                });
            }
        });
        
        //Exit button, exits BreakoutManager
        add(new JButton("Exit"){
            {
                this.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                        System.exit(0);
                    }
                });
            }
        });
        
    }
    
    public void newBreakoutGame()
    {
        b = new Breakout();
        b.getBreakoutGame().addBreakoutListener(this);
        setVisible(false);
    }
    
    public void exitBreakoutGame()
    {
        b.dispose();
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new BreakoutManager();
    }

    @Override
    public void gameOver(BreakoutEvent e)
    {
        JOptionPane.showMessageDialog(null, "You died :(", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        exitBreakoutGame();
    }

    @Override
    public void gameComplete(BreakoutEvent e)
    {
        JOptionPane.showMessageDialog(null, "Level cleared!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        exitBreakoutGame();
    }
}
