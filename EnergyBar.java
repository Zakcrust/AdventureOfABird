import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnergyBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnergyBar extends Actor
{
    GreenfootImage img,defaultImg;
    /**
     * Act - do whatever the EnergyBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public EnergyBar(){
        defaultImg = new GreenfootImage(200,20);
        defaultImg.setColor(Color.GREEN);
        defaultImg.fill();
        setImage(defaultImg);
    }
    
    public EnergyBar(int x,Color color)
    {
        img = new GreenfootImage(x*2,20);
        img.setColor(color);
        img.fill();
        setImage(img);
        img=null;
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
