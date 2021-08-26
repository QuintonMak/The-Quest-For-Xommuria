import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Healthbar extends Actor
{
    /**
     * This class was based off of Mr.Cohens healthbar class from the bugs simulation
     */
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean moving;
    
    private double currentHP;
    private double maxHP;
    private double percentHP;
    
    private Color green;
    private Color red;
    
    private Hero player;
    private Enemy e;
    public void act() 
    {
        update();
        setValues();
        
        if(moving)
        {
            x = e.getX() + 2;
            if(e.getImage() != null) y = e.getY() - (e.getImage().getHeight())/2 - 5;//Set coordinates above the enemy for the healthbar to follow
            setLocation(x, y);
            
        }
        
    } 
    //Separate constructors for Hero's healthbar and enemies healthbar
    public Healthbar(int width, int height, double maxHP, Hero player)
    {
        this.player = player;
        this.width = width;
        this.height = height;
        this.currentHP = maxHP;//Takes HP values from the player
        this.maxHP = maxHP;
        moving = false;
        red = new Color(255, 0, 0);
        green = new Color(0, 255, 0);
    }
    
    public Healthbar(int width, int height, double maxHP, Enemy enemy)
    {
        this.width = width;
        this.height = height;
        this.currentHP = maxHP;//Takes HP values from the enemy
        this.maxHP = maxHP;
        e = enemy;
        moving = true;
        red = new Color(255, 0, 0);
        green = new Color(0, 255, 0);
    }
    
    
    
    public void update()
    {
        //Updates the players/enemies HP in real time
        if(player != null) 
        {
            maxHP = player.getMaxHP();
            currentHP = player.getHP();
        }
        else if(e != null)
        {
            maxHP = e.getMaxHP();
            currentHP = e.getHP();
        }
        GreenfootImage bar = new GreenfootImage(width, height);//Construct a greenfoot image with dimensions (width, height)
        percentHP = currentHP/maxHP; //Percent of bar to fill green
        //Draw green and red segments at appropriate locations
        bar.setColor(green);
        bar.fillRect(0, 0, (int) (width*percentHP), height);
        bar.setColor(red);
        bar.fillRect((int) (width*percentHP), 0, width - (int) (width*percentHP), height);
        this.setImage(bar);
    }
    
    public void changeHP(double damage)
    {
        currentHP += damage;
    }
    
    public void setValues()//Prevents currentHP from going above the max and below zero, so the bar doesn't draw past the rectangle's width
    {
        if(currentHP > maxHP) currentHP = maxHP;
        if(currentHP < 0) currentHP = 0;
        //return currentHP == 0;
    }
    
}
