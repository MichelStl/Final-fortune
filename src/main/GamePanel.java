package main;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Sceen settings
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 16x16
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screeWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow;// 576px

    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screeWidth, screenHeight));
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
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
