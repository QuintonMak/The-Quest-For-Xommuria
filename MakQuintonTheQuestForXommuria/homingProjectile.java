import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Sspawned by the wizardBoss
 */
public class homingProjectile extends Projectile
{
    private int homingCounter;
    
    public homingProjectile(int damage, Hero h)
    {
        super("rapid.png", damage, "Hero", 7);
        homingCounter = 0;
        this.h = h;
    }
    
    public void act() //Turns towards the hero for 12 acts, "homing in" for a brief moment
    {
        if(homingCounter < 12) turnTowards(h.getX(), h.getY());
        homingCounter ++;
        super.act();
    }    
}
