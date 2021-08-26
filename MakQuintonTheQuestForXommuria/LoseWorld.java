import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoseWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoseWorld extends World
{
    //Objects form the game world to retain a certain instance of the game
    private MyWorld world;
    private Hero h;
    //Mouseinfo object to detect mouse clicks
    private MouseInfo mouse;
    //Button objects to create "buttons"
    private Button respawn, restart;
    private TextBox respawnInfo, restartInfo;
    //Colors
    private Color gray = new Color(129, 125, 150);
    private Color beige = new Color(245, 245, 220);
    private Color black = new Color(0, 0, 0);
    private Color white = new Color(255, 255, 255);
    private Color brown = new Color(150, 75, 0);
    
    
    public LoseWorld(MyWorld world, Hero h)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setPaintOrder(Box.class, Button.class, TextBox.class );
        setBackground("menuBackground.png");
        this.world = world;
        this.h = h;
        
        
        respawn = new Button(200, 75, "RESPAWN", 30, 70);
        restart = new Button(200, 75, "RESTART", 30, 70);
        respawnInfo = new TextBox(400, 200);
        restartInfo = new TextBox(400, 200);
        //Draw text with a method from the TextBox class
        respawnInfo.drawText("Continue playing from \nthe most recent checkpoint", 20);
        restartInfo.drawText("Restart the game from \nthe beginning", 20);
        
        //Add objects into the world
        addObject(respawn, 250, 300);
        addObject(restart, 550, 300);
        
        addObject(respawnInfo, 320, 350);
        addObject(restartInfo, 630, 350);
        
        
        addObject(new Box(800, 600, black, 3, 450), 400, 300);
    }
    
    public void act()
    {
        checkMouse();
        if(getObjectsAt(10, 10, Box.class).isEmpty()) setPaintOrder( Button.class, TextBox.class, Box.class);
    }
    
    public void checkMouse()
    {
        mouse = Greenfoot.getMouseInfo();
        //Switch to the proper world according to where the user clicks
        if(Greenfoot.mouseClicked(respawn))
        {
            h.respawn();
            Greenfoot.setWorld(world);
        }
        else if(Greenfoot.mouseClicked(restart))
        {
            
            Greenfoot.setWorld(new StartWorld());
        }
    }
}
