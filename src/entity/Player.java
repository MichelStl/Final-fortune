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
        type = EntityType.PLAYER;
        this.keyH = keyH;
        screenX = gp.screenWidth /2 - (gp.tileSize / 2);
        screenY = gp.screenHeight/2 - (gp.tileSize / 2);

        // Player Collision
        solidArea = new Rectangle(8, 8, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        //Starting position
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 3;
        direction = "down";

        //PLAYER LIFE STATUS
        maxLife = 8;
        life = maxLife;
    }
    public void getPlayerImage() {

        switch (playerClass) {
            case MINER:
                up1 = setup("/player/miner_up_1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/miner_up_2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/miner_down_1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/miner_down_2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/miner_left_1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/miner_left_2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/miner_right_1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/miner_right_2", gp.tileSize, gp.tileSize);
                break;
            case RED_HEAD_THEIF:
                up1 = setup("/player/redhead_thief_up_1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/redhead_thief_up_2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/redhead_thief_down_1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/redhead_thief_down_2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/redhead_thief_left_1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/redhead_thief_left_2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/redhead_thief_right_1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/redhead_thief_right_2", gp.tileSize, gp.tileSize);
                break;
            case OLD_FARMER:
                up1 = setup("/player/old_farmer_up_1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/old_farmer_up_2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/old_farmer_down_1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/old_farmer_down_2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/old_farmer_left_1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/old_farmer_left_2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/old_farmer_right_1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/old_farmer_right_2", gp.tileSize, gp.tileSize);
                break;
            default:
                break;
        }
    }

    public void getPlayerAttackImage(){
        switch (playerClass){
            case MINER:
                attackUp1 = setup("/player/attack/miner_mining_up_1", gp.tileSize, gp.tileSize * 2);
                attackUp2 = setup("/player/attack/miner_mining_up_2", gp.tileSize, gp.tileSize * 2);
                attackDown1 = setup("/player/attack/miner_mining_down_1", gp.tileSize, gp.tileSize * 2);
                attackDown2 = setup("/player/attack/miner_mining_down_2", gp.tileSize, gp.tileSize * 2);
                attackLeft1 = setup("/player/attack/miner_mining_left_1", gp.tileSize *2, gp.tileSize);
                attackLeft2 = setup("/player/attack/miner_mining_left_2", gp.tileSize *2, gp.tileSize);
                attackRight1 = setup("/player/attack/miner_mining_right_1", gp.tileSize *2, gp.tileSize);
                attackRight2 = setup("/player/attack/miner_mining_right_2", gp.tileSize *2, gp.tileSize);
                break;
            default:
                break;
        }
    }
    public void update(){
        if(attacking){
            attacking();
        } else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed == true){
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

            int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
            monsterContact(monsterIndex);
            //Check event
            gp.eventHandler.checkEvent();


            if(collisionOn == false && keyH.enterPressed == false){
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
            gp.keyH.enterPressed = false;
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
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
/*        g2.setColor(Color.white);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);*/

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction){
            case "up":
                if(!attacking) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                } else {
                    tempScreenY -= gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if(!attacking) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
                break;
            case "left":
                if(!attacking) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                } else {
                    tempScreenX -= gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }
                break;
            case "right":
                if(!attacking) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
        }


        if(invincible) {
            //Effet d'invincibilité par l'opacité
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            // Debug invincibilité
/*            g2.setFont(new Font("Arial", Font.PLAIN, 18));
            g2.drawString("Invincible", 10, 20);*/
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);
        //Reset opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

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
    public void attacking(){
        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        } else if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
        } else if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
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
        } else {
            if(keyH.enterPressed){
                attacking = true;
            }
        }
    }
    public void monsterContact(int monsterIndex){
        if(monsterIndex != 999){
            if(!invincible){
                life -= 1;
                invincible = true;
            }
        }
    }
}
