package object;

import entity.Entity;
import main.GamePanel;

public class DoorObject extends Entity {

    public DoorObject(GamePanel gp){
        super(gp);
        name = "door";
        down1 = setup("/objects/door1", gp.tileSize, gp.tileSize);
        collision = true;
    }

}
