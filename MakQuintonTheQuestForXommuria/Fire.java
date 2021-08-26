import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fire extends Actor
{
    protected int timer;//Determines whether to deal damage to the player
    private int transparencyTimer;//Determines the fading out effect
    protected Hero h;
    protected boolean heroInRange;
    protected GreenfootImage image;
    public void act() 
    {
        transparencyTimer --;//Decreases transparency to fade out
        timer ++;
        dealDamage();
        if(transparencyTimer < 1) getWorld().removeObject(this);//Removes the object once it fades out
        image.setTransparency(transparencyTimer);
    } 
    
    public Fire()
    {
        setImage("Fire.png");
        transparencyTimer = 255;
        timer = 0;
        heroInRange = false;
        image = this.getImage();
    }
    
    public void dealDamage()
    {
        if(!getObjectsInRange(image.getWidth()/2, Hero.class).isEmpty())//If the hero is within the objects radius, deal damage
        {
            if(!heroInRange)
            {
                heroInRange = true;//When the hero first touches the fire, the timer is set to 15 to damage the hero once immediately
                timer = 15;
            }
            h = (Hero) getOneIntersectingObject(Hero.class);
            if(timer % 15 == 0){
                if(h != null) h.changeHP(-14);//Damages the hero once every 15 acts
            }
        }
        else heroInRange = false;
    }
}
