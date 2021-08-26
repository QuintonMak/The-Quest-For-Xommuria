import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class is for use in wizardBoss
 */
public class Laser extends Actor
{
    private Hero target;
    private String rotationDirection, direction;
    private GreenfootImage image;
    private int counter, x, y, otherX, otherY, damage;
    public Laser(Hero target, String direction, int x, int y)
    {
        this.target = target;
        this.rotationDirection = direction;
        counter = 0;
        damage = (int) (-20 * StartWorld.difficultyAdj);
        this.x = x;
        this.y = y;
        setImage("laser.png");
        image = this.getImage();
        //turnTowards(target.getX(), target.getY());
        
        //move(450);
    }
    
    public void setValues(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void act() 
    {
        
        image.scale(image.getWidth(), 40);
        
        
        setImage(image);
        setLocation(x, y);//Go back to tip of staff (spawning location)
        if(getX() == x && getY() == y){//Failsafe since there were some bugs where it was turning in the wrong place
            if(counter % 3 == 0){
                if(rotationDirection.equals("clockwise")) turn(2);//Turns 2 degrees every 3 acts, rotating 100 degrees in total
                else turn(-2);
            }
        }
        
        move(image.getWidth()/2 - 5);//Moves the laser so it appears to come out of the staff
        counter ++;
        //Damages the hero if he is touching the laser
        if(getOneIntersectingObject(Hero.class) != null)
        {
            target = (Hero) getOneIntersectingObject(Hero.class);
            if(counter % 4 == 0) target.changeHP(damage);
        }
        //Removes itself if the wizardBoss is dead or if 150 acts has passes
        if(((MyWorld)getWorld()).getObjects(Enemy.class).isEmpty()) getWorld().removeObject(this);
        else if(counter >= 150) getWorld().removeObject(this);
        
    }    
}
