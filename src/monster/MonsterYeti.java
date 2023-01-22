package monster;

import entity.Entity;
import entity.EntityType;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class MonsterYeti extends Entity {
    public MonsterYeti(GamePanel gp) {
        super(gp);
        type = EntityType.MONSTER;
        name = "Yeti";
        speed = 1;
        maxLife = 5;
        life = maxLife;
        solidArea = new Rectangle(8, 8, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImages();
    }
    public void getImages(){
        up1 = setup("/monster/brown_monster_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/brown_monster_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/brown_monster_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/brown_monster_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/brown_monster_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/brown_monster_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/brown_monster_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/brown_monster_right_2", gp.tileSize, gp.tileSize);

    }
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 100) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
