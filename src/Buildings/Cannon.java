package Buildings;

import Entities.*;
import Game.Loader;
import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cannon extends Building {

    BufferedImage sprite = Loader.loadAsset("/buildings/cannon.png");

    public Cannon(int cost, int id, String name) {
        super(cost, id, name);
    }

    public void Update(Graphics2D graphics, int x, int y, boolean shopDraw) {
        Draw(graphics, sprite, x, y, shopDraw);
    }

    int furthestPosition = 0;
    Entity furthestEntity = null;


    public void doAttack(Graphics2D graphics, int x, int y) {

        if ((Main.currentTime - Main.buildingInfo[x][y].preAttackTime) > (3000 / ((Main.buildingInfo[x][y].upgradeLevel[0]+1) * 1.3)) || Main.buildingInfo[x][y].repeat > 0) {

            if (Main.buildingInfo[x][y].repeat > 0) {
                Main.buildingInfo[x][y].repeat--;
            }

            else {
                Main.buildingInfo[x][y].preAttackTime = Main.currentTime;
                Main.buildingInfo[x][y].repeat = 8;
                furthestEntity = null;
                furthestPosition = 0;
            }

            for (int a = 0; a < Main.entities.length; a++) {
                for (int b = 0; b < Main.entities[a].length; b++) {
                    if (Main.entities[a][b].position >= furthestPosition && Main.entities[a][b].survived) {
                        furthestPosition = Main.entities[a][b].position;

                        if (Main.buildingInfo[x][y].repeat == 8) {
                            furthestEntity = Main.entities[a][b];
                        }
                    }
                }
            }

            if (furthestEntity != null) {
                //drawing the shot
                graphics.setColor(new Color(0, 125, 22));
                graphics.setStroke(new BasicStroke(Main.buildingInfo[x][y].repeat * 3));
                graphics.drawLine(Main.buildingInfo[x][y].coordinates.x, Main.buildingInfo[x][y].coordinates.y, furthestEntity.position + 20,390 + 20);
                graphics.setColor(new Color(134, 0, 3));
                graphics.fillOval(furthestEntity.position - (20 * Main.buildingInfo[x][y].repeat)/2 + 20, 390 - (20 * Main.buildingInfo[x][y].repeat)/2 + 20, 20 * Main.buildingInfo[x][y].repeat, 20 * Main.buildingInfo[x][y].repeat);
                graphics.setColor(new Color(118, 103, 0));
                graphics.fillOval(furthestEntity.position - (10 * Main.buildingInfo[x][y].repeat)/2 + 20, 390 - (10 * Main.buildingInfo[x][y].repeat)/2 + 20, 10 * Main.buildingInfo[x][y].repeat, 10 * Main.buildingInfo[x][y].repeat);

                //accounting for damage done
                for (int a = 0; a < Main.entities.length; a++) {
                    for (int b = 0; b < Main.entities[a].length; b++) {

                        if (Main.buildingInfo[x][y].repeat == 8) {

                            if (Main.entities[a][b].position > furthestEntity.position - 100 || Main.entities[a][b].position > furthestEntity.position + 100)
                                Main.entities[a][b].health -= 100 * ((Main.buildingInfo[x][y].upgradeLevel[1]+1) * 1.3);
                        }
                    }
                }

            }

        }

    }

    public void establishUpgrades(int x, int y) {
        Main.buildingInfo[x][y].upgrades[0] = new BuildingUpgrade(80, 1);
        Main.buildingInfo[x][y].upgrades[1] = new BuildingUpgrade(100, 2);
    }

}