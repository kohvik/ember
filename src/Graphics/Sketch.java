package Graphics;

import Game.*;

import javax.swing.*;
import java.awt.*;

public class Sketch extends JPanel {

    @Override
    public void paintComponent(Graphics g) {

        //converting g to a graphics2d type
        Graphics2D graphics = (Graphics2D) g;

        //drawing menu if in the menu
        if (Main.menu) {
            DrawMenu(graphics);
        }

        else {
            //refreshing the screen with full white
            graphics.setColor(new Color(168, 102, 102));
            graphics.fillRect(0, 0, 840, 820);

            //draws placement squares
            graphics.setColor(new Color(0, 0, 0));
            graphics.setStroke(new BasicStroke(6));
            for (int y = 0; y < Main.buildingInfo.length; y++) {

                for (int x = 0; x < Main.buildingInfo[y].length; x++) {
                    graphics.drawRect(Main.buildingInfo[x][y].coordinates.x - 40, Main.buildingInfo[x][y].coordinates.y - 40, 80, 80);
                    graphics.fillRect(Main.buildingInfo[x][y].coordinates.x - 36, Main.buildingInfo[x][y].coordinates.y - 36, 72, 72);
                }
            }

            //draws main path for enemies
            graphics.setColor(new Color(76, 46, 50));
            graphics.fillRect(Main.buildingInfo[0][3].coordinates.x - 50, Main.buildingInfo[0][3].coordinates.y - 45, 820, 190);
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawLine(Main.buildingInfo[0][3].coordinates.x - 50, Main.buildingInfo[0][3].coordinates.y - 45, Main.buildingInfo[7][3].coordinates.x + 80, Main.buildingInfo[0][3].coordinates.y - 45);
            graphics.drawLine(Main.buildingInfo[0][5].coordinates.x - 50, Main.buildingInfo[0][5].coordinates.y - 55, Main.buildingInfo[7][5].coordinates.x + 80, Main.buildingInfo[0][5].coordinates.y - 55);

            //sketching entities
            try {
                for (int i = 0; i < Main.warriors.length; i++) {
                    Main.warriors[i].Update(graphics);
                }
                for (int i = 0; i < Main.vikings.length; i++) {
                    Main.vikings[i].Update(graphics);
                }
                for (int i = 0; i < Main.golems.length; i++) {
                    Main.golems[i].Update(graphics);
                }
            } catch (Exception e) {System.out.println("taylor did his job.");}


            //sketching towers and their attacks by iterating through occupied
            for (int x = 0; x < Main.buildingInfo.length; x++) {
                for (int y = 0; y < Main.buildingInfo[x].length; y++) {
                    for (int i = 0; i < Main.buildings.length; i++)
                        if (Main.buildingInfo[x][y].occupied == Main.buildings[i].id) {
                            Main.buildings[i].Update(graphics, x, y, false);
                            Main.buildings[i].doAttack(graphics, x, y);
                        }
                }
            }
            //draws nice spot for info
            graphics.setColor(new Color(168, 102, 102));
            graphics.fillRect(Main.buildingInfo[7][7].coordinates.x - 50,Main.buildingInfo[7][7].coordinates.y - 50, 100, 100);
            //sketching info
            graphics.setColor(Color.BLACK);
            graphics.drawString("gold: " + (Main.gold), Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y - 30);
            graphics.drawString("level: " + (Main.currentLevel + 1), Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y - 10);
            if (Main.lives > 0) {
                graphics.drawString("lives: " + (Main.lives), Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y + 10);
            }
            else {
                graphics.drawString("lives: 0", Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y + 10);
                DeathMessage(graphics);
            }
            graphics.drawString("[o] - open shop", Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y + 30);

            //sketching the shop if it's open
            if (Main.shop.isOpen) {
                Main.shop.Display(graphics);
            }
            //sketching upgrades if upgrading
            if (Main.upgrading) {
                Main.upgradeMenu.Display(graphics);
            }
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

    public void DrawMenu(Graphics2D graphics) {
        graphics.setColor(new Color(0, 57, 35));
        graphics.fillRect(0, 0, 820, 840);
        graphics.setColor(Color.WHITE);
        graphics.drawString("welcome to ember!", 350, 300);
        graphics.drawString("press any button to begin.", 335, 500);

    }

}
