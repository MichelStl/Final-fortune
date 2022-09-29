package main;

import object.KeyObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    BufferedImage keyImage;

    //BufferedImage keyImage;
    private Font zyphyte;
    //Font excludedItalic;
    private Font olgaItalic;
    Graphics2D g2;
    public boolean messageOn = false;
    public String message = "";

    public String currentDialogue = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNumber = 0;

    public ScreenState screenState = ScreenState.TITLE;
    //public double playtime;
    //DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/Zyphyte.ttf");
            zyphyte = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/font/Olga-Italic.ttf");
            olgaItalic = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch(FontFormatException e ){
            e.printStackTrace();
        } catch ( IOException e){
            e.printStackTrace();
        }
        // Déclaration des éléments fixe de l'écran---------------- A voir
            KeyObject key = new KeyObject(gp);
            keyImage = key.image;

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(zyphyte);
        //g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(g2.getFont().deriveFont(32F));
        g2.setColor(Color.orange);

        switch (gp.gameState){
            case TITLE_STATE:
                drawTitleScreen();
                break;
            case PLAY_STATE:
                drawPlayScreen();
                break;
            case PAUSE_STATE:
                drawPauseScreen();
                break;
            case STOP_STATE:
                break;
            case DIALOGUE:
                drawDialogueScreen();
                break;
            default:
                break;
        }
        if(gameFinished == true){
            //g2.setFont(g2.getFont().deriveFont(16F));
            //g2.setColor(Color.white);
            String text = "You found the treasure";
            int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            float x = gp.screenWidth / 2 - textLength/2;
            float y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            //text = "in " +decimalFormat.format(playtime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 - (gp.tileSize * 2.5f);
            g2.drawString(text, x, y);

            //g2.setFont(g2.getFont().deriveFont(80F));
            //g2.setColor(Color.orange);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength/2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            //Gestion des éléments fixe de l'affichage
            if(gp.gameState != GameState.TITLE_STATE) {
                g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize / 2, null);
                g2.drawString("x " + gp.player.hasKey, 50, 43);
            }
            //TIME
            //playtime +=(double)1/60;
            //g2.drawString("Time: " +decimalFormat.format(playtime), gp.screenWidth - (gp.tileSize * 3), 43);
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

    public void drawDialogueScreen(){
        // Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setColor(Color.red);
        x += gp.tileSize;
        y += gp.tileSize;
        //g2.setFont(g2.getFont().deriveFont(23F));
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x, y);
            y += 35;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        g2.setColor(new Color(9, 61, 61, 200));
        g2.fillRoundRect(x, y, width, height, 20,20);

        g2.setStroke(new BasicStroke(5));
        g2.setColor(new Color(255, 255, 255, 255));
        g2.drawRoundRect(x, y, width+3, height+3, 25,25);

    }
    public void drawTitleScreen() {
        switch (screenState) {
            case TITLE:
                g2.setColor(new Color(50,50,50));
                g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

                //Title
                g2.setFont(olgaItalic);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
                String text = "Final fortune";
                int x = getXTextCenter(text);
                int y = gp.tileSize * 3;

                //Shadow
                g2.setColor(Color.BLUE);
                g2.drawString(text, x + 5, y);

                // Main color
                g2.setColor(Color.white);
                g2.drawString(text, x, y);

                // Menu

                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
                text = "New game";
                x = getXTextCenter(text);
                y += gp.tileSize * 4;
                g2.drawString(text, x, y);
                if (commandNumber == 0) {
                    g2.setColor(Color.BLUE);
                    g2.drawString(text, x, y);
                }
                g2.setColor(Color.WHITE);
                text = "Load game";
                x = getXTextCenter(text);
                y += gp.tileSize;
                g2.drawString(text, x, y);
                if (commandNumber == 1) {
                    g2.setColor(Color.BLUE);
                    g2.drawString(text, x, y);
                }
                g2.setColor(Color.WHITE);
                text = "Quit";
                x = getXTextCenter(text);
                y += gp.tileSize;
                g2.drawString(text, x, y);
                if (commandNumber == 2) {
                    g2.setColor(Color.BLUE);
                    g2.drawString(text, x, y);
                }
                break;
            case CHARACTER_SELECTION:
                try {
                    BufferedImage image;
                    g2.setColor(new Color(50,50,50));
                    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

                    g2.setColor(Color.white);
                    g2.setFont(olgaItalic);
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
                    text = "Select character";
                    x = getXTextCenter(text);
                    y = gp.tileSize * 3;
                    g2.drawString(text, x, y);

                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
                    text = "Miner";
                    x = getXTextCenter(text) - gp.tileSize * 4;
                    y += gp.tileSize * 4;
                    image = ImageIO.read(getClass().getResourceAsStream("/player/miner_down_1.png"));
                    g2.drawImage(image, x, y - (gp.tileSize*2), gp.tileSize*2, gp.tileSize*2, null);
                    g2.drawString(text, x, y + (gp.tileSize /2));
                    if (commandNumber == 0) {
                        g2.setColor(Color.BLUE);
                        g2.drawString(text, x, y + (gp.tileSize /2));
                    }

                    text = "Theif";
                    g2.setColor(Color.WHITE);
                    x = getXTextCenter(text);
                    //y += gp.tileSize;
                    image = ImageIO.read(getClass().getResourceAsStream("/player/redhead_thief_down_1.png"));
                    g2.drawImage(image, x, y - (gp.tileSize*2), gp.tileSize*2, gp.tileSize*2, null);
                    g2.drawString(text, x, y + (gp.tileSize /2));
                    //g2.drawString(text, x, y);
                    if (commandNumber == 1) {
                        g2.setColor(Color.BLUE);
                        g2.drawString(text, x, y + (gp.tileSize /2));
                    }

                    text = "Farmer";
                    g2.setColor(Color.WHITE);
                    x = getXTextCenter(text) + gp.tileSize * 4;
                    //y += gp.tileSize;
                    image = ImageIO.read(getClass().getResourceAsStream("/player/old_farmer_down_1.png"));
                    g2.drawImage(image, x + 32, y - (gp.tileSize*2), gp.tileSize*2, gp.tileSize*2, null);
                    g2.drawString(text, x, y + (gp.tileSize /2));
                    //g2.drawString(text, x, y);
                    if (commandNumber == 2) {
                        g2.setColor(Color.BLUE);
                        g2.drawString(text, x, y + (gp.tileSize /2));
                    }
                    text = "Back";
                    g2.setColor(Color.WHITE);
                    x = getXTextCenter(text);
                    y += gp.tileSize*2;
                    g2.drawString(text, x, y);
                    if (commandNumber == -1) {
                        g2.setColor(Color.BLUE);
                        g2.drawString(text, x, y);
                    }
                } catch(IOException e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }
}
