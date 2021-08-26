import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class rotatingProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class rotatingProjectile extends Projectile
{
    private int spinCounter;
    private int angle;
    private int totalRotation;
    public rotatingProjectile(String s, int damage, String target, int speed)
    {
        super(s, damage, target, speed);
        angle = 25;
        spinCounter = 0;
    }
    
    public void act() 
    {
        if(angle < 1) angle = 1;//Minimum rotation is 1 degrees
        if(spinCounter % 2 == 0) turn(angle);//Turns the specified rotation
        totalRotation += angle;
        if(totalRotation > 270)//If the total rotations has exceeded 270, the rotation per 2 acts will decrease so the projectile will spiral out
        {
            totalRotation = 0;
            angle = angle / 2;//Every 270 degrees rotated, angle will go from 25, 12, 6, 3, 1
        }
        spinCounter ++;
        super.act();
    }    
}
