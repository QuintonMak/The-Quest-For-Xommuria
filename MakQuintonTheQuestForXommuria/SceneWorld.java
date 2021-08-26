import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SceneWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SceneWorld extends World
{
    //Opening Scene - Scene 1
    private String l0 = "Knight of Light: Greetings! I'm the Knight of Light. (Press Enter to proceed through text)";
    private String l1 = "Knight of Light: You're a descendant of King Xom, aren't you? ";
    //Probably won't use these 2 lines
    private String l2 = "Knight of Light:  ";
    private String l3 = "Knight of Light: Now, are you one of Xom's decendants or not?";
    //Goes to here in the script
    private String l4 = "You: Well, yes, I am, but ...";
    private String l5 = "Knight of Light: I knew it! I finally found the one!";
    private String l6 = "Knight of Light: As a decendant of King Xom, only you can stop the dark wizard Invictus!";
    private String l7 = "Knight of Light: Invictus, with the help of his armies, has obtained the Eon Generator!";
    private String l8 = "Knight of Light: With it, he has the power to destroy Xommuria! You must stop him!";
    private String l9 = "You: Ok, how am I supposed to do that?";
    private String l10 = "Knight of Light: Being a descendant of King Xom, you possess magic abilities to aid you.";
    private String l11 = "Knight of Light: The tactical guide will tell you how to use your skills.";
    private String l12 = "Knight of Light: It will also give you other essential information.";
    private String l13 = "Knight of Light: Click the button on the top right corner to access the tactical guide.";
    private String l14 = "** Note that most game controls won't be available in cutscenes **";
    private String l15 = "Knight of Light: Now, go quickly! You must stop Invictus as soon as possible!";
    private String l16 = "Knight of Light: Find a man named Malbus Drake. He will know how to stop Invictus.";
    
    //Scene 2
    private String l18 = "Invictus (voice): So, this is the one destined to defeat me?";
    private String l19 = "Invictus (voice): I must say, I'm a little dissapointed.";
    private String l20 = "You: Come out and face me yourself!";
    private String l21 = "Invictus (voice): Oh, I'm afraid I have other matters to attend to.";
    private String l22 = "Invictus (voice): However, I didn't want to be rude, so I've sent Gregg to keep you company.";
    private String l23 = "Invictus (voice): Won't you, Gregg?";
    private String l24 = "Gregg: (acknowledgement) Yes, my Lord ...";
    
    //Scene 3
    private String l25 = "Traveller: Impressive. Not many survive an encounter with Invictus. ";
    private String l26 = "Traveller: And, you even managed to defeat one of his lieutenants.";
    private String l27 = "You: Who are you? What do you know about Invictus? ";
    private String l28 = "Malbus: The name's Malbus Drake. The Knight of Light told me you would be coming.";
    private String l28b = "Malbus: As for Invictus, I knew him a long time ago.";
    private String l29 = "Malbus: I know what he seeks. If you are to succeed, you must find it first.";
    private String l30 = "You: Find what first?";
    private String l31 = "Malbus: Invictus needs the Eos Cube to be able to activate the Eon Generator.";
    private String l32 = "Malbus: Now, head east. You must find it before him!";
    private String l33 = "Malbus: Also, a fair warning. The monster hordes will only get worse from here ... ";
    
    
    //Scene 4
    private String l34 = "Malbus: Yes! Past those walls lies the Eos cube!";
    private String l35 = "You: So, how do we destroy this thing before Invictus finds it?";
    private String l36 = "Malbus: Destroy it? No, no the cube is worth more than that ...";
    private String l37 = "Malbus: The cube possesses great knowlege. Powerful knowlege!";
    private String l38 = "You: Enough of this! Who are you really?";
    private String l39 = "Malbus: One who can show you how to wield the cube's power!";
    private String l40 = "You: Using the Eos cube will only bring Invictus closer to victory!";
    private String l41 = "Malbus: How can you not see the bigger picture?";
    private String l42 = "Malbus: Forget about Invictus. With the power of the Eos cube, we would be unstoppable!";
    private String l43 = "You: Then you're no different from Invictus.";
    private String l44 = "Malbus: Very well. If you are not with me ...";
    private String l45 = "Malbus: Then you are my enemy.";
    
    
    //Scene 5
    private String l46 = "Invictus (voice): Many thanks, foolish wizard.";
    private String l47 = "Invictus (voice): Malbus Drake was always an obstacle to my plans.";
    private String l48 = "Invictus (voice): Now that you've convieniently dealt with him ...";
    private String l49 = "Invictus (voice): ... I can obtain the Eos cube with ease.";
    private String l50 = "You: Impossible! How did you vanish the cube?";
    private String l51 = "Invictus (voice): A simple matter of advanced sorcery. A technique your father showed me.";
    private String l52 = "You: That's impossible! King Xom would never join you!";
    private String l53 = "Invictus (voice): On the contrary, Xom has brought me closer to victory than you know ..."; 
    
    //Scene 6
    private String l54 = "Knight of Light: I see that Malbus betrayed you. I guess I should have known.";
    private String l55 = "You: Invictus has taken the Eos cube. We need to find him now!";
    private String l56 = "Knight of Light: Then it's time I told you the whole truth.";
    private String l57 = "Knight of Light: King Xom really did work with Invictus. The two worked together ...";
    private String l58 = "Knight of Light: ... to discover the secrets of the Eon Generator.";
    private String l59 = "Knight of Light: While King Xom wanted to use the Generator to create unlimited energy ...";
    private String l60 = "Knight of Light: Invictus wanted to use the Generator to seize power.";
    private String l61 = "Knight of Light: Invictus struck down King Xom when he refused to join him.";
    private String l62 = "Knight of Light: You are the last chance we have to defeat Invictus!";
    private String l63 = "Knight of Light: I will take you to his castle now ...";
    
    //Scene 7
    private String l64 = "Invictus: So ... You've finally arrived.";
    private String l65 = "You: Turn off the generator! It isn't right to use it like this!";
    private String l66 = "Invictus: The Knight never told you about your father.";
    private String l67 = "You: He told me enough! He told me you betrayed him!";
    private String l68 = "Invictus: No ... We worked with each other, but he betrayed me.";
    private String l69 = "Invictus: When King Xom realized the full power of the Eon Generator ...";
    private String l70 = "Invictus: ... he feared what he had created.";
    private String l71 = "Invictus: Xom turned to the excuse of unlimited energy to cover his mistake.";
    private String l72 = "Invictus: When he realised that I would use the Generator to its full potential ...";
    private String l73 = "You: You struck my father down so he couldn't stop you!";
    private String l74 = "Invictus: Your father killed himself so that the Eon Generator's secrets would die with him";
    private String l75 = "Invictus: King Xom was weak. If only he could see me now ...";
    private String l76 = "You: It doesn't matter. I must stop you, once and for all.";
    
    //Scene 8
    private String l77 = "Invictus: Impossible! I cannot be defeated!";
    private String l78 = "You: It's over Invictus! I will destroy the generator! Don't make me kill you!";
    private String l79 = "Invictus: Foolish boy! You have the chance to step up where your father could not.";
    private String l80 = "Invictus: Otherwise, eventually someone else will take my place...";
    private String l80b = "Invictus: ... and you won't be there to stop him.";
    private String l81 = "You: No. My father was right. The generator is too dangerous. It must be destroyed.";
    private String l82 = "You: Now, surrender or I will be forced to kill you.";
    private String l83 = "Invictus: I will never submit to the likes of you! I shall have my revenge ...";
    private String l84 = "Invictus: Flariteus Intimiotum!";
    
    //Most of this aims to stimulate playability in the game world to some extent
    private int lineNum = 0;
    
    private int playerSpeed = 2;
    private TextBox textBox;
    private TextBox HP;
    private TextBox Mana;
    private Button skip;//Skip button
    private Button pause; //Pause button
    
    private Color gray = new Color(129, 125, 150);
    private Color beige = new Color(245, 245, 220);
    private Color brown = new Color(150, 75, 0);
    private Color black = new Color(0, 0, 0);
    
    private Hero h; //Object for the player
    private MouseInfo mouse; //Object for the mouse
    
    private MyWorld world;
    
    //These arrays contain strings for the characters' speech
    private String[] script1 = {l0, l1, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, ""};
    private String[] script2 = {l18, l19, l20, l21, l22, l23, l24, ""};
    private String[] script3 = {l25, l26, l27, l28, l28b, l29, l30, l31, l32, l33, ""};
    private String[] script4 = {l34, l35, l36, l37, l38, l39, l40, l41, l42, l43, l44, l45, ""};
    private String[] script5 = {l46, l47, l48, l49, l50, l51, l52, l53, ""};
    private String[] script6 = {l54, l55, l56, l57, l58, l59, l60, l61, l62, l63, ""};
    private String[] script7 = {l64, l65, l66, l67, l68, l69, l70, l71, l72, l73, l74, l75, l76, ""};
    private String[] script8 = {l77, l78, l79, l80, l81, l82, l83, l84, ""};
    private String[] script = script1;
    public SceneWorld(MyWorld w) //Takes a MyWorld object as a parameter to save that instance of the game world
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        setActOrder(Hero.class, World.class, TextBox.class);
        setPaintOrder(Healthbar.class, Manabar.class, TextBox.class, Box.class, Button.class, StillCharacter.class, Enemy.class, Hero.class);
        //Initialize world objects
        h = new Hero();
        textBox = new TextBox(800, 100);
        Mana = new TextBox(400, 100);
        HP = new TextBox(400, 100);
        skip = new Button(80, 30, "Skip", 20, 20);
        pause = new Button(50, 50, "pause.png");
        
        addObject(h, 200, 298);
        //Add the UI objects
        addObject(new Box(800, 600, black, 4, 300), 400, 300);
        addObject(new Box(800, 100, beige), 400, 550);
        addObject(new Box(800, 100, gray), 400, 50);
        addObject(textBox, 400, 560);//Displays text for cutscene
        addObject(HP, 190, 28);
        addObject(Mana, 190, 58);
        addObject(skip, 60, 525);
        addObject(pause, 750, 50);
        
        setScene();
        
        MyWorld.stopMusic();
    }
    
    public SceneWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        setActOrder(World.class, TextBox.class);
        setPaintOrder(Healthbar.class, Manabar.class, TextBox.class, Box.class, Button.class, StillCharacter.class, Enemy.class, Hero.class);
        //Initialise objects
        h = new Hero();
        textBox = new TextBox(800, 100);
        Mana = new TextBox(400, 100);
        HP = new TextBox(400, 100);
        skip = new Button(80, 30, "Skip", 20, 20);
        pause = new Button(50, 50, "pause.png");
        //Add the player
        addObject(h, 200, 298);
        
        //Add UI objects
        addObject(new Box(800, 100, beige), 400, 550);
        addObject(new Box(800, 100, gray), 400, 50);
        addObject(new Box(800, 600, black, 4, 400), 400, 300);
        addObject(textBox, 400, 560);
        addObject(HP, 190, 28);
        addObject(Mana, 190, 58);
        addObject(skip, 60, 525);
        addObject(pause, 750, 50);
        
        setScene();
        
        
    }
    
    public void act()
    {
        //A box covering the whole screen creates a "fading in" effect, so the paint order must be switched when this box is present and when it is not
        if(!getObjectsAt(200, 300, Box.class).isEmpty()) setPaintOrder(Box.class, Button.class, TextBox.class, Healthbar.class, Manabar.class, StillCharacter.class, Enemy.class, Hero.class, Projectile.class, Fire.class);
        else setPaintOrder(Button.class, TextBox.class, Healthbar.class, Manabar.class, Box.class, StillCharacter.class, Enemy.class, Hero.class, Projectile.class, Fire.class);
        
        checkKeys();
        checkMouse();
        printStats();
        talk();
        
    }
    
    public void checkKeys() //The player gets limited, but still some, keyboard controls in the cutscene world
    {
        if(Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("a"))
        {
            if(Greenfoot.isKeyDown("s")) h.walkDown(playerSpeed);
            if(Greenfoot.isKeyDown("d")) h.walkRight(playerSpeed);
            if(Greenfoot.isKeyDown("a")) h.walkLeft(playerSpeed);
            if(Greenfoot.isKeyDown("w")) h.walkUp(playerSpeed);
            
        }
        else
        {
            if(h.getDirection().equals("up")) h.setImage("heroSprite0.png");
            if(h.getDirection().equals("left")) h.setImage("heroSprite9.png");
            if(h.getDirection().equals("down")) h.setImage("heroSprite18.png");
            if(h.getDirection().equals("right")) h.setImage("heroSprite27.png");
        }
    }
    
    public void checkMouse()
    {
        mouse = Greenfoot.getMouseInfo();
        //Does an action depending on where the mouse has clicked
        if(Greenfoot.mouseClicked(null) && mouse != null)
        {
            //If pause button is clicked, go to the tactical guide
            if(Greenfoot.mouseClicked(pause))
            {
                Greenfoot.setWorld(new MenuWorld(this, pause));
            }
            //If skip button is clicked, skip text
            if(Greenfoot.mouseClicked(skip))
            {
                lineNum = script.length - 1;
            }
        }

    }
    
    public void talk()
    {
        String key = Greenfoot.getKey();
        if(key != null)
        {
            if (key == "enter") //Player presses enter to proceed through text
            {
                if(getObjectsAt(200, 300, Box.class).isEmpty()) lineNum++; //lineNum increments to go to the next index of the script array 
                //Only proceed through text when the "fade in" box is gone, so no text is skipped
            }
            
        }
        
        if(script[lineNum].equals(""))//If the line's text is "", that means the end of the script has been reached
            {
                Hero.setRoomNumber(Hero.getRoomNumber() + 1);
                MyWorld.setEnemiesAdded(false);;
                Enemy.setNumEnemies(0);
                
                if(world != null) Greenfoot.setWorld(world); //Goes back to the gameworld, and proceeds to the next stage of the game
                else Greenfoot.setWorld(new MyWorld());
            }
        
        textBox.clear();
        textBox.drawText(script[lineNum], 20); //Prints the appropriate line of the script
    }
    
    public void setScene() //Sets which script to use, and the objects to add, based on the stage of the game
    {
        if(Hero.getRoomNumber() == 0)
        {
            script = script1;
            addObject(new StillCharacter("knight.png"), 600, 300);
            setBackground("grassBackground.png");
            addObject(new StillCharacter("checkPoint.png"), 400, 300);
        }
        if(Hero.getRoomNumber() == 5)
        {
            script = script2;
            addObject(new StillCharacter("orcBoss9.png"), 600, 300);
            setBackground("grassBackground.png");
        }
        if(Hero.getRoomNumber() == 8)
        {
            script = script3;
            addObject(new StillCharacter("malbus9.png"), 600, 300);
            setBackground("grassBackground.png");
        }
        if(Hero.getRoomNumber() == 12)
        {
            script = script4;
            addObject(new StillCharacter("malbus9.png"), 600, 300);
            addObject(new StillCharacter("wall.png"), 780, 175);
            addObject(new StillCharacter("wall.png"), 780, 425);
            setBackground("grassBackground.png");
        }
        if(Hero.getRoomNumber() == 14)
        {
            script = script5;
            setBackground("templeBackground.png");
            addObject(new StillCharacter("wall.png"), 20, 175);
            addObject(new StillCharacter("wall.png"), 20, 425);
            addObject(new StillCharacter("wall.png"), 780, 175);
            addObject(new StillCharacter("wall.png"), 780, 425);
            addObject(new StillCharacter("checkPoint.png"), 400, 300);
        }
        if(Hero.getRoomNumber() == 17)
        {
            script = script6;
            addObject(new StillCharacter("knight.png"), 600, 300);
            setBackground("grassBackground.png");
            addObject(new StillCharacter("checkPoint.png"), 400, 300);
        }
        if(Hero.getRoomNumber() == 23)
        {
            script = script7;
            setBackground("castleBackground.png");
            addObject(new StillCharacter("wizard9.png"), 400, 300);
            
        }
        if(Hero.getRoomNumber() == 24)
        {
            script = script8;
            setBackground("castleBackground.png");
            addObject(new StillCharacter("wizardDeath2.png"), 400, 300);

        }
    }
    
    public void printStats()//Does not show the stats in the cutscene world, only shows healthbar stuff for consistency with the game world
    {
        HP.clear();
        Mana.clear();
        HP.drawText("HP: --- /250", 20);
        Mana.drawText("Mana: --- /200", 20);
    }
}
