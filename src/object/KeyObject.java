package object;

import entity.Entity;
import main.GamePanel;

public class KeyObject extends Entity {

    public KeyObject(GamePanel gp){
        super(gp);
        name = "key";
        down1 = setup("/objects/key1", gp.tileSize, gp.tileSize);
    }
}
