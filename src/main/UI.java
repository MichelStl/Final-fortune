package main;

import object.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    BufferedImage keyImage;
    Font arial_16;
    Font arial_80_bold;
    //BufferedImage keyImage;

    Graphics2D g2;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public double playtime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gp){
        this.gp = gp;
        arial_16 = new Font("Arial", Font.PLAIN, 16);
        arial_80_bold = new Font("Arial", Font.BOLD, 80);
        KeyObject key = new KeyObject(gp);
        keyImage = key.image;
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_80_bold);
        g2.setColor(Color.orange);

        switch (gp.gameState){
            case PLAY_STATE:
                drawPlayScreen();
                break;
            case PAUSE_STATE:
                drawPauseScreen();
                break;
            case STOP_STATE:
                break;
            default:
                break;
        }
        if(gameFinished == true){
            g2.setFont(arial_16);
            g2.setColor(Color.white);
            String text = "You found the treasure";
            int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            float x = gp.screenWidth / 2 - textLength/2;
            float y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            text = "in " +decimalFormat.format(playtime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 - (gp.tileSize * 2.5f);
            g2.drawString(text, x, y);

            g2.setFont(arial_80_bold);
            g2.setColor(Color.orange);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            g2.setFont(arial_16);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize / 2, null);
            g2.drawString("x " + gp.player.hasKey, 50, 43);

            //TIME
            playtime +=(double)1/60;
            g2.drawString("Time: " +decimalFormat.format(playtime), gp.screenWidth - (gp.tileSize * 3), 43);
            // MESSAGE
            if (messageOn == true) {
                //g2.setFont(g2.getFont().deriveFont(12F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 2);

                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void drawPlayScreen(){

    }
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXTextCenter(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public int getXTextCenter(String text){
        return (gp.screenWidth / 2) - ((int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2);
    }
}
