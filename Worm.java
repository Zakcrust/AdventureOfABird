import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Worm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Worm extends Actor
{
    /**
     * Act - do whatever the Worm wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int DECAY_DELAY = 120;
    private int decayTimer=0;
    public void act() 
    {
        wormDecay();
    }    
    
    private void wormDecay()
    {
        if(decayTimer==DECAY_DELAY)
        {
            decayTimer=0;
            getWorld().removeObject(this);
        }
        decayTimer++;
        
    }
}
