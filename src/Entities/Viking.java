package Entities;

import Game.Main;

import java.awt.*;

public class Viking extends Entity {

    public Viking(int health, int speed, int position, boolean survived) {
        super(health, speed, 10, position, survived);

    }

    public void Draw(Graphics2D graphics) {
        if (health > 0) {
            if (position < 830) {
                graphics.setColor(new Color(52, 3, 3));
                graphics.fillOval(position,390,25,25);
                graphics.setColor(new Color(73, 20, 80));
                graphics.fillOval(position+6,396,12,12);

                graphics.setColor(Color.green);
                graphics.fillRect(position, 390 + 30, (health*25)/60, 5);
                position += speed;
            }
            else if (survived) {
                survived = false;
                Main.lives--;
                Main.livingEnemies--;
            }

        }
        else if (survived){

            Main.gold += value;
            survived = false;
            Main.livingEnemies--;

        }

    }

}
