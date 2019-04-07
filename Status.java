import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Status here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Status extends Actor
{
    /**
     * Act - do whatever the Status wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int health;
    private int Energy;
    private int wormTaken;
    
    public Status()
    {
        health=6;
        Energy=100;
        wormTaken=0;
    }
    
    public void decreaseLives(int damage)
    {
        health-= damage;
    }
    
    public void setLives(int health)
    {
        this.health=health;
    }
    
    public int getLives()
    {
        return health;
    }
    
    
    public void increaseEnergy(int value)
    {
        Energy+= value;
        if(Energy>100) Energy=100;
    }
    
    public void decreaseEnergy()
    {
        Energy--;
    }
    
    public int getEnergy()
    {
        return Energy;
    }
    
    public void increaseWormTaken()
    {
        wormTaken++;
    }
    
    public int getWormTaken()
    {
        return wormTaken;
    }
}
