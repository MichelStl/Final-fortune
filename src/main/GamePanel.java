package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale; // 16x16
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow;// 576px
    int FPS = 60;

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public  final int worldHeight = tileSize * maxWorldRow;

    TileManager tileManager = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);

    public Sound themeSound = new Sound();
    public Sound soundEffect = new Sound();

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);

    public UI ui = new UI(this);
    Thread gameThread;

    // Entity and Objects
    public Player player = new Player(this, keyH);
    public SuperObject gameObjects[] = new SuperObject[10];

    public Entity npc[] = new Entity[10];

    //Game State
    public GameState gameState = GameState.TITLE_STATE;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Game Thread and loop
     */
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Update GamePanel data
     */
    public void update(){
        switch (gameState){
            case PLAY_STATE:
                player.update();
                // NPC
                for (int i = 0; i < npc.length; i++){
                    if(npc[i] != null) {
                        npc[i].update();
                    }
                }
                break;
            case PAUSE_STATE:
                break;
            default:
                break;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
//DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
//
        if(gameState == GameState.TITLE_STATE){
            ui.draw(g2);
        } else {
            tileManager.draw(g2);
            for (int i = 0; i < gameObjects.length; i++) {
                if (gameObjects[i] != null) {
                    gameObjects[i].draw(g2, this);
                }
            }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }
            player.draw(g2);

            //UI
            ui.draw(g2);
        }
//DEBUG
        if(keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            System.out.println("Time to draw: " + passed);
        }
//
        g2.dispose();
    }

    public void setupGameObject(){
        assetSetter.setObject();
        assetSetter.setNPC();
        //TODO playThemeSound(0);
        gameState = GameState.TITLE_STATE;
    }
    public void playThemeSound(int index){
        themeSound.setFile(index);
        themeSound.play();
        themeSound.loop();
    }
    public void stopThemeSound(){
        themeSound.stop();
    }
    public void playSoundEffect(int index){
        soundEffect.setFile(index);
        soundEffect.play();
    }

}
