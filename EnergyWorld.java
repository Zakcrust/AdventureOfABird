import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class EnergyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnergyWorld extends World
{

    /**
     * Constructor for objects of class EnergyWorld.
     * 
     */
    private int blockX=16,heartX=30;
    private int FRUIT_SPAWN_DELAY = 120;
    private int fruitTimer=0;
    private int energyBarX;
    GreenfootSound music;
    Status status;
    Spawner spawner;
    List<EnergyBar> energyBar;
    public EnergyWorld()
    {
        super(600, 600, 1);
        createTiles();
        prepare();
        spawner = new Spawner((World)this);
        music = new GreenfootSound("Phyrnna - A Stroll Through Nostalgia.mp3");
        music.play();
        setPaintOrder(GameOver.class,Snake.class,Fruits.class,Block.class,Bird.class,EnergyBar.class,Branch.class,Tree.class);
    }

    public EnergyWorld(Status status)
    {
        this();
        this.status = status;
        spawner.spawnBird(this.status,true,3*getWidth()/4,getHeight()/3);
        createEnergyBar();
        for(int i=0;i<100-status.getEnergy();i++)
        updateEnergyBar();
        createHearts();
        showText("Worm Taken : "+status.getWormTaken(),getWidth()/2,30);
        energyBar = getObjects(EnergyBar.class);
        //GreenfootImage background = new GreenfootImage("ForestBackground.png");

    }

       
    public Status getStatus(){
        return status;
    }

    private void createTiles()
    {
        for(int i =0 ; i<20 ; i++)
        {
            if(i>0)
                blockX+=32;
            addObject(new Block(),blockX,getHeight()-15);
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

    private void createEnergyBar()
    {
        if(getObjects(EnergyBar.class).size()==0)
        {
            addObject(new EnergyBar(),getWidth()-120,30);
            this.energyBarX=energyBarX(getWidth());
        }
    }

    private void updateEnergyBar()
    {
        //this.energyBarX=energyBarX(getWidth());
        addObject(new EnergyBar(1,Color.RED),this.energyBarX,30);
        energyBarX+=2;
    }
    
    private int energyBarX(int x)
    {
        return (x-120)-(100-(2*status.getEnergy()));
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Tree tree = new Tree();
        addObject(tree,getWidth()/2,getHeight()/2+10);
        addObject(new Snake(),getWidth()/2,getHeight()-20);
        addObject(new Branch(),502,50);
    }
    
    public void act()
    {
        fruitSpawnTimer();
        birdRespawn();
    }

    public void birdRespawn()
    {
        if(getObjects(Bird.class).size()==0)
            spawner.spawnBird(this.status,true,3*getWidth()/4,getHeight()/3);
    }
    
    private void fruitSpawnTimer()
    {
        if(fruitTimer==FRUIT_SPAWN_DELAY)
        {
            spawner.spawnFruits();
            fruitTimer=0;
        }
        fruitTimer++;
    }
    
    
}
