import greenfoot.*;  // (World, Actor, bluefootImage, bluefoot and MouseInfo)


public class Manabar extends Actor
{
    /**
     * See the healthbar class, all the methods here exist in the healthbar class, except manaBar uses different colors and different values
     */
    private int width;
    private int height;
    private int x;
    private int y;
    
    
    private double currentMana;
    private double maxMana;
    private double percentMana;
    
    private Color blue;
    private Color red;
    
    private Hero player;
    private Enemy e;
    public void act() 
    {
        update();
        
        
        
        
    } 
    
    public Manabar(int width, int height, double maxMana, Hero player)
    {
        this.player = player;
        this.width = width;
        this.height = height;
        this.currentMana = maxMana;
        this.maxMana = maxMana;
        
        red = new Color(255, 0, 0);
        blue = new Color(0, 100, 255);
    }
    
    
    
    public void update()
    {
        currentMana = player.getMana();
        
        GreenfootImage bar = new GreenfootImage(width, height);
        percentMana = currentMana/maxMana;
        bar.setColor(blue);
        bar.fillRect(0, 0, (int) (width*percentMana), height);
        bar.setColor(red);
        bar.fillRect((int) (width*percentMana), 0, width - (int) (width*percentMana), height);
        this.setImage(bar);
    }
    
    public void changeMana(double change)
    {
        currentMana += change;
    }
    
    
}
