package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class ChestObject extends SuperObject {
    GamePanel gp;
    public ChestObject(GamePanel gp){
        this.gp = gp;
        name = "chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest1.png"));
            util.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
