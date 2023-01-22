package object;

import entity.Entity;
import main.GamePanel;

public class ChestObject extends Entity {

    public ChestObject(GamePanel gp){
        super(gp);
        name = "chest";
        down1 = setup("/objects/chest1", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
