import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StillCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StillCharacter extends Box
{
    /**This class makes an object that only aims to display an image
    //Since I originally intended to only use it for sprites, it is called StillCharacter even though 
    //it would be more appropriately called StillImage
    */
    private int room;
    private String s = "";
    private Color black = new Color(0, 0, 0);
    private Color transparent = new Color(0, 0, 0, 0);
    public void act() 
    {
        setImage(image);
        if(Hero.getRoomNumber() == room) removeSelf();//Removes itself if the player has proceeded to the next room
        if(Hero.getRoomNumber() != room) getWorld().removeObject(this);
        
    }  
    /**
    public StillCharacter(String s, int room)//Makes the object "bound to a room"
    {
        super(1, 1, new Color(0, 0, 0, 0));
        image = new GreenfootImage(s);
        setImage(image);
        this.s = s;
        this.room = room;
    }
    */
    public StillCharacter(String s)//s is the image that shows
    {
        super(1, 1, new Color(0, 0, 0, 0));
        image = new GreenfootImage(s);
        setImage(image);
        this.s = s;
        room = Hero.getRoomNumber();//The object is "bound" to the current room
    }
    
    public void removeSelf()
    {
        if(s.equals("cube.png"))//If the player goes towards the cube, it will proceed to the next world
        {
            if(!getObjectsInRange(image.getWidth(), Hero.class).isEmpty())
            {
                
                getWorld().addObject(new Box(800, 600, black, 5, 255), 400, 300);
                Greenfoot.setWorld(new SceneWorld((MyWorld) getWorld()));//Uses typecasting to get the world as a parameter
                
            }
        }
    }
}
