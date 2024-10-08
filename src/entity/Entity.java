package entity;

import main.GamePanel;
import main.GameState;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public GamePanel gp;
    public UtilityTool util = new UtilityTool();
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public BufferedImage image, image1, image2, image3, image4, image5, image6, image7, image8;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public String dialogues[] = new String[20];

    //region Movement BufferedImages
    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage right1;
    public BufferedImage right2;
    public BufferedImage left1;
    public BufferedImage left2;
    //endregion

    //region Attack BufferedImages
    public BufferedImage attackUp1;
    public BufferedImage attackUp2;
    public BufferedImage attackDown1;
    public BufferedImage attackDown2;
    public BufferedImage attackLeft1;
    public BufferedImage attackLeft2;
    public BufferedImage attackRight1;
    public BufferedImage attackRight2;
    //endregion
    public boolean collision = false;


    // STATE
    public int worldX;
    public int worldY;
    public String direction = "down";
    public int dialogueIndex = 0;
    public int spriteNum = 1;
    public boolean invincible = false;
    public boolean collisionOn = false;
    public boolean attacking = false;

    //COUNTER
    public int spriteCounter = 0;
    public int invincibleCounter = 0;
    public int actionLockCounter = 0;


    //CHARACTER Attribute
    public int maxLife;
    public int life;
    public int speed;
    public EntityType type;
    public String name;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public BufferedImage setup(String imagePath, int width, int height){
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = util.scaleImage(image, width, height);

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

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

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void setAction(){}
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
            default:
                break;
        }
    }
    public void update(){
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkEntity(this, gp.npc);
        gp.collisionChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
        if(this.type == EntityType.MONSTER && contactPlayer){
            if(!gp.player.invincible){
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

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
