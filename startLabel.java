import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class startLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class startLabel extends Actor
{
    GreenfootImage img;
    private Status status = new Status();
    private boolean isSpaceDown;
    public startLabel(String text, int size)
    {
        img = new GreenfootImage(text,size,Color.BLUE,null);
        setImage(img);
        img = null;
    }
    public void act()
    {
        onStart();
    }
    
    private void onStart()
    {
        if(Greenfoot.isKeyDown("Space"))
        {
            Greenfoot.setWorld(new BirdWorld(status,true));
        }
    }
}
