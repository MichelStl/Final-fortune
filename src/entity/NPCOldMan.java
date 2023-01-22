package entity;

import main.GamePanel;

import java.util.Random;

public class NPCOldMan extends Entity {

    public NPCOldMan(GamePanel gp){
        super(gp);
        type = EntityType.NPC;
        setDefaultValues();
        setDialogue();
    }

    public void setDefaultValues(){
        //Starting position
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 15;
        direction = "down";
        speed = 1;
        getNPCImage();
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
    public void getNPCImage() {

        up1 = setup("/npc/old-man-up1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/old-man-up2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/old-man-down1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/old-man-down2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/old-man-left1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/old-man-left2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/old-man-right1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/old-man-right2", gp.tileSize, gp.tileSize);
    }
    public void setDialogue(){
        dialogues[0] = "Hello man!";
        dialogues[1] = "You looks strong, but you go the wrong way \nto help an old wizard like me.";
        dialogues[2] = "Have you found the treasure?";
        dialogues[3] = "Well, good luck!";
    }
    public void speak(){
        super.speak();
    }
}
