package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNumbers[][];
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNumbers = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/map1.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water2.png"));

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void loadMap(String mapPath){
        try{
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = bufferedReader.readLine();
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    mapTileNumbers[col][row] = number;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
/*        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);*/

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNumbers[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
