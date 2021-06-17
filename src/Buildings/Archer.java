package Buildings;

import Entities.*;
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

    public void doAttack(Graphics2D graphics, int x, int y) {

        //accounting for attack speed of 1 attack per second

        if ((Main.currentTime - Main.preAttackTime[x][y]) > 1000 || Main.repeat[x][y] > 0) {

            if (Main.repeat[x][y] > 0) {
                Main.repeat[x][y]--;
            }

            else {
                Main.preAttackTime[x][y] = Main.currentTime;
                Main.repeat[x][y] = 6;
            }

            int furthestPosition = 0;
            Entity furthestEntity = null;

            //coordinates[x][y] ------>> (position, 390)
            //Main.entities[i][0]

            for (int a = 0; a < Main.entities.length; a++) {
                for (int b = 0; b < Main.entities[a].length; b++) {
                    if (Main.entities[a][b].position >= furthestPosition && Main.entities[a][b].survived) {
                        furthestPosition = Main.entities[a][b].position;
                        furthestEntity = Main.entities[a][b];
                    }
                }
            }

            if (furthestEntity != null) {
                //drawing the shot
                graphics.setColor(new Color(194, 158, 73));
                graphics.setStroke(new BasicStroke(Main.repeat[x][y]*2));
                graphics.drawLine(Main.coordinates[x][y].x, Main.coordinates[x][y].y, furthestPosition ,390);

                //accounting for damage done, only once per shot (hence only occurs for a single repeat value)
                if (Main.repeat[x][y] == 0) {
                    furthestEntity.health -= 50;
                    System.out.println(furthestEntity.health);
                }

            }

        }

    }

}




