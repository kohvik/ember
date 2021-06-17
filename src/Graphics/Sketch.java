package Graphics;

import Game.*;

import javax.swing.*;
import java.awt.*;

public class Sketch extends JPanel {

    @Override
    public void paintComponent(Graphics g) {

        // converting g to a graphics2d type
        Graphics2D graphics = (Graphics2D) g;


        // refreshing the screen with full white
        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(0, 0, 840, 820);


        graphics.setColor(new Color(0, 0, 0));

        // draws placement squares
        for (int y = 0; y < Main.coordinates.length; y++) {

            for (int x = 0; x < Main.coordinates[0].length; x++) {

                graphics.drawRect(Main.coordinates[x][y].x - 30, Main.coordinates[x][y].y - 30, 60, 60);

            }
        }

        //draws main path for enemies
        graphics.fillRect(Main.coordinates[0][3].x - 50, Main.coordinates[0][3].y - 50, 820, 200);
        //draws nice spot for info
        graphics.setColor(Color.WHITE);
        graphics.fillRect(Main.coordinates[7][7].x - 50,Main.coordinates[7][7].y - 50, 100, 100);

        //sketching entities
        for (int i = 0; i < Main.warriors.length; i++) {
            Main.warriors[i].Draw(graphics);
        }
        for (int i = 0; i < Main.vikings.length; i++) {
            Main.vikings[i].Draw(graphics);
        }

        //sketching towers and their attacks by iterating through occupied
        for (int y = 0; y < Main.occupied.length; y++) {
            for (int x = 0; x < Main.occupied[y].length; x++) {
                for (int i = 0; i < Main.buildings.length; i++)
                if (Main.occupied[x][y] == Main.buildings[i].id) {
                    Main.buildings[i].Draw(graphics, x, y);
                    Main.buildings[i].doAttack(graphics, x, y);
                }
            }
        }

        //sketching values
        graphics.setColor(Color.BLACK);
        graphics.drawString("gold: " + (Main.gold), Main.coordinates[7][7].x - 30, Main.coordinates[7][7].y - 20);
        graphics.drawString("level: " + (Main.currentLevel + 1), Main.coordinates[7][7].x - 30, Main.coordinates[7][7].y);
        if (Main.lives > 0) {
            graphics.drawString("lives: " + (Main.lives), Main.coordinates[7][7].x - 30, Main.coordinates[7][7].y + 20);
        }
        else {
            graphics.drawString("lives: 0", Main.coordinates[7][7].x - 30, Main.coordinates[7][7].y + 20);
            DeathMessage(graphics);
        }

    }

    public void DeathMessage(Graphics2D graphics) {
        graphics.setColor(new Color(0,0,0,150));
        graphics.fillRect(0,0,820,840);
        graphics.setColor(new Color(111, 48, 48));
        graphics.fillRect(205,350,410,100);
        graphics.setColor(Color.WHITE);
        graphics.drawString("you died!",370,400);

    }

}
