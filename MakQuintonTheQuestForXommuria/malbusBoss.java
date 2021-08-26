import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class malbusBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class malbusBoss extends Enemy
{
    private String l1 = "Malbus: You do not understand true power!";
    private String l2 = "Malbus: You disappoint me. The Knight of Light holds you in such high esteem.";
    private String l3 = "Malbus: Give up, you're no match for my skills!";
    private String[] lines = {l1, l2, l3};
    
    private String[] attackUp;
    private String[] attackLeft;
    private String[] attackDown;
    private String[] attackRight;
    
    private String[] throwUp;
    private String[] throwLeft;
    private String[] throwDown;
    private String[] throwRight;
    
    private GreenfootImage malbusImage;
    private Projectile p;
    
    private String direction;
    private int attackCounter;
    private int throwCounter;
    private int cycleCounter;
    private int speed;
    private boolean boolUp = false;
    private boolean boolLeft = false;
    private boolean boolDown = false;
    private boolean boolRight = false;
    private boolean throwing;
    public malbusBoss(Hero a)
    {
        super(1750, a, -60);
        //Loads animation arrays
        moveUp = loadArray("malbus", ".png", 0, 8);
        moveLeft = loadArray("malbus", ".png", 9, 17);
        moveDown = loadArray("malbus", ".png", 18, 26);
        moveRight = loadArray("malbus", ".png", 27, 35);
        throwUp = loadArray("malbus", ".png", 36, 41);
        throwLeft = loadArray("malbus", ".png", 42, 47);
        throwDown = loadArray("malbus", ".png", 48, 53);
        throwRight = loadArray("malbus", ".png", 54, 59);
        attackUp = loadArray("malbus", ".png", 60, 65);
        attackLeft = loadArray("malbus", ".png", 66, 71);
        attackDown = loadArray("malbus", ".png", 72, 77);
        attackRight = loadArray("malbus", ".png", 78, 83);
        deathAnimation = loadArray("malbusDeath", ".png", 0, 4);
        
        malbusImage = new GreenfootImage(moveRight[0]);
        
        setImage(malbusImage);
        
        attackCounter = 0;
        throwCounter = 0;
        cycleCounter = 0;
        speed = 2;
        isBoss = true;
        MyWorld.playMusic("malbus.mp3");
    }
    
    public void act() 
    {
        
        if(!isDead){
            cycleCounter ++;
                    
            if(cycleCounter > 651)
            {
                cycleCounter = 0;
                
            }
              
            direction = this.getDirection();
            if((cycleCounter > 100 && cycleCounter < 124) || (cycleCounter > 400 && cycleCounter < 424))
            {
                throwAnimation();
                throwProjectile();
            }
            else
            {
                if(attackCounter == 0) autoMove();
                checkAttack();
                attack();
            }
            taunt(lines);//See Enemy class
            if(cycleCounter % 240 == 0) speed = 10;//Dashes for a count of 12
            if(cycleCounter % 240 == 12) speed = 2;//Sets speed back to normal after 12 acts
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
        
        
            if(attackCounter < 17) malbusImage = new GreenfootImage(attackUp[attackCounter/3]);
        setImage(malbusImage);    
            
        
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
        
            
            if(attackCounter < 17) malbusImage = new GreenfootImage(attackLeft[attackCounter/3]);
        setImage(malbusImage);    
            
        
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
        
            
            if(attackCounter < 17) malbusImage = new GreenfootImage(attackDown[attackCounter/3]);
        setImage(malbusImage);    
            
        
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
        
            
            if(attackCounter < 17) malbusImage = new GreenfootImage(attackRight[attackCounter/3]);
            
            
        setImage(malbusImage);
    }
    
    public void autoMove()//See axeBoi class, but this boss moves with varying speeds
    {
        if(getDirection().equals("up")) moveUp(speed);
        if(getDirection().equals("down")) moveDown(speed);
        if(getDirection().equals("left")) moveLeft(speed);
        if(getDirection().equals("right")) moveRight(speed);
        
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
    
    public void attack()
    {
        int dashDamage = 0;
        if(speed == 10) dashDamage = -30;
        //If the target is close enough during the melee attack, the target takes damage
        if(boolUp){
            attackUp();
            if((Math.abs(vertical) < 58) && attackCounter == 12) target.changeHP(damage + dashDamage);
        }
        if(boolDown){
            attackDown();
            if((Math.abs(vertical) < 58) && attackCounter == 12) target.changeHP(damage + dashDamage);
        }
        if(boolRight){
            attackRight();
            if((Math.abs(horizontal) < 62) && attackCounter == 12) target.changeHP(damage + dashDamage);
        }
        if(boolLeft){
            attackLeft();
            if((Math.abs(horizontal) < 62) && attackCounter == 12) target.changeHP(damage + dashDamage);
        }
        
    }
    //Throws upwards (Animation)
    public void throwUp()
    {
        throwCounter++;
        if(throwCounter > 17)
        {
            throwCounter = 0;
	    
        }
        
        
            if(throwCounter < 17)enemyImage = new GreenfootImage(throwUp[throwCounter/3]);
            
            setImage(enemyImage);
        
    }
    //throws to the left (Animation)
    public void throwLeft()
    {
        throwCounter++;
        if(throwCounter > 17)
        {
            throwCounter = 0;
	    
        }
        
            
            if(throwCounter < 17)enemyImage = new GreenfootImage(throwLeft[throwCounter/3]);
            
            setImage(enemyImage);
        
    }
    //throws downwards (Animation)
    public void throwDown()
    {
        throwCounter++;
        if(throwCounter > 17)
        {
            throwCounter = 0;
            
        }
        
            
            if(throwCounter < 17)enemyImage = new GreenfootImage(throwDown[throwCounter/3]);
            
            setImage(enemyImage);
        
    }
    //throws to the right
    public void throwRight()
    {
        throwCounter++;
        if(throwCounter > 17)
        {
            throwCounter = 0;
            
        }
        
            
            if(throwCounter < 17)enemyImage = new GreenfootImage(throwRight[throwCounter/3]);
            
            setImage(enemyImage);
        
    }
    //Performs the appropriate throwing animation at certain intervals
    public void throwAnimation()
    {
        if((cycleCounter > 100 && cycleCounter < 118) || (cycleCounter > 400 && cycleCounter < 418))
        {
            if(getDirection().equals("up")) throwUp();
            if(getDirection().equals("left")) throwLeft();
            if(getDirection().equals("down")) throwDown();
            if(getDirection().equals("right")) throwRight();
        }
    }
    
    public void throwProjectile()
    {
        
        attackCounter = 0;
        if(cycleCounter == 109) 
        {
            for(int i = 0; i < 3; i++)//Throws a spread of 3 knives
            {
                p = new Projectile("knife.png", damage + 20, "Hero", 5);//See projectile class
                getWorld().addObject(p, getX(), getY());
                p.turnTowards(target.getX(), target.getY());
                p.turn((i - 1)* 8);//Makes it a spread as each is thrown at a different angle
            }
        }
        
        if(cycleCounter == 409)
        {
            //Throws a bomb that does area damage overtime
            p = new Projectile("bomb.png", damage, "Hero", 6);
            getWorld().addObject(p, getX(), getY());
            p.turnTowards(target.getX(), target.getY());
                
            
        }
    }
}
