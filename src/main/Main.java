package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Game start");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Final Fortune");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGameObject();
        gamePanel.startGameThread();
    }
}
