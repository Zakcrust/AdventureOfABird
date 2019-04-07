import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cat extends Actor
{
    /**
     * Act - do whatever the Cat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    private int speed = 4;
    private boolean isFacingRight=true;
    private boolean isWait=false;
    private int waitTime = 0;
    private int ANIM_DELAY = 5;
    private int animTime=0;
    private int animNumber=0;
    private boolean isChangeImg=false;
    public Cat()
    {
        
    }
    
    public void act() 
    {
        movement();
        CatBehaviour();
        animTimer();
        animate();
    }
    
    private void gravity()
    {
        setLocation(getX(),getY()+10);
    }
    
    private void movement()
    {
        setLocation(getX()+speed,getY());
    }
    
    private void CatBehaviour()
    {
        if(isTouching(Wall.class))
        {
            if(isFacingRight)
                isFacingRight=false;
            else
                isFacingRight=true;
            speed*=-1;
            getImage().mirrorHorizontally();
        }
    }
    
    private void animate()
    {
        if(isChangeImg)
        {
            if(animNumber==7)
            {
                animNumber=0;
                changeImg(animNumber);
                return;
            }
            animNumber++;
            changeImg(animNumber);
            isChangeImg=false;
        }
        
    }
    
    private void animTimer()
    {
        if(animTime==ANIM_DELAY)
        {
            isChangeImg=true;
            animTime=0;
        }
        animTime++;
    }
    
    private void changeImg(int imgNumber)
    {
        img = new GreenfootImage("Run ("+(imgNumber+1)+").png");
        setImage(img);
        if(!isFacingRight)
        getImage().mirrorHorizontally();
        img = null;
    }
    
}
