package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
    this.gp = gp;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumbers[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNumbers[entityRightCol][entityTopRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumbers[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNumbers[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumbers[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNumbers[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumbers[entityRightCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNumbers[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    /**
     * Check for object's solid area position
     * @param entity
     * @param player
     * @return
     */
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i < gp.gameObjects.length; i++){
            if(gp.gameObjects[i] != null){

                // Get entity's solid area position ( Tile )
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the object's solid area position
                gp.gameObjects[i].solidArea.x = gp.gameObjects[i].worldX + gp.gameObjects[i].solidArea.x;
                gp.gameObjects[i].solidArea.y = gp.gameObjects[i].worldY + gp.gameObjects[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.gameObjects[i].solidArea)){
                            if(gp.gameObjects[i].collision == true){
                                entity.collisionOn = true;
                            }
                            // vérifie si cest le joueur qui a fait la collision
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.gameObjects[i].solidArea)){
                            if(gp.gameObjects[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.gameObjects[i].solidArea)){
                            if(gp.gameObjects[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.gameObjects[i].solidArea)){
                            if(gp.gameObjects[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.gameObjects[i].solidArea.x = gp.gameObjects[i].solidAreaDefaultX;
                gp.gameObjects[i].solidArea.y = gp.gameObjects[i].solidAreaDefaultY;

            }
        }
        return index;
    }
}
