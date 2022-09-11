package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class KeyObject extends SuperObject {
    GamePanel gp;
    public KeyObject(GamePanel gp){
        this.gp = gp;
        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key1.png"));
            util.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
