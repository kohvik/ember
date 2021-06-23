package Buildings;

import java.awt.*;

public abstract class Building {

    public int cost;
    public int id;
    public String name;

    public Building(int cost, int id, String name) {

        this.cost = cost;
        this.id = id;
        this.name = name;

    }

    public abstract void Draw(Graphics2D graphics, int x, int y, boolean shopDraw);
    public abstract void doAttack(Graphics2D graphics, int x, int y);

}
