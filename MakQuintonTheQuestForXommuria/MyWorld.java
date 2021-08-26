import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Instructions:
 * The goal of the game is to defeat the dark wizard Invictus. The player progresses through the story by fighting various enemies/bosses 
 * until a final encounter with Invictus. 
 * Use the W A S D keys to move up, left, down, right
 * Left click on a point to shoot the basic attack in that direction
 * Right click on a point to shoot the special attack in that direction
 * Press space to recover HP (green bar)
 * Healing and special attack costs mana to use (blue bar)
 * Mana will fully recharge when entering a new room
 * The player can respawn at a checkpoint location if they have died (red flag)
 * 
 * Known Bugs:
 * Moving diagonally is faster than moving vertically/horizontally, due to setLocation() only accepting integer values.
 * (not really a bug, more of an unintended feature)
 * There may be random flickering on the screen, I do not know if this is because of a flaw in an older version of greenfoot or my code.
 * Laser has spawned in the wrong place before
 * 
 * Credits: 
 * Sprites: 
 * https://sanderfrenken.github.io/Universal-LPC-Spritesheet-Character-Generator/
 * Music:
 * https://www.youtube.com/playlist?list=PLTuWHppokt9W2iJaxq2P_zWFHXLj2XoCh (All the clips are in the playlist)
 * Graphics:
 * (I edited some of the images)
 * (I might not have found at least one of the pictures, couldn't find the link)
 * https://www.pinclipart.com/picdir/middle/30-301605_black-arrow-clip-art-archery-arrow-clip-art.png
 * https://pnghunter.com/png/fire-26/
 * http://www.textures4photoshop.com/tex/isolated-objects/ring-of-fire-png-image.aspx
 * https://i.ya-webdesign.com/images/fireball-clipart.png
 * https://vignette.wikia.nocookie.net/fallout/images/a/ac/Tesla_Coil.png/revision/latest/scale-to-width-down/340?cb=20191219161334
 * https://opengameart.org/sites/default/files/grass_14.png
 * https://www.kindpng.com/imgv/hiTxmRJ_big-game-knife-hd-png-download/
 * https://www.pngkit.com/png/detail/973-9730737_castle-wall-textures-castle-walls-png.png
 * https://www.pinclipart.com/picdir/middle/18-187351_transparent-laser-clipart-transparent-transparent-background-laser-beam.png
 * https://www.trzcacak.rs/file/max/67/671881_force-lightning-png.png
 * https://vignette.wikia.nocookie.net/elderscrolls/images/5/5f/MAGINVShockSpellArt.png/revision/latest?cb=20120221192951
 * https://w0.pngwave.com/png/829/548/magic-book-of-shadows-spell-others-png-clip-art.png
 * https://mpng.pngfly.com/20180309/pfw/kisspng-brick-wall-black-simple-brick-wall-5aa25c69ea66c0.5263716015205899299601.jpg
 * http://pluspng.com/img-png/png-sphere-sphere-with-blender-png-393.png
 * http://www.marlborofishandgame.com/images/7/78/Flag-icon-512.png
 * https://tech4gamers.com/wp-content/uploads/2019/09/Raid-Shadow-Legends.jpg
 * https://www.mmogames.com/wp-content/uploads/2019/09/Raid-Shadow-Legends-Spider-Boss.jpg
 * https://i.redd.it/bxnhnzu7cvi21.jpg
 * Code:
 * Healthbar/Manabar and Button code were based off Mr.Cohen's code
 * */

public class MyWorld extends World
{
    
        
    private Hero h; //Object for the player
    private MouseInfo mouse; 
    
    private TextBox textBox;//Shows game related text
    private Button pause; //Pause button
    private Box top, bottom, coverBox;//Makes the top and bottom borders
    //Displays the players stats
    private TextBox HP;
    private TextBox Mana;
    
    private int playerSpeed = 2; //Player Movement Speed
    
    private static boolean enemiesAdded = false;//If this is false, an action will be performed according to the room number
    
    private static GreenfootSound music;
    
    private Color gray = new Color(129, 125, 150);
    private Color beige = new Color(245, 245, 220);
    private Color black = new Color(0, 0, 0);
    private Color white = new Color(255, 255, 255);
    private Color brown = new Color(150, 75, 0);
    private Color red = new Color(255, 0, 0);
    public MyWorld()
    {    
        super(800, 600, 1, false);
        setPaintOrder(Box.class, Button.class, TextBox.class, Healthbar.class, Manabar.class, StillCharacter.class, Enemy.class, Hero.class, Projectile.class, Fire.class);
        setActOrder(Enemy.class, World.class, TextBox.class,  Healthbar.class, Laser.class);
        //Add the player sprite and initialize the player related objects
        h = new Hero();
        textBox = new TextBox(800, 100);
        HP = new TextBox(400, 100);
        Mana = new TextBox(400, 100);
        
        //Initialize the UI objects
        top = new Box(800, 100, gray);
        bottom = new Box(800, 100, beige);
        coverBox = new Box(800, 600, black);//This is so there is no abrupt switching of the background
        pause = new Button(50, 50, "pause.png");
        //Add the user interface objects and the player objects
        addObject(h, 200, 300);
        addObject(top, 400, 50);
        addObject(bottom, 400, 550);
        addObject(coverBox, 400, 300);
        addObject(textBox, 400, 550);
        addObject(HP, 190, 28);
        addObject(Mana, 190, 58);
        addObject(pause, 750, 50);
        
        //Hero.getCheckPoint() = 1;
    }
    
    public void act()
    {
        printStats();
        //If the player is not dead (hp is less than 0), they are able to use keyboard controls and mouse controls
        if(h.getHP() > 0) {
            checkKeys();//Keyboard controls
            checkMouse();//Mouse controls
            
        }
        checkRoom();
        if(coverBox != null) removeObject(coverBox);//Since the background sets in the act method, there is a cover box to not show the default background
        //Only the transition box goes on top of the whole screen, the rest of the boxes should be painted under some objects
        //So, the paint order is set based on if there is a transition box on the screen
        if(!getObjectsAt(200, 200, Box.class).isEmpty()) setPaintOrder(Box.class, Button.class, TextBox.class, Healthbar.class, Manabar.class, StillCharacter.class, Enemy.class, Hero.class, Projectile.class, Fire.class);
        else setPaintOrder(Button.class, TextBox.class, Healthbar.class, Manabar.class, Enemy.class, Hero.class, Box.class, StillCharacter.class, Laser.class, Projectile.class, Fire.class);
    }
    
    public static void setEnemiesAdded(boolean a)//Setter method for enemies added
    {
        enemiesAdded = a;
    }
    
    public void checkKeys()
    {
        //Do an action depending on the key clicked
        if("space".equals(Greenfoot.getKey())) 
        {
            if(h.getMana() > 40)
            {
                h.changeMana(-40);
                h.changeHP(100);
                
            }
        }
        if(Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("a"))
        {
            //Move with WASD
            if(Greenfoot.isKeyDown("s")) h.walkDown(playerSpeed);  
            if(Greenfoot.isKeyDown("d")) h.walkRight(playerSpeed);
            if(Greenfoot.isKeyDown("a")) h.walkLeft(playerSpeed);
            if(Greenfoot.isKeyDown("w")) h.walkUp(playerSpeed);
        }
        else
        {
            //If the player is not moving, set the player sprite to an idle image
            if(h.getDirection().equals("up")) h.setImage("heroSprite0.png");
            if(h.getDirection().equals("left")) h.setImage("heroSprite9.png");
            if(h.getDirection().equals("down")) h.setImage("heroSprite18.png");
            if(h.getDirection().equals("right")) h.setImage("heroSprite27.png");
        }
    }
    
    public void checkMouse()
    {
        mouse = Greenfoot.getMouseInfo();
        //Shoots a projectile where the mouse clicked
        if(Greenfoot.mouseClicked(null) && mouse != null)
        {
            if(!Greenfoot.mouseClicked(top) && !Greenfoot.mouseClicked(bottom) && !Greenfoot.mouseClicked(pause))
            {
                h.zeroCounter();
                h.attack(mouse);
            }
            else if(Greenfoot.mouseClicked(pause))
            {
                Greenfoot.setWorld(new MenuWorld(this, pause));
            }
        }

    }
    
    public String mouseDirection()//returns where the mouse is relative to the player
    {
       if(mouse != null){
            int vertical = mouse.getY() - h.getY();
            int horizontal = mouse.getX() - h.getX();
            if(Math.abs(vertical) > Math.abs(horizontal))
            {
               if(vertical < 0) return "up";
               if(vertical > 0) return "down";
            }
            else
            {
                if(horizontal > 0) return "right";
                if(horizontal < 0) return "left";
            }
        }
       return "no";
    }
    
    public void randomSpawn(int spawnCount)//Spawns a number of different types of enemies in random locations
    {
        int randX;
        int randY;
        for(int i = 0; i < spawnCount; i++)
        {
            randX = Greenfoot.getRandomNumber(180) + 550;
            randY = Greenfoot.getRandomNumber(270) + 120;
            
            if(Hero.getRoomNumber() >= 19)
            {
                if(i % 3 == 0) addObject(new guard(h), randX, randY);
                else if(i % 2 == 0) addObject(new axeBoi(h, false), randX, randY);
                else  addObject(new arrowBoi(h), randX, randY);
            }
            else if(i % 2 == 0) addObject(new axeBoi(h, false), randX, randY);
            else  addObject(new arrowBoi(h), randX, randY);
        }
    }
    
    public void nextRoom()//Makes it appear the player is proceeding to the "next stage"
    {
        if(Hero.getRoomNumber() != 14){
            Enemy.setNumEnemies(0);
            h.setLocation(50, h.getY());
            enemiesAdded = false;
            h.changeMana(200);
            if(Hero.getRoomNumber() != 17) addObject(new Box(800, 600, black, 5, 300), 400, 300);
            Hero.setRoomNumber(Hero.getRoomNumber() + 1);
            
        }
            
        
    }
    
    public void checkRoom()//Does an action according to what stage of the game the player is at
    {
        if(!enemiesAdded) //If proceeding to a new room, enemiesAdded will be false. It is turned true at the end of this method.
        //This is to ensure that the instructions for the room only happen once, and not every act.
        {
            if(Hero.getRoomNumber() == 1)
            {
                addObject(new StillCharacter("knight.png"), 600, 300);
                addObject(new StillCharacter("checkPoint.png"), 400, 300);
                Hero.setCheckPoint(1);
                addObject(new Box(800, 600, black, 5, 400), 400, 300);
                MyWorld.playMusic("main.mp3");
                //addObject(new generator(h), 400, 300);
            }
            else if(Hero.getRoomNumber() == 2)
            {
                randomSpawn(4);
                //addObject(new wizardBoss(h, false), 600, 300);
            }
            else if(Hero.getRoomNumber() == 3) randomSpawn(5);
            else if(Hero.getRoomNumber() == 4) addObject(new StillCharacter("checkPoint.png"), 400, 300);
            else if(Hero.getRoomNumber() == 5)
            {
                
                if(Hero.getCheckPoint() != 4)
                {
                    
                    Greenfoot.setWorld(new SceneWorld(this));
                }
                else
                {
                    Hero.setRoomNumber(Hero.getRoomNumber() + 1);
                    addObject(new OrcBoss(h), 600, 300);
                }
                Hero.setCheckPoint(4);
                
            }
            else if(Hero.getRoomNumber() == 6)
            {
                addObject(new OrcBoss(h), 600, 300);
                textBox.tempText("Tactical Guide Updated: Lieutenant Gregg", 20);
                addObject(new Box(800, 600, black, 5, 300), 400, 300);
            }
            else if(Hero.getRoomNumber() == 7)
            {
                if(Hero.getCheckPoint() == 7) addObject(new StillCharacter("malbus9.png"), 500, 300);
                addObject(new StillCharacter("checkPoint.png"), 400, 300);
                
            }
            else if(Hero.getRoomNumber() == 8) 
            {
                if(Hero.getCheckPoint() != 7) Greenfoot.setWorld(new SceneWorld(this));
                else
                {
                    Hero.setRoomNumber(Hero.getRoomNumber() + 1);
                    randomSpawn(6);
                    MyWorld.playMusic("main.mp3");
                }
                Hero.setCheckPoint(7);
                
            }
            else if(Hero.getRoomNumber() == 9)
            {
                randomSpawn(6);
                MyWorld.playMusic("main.mp3");
                addObject(new Box(800, 600, black, 5, 300), 400, 300);
            }
            else if(Hero.getRoomNumber() == 10) randomSpawn(7);
            else if(Hero.getRoomNumber() == 11) addObject(new StillCharacter("checkPoint.png"), 400, 300);
            else if(Hero.getRoomNumber() == 12)
            {
                if(Hero.getCheckPoint() != 11)
                {
                    
                    Greenfoot.setWorld(new SceneWorld(this));
                    }
                else
                {
                    Hero.setRoomNumber(Hero.getRoomNumber() + 1);
                    addObject(new malbusBoss(h), 600, 300);
                
                    addObject(new StillCharacter("wall.png"), 780, 175);
                    addObject(new StillCharacter("wall.png"), 780, 425);
                }
                Hero.setCheckPoint(11);
            }
            else if(Hero.getRoomNumber() == 13)
            {
                addObject(new malbusBoss(h), 600, 300);
                textBox.tempText("Tactical Guide Updated: Malbus Drake", 20);
                addObject(new StillCharacter("wall.png"), 780, 175);
                addObject(new StillCharacter("wall.png"), 780, 425);
                addObject(new Box(800, 600, black, 5, 300), 400, 300);
            }
            else if(Hero.getRoomNumber() == 14)
            {
                MyWorld.playMusic("temple theme.mp3");
                addObject(new StillCharacter("cube.png"), 300, 300);
                addObject(new StillCharacter("checkPoint.png"), 400, 300);
                addObject(new StillCharacter("wall.png"), 20, 175);
                addObject(new StillCharacter("wall.png"), 20, 425);
                addObject(new StillCharacter("wall.png"), 780, 175);
                addObject(new StillCharacter("wall.png"), 780, 425);
                
            }
            else if(Hero.getRoomNumber() == 15)
            {
                addObject(new Box(800, 600, black, 5, 300), 400, 300);
                MyWorld.playMusic("temple theme.mp3");
                addObject(new StillCharacter("wall.png"), 20, 175);
                addObject(new StillCharacter("wall.png"), 20, 425);
                addObject(new StillCharacter("wall.png"), 780, 175);
                addObject(new StillCharacter("wall.png"), 780, 425);
                addObject(new StillCharacter("checkPoint.png"), 400, 300);
                Hero.setCheckPoint(15);
            }
            else if(Hero.getRoomNumber() == 16)
            {
                MyWorld.playMusic("out of the temple.mp3");
                randomSpawn(8);
                addObject(new StillCharacter("wall.png"), 20, 175);
                addObject(new StillCharacter("wall.png"), 20, 425);
                
            }
            else if(Hero.getRoomNumber() == 17) Greenfoot.setWorld(new SceneWorld(this));
            else if(Hero.getRoomNumber() == 18)
            {
                if(Hero.getCheckPoint() != 18) textBox.tempText("Knight of Light: Good luck, hero ...", 20);
                addObject(new StillCharacter("knight.png"), 600, 300);
                h.setLocation(200, 298);
                addObject(new StillCharacter("wall2.png"), 780, 175);
                addObject(new StillCharacter("wall2.png"), 780, 425);
                addObject(new StillCharacter("checkPoint.png"), 400, 300);
                
                MyWorld.playMusic("castle theme.mp3");
                addObject(new Box(800, 600, white, 5, 400), 400, 300);
            }
            
            else if(Hero.getRoomNumber() == 19)
            {
                addObject(new StillCharacter("wall2.png"), 20, 175);
                addObject(new StillCharacter("wall2.png"), 20, 425);
                if(Hero.getCheckPoint() != 18) textBox.tempText("Tactical Guide Updated: Elite Guards", 20);
                randomSpawn(5);
                Hero.setCheckPoint(18);
            }
            else if(Hero.getRoomNumber() == 20) randomSpawn(6);
            else if(Hero.getRoomNumber() == 21) randomSpawn(7);
            else if(Hero.getRoomNumber() == 22) {
                addObject(new StillCharacter("checkPoint.png"), 400, 300);
                
            }
            else if(Hero.getRoomNumber() == 23)
            {
                if(Hero.getCheckPoint() != 22)
                {
                    
                    Greenfoot.setWorld(new SceneWorld(this));
                    
                }
                else
                {
                    Hero.setRoomNumber(Hero.getRoomNumber() + 1);
                    addObject(new wizardBoss(h, false), 600, 300);
                }
                Hero.setCheckPoint(22);
            }
            else if(Hero.getRoomNumber() == 24)
            {
                addObject(new wizardBoss(h, false), 600, 300);
                textBox.tempText("Tactical Guide Updated: Invictus", 20);
                addObject(new Box(800, 600, black, 5, 300), 400, 300);
            }
            else if(Hero.getRoomNumber() == 25)
            {
                addObject(new Box(800, 600, red, 5, 300), 400, 300);
                MyWorld.playMusic("temple theme.mp3");
                }
            else if(Hero.getRoomNumber() == 26) addObject(new generator(h), 400, 300);
            
            //Sets the world background
            if(Hero.getRoomNumber() >= 1 && Hero.getRoomNumber() <= 13) setBackground("grassBackground.png");
            if(Hero.getRoomNumber() >= 14 && Hero.getRoomNumber() <= 15) setBackground("templeBackground.png");
            if(Hero.getRoomNumber() >= 16 && Hero.getRoomNumber() <= 18) setBackground("grassBackground.png");
            if(Hero.getRoomNumber() >= 19) setBackground("castleBackground.png");
            
            enemiesAdded = true;
        }
        
        
    }
    
    public void printStats()//Displays the players stats in real time
    {
        h.setValues();
        HP.clear();
        Mana.clear();
        HP.drawText("HP: " + (int) h.getHP() + "/250", 20);
        Mana.drawText("Mana: " + (int) h.getMana() + "/200", 20);
    }
    
    public static void playMusic(String filename)//Public class method to play a music file
    {
        
        if(music != null)
        {
            music.stop();
            
        }
        music = new GreenfootSound(filename);
        music.playLoop();
    }
    
    public static void stopMusic()//Stops the music if music has been assigned a value
    {
        if(music != null) music.stop();
    }
    
    public static boolean musicPlaying()
    {
        if(music != null) return music.isPlaying();
        return false;
    }
    
    
}
