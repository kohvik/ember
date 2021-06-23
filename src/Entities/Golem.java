package Entities;

import Game.Main;

import java.awt.*;

public class Golem extends Entity {

    public Golem(int health, int speed, int position, boolean survived) {
        super(health, speed,20, position, survived);

    }

    public void Draw(Graphics2D graphics){
        if (health > 0) {
            if (position < 830) {
                graphics.setColor(new Color(128, 128, 128));
                graphics.fillRect(position,390,25,25);
                graphics.setColor(new Color(37, 69, 34));
                graphics.fillRect(position+6,396,12,12);
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
