import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The music plays before the run button is clicked, for some reason, so this
 * world is to solve that problem, as is goes to the title world only after run
 * is clicked
 */
public class CoverWorld extends World
{
    private Button instructions, start;
    private MouseInfo mouse;
    
    private Color gray = new Color(129, 125, 150);
    private Color beige = new Color(245, 245, 220);
    private Color black = new Color(0, 0, 0);
    private Color white = new Color(255, 255, 255);
    private Color brown = new Color(150, 75, 0);
    /**
     * Constructor for objects of class CoverWorld.
     * 
     */
    public CoverWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        setBackground("title.png");
        instructions = new Button(400, 50, "Instructions", 30, 72);
        start = new Button(400, 50, "Start", 30, 30);
        addObject(instructions, 400, 350);
        addObject(start, 400, 420);
    }
    
    public void act()
    {
        Greenfoot.setWorld(new TitleWorld()); //Goes to the actual world, which plays the music
    }
}
