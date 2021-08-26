import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The generator is an "enemy" that is supposed to be destroyed for the end of the game
 * As such, it doesn't do much except stay in place and explode
 */

public class generator extends Enemy
{
    
    public generator(Hero h)
    {
        super(50, h, 0);
        setImage("generator.png");
        deathAnimation = loadArray("explosion", ".png", 0, 4);//Explosion animation
    }
    
    public void act() 
    {
        checkIfDead();
    }    
}
