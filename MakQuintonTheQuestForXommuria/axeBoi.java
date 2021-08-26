import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class axeBoi extends Enemy
{
    
    private String[] attackUp;
    private String[] attackLeft;
    private String[] attackDown;
    private String[] attackRight;
    
    private GreenfootImage orcImage;
    


    private String direction;
    private int attackCounter;
    private boolean boolUp = false;
    private boolean boolLeft = false;
    private boolean boolDown = false;
    private boolean boolRight = false;
    private boolean summoned;
    
    

    
    
    
    public axeBoi(Hero a, boolean summoned)
    {
        super(200, a, -35);
        //Set animation arrays
        moveUp = loadArray("axeBoi", ".png", 0, 8);
        moveLeft = loadArray("axeBoi", ".png", 9, 17);
        moveDown = loadArray("axeBoi", ".png", 18, 26);
        moveRight = loadArray("axeBoi", ".png", 27, 35);
        attackUp = loadArray("axeBoi", ".png", 36, 41);
        attackLeft = loadArray("axeBoi", ".png", 42, 47);
        attackDown = loadArray("axeBoi", ".png", 48, 53);
        attackRight = loadArray("axeBoi", ".png", 54, 59);
        deathAnimation = loadArray("orcDeath", ".png", 0, 4);
        
        orcImage = new GreenfootImage(moveDown[0]);
        
        attackCounter = 0;
        
        this.summoned = summoned;
        setImage(orcImage);
        if(summoned)//If summoned from the orc boss, the orc fades in
        {
            orcImage.setTransparency(102);
            
        }
        
    }
    
    public void act() 
    {
        if(!isDead){
            if(summoned && orcImage.getTransparency() < 255)
            {
                orcImage.setTransparency(orcImage.getTransparency() + 3);//Fade in effect
                changeHP(500);//They can't take damage while they are spawning
                
                
            }
            else
            {
                direction = this.getDirection();
                if(attackCounter == 0) autoMove();
                checkAttack();
                attack();
                
                
            }
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
    
    public void autoMove()//Moves in the direction the enemy is facing
    {
        if(getDirection().equals("up")) moveUp(2);
        if(getDirection().equals("down")) moveDown(2);
        if(getDirection().equals("left")) moveLeft(2);
        if(getDirection().equals("right")) moveRight(2);
        
    }
    
    public void checkAttack()//If the hero is in close enough proximity of the boss, the boss attacks in the proper direction
    {
        if(attackCounter == 0){
            if(getOneIntersectingObject(Hero.class) != null){
                //Sets a boolean which tells the enemy which direction to attack
                //This is based on which direction the enemy is facing when coming into contact with the player
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
    
    public void attack()//If the target is close enough during an attack, the target takes damage
    {
        if(boolUp) attackUp();
        if(boolDown) attackDown();
        if(boolRight) attackRight();
        if(boolLeft) attackLeft();
        if((Math.abs(vertical) < 45 || Math.abs(horizontal) < 38) && attackCounter == 9) target.changeHP(damage);
    }
    
    
}
