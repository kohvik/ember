package Game;

import java.awt.*;

public class UpgradeMenu {

    int x;
    int y;

    public void setUpgradeLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void purchase(int recentKey) {
        int selected = Character.getNumericValue(recentKey);
        if (selected == 1) {
            if (Main.gold >= Main.buildingInfo[x][y].upgrades[0].cost) {
                Main.buildingInfo[x][y].upgradeLevel[0]++;
                Main.gold -= Main.buildingInfo[x][y].upgrades[0].cost;
                Main.buildingInfo[x][y].upgrades[0].cost *= 2;
                Main.upgrading = false;
            }
        }
        if (selected == 2) {
            if (Main.gold >= Main.buildingInfo[x][y].upgrades[1].cost) {
                Main.buildingInfo[x][y].upgradeLevel[1]++;
                Main.gold -= Main.buildingInfo[x][y].upgrades[1].cost;
                Main.buildingInfo[x][y].upgrades[1].cost *= 2;
                Main.upgrading = false;
            }
        }
    }

    public void Display(Graphics2D graphics) {
        graphics.setColor(new Color(136, 201, 110));
        //accounts also for the space required for upgrades text
        graphics.fillRect(410 - 150,300, 300,  150 + 20);
        if (Main.buildingInfo[x][y].occupied == 0) {
            graphics.setColor(Color.BLACK);
            graphics.drawString("you must build a building here first!", 320, 380);
        }
        else {
            graphics.setColor(Color.BLACK);
            graphics.drawString(Main.buildings[Main.buildingInfo[x][y].occupied - 1].name + " upgrades", 360, 320);

            graphics.drawString("upgrade attack speed [1]", 270, 360);
            graphics.drawString("(" + Main.buildingInfo[x][y].upgrades[0].cost + " gold)", 270, 380);
            graphics.drawString("current level: " + Main.buildingInfo[x][y].upgradeLevel[0], 270, 400);

            graphics.drawString("upgrade attack damage [2]", 410, 360);
            graphics.drawString("(" + Main.buildingInfo[x][y].upgrades[1].cost + " gold)", 410, 380);
            graphics.drawString("current level: " + Main.buildingInfo[x][y].upgradeLevel[1], 410, 400);
        }

    }
}
