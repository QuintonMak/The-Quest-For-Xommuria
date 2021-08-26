import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartWorld extends World
{
    //Objects to create "buttons" similar to the LoseWorld
    private TextBox text;
    private Button easy, normal, hard, back;
    private StillCharacter sponsor;
    private MouseInfo mouse;
    
    private Color gray = new Color(129, 125, 150);
    private Color beige = new Color(245, 245, 220);
    private Color black = new Color(0, 0, 0);
    private Color white = new Color(255, 255, 255);
    private Color brown = new Color(150, 75, 0);
    
    public static double difficultyAdj; //Adjusts the stats of the enemies based on difficulty
    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setPaintOrder(StillCharacter.class, Button.class, TextBox.class, Box.class);
        //Initialize the objects for the buttons
        setBackground("title.png");
        
        text = new TextBox(425, 100);
        easy = new Button(300, 50, "EASY", 30, 40);
        normal = new Button(300, 50, "NORMAL", 30, 63);
        hard = new Button(300, 50, "HARD", 30, 40);
        back = new Button(80, 40, "back.png");
        sponsor = new StillCharacter("sponsor.png");
        
        text.drawBoldText("Please select difficulty", 35);
        
        //Add objects into the world
        addObject(text, 410, 290);
        addObject(easy, 400, 350);
        addObject(normal, 400, 420);
        addObject(hard, 400, 490);
        addObject(back, 60, 35);
        addObject(sponsor, 400, 300);
        
        Hero.setRoomNumber(0);
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
        if(Greenfoot.mouseClicked(null) && sponsor != null)
        {
            removeObject(sponsor);
            sponsor = null;
            setPaintOrder(Button.class, TextBox.class, Box.class, StillCharacter.class);
        }
        else if(Greenfoot.mouseClicked(easy))
        {
            difficultyAdj = 0.8;
            Greenfoot.setWorld(new SceneWorld());
            MyWorld.stopMusic();
        }
        else if(Greenfoot.mouseClicked(normal))
        {
            difficultyAdj = 1;
            Greenfoot.setWorld(new SceneWorld());
            MyWorld.stopMusic();
        }
        else if(Greenfoot.mouseClicked(hard))
        {
            difficultyAdj = 1.2;
            Greenfoot.setWorld(new SceneWorld());
            MyWorld.stopMusic();
        }
        else if(Greenfoot.mouseClicked(back))
        {
            
            Greenfoot.setWorld(new TitleWorld());
        }
    }
}
