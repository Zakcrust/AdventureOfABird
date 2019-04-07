import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Papaya here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Papaya extends Fruits
{
    /**
     * Act - do whatever the Papaya wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    World world;
    public Papaya()
    {
    
    }
    
    public Papaya(World world)
    {
        this.world=world;
    }
    
    public void act() 
    {
        movement();
    }
    
    private void movement()
    {
        if(getY()<getWorld().getHeight()-25)
        {
            setLocation(getX(),getY()+5);
            turn(15);
        }
    }  
}
