package main;

import entity.Player;
import entity.PlayerClass;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean rightPressed;
    public boolean leftPressed;

    public boolean enterPressed;
    GamePanel gp;
//DEBUG
    boolean checkDrawTime = false;
//
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == GameState.TITLE_STATE){
            switch (gp.ui.screenState) {
                case TITLE:
                    // Main title navigation
                    switch (code){
                        case KeyEvent.VK_W:
                        case KeyEvent.VK_UP:
                            if (gp.ui.commandNumber > 0) {
                                gp.ui.commandNumber--;
                            }
                            break;
                        case KeyEvent.VK_S:
                        case KeyEvent.VK_DOWN:
                            if (gp.ui.commandNumber < 2) {
                                gp.ui.commandNumber++;
                            }
                            break;
                        case KeyEvent.VK_ENTER:
                            // Main title selection switch
                            switch (gp.ui.commandNumber){
                                case 0:
                                    gp.ui.screenState = ScreenState.CHARACTER_SELECTION;
                                    break;
                                case 1:
                                    // LOAD GAME -------
                                    break;
                                case 2:
                                    System.exit(0);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case CHARACTER_SELECTION:
                    // Character navigation switch
                    switch (code){
                        case KeyEvent.VK_A:
                        case KeyEvent.VK_LEFT:
                            if (gp.ui.commandNumber > 0) {
                                gp.ui.commandNumber--;
                            }
                            break;
                        case KeyEvent.VK_D:
                        case KeyEvent.VK_RIGHT:
                            if (gp.ui.commandNumber < 2) {
                                gp.ui.commandNumber++;
                            }
                            break;
                        case KeyEvent.VK_S:
                        case KeyEvent.VK_DOWN:
                            gp.ui.commandNumber = -1;
                            break;
                        case KeyEvent.VK_W:
                        case KeyEvent.VK_UP:
                            if(gp.ui.commandNumber < 0) {
                                gp.ui.commandNumber = 0;
                            }
                            break;
                        case KeyEvent.VK_ENTER:
                            //Character selection switch
                            switch (gp.ui.commandNumber){
                                case -1:
                                    System.out.println("Back to main menu");
                                    gp.ui.commandNumber = 0;
                                    gp.gameState = GameState.TITLE_STATE;
                                    gp.ui.screenState = ScreenState.TITLE;
                                    break;
                                case 0:
                                    System.out.println("Miner stuff");
                                    gp.player.playerClass = PlayerClass.MINER;
                                    gp.player.getPlayerImage();
                                    gp.gameState = GameState.PLAY_STATE;
                                    break;
                                case 1:
                                    System.out.println("Theif stuff");
                                    gp.player.playerClass = PlayerClass.RED_HEAD_THEIF;
                                    gp.player.getPlayerImage();
                                    gp.gameState = GameState.PLAY_STATE;
                                    break;
                                case 2:
                                    System.out.println("Farmer stuff");
                                    gp.player.playerClass = PlayerClass.OLD_FARMER;
                                    gp.player.getPlayerImage();
                                    gp.gameState = GameState.PLAY_STATE;
                                    break;
                            }
                            break;
                        default:
                            break;
                    }
                    default:
                    break;
            }
        }

        //GAME PLAY STATE
        if(gp.gameState == GameState.PLAY_STATE){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }

            if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }

            if(code == KeyEvent.VK_P){
                gp.gameState = GameState.PAUSE_STATE;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
//DEBUG
            if(code == KeyEvent.VK_T){
                if(checkDrawTime == false){
                    checkDrawTime = true;
                }
                else if(checkDrawTime == true){
                    checkDrawTime = false;
                }
            }
//---
            //GAME PAUSE STATE
        } else if (gp.gameState == GameState.PAUSE_STATE) {
            if(code == KeyEvent.VK_P){
                gp.gameState = GameState.PLAY_STATE;
            }
            // GAME DIALOGUE STATE
        } else if (gp.gameState == GameState.DIALOGUE) {
            if (code == KeyEvent.VK_ENTER){
                gp.gameState = GameState.PLAY_STATE;
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT ){
            rightPressed = false;
        }
    }
}
