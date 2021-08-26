import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Enemy extends Actor
{
    //This class defines the characteristics that all enemies share
    private static int numEnemies = 0;
    protected Hero target;
    protected int vertical, horizontal, damage;//The vertical/horizontal distance from the hero, and the damage, are inherited by the subclass
    
    protected double maxHP;
    protected double currentHP;
    protected boolean isDead;
    private int counter, deathCounter, tauntCounter; //counter for walking animations, and death animation, different counters for different animations
    
    //Instance Object for the healthbar
    protected Healthbar health;
    
    protected TextBox text;
    protected deathRing d;
    protected Laser l;
    //Animation arrays are protected so they can be accessed through the subclass
    protected String[] moveUp;
    protected String[] moveLeft;
    protected String[] moveDown;
    protected String[] moveRight;
    protected String[] deathAnimation;
    private String[] tempArray;
    
    protected GreenfootImage enemyImage;
    
    protected boolean cloned;
    protected boolean isBoss;
    public Enemy(double maxHP, Hero a, int damage)//Sets the stats such as maxHP, damage, and target (the hero)
    {
        numEnemies ++;
        counter = 0;
        deathCounter = 0;
        tauntCounter = 0;
        this.damage = (int) (damage * StartWorld.difficultyAdj);
        this.maxHP = maxHP * StartWorld.difficultyAdj;
        currentHP = this.maxHP;
        target = a;//the player is accessed through a parameter, target is a hero object
        health = new Healthbar(100, 5, maxHP, this);
        text = new TextBox(800, 100);
        cloned = false;
        isBoss = false;
    }
    
    public void act() 
    {
        
    }
    //Getter and setter methods for the number of enemies
    public static int getNumEnemies()
    {
        return numEnemies;
    }
    
    public static void setNumEnemies(int a)
    {
        numEnemies = a;
    }
    //Code to play a death animation
    public void die()
    {
        //currentHP = 0;
        enemyImage = new GreenfootImage(deathAnimation[deathCounter/7]);
        deathCounter++;
        if(cloned) enemyImage.setTransparency(150);
        
        setImage(enemyImage);
            
        if(deathCounter == 1)
        {

            text.clear();
            getWorld().removeObject(health);//Removes the healthbar to avoid null pointer exception
            
            if(d != null) getWorld().removeObject(d); 
            if(l != null) getWorld().removeObject(l); 
            
        }
        if(deathCounter >= deathAnimation.length*7 - 1)
        {
            numEnemies--;
            deathCounter = 0;
            if(isBoss) MyWorld.stopMusic();
            //Does a funcion if it is the right kind of enemy, uses maxHP to distinguish between types of enemies
            if(maxHP == 2000 || maxHP == 2400 || maxHP == 1600)
            {
                //getWorld().addObject(new Box(800, 600, new Color(255, 0, 0), 5, 300), 400, 300);
                Greenfoot.setWorld(new SceneWorld( (MyWorld) getWorld() ));
            }
            else if(maxHP <= 60) Greenfoot.setWorld(new WinWorld());
            getWorld().removeObject(this);
        }
    }
    
    public String getDirection()//Returns the direction the target is relative to the enemy
    //Ex. if direction is right, the HERO is to the RIGHT of the ENEMY
    {
        vertical = target.getY() - this.getY();
        horizontal = target.getX() - this.getX();
        if(Math.abs(vertical) > Math.abs(horizontal))
        {
           if(vertical < 0) return "up";
           if(vertical > 0) return "down";
        }
        else
        {
            if(horizontal > 0) return "right";
            if(horizontal < 0) return "left";
        }
        return "void";
    }
    //Adds the healthbar and textbox, as well as a "deathring" for the final boss
    protected void addedToWorld(World MyWorld)
    {
        if(maxHP > 60) getWorld().addObject(health, getX(), getY() - 30);
        getWorld().addObject(text, 400, 550);
        if(maxHP == 2000 || maxHP == 2400 || maxHP == 1600)
        {
            d = new deathRing((wizardBoss) this);
            getWorld().addObject(d, getX(), getY() + 15);
            
        }
    }
    //Checks if dead and also checks if target is dead
    public void checkIfDead()
    {
        isDead = currentHP < 1;
        if(isDead)
        {
            die();
        }
        if(currentHP > maxHP) currentHP = maxHP;//Prevent current HP from going above max
        
        if(target.getHP() < 1)
        {
            MyWorld.stopMusic();
            text.clear();
        }
    }
    
    //moves to the right (Animation)
    public void moveRight(int speed)
    {
        if(counter > 31) counter = 0;
        enemyImage = new GreenfootImage(moveRight[counter/4]);
        counter++;
        setImage(enemyImage);
        if(!detectObstacles()) setLocation(getX() + speed, getY());
    }
    //moves upwards (Animation)
    public void moveUp(int speed)
    {
        if(counter > 31) counter = 0;
        enemyImage = new GreenfootImage(moveUp[counter/4]);
        counter++;
        setImage(enemyImage);
        if(!detectObstacles()) setLocation(getX(), getY() - speed);
    }
    //moves to the left (Animation)
    public void moveLeft(int speed)
    {
        if(counter > 31) counter = 0;
        enemyImage = new GreenfootImage(moveLeft[counter/4]);
        counter++;
        setImage(enemyImage);
        if(!detectObstacles()) setLocation(getX() - speed, getY());
    }
    //moves downwards (Animation)
    public void moveDown(int speed)
    {
        if(counter > 31) counter = 0;
        enemyImage = new GreenfootImage(moveDown[counter/4]);
        counter++;
        setImage(enemyImage);
        if(!detectObstacles()) setLocation(getX(), getY() + speed);
    }
    //This is mostly a failsafe as I intended to put obstacles but decided against it, since it would open hiding exploits
    //Returns if a box object is in front of the enemy
    public boolean detectObstacles()
    {
        String direction = getDirection();
        if(direction.equals("right"))
        {
            if(getOneObjectAtOffset(15, 0, Box.class) != null)
            {
                if(getOneObjectAtOffset(15, 0, Box.class).getImage().getTransparency() > 204)return true;
            }
            
        }
        if(direction.equals("left"))
        {
            if(getOneObjectAtOffset(-15, 0, Box.class) != null)
            {
                if(getOneObjectAtOffset(-15, 0, Box.class).getImage().getTransparency() > 204)return true;
            }
        }
        if(direction.equals("up"))
        {
            if(getOneObjectAtOffset(0, -26, Box.class) != null)
            {
                if(getOneObjectAtOffset(0, -26, Box.class).getImage().getTransparency() > 204)return true;
            }
        }
        if(direction.equals("down"))
        {
            if(getOneObjectAtOffset(0, 30, Box.class) != null)
            {
                if(getOneObjectAtOffset(0, 30, Box.class).getImage().getTransparency() > 204)return true;
            }
        }
        return false;
    }
    //Sets an array with animation images
    public String[] loadArray(String prefix, String suffix, int startNum, int endNum)
    {
        tempArray = new String[endNum - startNum + 1];

        for(int i = startNum; i <= endNum; i++)
        {
            
            tempArray[i - startNum] = prefix + i + suffix;
        }
        return tempArray;
    }
    //Says the taunting lines at 500 act intervals
    //The bosses "taunt" the hero
    public void taunt(String[] lines)
    {
        tauntCounter ++;
        if(tauntCounter % 500 == 0)
        {
            text.tempText(lines[tauntCounter/500 - 1], 20); //Prints the appropriate line in the array
        }
        if(tauntCounter == lines.length*500) tauntCounter = 0;
    }
    //Getters and setter for (max)HP
    public void changeHP(double change)
    {
        currentHP += change;
    }
    
    public double getHP()
    {
        return currentHP;
    }
    
    public double getMaxHP()
    {
        return maxHP;
    }
}
