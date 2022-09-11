package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class BootsObject extends SuperObject {
    GamePanel gp;
    public BootsObject(GamePanel gp){
        this.gp = gp;
        name = "boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boot1.png"));
            util.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
