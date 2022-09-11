package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNumbers[][];
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[300];
        mapTileNumbers = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world2.txt");
    }

    public void getTileImage(){

        setup(0, "grass0", false);
        setup(1, "grass0", true);
        setup(2, "grass0", true);
        setup(3, "grass0", false);
        setup(4, "grass0", false);
        setup(5, "grass0", true);
        setup(6, "grass0", false);
        setup(7, "grass0", false);
        setup(8, "grass0",false);
        setup(9, "grass0",false);

        setup(110, "sand0", false);
        setup(111, "sand1", false);
        setup(112, "sand2", false);
        setup(113, "sand-water-bottom", false);
        setup(114, "sand-water-bottom-left-corner", false);
        setup(115, "sand-water-bottom-right-corner", false);
        setup(116, "sand-water-left", false);
        setup(117, "sand-water-right", false);
        setup(118, "sand-water-up-left-corner", false);
        setup(119, "sand-water-up", false);
        setup(120, "sand-water-up-right-corner", false);

        setup(121, "water0", true);
        setup(122, "water1", true);
        setup(123, "water2", true);
        setup(124, "water-sand-bottom-left-corner", false);
        setup(125, "water-sand-bottom-right-corner", false);
        setup(126, "water-sand-up-left-corner", false);
        setup(128, "water-sand-up-right-corner", false);
        setup(129, "water-sand-up", false);
        setup(130, "water-sand-right", false);
        setup(131, "water-sand-bottom", false);
        setup(132, "water-sand-left", false);

        setup(133, "sand-grass-bottom", false);
        setup(134, "sand-grass-bottom-left-corner", false);
        setup(135, "sand-grass-bottom-right-corner", false);
        setup(136, "sand-grass-left", false);
        setup(137, "sand-grass-right", false);
        setup(138, "sand-grass-up-left-corner", false);
        setup(139, "sand-grass-up", false);
        setup(140, "sand-grass-up-right-corner", false);

        setup(141, "grass0", false);
        setup(142, "grass1", false);
        setup(143, "grass2", false);
        setup(144, "grass-sand-bottom", false);
        setup(145, "grass-sand-bottom-left-corner", false);
        setup(146, "grass-sand-bottom-right-corner", false);
        setup(147, "grass-sand-left", false);
        setup(148, "grass-sand-right", false);
        setup(149, "grass-sand-up-left-corner", false);
        setup(150, "grass-sand-up", false);
        setup(151, "grass-sand-up-right-corner", false);

        setup(152, "water-grass-bottom-left-corner", true);
        setup(153, "water-grass-bottom-right-corner", true);
        setup(154, "water-grass-up-left-corner", true);
        setup(155, "water-grass-up-right-corner", true);
        setup(156, "water-grass-up", true);
        setup(157, "water-grass-right", true);
        setup(158, "water-grass-bottom", true);
        setup(159, "water-grass-left", true);

        setup(160, "grass-water-bottom", false);
        setup(161, "grass-water-bottom-left-corner", false);
        setup(162, "grass-water-bottom-right-corner", false);
        setup(163, "grass-water-left", false);
        setup(164, "grass-water-right", false);
        setup(165, "grass-water-up-left-corner", false);
        setup(166, "grass-water-up", false);
        setup(167, "grass-water-up-right-corner", false);

        setup(184, "rock0", true);
        setup(185, "grass-rock-up", true);
        setup(186, "grass-rock-up-right-corner", true);
        setup(187, "grass-rock-right", true);
        setup(188, "grass-rock-left", true);
        setup(189, "grass-rock-bottom-right-corner", false);
        setup(190, "grass-rock-bottom", false);
        setup(191, "grass-rock-bottom-left-corner", false);
        setup(192, "grass-rock-up-left-corner", true);

        setup(193, "grass-water-bottom", false);
        setup(194, "grass-water-bottom-left-corner", false);
        setup(195, "grass-water-bottom-right-corner", false);
        setup(196, "grass-water-left", false);
        setup(197, "grass-water-right", false);
        setup(198, "grass-water-up-left-corner", false);
        setup(199, "grass-water-up", false);
        setup(200, "grass-water-up-right-corner", false);

        setup(201, "rock-grass-bottom-left-corner", true);
        setup(202, "rock-grass-bottom-right-corner", true);
        setup(203, "rock-grass-up-left-corner", true);
        setup(204, "rock-grass-up-right-corner", true);
        setup(205, "rock-grass-up", true);
        setup(206, "rock-grass-right", true);
        setup(207, "rock-grass-bottom", true);
        setup(208, "rock-grass-left", true);

        setup(209, "water-rock-bottom-left-corner", true);
        setup(210, "water-rock-bottom-right-corner", true);
        setup(211, "water-rock-up-left-corner", true);
        setup(212, "water-rock-up-right-corner", true);
        setup(213, "water-rock-up", true);
        setup(214, "water-rock-right", true);
        setup(215, "water-rock-bottom", true);
        setup(216, "water-rock-left", true);

        setup(217, "rock-water-bottom-left-corner", true);
        setup(218, "rock-water-bottom-right-corner", true);
        setup(219, "rock-water-up-left-corner", true);
        setup(220, "rock-water-up-right-corner", true);
        setup(221, "rock-water-up", true);
        setup(222, "rock-water-right", true);
        setup(223, "rock-water-bottom", true);
        setup(224, "rock-water-left", true);

        // Forks
        setup(168, "grass-water-sand-up-left-corner", false);
        setup(169, "water-sand-grass-bottom-left-corner", false);
        setup(182, "water-sand-grass-up-right-corner", false);
        setup(183, "water-sand-grass-right-corner", false);
        setup(225, "water-grass-rock-up-left", false);
        setup(226, "water-grass-rock-up-right", false);
        setup(227, "water-grass-rock-bottom-right", false);
        setup(228, "water-grass-rock-bottom-left", false);
        // -----

        setup(170, "bridge0-on-grass-up-left-corner", true);
        setup(171, "bridge0-on-grass-up-right-corner", true);
        setup(172, "bridge0-on-grass-right", true);
        setup(173, "bridge0-on-grass-left", true);
        setup(174, "bridge0-on-grass-center", false);
        setup(175, "bridge0-on-grass-bottom-right-corner", false);
        setup(176, "bridge0-on-grass-bottom-left-corner", false);

        // collision inverse
        setup(177, "water-grass-bottom", false);
        setup(180, "water-grass-up-right-corner", false);
        setup(181, "grass-water-bottom", true);
        //------

        setup(229, "grass-rock-tree-hard-wood-bottom", true);
        setup(178, "tree-hardwood-on-grass1",true);
        setup(179, "tree-hardwood-on-sand1",true);
        setup(230, "grass-sand-tree-hardwood-left", true);
        setup(231, "grass-sand-tree-hardwood-bottom-left-corner", true);
        setup(232, "grass-water-tree-hardwood-bottom", true);
// 232

    }

    private void setup(int index, String imageName, boolean collision){
        UtilityTool util = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = util.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void loadMap(String mapPath){
        try{
            InputStream inputStream = getClass().getResourceAsStream(mapPath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = bufferedReader.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    mapTileNumbers[col][row] = number;
                    col++;
                }
                if(col == gp.maxWorldCol){
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

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNumbers[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }
        }
    }
}
