package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;

public class HealthIndicatorObject extends Entity {

    public HealthIndicatorObject(GamePanel gp){
        super(gp);
        name = "health";
/*        image = setup("/objects/health0-8");
        image1 = setup("/objects/health1-8");
        image2 = setup("/objects/health2-8");
        image3 = setup("/objects/health3-");
        image4 = setup("/objects/health4-8");
        image5 = setup("/objects/health5-8");
        image6 = setup("/objects/health6-8");
        image7 = setup("/objects/health7-8");
        image8 = setup("/objects/health8-8");*/

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/health0-8.png"));
            image1 = ImageIO.read(getClass().getResourceAsStream("/objects/health1-8.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/health2-8.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/health3-8.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/objects/health4-8.png"));
            image5 = ImageIO.read(getClass().getResourceAsStream("/objects/health5-8.png"));
            image6 = ImageIO.read(getClass().getResourceAsStream("/objects/health6-8.png"));
            image7 = ImageIO.read(getClass().getResourceAsStream("/objects/health7-8.png"));
            image8 = ImageIO.read(getClass().getResourceAsStream("/objects/health8-8.png"));

/*            image = util.scaleImage(image, gp.tileSize, gp.tileSize);
            image1 = util.scaleImage(image1, gp.tileSize, gp.tileSize);
            image2 = util.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = util.scaleImage(image3, gp.tileSize, gp.tileSize);
            image4 = util.scaleImage(image4, gp.tileSize, gp.tileSize);
            image5 = util.scaleImage(image5, gp.tileSize, gp.tileSize);
            image6 = util.scaleImage(image6, gp.tileSize, gp.tileSize);
            image7 = util.scaleImage(image7, gp.tileSize, gp.tileSize);
            image8 = util.scaleImage(image8, gp.tileSize, gp.tileSize);*/


        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
