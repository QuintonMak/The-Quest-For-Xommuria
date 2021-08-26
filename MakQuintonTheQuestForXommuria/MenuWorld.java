import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuWorld extends World
{
    private MyWorld world;
    private SceneWorld sceneWorld;
    private Button pause;
    //Objects to create "buttons" similar to the LoseWorld
    
    private Button enemyInfo, bossInfo, instructions, back, returnButton;
    private StillCharacter instructionPage, enemyPage, bossesPage, spoilerCover;
    private TextBox text, difficulty;
    private String difficultyStr;
    private MouseInfo mouse;
    
    private Color gray = new Color(129, 125, 150);
    private Color beige = new Color(245, 245, 220);
    private Color black = new Color(0, 0, 0);
    private Color white = new Color(255, 255, 255);
    private Color brown = new Color(150, 75, 0);
    
    public static double difficultyAdj; //Adjusts the stats of the enemies based on difficulty
    public MenuWorld(MyWorld world, Button pause)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false);
        setPaintOrder(Button.class, TextBox.class, Box.class);
        //Get info from the instance of the game world
        this.world = world;
        this.pause = pause;
        
        setBackground("menuBackground.png");
        //Initialize the objects for the buttons
        
        instructions = new Button(400, 50, "Instructions", 30, 72);
        enemyInfo = new Button(400, 50, "Enemies", 30, 50);
        bossInfo = new Button(400, 50, "Bosses", 30, 40);
        returnButton = new Button(400, 50, "Return to game", 30, 90);
        back = new Button(80, 40, "back.png");
        //Create the images for the menu's pages
        instructionPage = new StillCharacter("instructions.png");
        enemyPage = new StillCharacter("enemyGuide.png");
        bossesPage = new StillCharacter("bossGuide.png");
        spoilerCover = new StillCharacter("menuBackground.png");
        //Initialize and draw text onto text objects
        text = new TextBox(450, 100);
        difficulty = new TextBox(450, 100);
        text.drawBoldText("TACTICAL GUIDE", 45);
        if(StartWorld.difficultyAdj == 0.8) difficultyStr = "Easy";
        else if(StartWorld.difficultyAdj == 1) difficultyStr = "Normal";
        else difficultyStr = "Hard";
        difficulty.drawText("Difficulty: " + difficultyStr, 25);
        //Add objects into the world
        addObject(text, 390, 120);
        addObject(difficulty, 420, 170);
        addObject(instructions, 400, 280);
        addObject(enemyInfo, 400, 350);
        addObject(bossInfo, 400, 420);
        addObject(returnButton, 400, 490);
    }
    
    public MenuWorld(SceneWorld world, Button pause)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false);
        setPaintOrder(Button.class, TextBox.class, Box.class);
        //Get info from the instance of the game world
        this.sceneWorld = world;
        this.pause = pause;
        
        setBackground("menuBackground.png");
        //Initialize the objects for the buttons
        
        instructions = new Button(400, 50, "Instructions", 30, 72);
        enemyInfo = new Button(400, 50, "Enemies", 30, 50);
        bossInfo = new Button(400, 50, "Bosses", 30, 40);
        returnButton = new Button(400, 50, "Return to game", 30, 90);
        back = new Button(80, 40, "back.png");

        instructionPage = new StillCharacter("instructions.png");
        enemyPage = new StillCharacter("enemyGuide.png");
        bossesPage = new StillCharacter("bossGuide.png");
        spoilerCover = new StillCharacter("menuBackground.png");
        
        text = new TextBox(450, 100);
        difficulty = new TextBox(450, 100);
        text.drawBoldText("TACTICAL GUIDE", 45);
        if(StartWorld.difficultyAdj == 0.8) difficultyStr = "Easy";
        else if(StartWorld.difficultyAdj == 1) difficultyStr = "Normal";
        else difficultyStr = "Hard";
        difficulty.drawText("Difficulty: " + difficultyStr, 25);
        //Add objects into the world
        addObject(text, 390, 120);
        addObject(difficulty, 420, 170);
        addObject(instructions, 400, 280);
        addObject(enemyInfo, 400, 350);
        addObject(bossInfo, 400, 420);
        addObject(returnButton, 400, 490);
    }
    
    public void act()
    {
        checkMouse();
    }
    
    public void checkMouse()
    {
        mouse = Greenfoot.getMouseInfo();
        //Switch to the proper world according to where the user clicks
        //When switching to another page, it removes all the buttons and adds 
        //the corresponding page and back button
        //When the back button is clicked, it will re-add all the objects
        //and reset the menu to its original state
        if(Greenfoot.mouseClicked(instructions))
        {
            addObject(instructionPage, 400, 300);
            addObject(back, 60, 35);
            back.resetButton();
            removeObject(text);
            removeObject(difficulty);
            removeObject(instructions);
            removeObject(bossInfo);
            removeObject(enemyInfo);
            removeObject(returnButton);
        }
        else if(Greenfoot.mouseClicked(enemyInfo))
        {
            addObject(enemyPage, 400, 300);
            addObject(back, 60, 35);
            if(Hero.getRoomNumber() < 19 && Hero.getCheckPoint() < 18) addObject(spoilerCover, 400, 750);
            back.resetButton();
            removeObject(text);
            removeObject(difficulty);
            removeObject(instructions);
            removeObject(bossInfo);
            removeObject(enemyInfo);
            removeObject(returnButton);
        }
        else if(Greenfoot.mouseClicked(bossInfo))
        {
            addObject(bossesPage, 400, 300);
            addObject(back, 60, 35);
            if(Hero.getRoomNumber() < 5 && Hero.getCheckPoint() < 4) addObject(spoilerCover, 400, 473);
            else if(Hero.getRoomNumber() < 12 && Hero.getCheckPoint() < 11) addObject(spoilerCover, 400, 623);
            else if(Hero.getRoomNumber() < 23 && Hero.getCheckPoint() < 22) addObject(spoilerCover, 400, 773);
            back.resetButton();
            removeObject(text);
            removeObject(difficulty);
            removeObject(instructions);
            removeObject(bossInfo);
            removeObject(enemyInfo);
            removeObject(returnButton);
        }
        else if(Greenfoot.mouseClicked(returnButton))
        {
            pause.resetButton();
            if(world != null) Greenfoot.setWorld(world);
            else Greenfoot.setWorld(sceneWorld);
        }
        else if(back != null)
        {
            if(Greenfoot.mouseClicked(back))
            {
                if(instructionPage != null) removeObject(instructionPage);
                if(enemyPage != null) removeObject(enemyPage);
                if(bossesPage != null) removeObject(bossesPage);
                if(spoilerCover != null) removeObject(spoilerCover);
                removeObject(back);
                addObject(text, 400, 120);
                addObject(difficulty, 420, 170);
                addObject(instructions, 400, 280);
                addObject(enemyInfo, 400, 350);
                addObject(bossInfo, 400, 420);
                addObject(returnButton, 400, 490);
                instructions.resetButton();
                enemyInfo.resetButton();
                bossInfo.resetButton();
                returnButton.resetButton();
            }
        }   
        
    }
}
