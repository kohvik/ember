package Game.Player;

import Game.Main;

import java.awt.*;

public class PlayerShop {

    public boolean isOpen;
    public Dimension shopSize;

    private boolean selling;

    public static Point[][] shopCoordinates = new Point[2][4];

    public PlayerShop(boolean isOpen, Dimension shopSize) {
        this.isOpen = isOpen;
        this.shopSize = shopSize;
        for (int x = 0; x < shopCoordinates.length; x++) {
            for (int y = 0; y < shopCoordinates[x].length; y++) {
                shopCoordinates[x][y] = new Point(x * (shopSize.width/shopCoordinates.length) + (820 - shopSize.width)/2 + (shopSize.width/shopCoordinates.length)/2, y * (shopSize.height/shopCoordinates[0].length) + (840 - shopSize.height)/2 + (shopSize.height/shopCoordinates[0].length)/2);
            }
        }
    }

    public void purchase(char recentKey) {

        int selected = Character.getNumericValue(recentKey);

        for (int i = 0; i < 2; i++) {
            for (int a = 0; a < Main.player.upgrades[i].length && Main.player.upgrades[i][a] != null; a++) {
                if (selected == Main.player.upgrades[i][a].id) {
                    if (Main.player.upgrades[i][a].level == 0) {
                        if (Main.gold >= Main.player.upgrades[i][a].cost) {
                            Main.player.upgrades[i][a].level++;
                            Main.gold -= Main.player.upgrades[i][a].cost;
                            Main.player.currentUpgrade[i] = Main.player.upgrades[i][a].id;

                        }
                    }
                    else if (Main.player.upgrades[i][a].level > 0) {
                        if (i == 0 && Main.player.upgrades[i][a].type.equals("attack")) {
                            if (Main.player.currentUpgrade[i] == Main.player.upgrades[i][a].id) {
                                Main.player.currentUpgrade[i] = 0;
                            }
                            else {
                                Main.player.currentUpgrade[i] = Main.player.upgrades[i][a].id;
                            }
                        }
                        if (i == 1 && Main.player.upgrades[i][a].type.equals("ability")) {
                            if (Main.player.currentUpgrade[i] == Main.player.upgrades[i][a].id) {
                                Main.player.currentUpgrade[i] = 0;
                            }
                            else {
                                Main.player.currentUpgrade[i] = Main.player.upgrades[i][a].id;
                            }
                        }
                    }
                }
            }
        }
    }

    public void display(Graphics2D graphics) {
        graphics.setColor(new Color(61, 106, 79));
        //here there is -20 on the y and + 20 later in the equation to account for space to add "shop" text
        graphics.fillRect(410 - (shopSize.width/2),380 - (shopSize.height/2), shopSize.width, shopSize.height + 60);
        graphics.setColor(Color.BLACK);
        graphics.drawString("shop", 397, 400 - (shopSize.height/2));
        graphics.drawString("attacks", shopCoordinates[0][0].x - 30, shopCoordinates[0][0].y - 60);
        graphics.drawString("abilites", shopCoordinates[1][0].x - 30, shopCoordinates[1][0].y - 60);

        for (int a = 0; a < Main.player.upgrades[0].length; a++) {
            if (Main.player.upgrades[0][a] != null)
            Main.player.upgrades[0][a].display(graphics, 0, a);
        }

        for (int a = 0; a < Main.player.upgrades[1].length; a++) {
            if (Main.player.upgrades[1][a] != null)
            Main.player.upgrades[1][a].display(graphics, 1, a);
        }
    }
}
