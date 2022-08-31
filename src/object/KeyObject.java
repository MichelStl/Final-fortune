package object;

import javax.imageio.ImageIO;

public class KeyObject extends SuperObject {
    public KeyObject(){
        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key1.png"));

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
