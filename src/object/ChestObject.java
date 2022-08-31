package object;

import javax.imageio.ImageIO;

public class ChestObject extends SuperObject {
    public ChestObject(){
        name = "chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest1.png"));

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
