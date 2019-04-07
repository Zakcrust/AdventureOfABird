import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class startWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class startWorld extends World
{

    /**
     * Constructor for objects of class startWorld.
     * 
     */
    GreenfootImage img;
    public startWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        addObject(new startLabel("Press Space to Start",64),getWidth()/2,getHeight()/2);
   
    }

  
}
