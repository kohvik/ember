package Game;

import java.awt.*;

public class Shop {

    public int selected;
    public boolean isOpen;
    public Dimension shopSize;

    private boolean selling;

    public static Point[][] shopCoordinates = new Point[5][3];

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
            //parsing to make sure you cannot select a tower that does not exist
            if (recentKey == 'x') {
                selling = true;
                selected = 0;
            }
            int parse = Character.getNumericValue(recentKey);
            if (parse <= Main.buildings.length) {
                selected = Character.getNumericValue(recentKey);
                selling = false;
            }
        }
    }

    public void Purchase(Point mousePosition) {

        if (selling) {
            if (mousePosition.y < 3 || mousePosition.y > 4) {
                //ensuring you can't build on the info
                if (mousePosition.x == 7 && mousePosition.y == 7) {

                }
                else if (Main.buildingInfo[mousePosition.x][mousePosition.y].occupied != 0) {
                    Main.gold += (Main.buildings[Main.buildingInfo[mousePosition.x][mousePosition.y].occupied - 1].cost)/2;
                    Main.buildingInfo[mousePosition.x][mousePosition.y].setDefault();
                }
            }
        }
        else if (Main.gold >= Main.buildings[(selected - 1)].cost) {
            //ensuring you can't build on the main path
            if (mousePosition.y < 3 || mousePosition.y > 4) {
                //ensuring you can't build on the info
                if (mousePosition.x == 7 && mousePosition.y == 7) {

                }
                //ensuring you cannot build 2 towers on one square
                else if (Main.buildingInfo[mousePosition.x][mousePosition.y].occupied == 0) {
                    Main.buildingInfo[mousePosition.x][mousePosition.y].occupied = Main.buildings[selected - 1].id;
                    Main.gold -= Main.buildings[(selected - 1)].cost;
                    Main.buildings[(selected - 1)].establishUpgrades(mousePosition.x, mousePosition.y);
                }
            }
        }

    }

    public void Display(Graphics2D graphics) {
        graphics.setColor(new Color(96, 96, 96));
        //here there is -20 on the y and + 20 later in the equation to account for space to add "shop" text
        graphics.fillRect(410 - (shopSize.width/2),400 - (shopSize.height/2), shopSize.width, shopSize.height + 20);
        graphics.setColor(Color.BLACK);
        graphics.drawString("shop", 397, 425 - (shopSize.height/2));

        //drawing shop buildings and their related text
        int i = 0;
        for (int a = 0; a < Main.buildings.length; a++) {

            //drawing dark square to indicate what is selected
            if (a == (selected - 1)) {
                graphics.setColor(new Color(61, 61, 61));
                graphics.fillRect(shopCoordinates[i][a/ shopCoordinates.length].x - 30, shopCoordinates[i][a/ shopCoordinates.length].y - 30, 60, 60);
            }

            Main.buildings[a].Update(graphics, i, a/shopCoordinates.length, true);

            //abusing the fact that integers round down to get the y position of the buildings
            graphics.setColor(Color.BLACK);
            graphics.drawString(Main.buildings[a].name + " [" + Main.buildings[a].id + "]", shopCoordinates[i][a/shopCoordinates.length].x - 20, shopCoordinates[i][a/shopCoordinates.length].y - 35);
            graphics.drawString(Main.buildings[a].cost + " gold", shopCoordinates[i][a/shopCoordinates.length].x - 20, shopCoordinates[i][a/shopCoordinates.length].y + 45);

            i++;

            if (i >= shopCoordinates.length) {
                i = 0;
            }
        }
        if (selling) {
            graphics.setColor(new Color(61, 61, 61));
            graphics.fillRect(shopCoordinates[shopCoordinates.length - 1][shopCoordinates[0].length - 1].x - 58, shopCoordinates[shopCoordinates.length - 1][shopCoordinates[0].length - 1].y - 10, 70, 40);
        }
        //drawing sell function
        graphics.setColor(Color.black);
        graphics.drawString("sell [press x]", shopCoordinates[shopCoordinates.length - 1][shopCoordinates[0].length - 1].x - 55, shopCoordinates[shopCoordinates.length - 1][shopCoordinates[0].length - 1].y + 10);

    }



}