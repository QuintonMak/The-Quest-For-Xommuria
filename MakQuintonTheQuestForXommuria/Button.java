import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Box
{
    private GreenfootImage textImage;
    
    private Color black = new Color(0, 0, 0);
    private Color transparent = new Color(0, 0, 0, 0);
    private Color gray = new Color(129, 125, 150);
    private Color green = new Color(0, 255, 0);
    
    
    private Font f;
    private String text;
    public Button(int width, int height, String text, int textSize, int xAdj)//Makes a button with text inside
    {
        super(width, height, new Color(129, 125, 150));//Makes a box as the background for the button
        this.text = text;
        textImage = new GreenfootImage(width, height);
        f = new Font("Times New Roman", textSize);
        textImage.setFont(f);
        textImage.drawString(text, width/2 - xAdj, height/2 + textSize/3);//xAdj is used to center the text for the button
        //As well, textSize/3 will approximately center the y component of the text since that is always proportional to textsize
        image.drawImage(textImage, 0, 0);
    }
    
    public Button(int width, int height, String fileName)//Makes a button with an image inside
    {
        super(width, height, new Color(129, 125, 150));
        this.text = fileName;
        textImage = new GreenfootImage(fileName);
        image.drawImage(textImage, 0, 0);
    }
    
    public void resetButton()//Resets the button color
    {
        image.setColor(gray);
    }
    
    public void act() 
    {
        
        if(Greenfoot.mousePressed(this)) image.setColor(green);//If the button is pressed, set color to green
        
        if(Greenfoot.mouseDragEnded(this)) //If the mouse is dragged off the button and let go, set color back to gray
        {
            if(!Greenfoot.mouseClicked(this)) image.setColor(gray);
        }
        
        image.fill();
        image.drawImage(textImage, 0, 0);
        setImage(image);
    }    
}
