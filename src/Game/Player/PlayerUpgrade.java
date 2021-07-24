package Game.Player;

import Game.Main;

import java.awt.*;

public abstract class PlayerUpgrade {
    public int cost;
    public int id;
    public int level;
    public String name;
    public String type;
    public int attackSpeed;

    public PlayerUpgrade(int cost, int id, int level, String name, String type, boolean equipped, int attackSpeed) {

        this.cost = cost;
        this.id = id;
        this.level = level;
        this.name = name;
        this.type = type;
        this.attackSpeed = attackSpeed;

    }

    public abstract void doAttack();
    public void display(Graphics2D graphics, int x, int y) {

        graphics.setColor(Color.white);

        for (int i = 0; i < 2; i++) {

            if (i == 0 && type.equals("attack") || i == 1 && type.equals("ability")) {
                if (Main.player.currentUpgrade[i] == id) {

                    graphics.setColor(new Color(33, 56, 56));
                    graphics.fillRect(PlayerShop.shopCoordinates[x][y].x - 45, PlayerShop.shopCoordinates[x][y].y - 45, 90, 90);
                    graphics.setColor(Color.white);
                    graphics.drawString("equipped!", PlayerShop.shopCoordinates[x][y].x - 30, PlayerShop.shopCoordinates[x][y].y + 30);

                }

                else if (level > 0) {
                    graphics.drawString("owned", PlayerShop.shopCoordinates[x][y].x - 30, PlayerShop.shopCoordinates[x][y].y + 30);
                }
                else {
                    graphics.drawString("(" + cost + " gold)", PlayerShop.shopCoordinates[x][y].x - 30, PlayerShop.shopCoordinates[x][y].y + 30);
                }
            }
        }


        graphics.drawString(name + " [" + id + "]", PlayerShop.shopCoordinates[x][y].x - 30, PlayerShop.shopCoordinates[x][y].y - 30);

        graphics.drawImage(Main.player.projectileSprites.get(id+1),
                PlayerShop.shopCoordinates[x][y].x - 20, PlayerShop.shopCoordinates[x][y].y - 20,
                40, 40, null);
    }
}
