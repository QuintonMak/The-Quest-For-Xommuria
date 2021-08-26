import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class deathRing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class deathRing extends Fire
{
    private wizardBoss wizard;//Spawns on the wizard boss
    public deathRing(wizardBoss w)
    {
        super();
        wizard = w;
        setImage("circle.png");
        image = this.getImage();
        
    }
    
    public void act() 
    {
        timer ++;
        dealDamage();
        if(!getObjectsInRange(image.getWidth()/2, Hero.class).isEmpty())//Same as fire class, but for mana instead of health
        {
            h = (Hero) getOneIntersectingObject(Hero.class);
            if(timer % 15 == 0){
                if(h != null) h.changeMana(-5);
            }
        }
        setLocation(wizard.getX(), wizard.getY() + 15);
        image.scale(316, 316);//Scale the image
        image.setTransparency(200);//Makes the image translucent
        setImage(image);
        if(timer % 3 == 0) turn(1);//Makes the deathring look like it's spinning around
    }    
}
