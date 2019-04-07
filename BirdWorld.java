import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BirdWorld extends World
{

    private int CLOUD_SPAWN_DELAY = 60;
    private int WORM_SPAWN_DELAY = 60;
    private int wormSpawnTimer;
    private int cloudSpawnTimer;
    private int blockX,heartX=30,energyBarX;
    public static int leftWorm;
    public static int rightWorm;
    private boolean isFirstSpawn=true;
    private BirdHearts heart;
    private boolean onStart=true;
    GreenfootImage landTiles;
    GreenfootSound music;
    Bird bird;
    Status status;
    Status temp;
    Spawner spawner;
    
 
    public BirdWorld()
    {
        super(800, 600, 1);
        GreenfootImage background = new GreenfootImage("MountainBackground.png");
        GreenfootImage landTiles = new GreenfootImage("block.png");
        music = new GreenfootSound("Phyrnna - Elfin.mp3");
        music.play();
        spawner = new Spawner((World)this);
        heart = new BirdHearts();
    }
    public BirdWorld(Status status, boolean onStart)
    {
        this();
        this.status=status;
        this.onStart=onStart;
        if(onStart)
        spawner.spawnBird(status,false,100,100);
        else
        spawner.spawnBird(status, false, getWidth()-100, 2*getHeight()/3);
        createEnergyBar();
        createTiles();
        createWall();
        createBranch();
        addCats();
        setPaintOrder(Win.class,GameOver.class,EnergyBar.class,Bird.class,Cloud.class,Cat.class,Worm.class,Block.class,BirdHearts.class,Wall.class);
        showText("Worms Taken : "+status.getWormTaken(),getWidth()/2,25);
        leftWorm=0;
        rightWorm=0;
        wormSpawnTimer=0;
        cloudSpawnTimer=0;
        blockX=0;
        for(int i=0;i<100-status.getEnergy();i++)
        updateEnergyBar();
        createHearts();
        
        
        
    }

    public void act()
    {
        //cloudSpawn();
        birdRespawn();
        checkWorm();
        wormSpawn();
    }
    
    public Status getStatus(){
        return status;
    }
    
    private void createBranch()
    {
        addObject(new Branch(),700,60);
    }
    
    private void createEnergyBar()
    {
        if(getObjects(EnergyBar.class).size()==0)
        {
            addObject(new EnergyBar(),getWidth()-120,30);
            this.energyBarX=energyBarX(getWidth());
        }
    }
    
    private void createHearts()
    {
        
        
        for(int i=1;i<=2;i++)
        {
            if(i%2!=0)
            {
                addObject(new BirdHearts(0),heartX,35);
                heartX+=20;
            }
            else
            {
                addObject(new BirdHearts(1),heartX,35);
                heartX+=30;
            }
        }
        showText(": "+(float)status.getLives()/2, 80, 35);
    } 

    private void addCats()
    {
        addObject(new Cat(),3*getWidth()/4,getHeight()-(76));
        addObject(new Cat(),getWidth()/4,getHeight()-76);
    }
    
    private void createWall()
    {
        Wall wall = new Wall();
        addObject(new Wall(),0+wall.getImage().getWidth()/2,getHeight()-wall.getImage().getHeight()/2);
        addObject(new Wall(),getWidth()-wall.getImage().getWidth()/2,getHeight()-wall.getImage().getHeight()/2);
        addObject(new Wall(),getWidth()/2+wall.getImage().getWidth()/2,getHeight()-wall.getImage().getHeight()/2);
        addObject(new Wall(),getWidth()/4+wall.getImage().getWidth()/2,0+wall.getImage().getHeight()/2);
        addObject(new Wall(),3*getWidth()/4+wall.getImage().getWidth()/2,0+wall.getImage().getHeight()/2);
    }

    private void createTiles()
    {
        for(int i =0 ; i<26 ; i++)
        {
            if(i>0)
                blockX+=32;
            addObject(new Block(),blockX,getHeight()-15);
        }
    }

    private void cloudSpawn()
    {
        if(cloudSpawnTimer==CLOUD_SPAWN_DELAY)
        {
            addObject(new Cloud(),getWidth(),Greenfoot.getRandomNumber(getHeight()/4));
            cloudSpawnTimer=0;
        }
        cloudSpawnTimer++;

    }

    public void birdRespawn()
    {
        if(getObjects(Bird.class).size()==0)
            spawner.spawnBird(status,false,100,100);
    }

    private void leftWormSpawn()
    {
        if(leftWorm<2)
        {
            addObject(new Worm(),Greenfoot.getRandomNumber(200)+100,getHeight()-32);
            leftWorm++;
        }
    }

    private void rightWormSpawn()
    {
        if(rightWorm<2)
        {
            addObject(new Worm(),Greenfoot.getRandomNumber(200)+500,getHeight()-32);
            rightWorm++;
        }
    }
    
    private void checkWorm()
    {
        if(getObjects(Worm.class).size()==0)
        {
            leftWorm=0;
            rightWorm=0;
        }
    }
    
    private void wormSpawn()
    {
        if(wormSpawnTimer== WORM_SPAWN_DELAY)
        {
            leftWormSpawn();
            rightWormSpawn();
            wormSpawnTimer=0;
        }
        wormSpawnTimer++;
    }
    
    private void updateEnergyBar()
    {
        addObject(new EnergyBar(1,Color.RED),this.energyBarX,30);
        energyBarX+=2;
    }
    
    private int energyBarX(int x)
    {
        return (x-120)-(100-(2*status.getEnergy()));
    }
}
