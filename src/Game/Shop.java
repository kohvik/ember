package Game;

import Buildings.*;
import java.awt.*;

public class Shop {

    public static void Purchase(Point mousePosition) {

        if (Main.gold >= Main.buildings[0].cost) {

            Main.occupied[mousePosition.x][mousePosition.y] = Main.buildings[0].id;
            Main.gold -= Main.buildings[0].cost;

        }
    }

}
