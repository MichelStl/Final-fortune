package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth /2 - (gp.tileSize / 2);
        screenY = gp.screenHeight/2 - (gp.tileSize / 2);

        // Player Collision
        solidArea = new Rectangle(8, 8, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        //Starting position
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/blue_boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/blue_boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/blue_boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/blue_boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/blue_boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/blue_boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/blue_boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/blue_boy_right_2.png"));
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }

            // Tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickObject(objIndex);
            if(collisionOn == false){
                switch(direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2){
/*        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);*/
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);
    }

    /**
     * Comportement lors du ramassage d'un objet.
     * @param index
     */
    public void pickObject(int index){
        if(index != 999){
            String objectName = gp.gameObjects[index].name;
            switch (objectName){
                case "key":
                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.gameObjects[index] = null;
                    gp.ui.showMessage("You found a key!!");
                    break;
                case "door":
                    if(hasKey > 0){
                        gp.playSoundEffect(2);
                        gp.gameObjects[index] = null;
                        hasKey--;
                        gp.ui.showMessage("You open a door!!");
                    } else {
                        gp.ui.showMessage("Find the door key!");
                    }
                    break;
                case "boots":
                    speed += 2;
                    gp.playSoundEffect(3);
                    gp.gameObjects[index] = null;
                    gp.ui.showMessage("Speed up!!");
                    break;
                case "chest":
                    gp.ui.gameFinished = true;
                    gp.stopThemeSound();
                    gp.playSoundEffect(4);
                    break;
            }
        }
    }
}
