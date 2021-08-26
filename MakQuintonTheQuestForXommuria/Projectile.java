import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Projectile extends Actor
{
    private int targetX;
    private int targetY;
    //Instance variables to detect the x and y offsets of the front of 
    //the projectile, relative to the center of its image
    private double rotation;
    private int xOffset;
    private int yOffset;
    private int speed; 
    
    private boolean explosive;
    
    
    private Enemy e;
    protected Hero h;
    private GreenfootImage i;
    private int damage;
    private String target;
    public Projectile(String s, int damage, String target, int speed)
    {
        this.speed = speed;
        this.damage = damage;
        this.target = target;//The target (enemy or hero) is a string parameter
        setImage(s);
        i = this.getImage();
        explosive = speed == 6;
        
    }
    
    
    
    public void act() 
    {
        setOffsets();
        move(speed);
        check();
        
    }
    
    public void checkIfHitEnemy()
    {
        if(getOneObjectAtOffset(xOffset, yOffset, Enemy.class) != null)
        {
            e = (Enemy) getOneObjectAtOffset(xOffset, yOffset, Enemy.class);
            e.changeHP(damage);
            if(speed != 10) getWorld().removeObject(this);//Special attack pierces enemies
        }
    }
    
    public void checkIfHitHero()
    {
        if(getOneObjectAtOffset(xOffset, yOffset, Hero.class) != null)
        {
            h = (Hero) getOneObjectAtOffset(xOffset, yOffset, Hero.class);
            h.changeHP(damage);
            if(explosive) getWorld().addObject(new Fire(), getX(), getY());//Malbus' attack explodes, adding fire
            getWorld().removeObject(this);
        }
    }
    
    public boolean checkIfHitBox()
    {
        if(getOneObjectAtOffset(xOffset, yOffset, Box.class) != null)
        {
            return true;
        }
        return false;
    }
    
    public void check()//If the projectile has not hit a box, check if it has hit an appropriate target
    {
        if (isAtEdge() || checkIfHitBox()) getWorld().removeObject(this);
        else if(target.equals("Enemy"))
        {
            checkIfHitEnemy();       
            
        }
        else if (target.equals("Hero")) checkIfHitHero();
    }
    //Uses the objects rotation in radians, to find the horizontal 
    //and vertical offsets of the front of the projectile from its 
    //center
    public void setOffsets()
    {
        rotation = Math.toRadians((double) this.getRotation());
        xOffset = (int) (Math.cos(rotation) * i.getWidth()/2);
        yOffset = (int) (Math.sin(rotation) * i.getWidth()/2);
        if(speed == 10)
        {
            xOffset = 0;
            yOffset = 0;
        }
    }
    
    
}
