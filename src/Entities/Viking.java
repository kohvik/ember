package Entities;

import Game.Main;

import java.awt.*;

public class Viking extends Entity {

    public Viking(int health, int speed, int position, boolean survived) {
        super(health, speed, 80, position, survived);

    }

    public void Draw(Graphics2D graphics) {
        if (health > 0) {
            if (position < 830) {
                graphics.setColor(new Color(52, 3, 3));
                graphics.fillOval(position,390,25,25);
                graphics.setColor(new Color(73, 20, 80));
                graphics.fillOval(position+6,396,12,12);
                position += speed;
            }
            else if (survived) {
                survived = false;
                Main.lives--;
            }

        }
        else if (survived == true){

            Main.gold += value;
            survived = false;
        }

    }

}
