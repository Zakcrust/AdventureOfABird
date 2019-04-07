import greenfoot.*;
/**
 * Write a description of class Spawner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spawner  
{
    // instance variables - replace the example below with your own
    private World world;
    private Status status;
    public Spawner()
    {
    
    }
    public Spawner(World world)
    {
        this.world=world;
    }
        
    public void spawnBird(Status status, boolean isEnergyWorld,int x,int y )
    {
        world.addObject(new Bird(this.world,status,0,isEnergyWorld),x,y);
    }
    
    public void spawnFruits()
    {
        world.addObject(new Apple(world),Greenfoot.getRandomNumber(world.getWidth()/2)+200,world.getHeight()/2);
        world.addObject(new Banana(world),Greenfoot.getRandomNumber(world.getWidth()/2)+200,world.getHeight()/2);
        world.addObject(new Papaya(world),Greenfoot.getRandomNumber(world.getWidth()/2)+200,world.getHeight()/2);
    }
}