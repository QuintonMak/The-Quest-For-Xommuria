import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OrcBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrcBoss extends Enemy
{
    //Taunting lines
    private String l1 = "Lieutenant Gregg: Troops, destroy him!";
    private String l2 = "Lieutenant Gregg: I thought you would be a challenge!";
    private String l3 = "Lieutenant Gregg: I'm not done with you!";
    private String[] lines = {l1, l2, l3};
    
    private String[] attackUp;
    private String[] attackLeft;
    private String[] attackDown;
    private String[] attackRight;
    
    private GreenfootImage orcImage;
    private String direction;
    private int attackCounter;
    private int summonCounter;
    private int cycleCounter;
    private boolean boolUp = false;
    private boolean boolLeft = false;
    private boolean boolDown = false;
    private boolean boolRight = false;
    private boolean summoning = false;
    public OrcBoss(Hero a)
    {
        super(1650, a, -80);
        //Sets animation arrays
        moveUp = loadArray("orcBoss", ".png", 0, 8);
        moveLeft = loadArray("orcBoss", ".png", 9, 17);
        moveDown = loadArray("orcBoss", ".png", 18, 26);
        moveRight = loadArray("orcBoss", ".png", 27, 35);
        attackUp = loadArray("orcBoss", ".png", 36, 41);
        attackLeft = loadArray("orcBoss", ".png", 42, 47);
        attackDown = loadArray("orcBoss", ".png", 48, 53);
        attackRight = loadArray("orcBoss", ".png", 54, 59);
        deathAnimation = loadArray("orcBossDeath", ".png", 0, 4);
        
        orcImage = new GreenfootImage(moveRight[0]);
        
        setImage(orcImage);
        
        attackCounter = 0;
        cycleCounter = 0;
        isBoss = true;
        MyWorld.playMusic("gregg.mp3");
    }
    
    public void act() 
    {
        if(!isDead){
            cycleCounter ++;
            taunt(lines);//See Enemy class
            if(cycleCounter == 50)
            {
                summoning = true;
                //Summons troops periodically
            }
            if(cycleCounter > 600) cycleCounter = 0;//Resets the action 'cycle'
            
            if(!summoning){
                direction = this.getDirection();
                if(attackCounter == 0) autoMove();
                checkAttack();
                attack();
                
            }
            else summon();
        }
        checkIfDead();
        
    } 
    
    //attacks upwards (Animation)
    public void attackUp()
    {
        attackCounter++;
        if(attackCounter > 17)
        {
            attackCounter = 0;
            boolUp = false;
        }
        
        
            if(attackCounter < 17) orcImage = new GreenfootImage(attackUp[attackCounter/3]);
        setImage(orcImage);    
            
        
    }
    //attacks to the left (Animation)
    public void attackLeft()
    {
        attackCounter++;
        if(attackCounter > 17)
        {
            attackCounter = 0;
            boolLeft = false;
        }
        
            
            if(attackCounter < 17) orcImage = new GreenfootImage(attackLeft[attackCounter/3]);
        setImage(orcImage);    
            
        
    }
    //attacks downwards (Animation)
    public void attackDown()
    {
        attackCounter++;
        if(attackCounter > 17)
        {
            attackCounter = 0;
            boolDown = false;
        }
        
            
            if(attackCounter < 17) orcImage = new GreenfootImage(attackDown[attackCounter/3]);
        setImage(orcImage);    
            
        
    }
    //attacks to the right
    public void attackRight()
    {
        attackCounter++;
        if(attackCounter > 17)
        {
            attackCounter = 0;
            boolRight = false;
        }
        
            
            if(attackCounter < 17) orcImage = new GreenfootImage(attackRight[attackCounter/3]);
            
            
        setImage(orcImage);
    }
    
    public void autoMove()//See axeBoi class
    {
        if(getDirection().equals("up")) moveUp(2);
        if(getDirection().equals("down")) moveDown(2);
        if(getDirection().equals("left")) moveLeft(2);
        if(getDirection().equals("right")) moveRight(2);
        
    }
    
    public void checkAttack()//See axeBoi class
    {
        if(attackCounter == 0){
            if(getOneIntersectingObject(Hero.class) != null){
                if(getDirection().equals("down"))
                {
                    boolRight = false;
                    boolLeft = false;
                    boolUp = false;
                    boolDown = true;
                    
                }
                else if(getDirection().equals("up"))
                {
                   boolRight = false;
                   boolLeft = false;
                   boolUp = true;
                   boolDown = false; 
                   
                }
                else if(getDirection().equals("right")) 
                {
                   boolRight = true;
                   boolLeft = false;
                   boolUp = false;
                   boolDown = false; 
                   
                }
                else if(getDirection().equals("left")) 
                {
                   boolRight = false;
                   boolLeft = true;
                   boolUp = false;
                   boolDown = false; 
                   
                }
                else
                {
                   boolRight = false;
                   boolLeft = false;
                   boolUp = false;
                   boolDown = false; 
                }
            }
        }
    }
    
    public void attack()//If the target is close enough during the attack, the target takes damage
    {
        if(boolUp) attackUp();
        if(boolDown) attackDown();
        if(boolRight) attackRight();
        if(boolLeft) attackLeft();
        if((Math.abs(vertical) < 23 || Math.abs(horizontal) < 19) && attackCounter == 9) target.changeHP(damage);
    }
    
    public void summon()//Creates two summonsed troops and stands still while doing it
    {
        summoning = true;
        attackCounter = 0;
        
        
        if(cycleCounter == 50){    
            getWorld().addObject(new axeBoi(target, true), getX() - 50, getY() + 50);
            getWorld().addObject(new axeBoi(target, true), getX() + 50, getY() - 50);
            if(direction.equals("up")) setImage(moveUp[0]);
            if(direction.equals("left")) setImage(moveLeft[0]);
            if(direction.equals("down")) setImage(moveDown[0]);
            if(direction.equals("right")) setImage(moveRight[0]);
            
        }
        if(cycleCounter == 102) summoning = false;
        
        
    }
}
