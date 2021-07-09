package Buildings;

import Entities.*;
import Game.Loader;
import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sniper extends Building {

    BufferedImage sprite = Loader.loadAsset("/buildings/sniper.png");

    public Sniper(int cost, int id, String name) {
        super(cost, id, name);
    }

    public void Update(Graphics2D graphics, int x, int y, boolean shopDraw) {
        Draw(graphics, sprite, x, y, shopDraw);
    }

    int highestHp = 0;
    Entity healthiestEntity = null;

    public void doAttack(Graphics2D graphics, int x, int y) {

        if ((Main.currentTime - Main.buildingInfo[x][y].preAttackTime) > (3000 / ((Main.buildingInfo[x][y].upgradeLevel[0]+1) * 1.3)) || Main.buildingInfo[x][y].repeat > 0) {

            if (Main.buildingInfo[x][y].repeat > 0) {
                Main.buildingInfo[x][y].repeat--;
            }

            else {
                Main.buildingInfo[x][y].preAttackTime = Main.currentTime;
                Main.buildingInfo[x][y].repeat = 10;
                healthiestEntity = null;
                highestHp = 0;
            }

            for (int a = 0; a < Main.entities.length; a++) {
                for (int b = 0; b < Main.entities[a].length; b++) {
                    if (Main.entities[a][b].health >= highestHp && Main.entities[a][b].survived && Main.entities[a][b].position > 0) {
                        highestHp = Main.entities[a][b].health;

                        if (Main.buildingInfo[x][y].repeat == 10) {
                            healthiestEntity = Main.entities[a][b];
                        }
                    }
                }
            }

            if (healthiestEntity != null) {
                //drawing the shot
                graphics.setColor(new Color(151, 184, 255));
                graphics.setStroke(new BasicStroke(Main.buildingInfo[x][y].repeat * 2));
                graphics.drawLine(Main.buildingInfo[x][y].coordinates.x, Main.buildingInfo[x][y].coordinates.y, healthiestEntity.position + 20,390 + 20);

                //accounting for damage done
                if (Main.buildingInfo[x][y].repeat == 10) {
                    healthiestEntity.health -= 100 * ((Main.buildingInfo[x][y].upgradeLevel[1]+1) * 1.3);
                }

            }

        }

    }

    public void establishUpgrades(int x, int y) {
        Main.buildingInfo[x][y].upgrades[0] = new Upgrade(80, 1);
        Main.buildingInfo[x][y].upgrades[1] = new Upgrade(100, 2);
    }

}