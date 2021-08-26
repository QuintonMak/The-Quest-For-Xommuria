import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WinWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinWorld extends World
{
    private boolean musicPlaying;    
    
    
    private Color gray = new Color(129, 125, 150);
    private Color beige = new Color(245, 245, 220);
    private Color black = new Color(0, 0, 0);
    private Color white = new Color(255, 255, 255);
    private Color red = new Color(255, 0, 0);
    public WinWorld()
    {    
        super(800, 600, 1);
        
        setActOrder(Enemy.class, Hero.class, World.class, TextBox.class,  Healthbar.class, Laser.class);
        setBackground("credits.png");
        musicPlaying = false;
        //Add the user interface objects
        addObject(new Box(800, 600, black, 2, 400), 400, 300);
        MyWorld.stopMusic();
        //Hero.checkPoint = 1;
    }
    
    public void act()
    {
        if(getObjectsAt(10, 10, Box.class).isEmpty() && !musicPlaying)
        {
            MyWorld.playMusic("title.mp3");//plays music at the end of the game
            musicPlaying = true;
        }
    }
}
