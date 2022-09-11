package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class DoorObject extends SuperObject {
    GamePanel gp;
    public DoorObject(GamePanel gp){
        this.gp = gp;
        name = "door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door1.png"));
            util.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        collision = true;
    }

}
