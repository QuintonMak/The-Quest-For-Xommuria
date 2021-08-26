import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class wizardBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class wizardBoss extends Enemy
{
    private String l1 = "Invictus: Pathetic. Just like your father.";
    private String l2 = "Invictus: My sorcerer's powers are far beyond yours!";
    private String l3 = "Invictus: What would King Xom say if he could see you now?";
    private String[] lines = {l1, l2, l3};
    
    private String[] attackUp;
    private String[] attackLeft;
    private String[] attackDown;
    private String[] attackRight;
    
    private int spawnX, spawnY;
    private double cloneAdj;
    
    private Projectile p;
    
    
    private String direction;
    private int attackCounter, rapidCounter;
    private int cycleCounter;
    private int speed;
    
    private boolean boolUp = false;
    private boolean boolLeft = false;
    private boolean boolDown = false;
    private boolean boolRight = false;
    
    
    private boolean revived;
    
    public wizardBoss(Hero a, boolean cloned)
    {
        super(2000, a, -70);
        this.cloned = cloned;
        cloneAdj = 1;
        
        //load animation arrays
        moveUp = loadArray("wizard", ".png", 0, 8);
        moveLeft = loadArray("wizard", ".png", 9, 17);
        moveDown = loadArray("wizard", ".png", 18, 26);
        moveRight = loadArray("wizard", ".png", 27, 35);
        attackUp = loadArray("wizardShoot", ".png", 0, 7);
        attackLeft = loadArray("wizardShoot", ".png", 8, 15);
        attackDown = loadArray("wizardShoot", ".png", 16, 23);
        attackRight = loadArray("wizardShoot", ".png", 24, 31);
        deathAnimation = loadArray("wizardDeath", ".png", 0, 4);
        enemyImage = new GreenfootImage(moveLeft[0]);
        if(cloned)
        {
            maxHP = 250;//Clones have decreased health
            currentHP = 250;
            speed = 2;
            cloneAdj = 1.5;//clones have decreased damage
            health = new Healthbar(100, 5, maxHP, this);//Sets the healthbar for the cloned values
            enemyImage.setTransparency(102);
        }
        setImage(enemyImage);
        
        
        
        attackCounter = 0;
        rapidCounter = 0;
        cycleCounter = 1;
        
        if(!cloned)
        {
            speed = 1;
            //Invictus moves at a different speed that his clones
            
        }
        
        damage = (int) (damage/cloneAdj);//Adjustment to damage based on if this object is a clone or not
        
        if(!cloned)
        {
            MyWorld.playMusic("invictus.mp3");
            isBoss = true;
        }
    }
    
    public void act() 
    {
        
        if(!isDead){
            if(cloned && enemyImage.getTransparency() < 150)
            {
                enemyImage.setTransparency(enemyImage.getTransparency() + 3);//Clones fade in
                changeHP(500);//Clones are invincible while fading in
                //checkIfDead();
            }
            else{
                if(cycleCounter > 1548 && cycleCounter < 1600)
                {
                    if(!cloned) cloneSelf();//Clone at a certain interval
                }
                else if((cycleCounter > 800 && cycleCounter < 850) || (cycleCounter > 1500 && cycleCounter < 1550))
                {
                    if(!cloned) currentHP += 6;//Heal at a certain interval
                    if(getDirection().equals("right")) enemyImage = new GreenfootImage(moveRight[0]);
                    if(getDirection().equals("left")) enemyImage = new GreenfootImage(moveLeft[0]);
                    if(getDirection().equals("up")) enemyImage = new GreenfootImage(moveUp[0]);
                    if(getDirection().equals("down")) enemyImage = new GreenfootImage(moveDown[0]);
                    setImage(enemyImage);
                }
                else{
                    checkAttack();
                    attack();
                    shoot();
                }
                cycleCounter ++;
                if(cycleCounter == 1600)
                {
                    cycleCounter = 0;
                    
                }
                if(cloned) enemyImage.setTransparency(150);//Clones appear translucent
                if(!cloned) taunt(lines);
                revive();
            }
        }
        
        checkIfDead();
    }  
    //Attack upwards (animation)
    public void attackUp()
    {
        attackCounter++;
        if(attackCounter > 23)
        {
            attackCounter = 0;
            boolUp = false;
        }
        
        
            if(attackCounter < 23) enemyImage = new GreenfootImage(attackUp[attackCounter/3]);
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
        
            
            if(attackCounter < 23) enemyImage = new GreenfootImage(attackLeft[attackCounter/3]);
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
        
            
            if(attackCounter < 23) enemyImage = new GreenfootImage(attackDown[attackCounter/3]);
        setImage(enemyImage);    
            
        
    }
    //attacks to the right
    public void attackRight()
    {
        attackCounter++;
        if(attackCounter > 23)
        {
            attackCounter = 0;
            boolRight = false;
        }
        
            
            if(attackCounter < 23) enemyImage = new GreenfootImage(attackRight[attackCounter/3]);
            
            
        setImage(enemyImage);
    }
    //Attack Animation
    public void attack()
    {
        if(boolUp || boolLeft || boolDown || boolRight)//Plays an animation based on which direction Invictus is facing
        {
            if(boolUp){
                attackUp();
                
            }
            if(boolDown){
                attackDown();
               
            }
            if(boolRight){
                attackRight();
                
            }
            if(boolLeft){
                attackLeft();
                
            }
        }
        else autoMove();
    }
    
    public void checkAttack()
    {
        if(cycleCounter % 64 == 0)//Attacks every 64 acts
        {
            if(getDirection().equals("up"))
            {
                boolUp = true;
                boolLeft = false;
                boolDown = false;
                boolRight = false;
            }
            if(getDirection().equals("left"))
            {
                boolUp = false;
                boolLeft = true;
                boolDown = false;
                boolRight = false;
            }
            if(getDirection().equals("down"))
            {
                boolUp = false;
                boolLeft = false;
                boolDown = true;
                boolRight = false;
            }
            if(getDirection().equals("right")) 
            {
                boolUp = false;
                boolLeft = false;
                boolDown = false;
                boolRight = true;
            }
            
            
        }
    }
    
    public void autoMove()
    {
        if(getDirection().equals("up")) moveUp(speed);
        if(getDirection().equals("down")) moveDown(speed);
        if(getDirection().equals("left")) moveLeft(speed);
        if(getDirection().equals("right")) moveRight(speed);
        
    }
    
    public void shoot()
    {
        //Sets projectile spawning adjustments so it appears to shoot projectiles out of the staff
        if(getDirection().equals("up"))
            {
                spawnX = 0;
                spawnY = -1 * enemyImage.getHeight() / 2 + 5;
            }
        if(getDirection().equals("left"))
            {
                spawnX = -1 * enemyImage.getWidth() / 2 + 8;
                spawnY = 10;
            }
        if(getDirection().equals("down"))
            {
                spawnX = 0;
                spawnY = enemyImage.getHeight() / 2 - 5;
            }
        if(getDirection().equals("right")) 
            {
                spawnX = enemyImage.getWidth() / 2 - 8;
                spawnY = 10;
            }
        
        if(attackCounter == 12) 
        {
            //If Invictus is below half health or has revived, and is not a clone, he will rapid fire at a certain interval
            if((revived || currentHP <= maxHP/2) && !cloned && ((cycleCounter >= 640 && cycleCounter <= 735) || (cycleCounter >= 1280 && cycleCounter <= 1375)) )
            {
                rapidFire();
                //When rapid firing, Invictus stays still, so he will need to turn as soon as the player is at a different direction
                //So, direction must be updated every act as opposed to every 64 acts
                if(getDirection().equals("up"))
                {
                    boolUp = true;
                    boolLeft = false;
                    boolDown = false;
                    boolRight = false;
                }
                if(getDirection().equals("left"))
                {
                    boolUp = false;
                    boolLeft = true;
                    boolDown = false;
                    boolRight = false;
                }
                if(getDirection().equals("down"))
                {
                    boolUp = false;
                    boolLeft = false;
                    boolDown = true;
                    boolRight = false;
                }
                if(getDirection().equals("right")) 
                {
                    boolUp = false;
                    boolLeft = false;
                    boolDown = false;
                    boolRight = true;
                }
            }
            //If Invictus is in the revived stage, he will shoot out a laser at a certain interval
            else if(revived && !cloned && (cycleCounter >= 320 && cycleCounter <= 470))
            {
                //Shoots a laser rotating counter clockwise
                laser("counterclockwise");
                //Same reason as rapid fire
                if(getDirection().equals("up"))
                {
                    boolUp = true;
                    boolLeft = false;
                    boolDown = false;
                    boolRight = false;
                }
                if(getDirection().equals("left"))
                {
                    boolUp = false;
                    boolLeft = true;
                    boolDown = false;
                    boolRight = false;
                }
                if(getDirection().equals("down"))
                {
                    boolUp = false;
                    boolLeft = false;
                    boolDown = true;
                    boolRight = false;
                }
                if(getDirection().equals("right")) 
                {
                    boolUp = false;
                    boolLeft = false;
                    boolDown = false;
                    boolRight = true;
                }
            }
            else if(revived && !cloned && (cycleCounter >= 960 && cycleCounter <= 1110))
            {
                //if(cycleCounter < 1000) laser("counterclockwise");
                //Shoots a laser rotating clockwise at a certain interval
                laser("clockwise");
                if(getDirection().equals("up"))
                {
                    boolUp = true;
                    boolLeft = false;
                    boolDown = false;
                    boolRight = false;
                }
                if(getDirection().equals("left"))
                {
                    boolUp = false;
                    boolLeft = true;
                    boolDown = false;
                    boolRight = false;
                }
                if(getDirection().equals("down"))
                {
                    boolUp = false;
                    boolLeft = false;
                    boolDown = true;
                    boolRight = false;
                }
                if(getDirection().equals("right")) 
                {
                    boolUp = false;
                    boolLeft = false;
                    boolDown = false;
                    boolRight = true;
                }
            }
            else if(cycleCounter % 192 == 11)
            {
                //Shoots out spinning projectiles every 3 attacks, excluding rapid fire and laser (192/64 = 3)
                for(int i = 0; i < 4; i++)
                {
                    p = new rotatingProjectile("rotating.png", damage + 10, "Hero", 7);
                    if(i % 2 == 0)
                    {
                        if(!cloned){
                            getWorld().addObject(p, getX() + spawnX, getY() + spawnY);
                            p.turnTowards(target.getX(), target.getY());
                            p.turn(90 * i);
                        }
                    }
                    else
                    {
                        getWorld().addObject(p, getX() + spawnX, getY() + spawnY);
                        p.turnTowards(target.getX(), target.getY());
                        p.turn(90 * i);
                    }
                }
                //Also shoot a fireball at the hero
                p = new Projectile("fireball.png", damage, "Hero", 11);
                getWorld().addObject(p, getX() + spawnX, getY() + spawnY);
                p.turnTowards(target.getX(), target.getY());
            }
            else
            {
                //If there are no special attacks, shoots a fire ball at the hero(see arrowBoi for similar segment)
                p = new Projectile("fireball.png", damage, "Hero", 11);
                getWorld().addObject(p, getX() + spawnX, getY() + spawnY);
                p.turnTowards(target.getX(), target.getY());
                rapidCounter = 0;
            }
        }
    }
    
    public void rapidFire()
    {
        //Shoots a projectile every 20 acts
        if(rapidCounter == 20)
        {
            p = new homingProjectile(damage + 20, target);
            getWorld().addObject(p, getX() + spawnX, getY() + spawnY);
            p.turnTowards(target.getX(), target.getY());
            rapidCounter = 0;
        }
        rapidCounter ++;
        attackCounter --;//ensures the animation doesn't change
        
    }

    public void laser(String direction)
    {
        //shoots out a rotating laser
        if( ((MyWorld)getWorld()).getObjects(Laser.class).isEmpty())//Adds the laser if it hadn't already
        {
            
            l = new Laser(target, direction, getX() + spawnX, getY() + spawnY);
            
            getWorld().addObject(l, getX() + spawnX, getY() + spawnY);//Spawn at tip of the staff
            l.turnTowards(target.getX(), target.getY());//Turn towards the hero
            //Laser starts rotated 50 degrees away from the hero, to give time to evade
            if(direction.equals("clockwise"))
            {
                l.turn(-50);
            }
            else l.turn(50);
            
            l.move(l.getImage().getWidth()/2 - 5);//Moves the laser so it appears to come out of the staff
            
        }
        l.setValues(getX() + spawnX, getY() + spawnY);
        attackCounter --;//ensures the animation doesn't change
        currentHP ++;//Heals during the laser
    }
    
    public void cloneSelf()
    {
       
        attackCounter = 0;
        
        
        if(cycleCounter == 1550){    
            getWorld().addObject(new wizardBoss(target, true), 400, 300);//Add a clone of self
            //Stands still while cloning
            if(getDirection().equals("up")) setImage(moveUp[0]);
            if(getDirection().equals("left")) setImage(moveLeft[0]);
            if(getDirection().equals("down")) setImage(moveDown[0]);
            if(getDirection().equals("right")) setImage(moveRight[0]);
            
        }
        
        
        
    }
    
    public void revive()
    {
        if(!cloned)
        {
            if(!revived)
            {
                if(currentHP < 1)
                {
                    currentHP = maxHP / 2;//Revives at half health
                    revived = true;
                    text.tempText("Invictus: You think you can defeat me that easily?", 20);
                }
            }
        }
    }
    
    public boolean isCloned()
    {
        return cloned;
    }
}
