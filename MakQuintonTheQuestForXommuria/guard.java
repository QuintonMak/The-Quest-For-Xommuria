import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class guard extends Enemy
{
    //Animation arrays
    private String[] attackUp;
    private String[] attackLeft;
    private String[] attackDown;
    private String[] attackRight;
    private String[] shootUp;
    private String[] shootLeft;
    private String[] shootDown;
    private String[] shootRight;
    
    private GreenfootImage guardImage;
    


    private String direction;
    private int attackCounter, shootCounter, cycleCounter;//Counters for meele attacking, shooting, and an act cycle
    private boolean boolUp = false;
    private boolean boolLeft = false;
    private boolean boolDown = false;
    private boolean boolRight = false;
    
    
    private Projectile p;

    
    
    
    public guard(Hero a)
    {
        super(280, a, -40);
        //Load animation arrays
        moveUp = loadArray("guard", ".png", 0, 8);
        moveLeft = loadArray("guard", ".png", 9, 17);
        moveDown = loadArray("guard", ".png", 18, 26);
        moveRight = loadArray("guard", ".png", 27, 35);
        attackUp = loadArray("guard", ".png", 36, 41);
        attackLeft = loadArray("guard", ".png", 42, 47);
        attackDown = loadArray("guard", ".png", 48, 53);
        attackRight = loadArray("guard", ".png", 54, 59);
        shootUp = loadArray("guardShooting", ".png", 0, 11);
        shootLeft = loadArray("guardShooting", ".png", 13, 24);
        shootDown = loadArray("guardShooting", ".png", 26, 37);
        shootRight = loadArray("guardShooting", ".png", 39, 50);
        deathAnimation = loadArray("guardDeath", ".png", 0, 4);
        
        guardImage = new GreenfootImage(moveDown[0]);
        //Set counters to zero
        attackCounter = 0;
        shootCounter = 0;
        cycleCounter = 0;
        
        
        setImage(guardImage);
        
        
    }
    
    public void act() 
    {
        if(!isDead){
            if(cycleCounter > 100) cycleCounter = 0;//One cycle is 100 acts
            
            direction = this.getDirection();
            
            
            if(cycleCounter <= 23)//Shoots at a certain interval
            {
                shootAnimation();
                shoot();
            }
            else//If not shooting, move towards the hero and meele attack if close enough
            {
                if(attackCounter == 0) autoMove();
                checkAttack();
                attack();
            }
                
            
            cycleCounter ++;  
        }
        checkIfDead();
        
    }
    
    //attacks upwards (Animation)
    public void attackUp()
    {
        attackCounter++;
        if(attackCounter >= 17)
        {
            attackCounter = 0;
            boolUp = false;
        }
        
            setLocation(getX(), getY() - 1);
            if(attackCounter < 17) guardImage = new GreenfootImage(attackUp[attackCounter/3]);
            
            setImage(guardImage);
        
    }
    //attacks to the left (Animation)
    public void attackLeft()
    {
        attackCounter++;
        if(attackCounter >= 17)
        {
            attackCounter = 0;
            boolLeft = false;
        }
        
            setLocation(getX() - 1, getY());
            if(attackCounter < 17) guardImage = new GreenfootImage(attackLeft[attackCounter/3]);
            
            setImage(guardImage);
        
    }
    //attacks downwards (Animation)
    public void attackDown()
    {
        attackCounter++;
        if(attackCounter >= 17)
        {
            attackCounter = 0;
            boolDown = false;
        }
        
            setLocation(getX(), getY() + 1);
            if(attackCounter < 17) guardImage = new GreenfootImage(attackDown[attackCounter/3]);
            
            setImage(guardImage);
        
    }
    //attacks to the right
    public void attackRight()
    {
        attackCounter++;
        if(attackCounter >= 17)
        {
            attackCounter = 0;
            boolRight = false;
        }
        
            setLocation(getX() + 1, getY());
            if(attackCounter < 17) guardImage = new GreenfootImage(attackRight[attackCounter/3]);
            
            setImage(guardImage);
        
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
    //See axeBoi class
    public void attack()
    {
        if(boolUp) attackUp();
        if(boolDown) attackDown();
        if(boolRight) attackRight();
        if(boolLeft) attackLeft();
        if((Math.abs(vertical) < 45 || Math.abs(horizontal) < 38) && attackCounter == 9) target.changeHP(damage - 10);
    }
    //Shoot upwards (Animation)
    public void shootUp()
    {
        shootCounter++;
        if(shootCounter >= 23)
        {
            shootCounter = 0;
            
        }
        
         
            if(shootCounter < 23)enemyImage = new GreenfootImage(shootUp[shootCounter/2]);
            
            setImage(enemyImage);
        
    }
    //shoots to the left (Animation)
    public void shootLeft()
    {
        shootCounter++;
        if(shootCounter >= 23)
        {
            shootCounter = 0;
            
        }
        
             
            if(shootCounter < 23)enemyImage = new GreenfootImage(shootLeft[shootCounter/2]);
            
            setImage(enemyImage);
        
    }
    //shoots downwards (Animation)
    public void shootDown()
    {
        shootCounter++;
        if(shootCounter >= 23)
        {
            shootCounter = 0;
            
        }
        
            
            if(shootCounter < 23)enemyImage = new GreenfootImage(shootDown[shootCounter/2]);
            
            setImage(enemyImage);
        
    }
    //shoots to the right
    public void shootRight()
    {
        shootCounter++;
        if(shootCounter >= 23)
        {
            shootCounter = 0;
            
        }
        
            
            if(shootCounter < 23)enemyImage = new GreenfootImage(shootRight[shootCounter/2]);
            
            setImage(enemyImage);
        
    }
    
    public void shoot()
    {
        if(shootCounter == 18) 
        {
            p = new Projectile("arrow.png", damage, "Hero", 11);
            
            getWorld().addObject(p, getX(), getY());
            p.turnTowards(target.getX(), target.getY());
        }
    }
    
    public void shootAnimation()//See arrowBoi class
    {
        if(cycleCounter < 23)
        {
            if(getDirection().equals("up")) shootUp();
            if(getDirection().equals("left")) shootLeft();
            if(getDirection().equals("down")) shootDown();
            if(getDirection().equals("right")) shootRight();
        }
    }
}
