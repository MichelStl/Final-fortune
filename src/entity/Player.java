package entity;

import main.GamePanel;
import main.GameState;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;
    public int hasKey = 0;
    public final int screenX;
    public final int screenY;

    public PlayerClass playerClass = PlayerClass.MINER;
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
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
        speed = 3;
        direction = "down";
    }
    public void getPlayerImage() {

        switch (playerClass) {
            case MINER:
                up1 = setup("/player/miner_up_1");
                up2 = setup("/player/miner_up_2");
                down1 = setup("/player/miner_down_1");
                down2 = setup("/player/miner_down_2");
                left1 = setup("/player/miner_left_1");
                left2 = setup("/player/miner_left_2");
                right1 = setup("/player/miner_right_1");
                right2 = setup("/player/miner_right_2");
                break;
            case RED_HEAD_THEIF:
                up1 = setup("/player/redhead_thief_up_1");
                up2 = setup("/player/redhead_thief_up_2");
                down1 = setup("/player/redhead_thief_down_1");
                down2 = setup("/player/redhead_thief_down_2");
                left1 = setup("/player/redhead_thief_left_1");
                left2 = setup("/player/redhead_thief_left_2");
                right1 = setup("/player/redhead_thief_right_1");
                right2 = setup("/player/redhead_thief_right_2");
                break;
            case OLD_FARMER:
                up1 = setup("/player/old_farmer_up_1");
                up2 = setup("/player/old_farmer_up_2");
                down1 = setup("/player/old_farmer_down_1");
                down2 = setup("/player/old_farmer_down_2");
                left1 = setup("/player/old_farmer_left_1");
                left2 = setup("/player/old_farmer_left_2");
                right1 = setup("/player/old_farmer_right_1");
                right2 = setup("/player/old_farmer_right_2");
                break;
        }
    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed ){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else if(keyH.rightPressed){
                direction = "right";
            }

            // Tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            // Objects collision
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickObject(objIndex);

            //NPC collision
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

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
        g2.drawImage(image, screenX, screenY,null);
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

    /**
     * Intéraction avec un NPC
     */
    public void interactNPC(int index){
        if(index != 999){
            //if(gp.keyH.enterPressed) {
                gp.gameState = GameState.DIALOGUE;
                gp.npc[index].speak();
            //}
            gp.keyH.enterPressed = false;
        }
    }
}
