import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BirdHearts here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BirdHearts extends Actor
{
    /**
     * Act - do whatever the BirdHearts wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    public BirdHearts()
    {
        
    }
    
    public BirdHearts(int id)
    {
        img = new GreenfootImage("heart_"+id+".png");
        setImage(img);
        img =null;
    }

}
