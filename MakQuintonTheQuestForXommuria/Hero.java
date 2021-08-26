import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Hero extends Actor
{
    private static int roomNumber = 0;//Represents the stage of the game that the player is on
    private static int checkPoint;//Represents which room the player will respawn at if they happen to die
    
    //Instance arrays containing the frames of the sprite animation
    private String[] walkUp= {"heroSprite0.png", "heroSprite1.png", "heroSprite2.png", "heroSprite3.png", "heroSprite4.png", "heroSprite5.png", "heroSprite6.png", "heroSprite7.png", "heroSprite8.png"}; 
    private String[] walkRight= {"heroSprite27.png", "heroSprite28.png", "heroSprite29.png", "heroSprite30.png", "heroSprite31.png", "heroSprite32.png", "heroSprite33.png", "heroSprite34.png", "heroSprite35.png"};
    private String[] walkLeft= {"heroSprite9.png", "heroSprite10.png", "heroSprite11.png", "heroSprite12.png", "heroSprite13.png", "heroSprite14.png", "heroSprite15.png", "heroSprite16.png", "heroSprite17.png"};
    private String[] walkDown= {"heroSprite18.png", "heroSprite19.png", "heroSprite20.png", "heroSprite21.png", "heroSprite22.png", "heroSprite23.png", "heroSprite24.png", "heroSprite25.png", "heroSprite26.png"};
    private String[] castUp = {"heroSprite36.png", "heroSprite37.png", "heroSprite38.png", "heroSprite39.png", "heroSprite40.png", "heroSprite41.png"};
    private String[] castLeft = {"heroSprite42.png", "heroSprite43.png", "heroSprite44.png", "heroSprite45.png", "heroSprite46.png", "heroSprite47.png"};
    private String[] castDown =  {"heroSprite48.png", "heroSprite49.png", "heroSprite50.png", "heroSprite51.png", "heroSprite52.png", "heroSprite53.png"};
    private String[] castRight =  {"heroSprite54.png", "heroSprite55.png", "heroSprite56.png", "heroSprite57.png", "heroSprite58.png", "heroSprite59.png"};
    private String[] deathAnimation;
    //Instance image object for the hero actor
    private GreenfootImage heroImage;
    
    //Instance Object for the healthbar
    Healthbar health;
    Manabar mana;
    //Instance Object for the access of projectile data
    private Projectile p;
    private MouseInfo m;
    //Instance Variables
    private int counter = 0;
    private int castCounter = 0;
    private int deathCounter;
    private double maxHP;
    private double currentHP;
    private int damage;
    private double maxMana;
    private double currentMana;
    //Booleans to show if the player is attacking in an direction
    private boolean boolUp = false;
    private boolean boolLeft = false;
    private boolean boolDown = false;
    private boolean boolRight = false;
    private boolean isDead;
    private String direction;//Direction the player is facing
    
    private Color black = new Color(0, 0, 0);
    private Color white = new Color(255, 255, 255);
    public void act() 
    {
        //Does an attack animation if needed
        if(boolUp) castUp();
        if(boolLeft) castLeft();
        if(boolDown) castDown();
        if(boolRight) castRight();
        
        setValues();
        
        //Goes to the game over world if the player dies
        if(currentHP < 1)
        {
            
            die();
            
        }
    }
    
    public Hero()
    {
        heroImage = new GreenfootImage(walkRight[0]);
        setImage(heroImage);
        direction = "right";
        maxHP = 250;
        currentHP = maxHP;
        maxMana = 200;
        damage = -15;
        currentMana = maxMana;
        health = new Healthbar(200, 10, maxHP, this);
        mana = new Manabar(200, 10, maxMana, this);
        deathAnimation = loadArray("heroDeath", ".png", 0, 4);
        deathCounter = 0;
    }
    //Getter and setter methods for the room number and checkpoint number
    public static void setRoomNumber(int a)
    {
        roomNumber = a;
    }
    
    public static void setCheckPoint(int a)
    {
        checkPoint = a;
    }
    
    public static int getRoomNumber()
    {
        return roomNumber;
    }
    
    public static int getCheckPoint()
    {
        return checkPoint;
    }
    
    //Method to input file names into the array for animation
    public String[] loadArray(String prefix, String suffix, int startNum, int endNum)
    {
        String[] tempArray = new String[endNum - startNum + 1];

        for(int i = startNum; i <= endNum; i++)
        {
            
            tempArray[i - startNum] = prefix + i + suffix;
        }
        return tempArray;
    }
    //If the player has died, a death animation plays
    public void die()
    {
        //currentHP = 0;
        heroImage = new GreenfootImage(deathAnimation[deathCounter/7]);
        deathCounter++;
        setImage(heroImage);
            
        if(deathCounter >= 34)
        {
            deathCounter = 0;
            Greenfoot.setWorld(new LoseWorld( (MyWorld)getWorld(), this ));
        }
    }
    
    public void zeroCounter()
    {
        counter = 0;
    }
    //Walks to the right (Animation)
    public void walkRight(int speed)
    {
        if(counter > 62) counter = 0;
        heroImage = new GreenfootImage(walkRight[counter/7]);
        counter++;
        setImage(heroImage);
        //Lets the player proceed to the next room by moving to the right side of the map
        if(Enemy.getNumEnemies() == 0)
        {
            if(getX() > 784)
            {
                if(getWorld().getClass().equals(MyWorld.class))
                {
                    ((MyWorld)getWorld()).nextRoom();
                    
                }
            }
            else if(getX() < 785 && getOneObjectAtOffset(15, 22, Box.class) == null && getOneObjectAtOffset(15, -22, Box.class) == null) setLocation(getX() + speed, getY());
            else if(getOneIntersectingObject(Box.class) != null)
            {
                if (getOneIntersectingObject(Box.class).getImage().getTransparency() < 235) setLocation(getX() + speed, getY());
            }
            
        }
        //Does not let the player walk through walls or obstacles (similar for all 4 directions)
        else if(getX() < 785 && getOneObjectAtOffset(15, 22, Box.class) == null && getOneObjectAtOffset(15, -22, Box.class) == null) setLocation(getX() + speed, getY());
        else if (getOneIntersectingObject(Box.class) != null)
        {
            if(getOneIntersectingObject(Box.class).getImage().getTransparency() < 235) setLocation(getX() + speed, getY());
        }
                
        direction = "right";
    }
    //Walks upwards (Animation)
    public void walkUp(int speed)
    {
        if(counter > 62) counter = 0;
        heroImage = new GreenfootImage(walkUp[counter/7]);
        counter++;
        setImage(heroImage);
        if(getY() < 580 && getOneObjectAtOffset(13, -24, Box.class) == null && getOneObjectAtOffset(-13, -24, Box.class) == null) setLocation(getX(), getY() - speed);
        else if (getOneIntersectingObject(Box.class) != null)
        {
            if(getOneIntersectingObject(Box.class).getImage().getTransparency() < 235) setLocation(getX(), getY() - speed);
        }        
        direction = "up";
    }
    //Walks to the left (Animation)
    public void walkLeft(int speed)
    {
        if(counter > 62) counter = 0;
        heroImage = new GreenfootImage(walkLeft[counter/7]);
        counter++;
        setImage(heroImage);
        
        if(getX() > 18 && getOneObjectAtOffset(-15, 22, Box.class) == null && getOneObjectAtOffset(-15, -22, Box.class) == null) setLocation(getX() - speed, getY());
        else if (getOneIntersectingObject(Box.class) != null)
        {
            if(getOneIntersectingObject(Box.class).getImage().getTransparency() < 235) setLocation(getX() - speed, getY());
        }
            direction = "left";
    }
    //Walks downwards (Animation)
    public void walkDown(int speed)
    {
        if(counter > 62) counter = 0;
        heroImage = new GreenfootImage(walkDown[counter/7]);
        counter++;
        setImage(heroImage);
        if(getY() < 580 && getOneObjectAtOffset(-13, 24, Box.class) == null && getOneObjectAtOffset(13, 24, Box.class) == null) setLocation(getX(), getY() + speed);
        else if (getOneIntersectingObject(Box.class) != null)
        {
            if(getOneIntersectingObject(Box.class).getImage().getTransparency() < 235) setLocation(getX(), getY() + speed);
        } 
        direction = "down";
    }

    //Casts a spell upwards (Animation)
    public void castUp()
    {
        if(castCounter > 17)
        {
            castCounter = 0;
            boolUp = false;
        }
        
            
            heroImage = new GreenfootImage(castUp[castCounter/3]);
            castCounter++;
            setImage(heroImage);
        
    }
    //Casts a spell to the left (Animation)
    public void castLeft()
    {
        if(castCounter > 17)
        {
            castCounter = 0;
            boolLeft = false;
        }
        
            
            heroImage = new GreenfootImage(castLeft[castCounter/3]);
            castCounter++;
            setImage(heroImage);
        
    }
    //Casts a spell downwards (Animation)
    public void castDown()
    {
        if(castCounter > 17)
        {
            castCounter = 0;
            boolDown = false;
        }
        
            
            heroImage = new GreenfootImage(castDown[castCounter/3]);
            castCounter++;
            setImage(heroImage);
        
    }
    //Casts a spell to the right
    public void castRight()
    {
        if(castCounter > 17)
        {
            castCounter = 0;
            boolRight = false;
        }
        
            
            heroImage = new GreenfootImage(castRight[castCounter/3]);
            castCounter++;
            setImage(heroImage);
        
    }
    
    public String getDirection()
    {
        return direction;
    }

    //Decides which direction of attack animation will be played and creates a 
    //projectile object
    public void attack(MouseInfo m)
    {
        String direction;
        String image = "projectile.png";
        int projectileDamage = damage;
        int speed = 8;
        boolean enoughMana;
        direction = ((MyWorld)getWorld()).mouseDirection();
        //determines which of 2 possible projectiles will be used
        if(m.getButton() == 3)
        {
            image = "lightning.png";
            projectileDamage = damage - 35;
            speed = 10;
            if(70 < currentMana)
            {
                currentMana -= 70;
                enoughMana = true;
            }
            else enoughMana = false;
        }
        else
        {
            
            enoughMana = true;
        }
        
        p = new Projectile(image, projectileDamage, "Enemy", speed);
        //Sets appropriate boolean to true, which determines which animation plays
        if(direction.equals("right"))
        {
            
            if(enoughMana)
            {
                getWorld().addObject(p, this.getX() + 5, this.getY());
                boolRight = true;
                boolLeft = false;
                boolUp = false;
                boolDown = false;
            }
        }
        if(direction.equals("left"))
        {
            
            if(enoughMana)
            {
                getWorld().addObject(p, this.getX() - 5, this.getY());
                boolRight = false;
                boolLeft = true;
                boolUp = false;
                boolDown = false;
            }
        }
        if(direction.equals("up"))
        {
            
            if(enoughMana)
            {
                getWorld().addObject(p, this.getX(), this.getY() - 5);
                boolRight = false;
                boolLeft = false;
                boolUp = true;
                boolDown = false;
            }
        }
        if(direction.equals("down"))
        {
            
            if(enoughMana)
            {
                getWorld().addObject(p, this.getX(), this.getY() + 5);
                boolRight = false;
                boolLeft = false;
                boolUp = false;
                boolDown = true;
            }
        }
        p.turnTowards (m.getX(), m.getY()); //Shoots at the target (where the mouse is pointing)
        
    }
    
    //Getter methods and methods to change stat values
    public void changeHP(double change)
    {
        currentHP += change;
    }
    
    public double getHP()
    {
        return currentHP;
    }

    public void changeMana(double change)
    {
        currentMana += change;
    }
    
    public double getMana()
    {
        return currentMana;
    }
    
    public double getMaxHP()
    {
        return maxHP;
    }
    
    public double getMaxMana()
    {
        return maxMana;
    }
    
    public void setValues()//Prevents stats from going over the maximum and below 0
    {
        if(currentHP > 0) currentMana += 0.2;
        if(currentHP > maxHP) currentHP = maxHP;
        if(currentHP < 0) currentHP = 0;
        if(currentMana > maxMana) currentMana = maxMana;
        if(currentMana < 0) currentMana = 0;
        
    }
    
    protected void addedToWorld(World MyWorld)
    {
        getWorld().addObject(health, 250, 30);
        getWorld().addObject(mana, 250, 60);
    }
    
    public void respawn() //Resets values when the player respawns
    {
        heroImage = new GreenfootImage(walkRight[0]);
        setImage(heroImage);
        direction = "right";
        maxHP = 250;
        currentHP = maxHP;
        maxMana = 200;
        damage = -15;
        
        currentMana = maxMana;
        setLocation(410, 325);
        
        getWorld().setPaintOrder(Box.class, Hero.class);
        getWorld().addObject(new Box(800, 600, black, 5, 255), 400, 300);
        roomNumber = checkPoint;
        MyWorld.setEnemiesAdded(false);
        Enemy.setNumEnemies(0);
        //Removes all enemies and enemy related objects as to let the player "try again"
        getWorld().removeObjects( getWorld().getObjects(Healthbar.class) );
        getWorld().removeObjects( getWorld().getObjects(Fire.class) );
        getWorld().removeObjects( getWorld().getObjects(Enemy.class) );
        getWorld().removeObjects( getWorld().getObjects(Projectile.class) );
        getWorld().removeObjects( getWorld().getObjects(Laser.class) );
        //Re - adds only the player's healthbar objects
        getWorld().addObject(health, 250, 30);
        getWorld().addObject(mana, 250, 60);
    }
}
