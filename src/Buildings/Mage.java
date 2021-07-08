package Buildings;

import Entities.*;
import Game.Loader;
import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mage extends Building {

    BufferedImage sprite = Loader.loadAsset("/buildings/mage.png");


    public Mage(int cost, int id, String name) {
        super(cost, id, name);
    }

    public void Update(Graphics2D graphics, int x, int y, boolean shopDraw) {
        Draw(graphics, sprite, x, y, shopDraw);
    }


    int furthestPosition = 0;
    Entity furthestEntity = null;
    Entity secondEntity = null;

    public void doAttack(Graphics2D graphics, int x, int y) {

        //setting attack speed
        if ((Main.currentTime - Main.buildingInfo[x][y].preAttackTime) > 300 || Main.buildingInfo[x][y].repeat > 0) {

            if (Main.buildingInfo[x][y].repeat > 0) {
                Main.buildingInfo[x][y].repeat--;
            }

            else {
                Main.buildingInfo[x][y].preAttackTime = Main.currentTime;
                Main.buildingInfo[x][y].repeat = 3;
                furthestEntity = null;
                secondEntity = null;
                furthestPosition = 0;
            }

            for (int a = 0; a < Main.entities.length; a++) {
                for (int b = 0; b < Main.entities[a].length; b++) {
                    if ((secondEntity == null || Main.entities[a][b].position >= secondEntity.position) && Main.entities[a][b].survived && Main.entities[a][b].position > 0) {
                        if (furthestEntity != null) {
                            secondEntity = Main.entities[a][b];
                        }
                    }
                    if (Main.entities[a][b].position >= furthestPosition && Main.entities[a][b].survived) {
                        furthestPosition = Main.entities[a][b].position;

                        if (Main.buildingInfo[x][y].repeat == 3) {
                            furthestEntity = Main.entities[a][b];
                        }
                    }
                }
            }

            if (furthestEntity != null) {
                //drawing the shot
                graphics.setColor(new Color(58, 38, 229));
                graphics.setStroke(new BasicStroke(Main.buildingInfo[x][y].repeat * 3));

                graphics.drawLine(Main.buildingInfo[x][y].coordinates.x, Main.buildingInfo[x][y].coordinates.y, furthestEntity.position,390);
                if (secondEntity != null) {
                    graphics.drawLine(Main.buildingInfo[x][y].coordinates.x, Main.buildingInfo[x][y].coordinates.y, secondEntity.position,390);
                }

                //accounting for damage done
                if (Main.buildingInfo[x][y].repeat == 3) {
                    furthestEntity.health -= (7 * ((Main.buildingInfo[x][y].upgradeLevel[1]+1) * 1.3));
                    if (secondEntity != null) {
                        secondEntity.health -= (7 * ((Main.buildingInfo[x][y].upgradeLevel[1]+1) * 1.3));
                    }

                }

            }

        }

    }

    public void establishUpgrades(int x, int y) {
        Main.buildingInfo[x][y].upgrades[0] = new Upgrade(70, 1);
        Main.buildingInfo[x][y].upgrades[1] = new Upgrade(50, 2);
    }

}