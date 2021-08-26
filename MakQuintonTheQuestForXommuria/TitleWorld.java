import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleWorld extends World
{

    //Objects to create "buttons" similar to the LoseWorld
    
    private Button instructions, start, back;
    private StillCharacter instructionPage;
    private MouseInfo mouse;
    
    private Color gray = new Color(129, 125, 150);
    private Color beige = new Color(245, 245, 220);
    private Color black = new Color(0, 0, 0);
    private Color white = new Color(255, 255, 255);
    private Color brown = new Color(150, 75, 0);
    
    public static double difficultyAdj; //Adjusts the stats of the enemies based on difficulty
    public TitleWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setPaintOrder(Button.class, TextBox.class, Box.class);
        setBackground("title.png");
        //Initialize the objects for the buttons
        
        instructions = new Button(400, 50, "Instructions", 30, 72);
        start = new Button(400, 50, "Start", 30, 30);
        back = new Button(80, 40, "back.png");
        
        instructionPage = new StillCharacter("instructions.png");
        
        //Add objects into the world
        //addObject(text, 400, 300);
        addObject(instructions, 400, 350);
        addObject(start, 400, 420);
        
        if(!MyWorld.musicPlaying()) MyWorld.playMusic("title.mp3");
    }
    
    public void act()
    {
         
        checkMouse();
    }
    
    public void checkMouse()
    {
        mouse = Greenfoot.getMouseInfo();
        //Switch to the proper world according to where the user clicks 
        if(Greenfoot.mouseClicked(instructions))
        {
            addObject(instructionPage, 400, 300);
            addObject(back, 60, 35);
            back.resetButton();
            removeObject(instructions);
            removeObject(start);
        }
        else if(Greenfoot.mouseClicked(start))
        {
            
            Greenfoot.setWorld(new StartWorld());
        }
        else if(back != null)
        {
            if(Greenfoot.mouseClicked(back))
            {
                removeObject(instructionPage);
                removeObject(back);
                addObject(instructions, 400, 350);
                addObject(start, 400, 420);
                instructions.resetButton();
            }
        }   
        //MyWorld.playMusic("title.mp3");
    }
}
