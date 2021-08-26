import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.Font;
/**
 *Used to draw text on a blank surface
 */
public class TextBox extends Actor
{
    private GreenfootImage textImage;
    private Color black = new Color(0, 0, 0);
    private Color transparent = new Color(0, 0, 0, 0);
    private Font f = new Font("Times New Roman", 20);
    private int timer;
    private boolean temp;
    public TextBox(int length, int height)
    {
        textImage = new GreenfootImage(length, height);//Defines the image for the textbox
        timer = 300;//For temporary text
    }
    
    public void drawText(String text, int size)
    {
        clear();
        f = new Font("Times New Roman", size);//Sets font
        temp = false;
        timer = 300;
        textImage.setColor(black);
        textImage.setFont(f);
        textImage.drawString(text, 30, textImage.getHeight()/2 + size/3);//Draws the text 30 from the left, and centered vertically
    }
    
    public void drawBoldText(String text, int size)
    {
        clear();
        f = new Font("Times New Roman", true, false, size);//Sets font to be bold
        temp = false;
        timer = 300;
        textImage.setColor(black);
        textImage.setFont(f);
        textImage.drawString(text, 30, textImage.getHeight()/2 + size/3);
    }
    
    public void tempText(String text, int size)
    {
        clear();
        temp = true;//Text will dissapear after 300 acts
        timer = 300;
        textImage.setColor(black);
        f = new Font("Times New Roman", size);
        textImage.setFont(f);
        textImage.drawString(text, 30, textImage.getHeight()/2 + size/3);
    }
    
    public void clear()
    {
        textImage.clear();
    }
    
    public void act() 
    {
        setImage(textImage);
        if(temp)
        {
            timer --;
            if(timer == 0) clear();//Text clears if timer reaches 0 (if 300 acts has passed for tempText)
        }
    }    
}
