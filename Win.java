import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Win here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Win extends Actor
{
    /**
     * Act - do whatever the Win wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Win()
    {
    }
    
    public Win(String text)
    {
        GreenfootImage label = new GreenfootImage(text,100,Color.BLUE,null);
        setImage(label);
        label = null;
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
