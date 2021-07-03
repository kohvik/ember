package Buildings;

import Entities.*;
import Game.Main;
import Game.Shop;

import java.awt.*;

public class Sniper extends Building {

    int furthestPosition = 0;
    Entity furthestEntity = null;

    public Sniper(int cost, int id, String name) {
        super(cost, id, name);
    }

    public void Draw(Graphics2D graphics, int x, int y, boolean shopDraw) {

        if (shopDraw) {
            graphics.setColor(new Color(104, 8, 25));
            graphics.fillRect(Shop.shopCoordinates[x][y].x - 20, Shop.shopCoordinates[x][y].y - 20, 40, 40);
            graphics.setColor(new Color(0, 0, 0));
            graphics.fillRect(Shop.shopCoordinates[x][y].x - 10, Shop.shopCoordinates[x][y].y - 10, 20, 20);
        }
        else {
            graphics.setColor(new Color(104, 8, 25));
            graphics.fillRect(Main.buildingInfo[x][y].coordinates.x - 20, Main.buildingInfo[x][y].coordinates.y - 20, 40, 40);
            graphics.setColor(new Color(0, 0, 0));
            graphics.fillRect(Main.buildingInfo[x][y].coordinates.x - 10, Main.buildingInfo[x][y].coordinates.y - 10, 20, 20);
        }
    }

    public void doAttack(Graphics2D graphics, int x, int y) {

        if ((Main.currentTime - Main.buildingInfo[x][y].preAttackTime) > (3000 / ((Main.buildingInfo[x][y].upgradeLevel[0]+1) * 1.3)) || Main.buildingInfo[x][y].repeat > 0) {

            if (Main.buildingInfo[x][y].repeat > 0) {
                Main.buildingInfo[x][y].repeat--;
            }

            else {
                Main.buildingInfo[x][y].preAttackTime = Main.currentTime;
                Main.buildingInfo[x][y].repeat = 10;
                furthestEntity = null;
                furthestPosition = 0;
            }

            for (int a = 0; a < Main.entities.length; a++) {
                for (int b = 0; b < Main.entities[a].length; b++) {
                    if (Main.entities[a][b].position >= furthestPosition && Main.entities[a][b].survived) {
                        furthestPosition = Main.entities[a][b].position;

                        if (Main.buildingInfo[x][y].repeat == 10) {
                            furthestEntity = Main.entities[a][b];
                        }
                    }
                }
            }

            if (furthestEntity != null) {
                //drawing the shot
                graphics.setColor(new Color(97, 144, 253));
                graphics.setStroke(new BasicStroke(Main.buildingInfo[x][y].repeat * 2));
                graphics.drawLine(Main.buildingInfo[x][y].coordinates.x, Main.buildingInfo[x][y].coordinates.y, furthestEntity.position ,390);

                //accounting for damage done
                if (Main.buildingInfo[x][y].repeat == 10) {
                    furthestEntity.health -= 100 * ((Main.buildingInfo[x][y].upgradeLevel[1]+1) * 1.3);
                }

            }

        }

    }

    public void establishUpgrades(int x, int y) {
        Main.buildingInfo[x][y].upgrades[0] = new Upgrade(80, 1);
        Main.buildingInfo[x][y].upgrades[1] = new Upgrade(100, 2);
    }

}