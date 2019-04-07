import greenfoot.*;  // (getWorld(), Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Bird here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bird extends Actor
{
    /**
     * Act - do whatever the Bird wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    GreenfootImage defaultImg;

    private int ANIM_DELAY = 5;
    private int animTime=0;
    private int animNumber=0;
    private int ROTATION_MAX = 30;
    public static int wormTaken = 0;
    private int ENERGY_DELAY=20;
    private int energyWait=0;
    private int Energy;
    public int health;
    int heartX=30;
    private boolean isChangeImg=false;
    private boolean isFacingRight;
    public boolean onDefeat=false;
    private boolean lastLife=true;
    private boolean isSpaceDown=false;
    private Status status;
    Spawner spawner;
    World world;
    private boolean isOnEnergyWorld;
    private float shownHealth;
    List<EnergyBar> energybar;
    public Bird()
    {
    }

    public Bird(int id)
    {
        defaultImg = new GreenfootImage("Bird-"+id+".png");
        setImage(defaultImg);
        getImage().mirrorHorizontally();
        isFacingRight=true;
        spawner = new Spawner(getWorld());
    }

    public Bird(World world,Status status,int id,boolean isOnEnergyWorld)
    {
        this(id);
        this.status = status;
        this.world = world;
        this.isOnEnergyWorld = false;
    }

    private void changeImg(int imgNumber)
    {
        img = new GreenfootImage("Bird-"+imgNumber+".png");
        setImage(img);
        if(isFacingRight)
            getImage().mirrorHorizontally();
        img = null;
    }

    public void act() 
    {
        if(getWorld() instanceof BirdWorld)
            energyCheck();
        gameOver();
        if(getWorld()==null) return;
        animTimer();
        animate();
        movement();
        surfaceCollider();
        onFruitsTouch();
        onWallTouch();
        if(getWorld()==null) return;
        onCatTouch();
        if(getWorld()==null) return;
        onWormTouch();
        onSnakeTouch();
        if(getWorld()==null) return;
        checkWormTaken();
        if(getWorld()==null) return;
        checkWorld();
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

    private void animate()
    {
        if(isChangeImg)
        {
            if(animNumber==3)
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

    private void movement()
    {
        if(Greenfoot.isKeyDown("W"))
        {
            setLocation(getX(),getY()-5);
            //System.out.println(getRotation());
            if(isFacingRight)
                setRotation(-ROTATION_MAX);
            else
                setRotation(ROTATION_MAX);
        }
        if(Greenfoot.isKeyDown("S"))
        {
            setLocation(getX(),getY()+5);
            if(isFacingRight)
                setRotation(ROTATION_MAX);
            else
                setRotation(-ROTATION_MAX);
        }
        if(Greenfoot.isKeyDown("A"))
        {
            setLocation(getX()-5,getY());
            if(isFacingRight)
                isFacingRight=false;
        }
        if(Greenfoot.isKeyDown("D"))
        {
            setLocation(getX()+5,getY());
            if(!isFacingRight)
                isFacingRight=true;
        }
        if(!Greenfoot.isKeyDown("W") && !Greenfoot.isKeyDown("S"))
        {
            setRotation(0);
        }

    }

    private void surfaceCollider()
    {
        while(getOneIntersectingObject(Block.class)!=null)
        {
            setLocation(getX(),getY()-1);
        }
    }

    private void onWallTouch()
    {
        // get instances from BirdgetWorld() Class
        //hearts = getWorld().getObjects(BirdHearts.class);
        if(!onDefeat)
        {
            if(status.getLives()==0)
            {
                onDefeat=true;
                getWorld().removeObject(this);
                return;
            }
            if(isTouching(Wall.class))
            {
                status.decreaseLives(1);
                if(status.getLives()<0)
                {
                    status.setLives(0);
                }
                shownHealth = (float)status.getLives()/2;
                getWorld().showText(": "+shownHealth, 80, 35);
                getWorld().removeObject(this);

            }
        }
    }

    private void onCatTouch()
    {
        if(!onDefeat)
        {
            if(status.getLives()==0)
            {
                onDefeat=true;
                getWorld().removeObject(this);
                return;
            }    
            if(isTouching(Cat.class))
            {
                status.decreaseLives(2);
                if(status.getLives()<0)
                {
                    status.setLives(0);
                }
                shownHealth = (float)status.getLives()/2;
                getWorld().showText(": "+shownHealth, 80, 35);
                getWorld().removeObject(this);
            }
        }
    }

    private void onSnakeTouch()
    {
        if(!onDefeat)
        {
            if(status.getLives()==0)
            {
                onDefeat=true;
                getWorld().removeObject(this);
                return;
            }    
            if(isTouching(Snake.class))
            {
                status.decreaseLives(2);
                if(status.getLives()<0)
                {
                    status.setLives(0);
                }
                shownHealth = (float)status.getLives()/2;
                getWorld().showText(": "+shownHealth, 80, 35);
                getWorld().removeObject(this);
            }
        }
    }

    private void onWormTouch()
    {
        Actor worm = getOneIntersectingObject(Worm.class);
        if(worm!=null)
        {
            getWorld().removeObject(worm);
            if(getX()<getWorld().getWidth()/2)
                getWorldOfType(BirdWorld.class).leftWorm--;
            else
                getWorldOfType(BirdWorld.class).rightWorm--;
            status.increaseWormTaken();
            getWorld().showText("Worms Taken : "+status.getWormTaken(),getWorld().getWidth()/2,25);
        }
    }

    private void onFruitsTouch()
    {
        Actor apple = getOneIntersectingObject(Apple.class);
        Actor banana = getOneIntersectingObject(Banana.class);
        Actor papaya = getOneIntersectingObject(Papaya.class);
        energybar = getWorld().getObjects(EnergyBar.class);
        Collections.reverse(energybar);
        energybar.remove(energybar.size()-1);
        if(apple!=null)
        {
            status.increaseEnergy(5);
            for(int i=0;i<5;i++)
            {
                if(energybar.size()>0)
                {
                    getWorld().removeObject(energybar.get(energybar.size()-1));
                    energybar.remove(energybar.size()-1);
                }
            }
            getWorld().removeObject(apple);
        }
        if(banana!=null)
        {
            status.increaseEnergy(1);
            for(int i=0;i<1;i++)
            {
                if(energybar.size()>0)
                {
                    getWorld().removeObject(energybar.get(energybar.size()-1));
                    energybar.remove(energybar.size()-1);
                }
            }
            getWorld().removeObject(banana);
        }
        if(papaya!=null)
        {
            status.increaseEnergy(10);
            for(int i=0;i<10;i++)
            {
                if(energybar.size()>0)
                {
                    getWorld().removeObject(energybar.get(energybar.size()-1));
                    energybar.remove(energybar.size()-1);
                }
            }
            getWorld().removeObject(papaya);
        }
    }

    private void checkWormTaken()
    {
        if(status.getWormTaken()>=9)
        {
            if(getWorld().getObjects(Win.class).size()==0)
                getWorld().addObject(new Win("Kau Menang!"),getWorldXMid(),getWorldYMid());
            getWorld().removeObject(this);
            return;
        }
    }

    private void energyCheck()
    {
        Energy = status.getEnergy();
        if(Energy==0)
        {
            if(getWorld().getObjects(GameOver.class).size()==0)
                getWorld().addObject(new GameOver("Kau Kalah!"),getWorldXMid(),getWorldYMid());
            getWorld().removeObject(this);
            Greenfoot.stop();
            return;
        }
        energyTimer();
    }

    private void energyTimer()
    {
        if(energyWait==ENERGY_DELAY)
        {
            status.decreaseEnergy();
            getWorld().addObject(new EnergyBar(1,Color.RED),energyBarX(getWorld().getWidth()),30);
            energyWait=0;
            return;
        }
        energyWait++;
    }

    private int energyBarX(int x)
    {
        if(getWorld() instanceof BirdWorld)
            return (x-120)-(100-(2*status.getEnergy()));
        else
            return (x-100)-(100-(2*status.getEnergy()));
    }

    private int getWorldXMid()
    {
        return getWorld().getWidth()/2;
    }

    private int getWorldYMid()
    {
        return getWorld().getHeight()/2;
    }

    private void checkWorld()
    {
        if(isTouching(Branch.class))
        {
            changeWorld();
        }
    }

    private void changeWorld()
    {
        if(getWorld() instanceof BirdWorld)
        {
            ((BirdWorld)getWorld()).music.stop();
            Greenfoot.setWorld(new EnergyWorld(status));
        }
        else
        {
            ((EnergyWorld)getWorld()).music.stop();
            Greenfoot.setWorld(new BirdWorld(status,false));
        }
    }

    public void gameOver()
    {

        if(status.getLives()==0)
            getWorld().addObject(new GameOver("Kau Kalah!"),getWorld().getWidth()/2,getWorld().getHeight()/2);

    }
}