package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];
    int previousEventX;
    int previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectangleDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectangleDefaultY = eventRect[col][row].y;
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }


    }

    public void checkEvent()
    {
        // Check if player is gone away of event
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent){
            if(hit(10, 10, "any")){
                damageRect(10, 10, GameState.DIALOGUE);
            }else if(hit(8, 10, "any")){
                healingRect(8, 10, GameState.DIALOGUE);
            } else if (hit(8, 12, "up")) {
                teleport(GameState.DIALOGUE);
            }
        }
    }
    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x  = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(eventRect[col][row].intersects(gp.player.solidArea) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.equals(reqDirection) || reqDirection.equals("any")){
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectangleDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectangleDefaultY;
        return hit;
    }

    public void damageRect(int col, int row, GameState gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You walk on a cactus!";
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true; // Pour que l'évènement ne se reproduise pas
        canTouchEvent = false;
    }

    public void healingRect(int col, int row, GameState gameState){
        if(gp.player.life < gp.player.maxLife) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "healing!";
            gp.player.life = gp.player.maxLife;
        }
    }

    public void teleport(GameState gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";
        gp.player.worldY = 33 * gp.tileSize;
        gp.player.worldX = 41 * gp.tileSize;
    }
}
