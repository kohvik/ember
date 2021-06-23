package Game;

import Buildings.*;
import java.awt.*;

public class Shop {

    public int selected;
    public boolean isOpen;
    public Dimension shopSize;

    public static Point[][] shopCoordinates = new Point[5][2];

    public void Establish() {
        for (int x = 0; x < shopCoordinates.length; x++) {
            for (int y = 0; y < shopCoordinates[x].length; y++) {
                //terrible equations that i worked out in notebook sorry
                shopCoordinates[x][y] = new Point(x * (shopSize.width/shopCoordinates.length) + (820 - shopSize.width)/2 + (shopSize.width/shopCoordinates.length)/2, y * (shopSize.height/shopCoordinates[0].length) + (840 - shopSize.height)/2 + (shopSize.height/shopCoordinates[0].length)/2);
            }
        }
    }

    Shop(int selected, boolean isOpen, Dimension shopSize) {

        this.selected = selected;
        this.isOpen = isOpen;
        this.shopSize = shopSize;

    }

    public void Select(char recentKey) {
        if (Main.shop.isOpen) {
            selected = Character.getNumericValue(recentKey);
        }
    }

    public void Purchase(Point mousePosition) {

        if (Main.gold >= Main.buildings[(selected - 1)].cost) {

            Main.buildingInfo[mousePosition.x][mousePosition.y].occupied = Main.buildings[selected - 1].id;
            Main.gold -= Main.buildings[(selected - 1)].cost;

        }
    }

    public void Display(Graphics2D graphics) {
        graphics.setColor(new Color(97, 97, 97));
        //here there is -20 on the y and + 20 later in the equation to account for space to add "shop" text
        graphics.fillRect(410 - (shopSize.width/2),400 - (shopSize.height/2), shopSize.width, shopSize.height + 20);
        graphics.setColor(Color.BLACK);
        graphics.drawString("shop", 397, 425 - (shopSize.height/2));

        //still unsure how to iterate drawing
//        for (int x = 0; x < shopCoordinates[0].length && x < Main.buildings.length; x++) {
//                Main.buildings[x].Draw(graphics, x, 0, true);
//        }

        //drawing the shop buildings
        Main.buildings[0].Draw(graphics, 0, 0, true);
        Main.buildings[1].Draw(graphics, 1, 0, true);

        //drawing text of names
        graphics.drawString(Main.buildings[0].name + " [" + Main.buildings[0].id + "]", shopCoordinates[0][0].x - 20, shopCoordinates[0][0].y - 30);
        graphics.drawString(Main.buildings[1].name + " [" + Main.buildings[1].id + "]", shopCoordinates[1][0].x - 20, shopCoordinates[1][0].y - 30);

    }

}
