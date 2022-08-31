package object;

import javax.imageio.ImageIO;

public class BootsObject extends SuperObject {
    public BootsObject(){
        name = "boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boot1.png"));

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
