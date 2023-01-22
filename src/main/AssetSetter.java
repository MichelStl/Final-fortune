package main;

import entity.NPCOldMan;
import monster.MonsterYeti;
import object.BootsObject;
import object.ChestObject;
import object.DoorObject;
import object.KeyObject;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.gameObjects[0] = new KeyObject(gp);
        gp.gameObjects[0].worldX = 10 * gp.tileSize;
        gp.gameObjects[0].worldY = 42 * gp.tileSize;

        gp.gameObjects[1] = new KeyObject(gp);
        gp.gameObjects[1].worldX = 48 * gp.tileSize;
        gp.gameObjects[1].worldY = 23 * gp.tileSize;

        gp.gameObjects[2] = new KeyObject(gp);
        gp.gameObjects[2].worldX = 15 * gp.tileSize;
        gp.gameObjects[2].worldY = 9 * gp.tileSize;

        gp.gameObjects[3] = new DoorObject(gp);
        gp.gameObjects[3].worldX = 41 * gp.tileSize;
        gp.gameObjects[3].worldY = 29 * gp.tileSize;

        gp.gameObjects[4] = new ChestObject(gp);
        gp.gameObjects[4].worldX = 46 * gp.tileSize;
        gp.gameObjects[4].worldY = 34 * gp.tileSize;

        gp.gameObjects[5] = new DoorObject(gp);
        gp.gameObjects[5].worldX = 47 * gp.tileSize;
        gp.gameObjects[5].worldY = 44 * gp.tileSize;

        gp.gameObjects[6] = new DoorObject(gp);
        gp.gameObjects[6].worldX = 41 * gp.tileSize;
        gp.gameObjects[6].worldY = 15 * gp.tileSize;

        gp.gameObjects[7] = new BootsObject(gp);
        gp.gameObjects[7].worldX = 1 * gp.tileSize;
        gp.gameObjects[7].worldY = 11 * gp.tileSize;

    }

    public void setNPC(){
        gp.npc[0] = new NPCOldMan(gp);
        gp.npc[0].worldX = 26 * gp.tileSize;
        gp.npc[0].worldY = 18 * gp.tileSize;

        gp.npc[1] = new NPCOldMan(gp);
        gp.npc[1].worldX = 28 * gp.tileSize;
        gp.npc[1].worldY = 16 * gp.tileSize;

    }
    public void setMonster(){
        gp.monster[0] = new MonsterYeti(gp);
        gp.monster[0].worldX = 38 * gp.tileSize;
        gp.monster[0].worldY  = 7 * gp.tileSize;

        gp.monster[1] = new MonsterYeti(gp);
        gp.monster[1].worldX = 39 * gp.tileSize;
        gp.monster[1].worldY  = 7 * gp.tileSize;
    }
}
