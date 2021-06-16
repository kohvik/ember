package Buildings;

import java.awt.*;

public abstract class Building {

    public int cost;
    public int id;

    public Building(int cost, int id) {

        this.cost = cost;
        this.id = id;

    }

    public abstract void Draw(Graphics2D graphics, int x, int y);

}
