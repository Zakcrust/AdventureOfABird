import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Banana here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Banana extends Fruits
{
    /**
     * Act - do whatever the Banana wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    World world;
    public Banana()
    {
    
    }
    
    public Banana(World world)
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
