package Buildings;

import Game.Main;
import Game.BuildingShop;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Building {

    public int cost;
    public int id;
    public String name;

    public Building(int cost, int id, String name) {

        this.cost = cost;
        this.id = id;
        this.name = name;

    }

    public abstract void Update(Graphics2D graphics, int x, int y, boolean shopDraw);
    public abstract void doAttack(Graphics2D graphics, int x, int y);
    public abstract void establishUpgrades(int x, int y);

    protected void Draw(Graphics2D graphics, BufferedImage sprite, int x, int y, boolean shopDraw) {
        if (shopDraw) {
            graphics.drawImage(sprite, BuildingShop.shopCoordinates[x][y].x - 30, BuildingShop.shopCoordinates[x][y].y - 30, 60, 60, null);
        } else {
            graphics.drawImage(sprite, Main.buildingInfo[x][y].coordinates.x - 50, Main.buildingInfo[x][y].coordinates.y - 50, 100, 100, null);
        }
    }

}
