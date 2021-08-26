import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class arrowBoi here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class arrowBoi extends Enemy
{
    //I had manually set these arrays before I made the loadArrays method
    private String[] attackUp = { "skeleton0.png", "skeleton1.png", "skeleton2.png", "skeleton3.png", "skeleton4.png", "skeleton5.png", "skeleton6.png", "skeleton7.png", "skeleton8.png", "skeleton9.png", "skeleton10.png", "skeleton11.png"};
    private String[] attackLeft = {"skeleton13.png", "skeleton14.png", "skeleton15.png", "skeleton16.png", "skeleton17.png", "skeleton18.png", "skeleton19.png", "skeleton20.png", "skeleton21.png", "skeleton22.png", "skeleton23.png", "skeleton24.png"};
    private String[] attackDown = {"skeleton26.png", "skeleton27.png", "skeleton28.png", "skeleton29.png", "skeleton30.png", "skeleton31.png", "skeleton32.png", "skeleton33.png", "skeleton34.png", "skeleton35.png", "skeleton36.png", "skeleton37.png"};
    private String[] attackRight = {"skeleton39.png", "skeleton40.png", "skeleton41.png", "skeleton42.png", "skeleton43.png", "skeleton44.png", "skeleton45.png", "skeleton46.png", "skeleton47.png", "skeleton48.png", "skeleton49.png", "skeleton50.png"};
    private String direction;
    private int attackCounter;
    private int cycleCounter;
    
    private GreenfootImage enemyImage;
    private Projectile p;
    
    private boolean boolUp = false;
    private boolean boolLeft = false;
    private boolean boolDown = false;
    private boolean boolRight = false;
    public arrowBoi(Hero a)
    {
        super(100, a, -50);
        attackCounter = 0;
        cycleCounter = 0;
        deathAnimation = loadArray("skeletonDeath", ".png", 0, 4);
        setImage(attackRight[0]);
    }
    
    public void act() 
    {
        if(!isDead){
            direction = this.getDirection();
            attack();
            shoot();
            cycleCounter++;
            if(cycleCounter > 70) cycleCounter = 0;
            faceTarget();
            setImage(enemyImage);
        }
        checkIfDead();
        
    }  
    
    public void attack()//Attacks at a certain interval
    {
        if(cycleCounter < 23)
        {
            if(getDirection().equals("up")) attackUp();
            if(getDirection().equals("left")) attackLeft();
            if(getDirection().equals("down")) attackDown();
            if(getDirection().equals("right")) attackRight();
        }
    }
    
    public void faceTarget()//If not attacking, face towards the target
    {
        if(cycleCounter >= 23)
        {
            attackCounter = 0;
            if(getDirection().equals("up")) setImage(attackUp[0]);
            if(getDirection().equals("left")) setImage(attackLeft[0]);
            if(getDirection().equals("down")) setImage(attackDown[0]);
            if(getDirection().equals("right")) setImage(attackRight[0]);
        }
    }
    //Attack upwards (Animation)
    public void attackUp()
    {
        attackCounter++;
        if(attackCounter > 23)
        {
            attackCounter = 0;
	    boolUp = false;
        }
        
        
            if(attackCounter < 23)enemyImage = new GreenfootImage(attackUp[attackCounter/2]);
            
            setImage(enemyImage);
        
    }
    //attacks to the left (Animation)
    public void attackLeft()
    {
        attackCounter++;
        if(attackCounter > 23)
        {
            attackCounter = 0;
	    boolLeft = false;
        }
        
            
            if(attackCounter < 23)enemyImage = new GreenfootImage(attackLeft[attackCounter/2]);
            
            setImage(enemyImage);
        
    }
    //attacks downwards (Animation)
    public void attackDown()
    {
        attackCounter++;
        if(attackCounter > 23)
        {
            attackCounter = 0;
            boolDown = false;
        }
        
            
            if(attackCounter < 23)enemyImage = new GreenfootImage(attackDown[attackCounter/2]);
            
            setImage(enemyImage);
        
    }
    //attacks to the right (Animation)
    public void attackRight()
    {
        attackCounter++;
        if(attackCounter > 23)
        {
            attackCounter = 0;
            boolRight = false;
        }
        
            
            if(attackCounter < 23)enemyImage = new GreenfootImage(attackRight[attackCounter/2]);
            
            setImage(enemyImage);
        
    }
    //Creates an arrow that shoots in the direction of the hero
    public void shoot()
    {
        if(attackCounter == 18) 
        {
            p = new Projectile("arrow.png", damage, "Hero", 13);//See projectile class
            
            getWorld().addObject(p, getX(), getY());
            p.turnTowards(target.getX(), target.getY());
        }
    }
}
