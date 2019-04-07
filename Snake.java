import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends Actor
{
   GreenfootImage img;
    private int speed = 5;
    private boolean isFacingRight=true;
    private boolean isWait=false;
    private int waitTime = 0;
    private int ANIM_DELAY = 4;
    private int animTime=0;
    private int animNumber=32;
    private boolean isChangeImg=false;
    public Snake()
    {
        
    }
    
    public void act() 
    {
        movement();
        SnakeBehaviour();
        fruitsCollider();
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
    
    private void SnakeBehaviour()
    {
        if(isAtEdge())
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
            if(animNumber==1)
            {
                changeImg(animNumber);
                animNumber=32;
                return;
            }
            animNumber--;
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
        if(imgNumber>=10)
        {
        img = new GreenfootImage("image_part_0"+(imgNumber)+".png");
        }
        else
        {
            img = new GreenfootImage("image_part_00"+(imgNumber)+".png");
        }
        setImage(img);
        if(!isFacingRight)
        getImage().mirrorHorizontally();
        img = null;
    }
    
    private void fruitsCollider()
    {
        if(isTouching(Fruits.class))
        {
            removeTouching(Fruits.class);
        }
    }
    
}
