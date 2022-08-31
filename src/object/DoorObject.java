package object;

import javax.imageio.ImageIO;

public class DoorObject extends SuperObject {
    public DoorObject(){
        name = "door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door1.png"));

        } catch (Exception ex){
            ex.printStackTrace();
        }
        collision = true;
    }

}
