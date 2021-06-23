package Buildings;

import Entities.*;
import Game.Main;
import Game.Shop;

import java.awt.*;

public class Archer extends Building {

    int furthestPosition = 0;
    Entity furthestEntity = null;

    public Archer(int cost, int id, String name) {
        super(cost, id, name);
    }

    public void Draw(Graphics2D graphics, int x, int y, boolean shopDraw) {

        if (shopDraw) {
            graphics.setColor(new Color(19, 60, 121));
            graphics.fillRect(Shop.shopCoordinates[x][y].x - 15, Shop.shopCoordinates[x][y].y - 15, 30, 30);
        }
        else {
            graphics.setColor(new Color(19, 60, 121));
            graphics.fillRect(Main.buildingInfo[x][y].coordinates.x - 15, Main.buildingInfo[x][y].coordinates.y - 15, 30, 30);
        }

    }

    public void doAttack(Graphics2D graphics, int x, int y) {

        //accounting for attack speed of 1 attack per second

        if ((Main.currentTime - Main.buildingInfo[x][y].preAttackTime) > 1000 || Main.buildingInfo[x][y].repeat > 0) {

            if (Main.buildingInfo[x][y].repeat > 0) {
                Main.buildingInfo[x][y].repeat--;
            }

            else {
                Main.buildingInfo[x][y].preAttackTime = Main.currentTime;
                Main.buildingInfo[x][y].repeat = 6;
                furthestEntity = null;
                furthestPosition = 0;
            }


            for (int a = 0; a < Main.entities.length; a++) {
                for (int b = 0; b < Main.entities[a].length; b++) {
                    if (Main.entities[a][b].position >= furthestPosition && Main.entities[a][b].survived) {
                        furthestPosition = Main.entities[a][b].position;

                        if (Main.buildingInfo[x][y].repeat == 6)
                        furthestEntity = Main.entities[a][b];
                    }
                }
            }

            if (furthestEntity != null) {
                //drawing the shot
                graphics.setColor(new Color(194, 158, 73));
                graphics.setStroke(new BasicStroke(Main.buildingInfo[x][y].repeat * 2));
                graphics.drawLine(Main.buildingInfo[x][y].coordinates.x, Main.buildingInfo[x][y].coordinates.y, furthestEntity.position ,390);

                //accounting for damage done, only once per shot (hence only occurs for a single repeat value)
                if (Main.buildingInfo[x][y].repeat == 6) {
                    furthestEntity.health -= 20;
                }

            }

        }

    }

}




