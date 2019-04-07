import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Apple here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Apple extends Fruits
{
    /**
     * Act - do whatever the Apple wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    World world;
    public Apple()
    {
    
    }
    public Apple(World world)
    {
        this.world = world;
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
