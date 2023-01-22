package object;

import entity.Entity;
import main.GamePanel;

public class BootsObject extends Entity {
    public BootsObject(GamePanel gp){
        super(gp);
        name = "boots";
        down1 = setup("/objects/boot1", gp.tileSize, gp.tileSize);
    }
}
