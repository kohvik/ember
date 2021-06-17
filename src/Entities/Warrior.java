package Entities;

import Game.Main;

import java.awt.*;

public class Warrior extends Entity {

    public Warrior(int health, int speed, int position, boolean survived) {
        super(health, speed,50, position, survived);

    }

    public void Draw(Graphics2D graphics){
        if (health > 0) {
            if (position < 830) {
                graphics.setColor(new Color(44, 95, 33));
                graphics.fillOval(position,390,25,25);
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
