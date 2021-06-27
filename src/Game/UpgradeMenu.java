package Game;

import java.awt.*;

public class UpgradeMenu {

    int x;
    int y;

    public void setUpgradeLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void Purchase(int recentKey) {
        int selected = Character.getNumericValue(recentKey);
        if (selected == 1) {
            if (Main.gold >= Main.buildings[Main.buildingInfo[x][y].occupied - 1].attackSpeed.cost) {
                Main.buildingInfo[x][y].upgrades[0]++;
                Main.gold -= Main.buildings[Main.buildingInfo[x][y].occupied - 1].attackSpeed.cost;
            }
        }
        if (selected == 2) {
            if (Main.gold >= Main.buildings[Main.buildingInfo[x][y].occupied - 1].attackDamage.cost) {
                Main.buildingInfo[x][y].upgrades[1]++;
                Main.gold -= Main.buildings[Main.buildingInfo[x][y].occupied - 1].attackDamage.cost;
            }
        }
    }

    public void Display(Graphics2D graphics) {
        graphics.setColor(new Color(97, 97, 97));
        //accounts also for the space required for upgrades text
        graphics.fillRect(410 - 150,300, 300,  150 + 20);
        if (Main.buildingInfo[x][y].occupied == 0) {
            graphics.setColor(Color.BLACK);
            graphics.drawString("you must build a building here first!", 320, 380);
        }
        else {
            graphics.setColor(Color.BLACK);
            graphics.drawString(Main.buildings[Main.buildingInfo[x][y].occupied - 1].name + " upgrades", 360, 320);
        }

    }
}
