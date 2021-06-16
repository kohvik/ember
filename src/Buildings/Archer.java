package Buildings;

import Game.Main;

import java.awt.*;

public class Archer extends Building {

    public Archer(int cost, int id) {
        super(cost, id);
    }

    public void Draw(Graphics2D graphics, int x, int y) {

        graphics.setColor(new Color(19, 60, 121));
        graphics.fillRect(Main.coordinates[x][y].x-20, Main.coordinates[x][y].y-20, 40, 40);

    }

}
