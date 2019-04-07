import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cloud here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cloud extends Actor
{
    /**
     * Act - do whatever the Cloud wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    public Cloud()
    {
        scaleImage(4);
    }
    
    public void act() 
    {
        move(-3);
        destroyOnEdge();
    }
    
    private void destroyOnEdge()
    {
        if(isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
    
    private void scaleImage(int x)
    {
        img = getImage();
        img.scale(img.getWidth()/(Greenfoot.getRandomNumber(x)+1),img.getHeight()/(Greenfoot.getRandomNumber(x)+1));
        setImage(img);
        img = null;
    }
}
