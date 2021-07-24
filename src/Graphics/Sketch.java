package Graphics;

import Game.Main;

import javax.swing.*;
import java.awt.*;

public class Sketch extends JPanel {

    public void refresh() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {


        //converting g to a graphics2d type
        Graphics2D graphics = (Graphics2D) g;

        if (Main.menuScene == 1) {
            Main.menu.drawMainMenu(graphics);
        }
        else if (Main.menuScene == 2) {
            Main.menu.drawLoadingMessage(graphics);
        }
        else {
            //refreshing the screen with full white
            graphics.setColor(new Color(45, 45, 45));
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
            graphics.setColor(new Color(0, 0, 0));
            graphics.fillRect(Main.buildingInfo[0][3].coordinates.x - 50, Main.buildingInfo[0][3].coordinates.y - 45, 820, 190);

            //sketching entities
            try {
                for (int i = 0; i < Main.warriors.length; i++) {
                    Main.warriors[i].update(graphics);
                }
                for (int i = 0; i < Main.vikings.length; i++) {
                    Main.vikings[i].update(graphics);
                }
                for (int i = 0; i < Main.golems.length; i++) {
                    Main.golems[i].update(graphics);
                }
            } catch (Exception e) {
                System.out.println("taylor did his job.");
            }


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

            //updating player and respective elements (projectiles, etc)
            Main.player.update(graphics);

            //draws nice spot for info
            graphics.setColor(new Color(45, 45, 45));
            graphics.fillRect(Main.buildingInfo[7][7].coordinates.x - 50, Main.buildingInfo[7][7].coordinates.y - 50, 100, 100);
            //sketching info
            graphics.setColor(Color.WHITE);

            graphics.drawString("level: " + (Main.currentLevel + 1), Main.buildingInfo[7][3].coordinates.x - 20, Main.buildingInfo[7][4].coordinates.y - 60);
            if (Main.lives > 0) {
                graphics.drawString("lives: " + (Main.lives), Main.buildingInfo[7][4].coordinates.x - 20, Main.buildingInfo[7][4].coordinates.y - 40);
            } else {
                graphics.drawString("lives: 0", Main.buildingInfo[7][4].coordinates.x - 20, Main.buildingInfo[7][4].coordinates.y - 40);
                deathMessage(graphics);
            }
            graphics.drawString("gold: " + (Main.gold), Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y - 35);
            graphics.drawString("t - shop", Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y - 15);
            graphics.drawString("o - buildings", Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y + 5);
            graphics.drawString("v - upgrades", Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y + 25);
            graphics.drawString("m - open menu", Main.buildingInfo[7][7].coordinates.x - 40, Main.buildingInfo[7][7].coordinates.y + 45);

            //sketching the shop if it's open
            if (Main.buildingShop.isOpen) {
                Main.buildingShop.display(graphics);
            }
            //sketching the playershop if it's open
            if (Main.playerShop.isOpen) {
                Main.playerShop.display(graphics);
            }
            //sketching upgrades if upgrading
            if (Main.upgrading) {
                Main.upgradeMenu.Display(graphics);
            }
            //sketching in game menu if it's open
            if (Main.menuScene == 3) {
                Main.menu.drawInGameMenu(graphics);
            }
            //sketching win menu if next level cannot be loaded
            if (Main.menuScene == 4) {
                Main.menu.drawWonMessage(graphics);
            }
        }
    }

    public void deathMessage(Graphics2D graphics) {
        graphics.setColor(new Color(0, 0, 0, 150));
        graphics.fillRect(0, 0, 820, 840);
        graphics.setColor(new Color(111, 48, 48));
        graphics.fillRect(205, 350, 410, 100);
        graphics.setColor(Color.WHITE);
        graphics.drawString("you died!", 370, 400);

    }



}
